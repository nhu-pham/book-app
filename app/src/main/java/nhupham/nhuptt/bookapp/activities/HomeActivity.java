package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.adapters.BookAdapter;
import nhupham.nhuptt.bookapp.adapters.CategoryAdapter;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.models.Book;
import nhupham.nhuptt.bookapp.models.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView newBooksRecyclerView, popularBooksRecyclerView;
    private BookAdapter newBookAdapter, popularBookAdapter;
    List<Book> searchResults = new ArrayList<>();
    private ApiService apiService;
    private List<Book> newBookList = new ArrayList<>();
    private List<Book> popularBookList = new ArrayList<>();
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        setupBottomNavigation();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ImageSlider
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.book1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.book2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.book3, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        // Khởi tạo Retrofit
        apiService = ApiClient.getClient().create(ApiService.class);

        // Setup RecyclerView
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        newBooksRecyclerView = findViewById(R.id.newBooksRecyclerView);
        newBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        popularBooksRecyclerView = findViewById(R.id.popularBooksRecyclerView);
        popularBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // userId
        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        userId = sp.getInt("user_id", -1);

        // Gọi API load dữ liêu
        loadCategories();
        loadNewBooks();
        loadPopularBooks();

        TextView viewAllCategory = findViewById(R.id.viewAllCategory);
        TextView viewAllNew = findViewById(R.id.viewAllNew);
        TextView viewAllPopular = findViewById(R.id.viewAllPopular);


        viewAllCategory.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            intent.putExtra("viewType", "category");
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        viewAllNew.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            intent.putExtra("viewType", "new");
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        viewAllPopular.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            intent.putExtra("viewType", "popular");
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        // Search
        EditText searchEt = findViewById(R.id.searchEt);
        ImageView searchIcon = findViewById(R.id.searchIcon);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchEt.getText().toString().trim();
                if (!keyword.isEmpty()) {
                    Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                    intent.putExtra("keyword", keyword);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                } else {
                    Toast.makeText(HomeActivity.this, "Nhập nội dung tìm kiếm", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected int getCurrentNavItemId() {
        return R.id.nav_home;
    }

    private void loadCategories() {
        apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categoryList = response.body();
                    categoryAdapter = new CategoryAdapter(HomeActivity.this, categoryList, category -> {
                        // Chuyển categoryId sang SearchActivity
                        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                        intent.putExtra("categoryId", category.getCategoryId()); // Truyền categoryId
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                    });
                    categoryRecyclerView.setAdapter(categoryAdapter);

                    categoryRecyclerView.setAdapter(categoryAdapter);
                } else {
                    Toast.makeText(HomeActivity.this, "Không tải được danh mục", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNewBooks() {
        apiService.getNewBooks(userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newBookAdapter = new BookAdapter(HomeActivity.this, response.body(), false);
                    newBooksRecyclerView.setAdapter(newBookAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Lỗi tải sách mới", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPopularBooks() {
        apiService.getPopularBooks(userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    popularBookAdapter = new BookAdapter(HomeActivity.this, response.body(), false);
                    popularBooksRecyclerView.setAdapter(popularBookAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Lỗi tải sách phổ biến", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
