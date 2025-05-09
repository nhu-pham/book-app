package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.os.Bundle;
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

public class HomeActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView newBooksRecyclerView, popularBooksRecyclerView;
    private BookAdapter newBookAdapter, popularBookAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

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

        // Gọi API load dữ liêu
        loadCategories();
        loadNewBooks();
        loadPopularBooks();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Đã ở Home, không cần chuyển
                return true;
            } else if (itemId == R.id.nav_search) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                return true;
            } else if (itemId == R.id.nav_library) {
                startActivity(new Intent(HomeActivity.this, LibraryActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                return true;
            }

            return false;
        });

//        TextView viewAllCategory = findViewById(R.id.viewAllCategory);
//        viewAllCategory.setOnClickListener(v -> {
//            startActivity(new Intent(this, CategoryListActivity.class));
//        });


    }

    private void loadCategories() {
        apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categoryList = response.body();
                    categoryAdapter = new CategoryAdapter(HomeActivity.this, categoryList);
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
        apiService.getNewBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newBookAdapter = new BookAdapter(HomeActivity.this, response.body());
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
        apiService.getPopularBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    popularBookAdapter = new BookAdapter(HomeActivity.this, response.body());
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
