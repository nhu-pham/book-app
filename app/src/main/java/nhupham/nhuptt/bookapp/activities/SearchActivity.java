package nhupham.nhuptt.bookapp.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class SearchActivity extends BaseActivity {
    private ApiService apiService;
    private RecyclerView optionRecyclerView;
    private CategoryAdapter categoryAdapter;
    private BookAdapter bookAdapter;
    private List<Book> bookList;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupBottomNavigation();

        RecyclerView booksRv = findViewById(R.id.booksRv);
        EditText searchEt = findViewById(R.id.searchEt);
        ImageView backIv = findViewById(R.id.backIv);

        // Khởi tạo Retrofit
        apiService = ApiClient.getClient().create(ApiService.class);

        // Setup RecyclerView
        optionRecyclerView = findViewById(R.id.booksRv);
        optionRecyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));

        // Lấy categoryId, userId từ Intent
        int categoryId = getIntent().getIntExtra("categoryId", -1); // Lấy categoryId
        userId = getIntent().getIntExtra("userId", -1); // Lấy userId

        // Nếu categoryId hợp lệ, tải sách theo thể loại
        if (categoryId != -1) {
            loadBooksByCategory(categoryId);
        }

        backIv.setOnClickListener(v -> finish());

        String viewType = getIntent().getStringExtra("viewType");

        if (viewType != null) {
            switch (viewType) {
                case "category":
                    loadCategories();
                    break;
                case "new":
                    loadNewBooks();
                    break;
                case "popular":
                    loadPopularBooks();
                    break;
            }
        }

        // Search
        String keyword = getIntent().getStringExtra("keyword");

        searchEt.setText(keyword);

        if (keyword != null && !keyword.isEmpty()) {
            searchEt.setText(keyword);
            searchBooks(keyword);
        }

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String newKeyword = s.toString().trim();
                if (!newKeyword.isEmpty()) {
                    searchBooks(newKeyword);
                } else {
                    if (bookAdapter != null) {
                        bookAdapter.updateData(bookList);
                    }
                }
            }
        });
    }

    @Override
    protected int getCurrentNavItemId() {
        return R.id.nav_home;
    }

    private void loadCategories() {
        apiService.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> categoryList = response.body();
                    categoryAdapter = new CategoryAdapter(SearchActivity.this, categoryList, category -> {
//                        titleTv.setText("Category: " + category.getCategoryName()); // Set lại tiêu đề
                        loadBooksByCategory(category.getCategoryId());              // Gọi hàm lấy sách
                    });
                    optionRecyclerView.setAdapter(categoryAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Không tải được danh mục", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNewBooks() {
        apiService.getAllNewBooks(userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    bookList = response.body();
                    bookAdapter = new BookAdapter(SearchActivity.this, bookList, false);
                    optionRecyclerView.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Lỗi tải sách mới", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPopularBooks() {
        apiService.getAllPopularBooks(userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    bookList = response.body();
                    bookAdapter = new BookAdapter(SearchActivity.this, bookList, false);
                    optionRecyclerView.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Lỗi tải sách phổ biến", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBooksByCategory(Integer categoryId) {
        apiService.getBooksByCategory(categoryId, userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookList = response.body();
                    bookAdapter = new BookAdapter(SearchActivity.this, bookList, false);
                    optionRecyclerView.setAdapter(bookAdapter);
                } else {
                    Toast.makeText(SearchActivity.this, "Không có sách cho thể loại này", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchBooks(String keyword) {
        apiService.searchBooks(keyword, userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookList = response.body();
                    if (bookAdapter == null) {
                        bookAdapter = new BookAdapter(SearchActivity.this, bookList, false);
                        optionRecyclerView.setAdapter(bookAdapter);
                    } else {
                        bookAdapter.updateData(bookList);
                    }
                } else {
                    Toast.makeText(SearchActivity.this, "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Lỗi tìm kiếm", Toast.LENGTH_SHORT).show();
            }
        });
    }


}