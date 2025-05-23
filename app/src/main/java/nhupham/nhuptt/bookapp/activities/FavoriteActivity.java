package nhupham.nhuptt.bookapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.adapters.BookAdapter;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends BaseActivity {

    private RecyclerView favoriteBooksRv;
    private BookAdapter favoriteAdapter;
    private List<Book> favoriteBookList = new ArrayList<>();
    private ApiService apiService;
    private int userId;

    private EditText searchEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        setupBottomNavigation();

        // Lấy user_id từ SharedPreferences
        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        userId = sp.getInt("user_id", -1);

        favoriteBooksRv = findViewById(R.id.favoriteBooksRv);
        favoriteBooksRv.setLayoutManager(new GridLayoutManager(this, 2));
        favoriteAdapter = new BookAdapter(this, favoriteBookList, true);
        favoriteBooksRv.setAdapter(favoriteAdapter);
        searchEt = findViewById(R.id.searchEt);

        // Khởi tạo Retrofit
        apiService = ApiClient.getClient().create(ApiService.class);

        // Tải danh sách sách yêu thích
        loadFavoriteBooks();

        // Search
        String keyword = getIntent().getStringExtra("keyword");

        searchEt = findViewById(R.id.searchEt);
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
                    if (favoriteAdapter != null) {
                        favoriteAdapter.updateData(favoriteBookList);
                    }
                }
            }
        });
    }

    private void loadFavoriteBooks() {
        if (userId == -1) {
            Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.getFavoriteBooks(userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    favoriteBookList.clear();
                    favoriteBookList.addAll(response.body());
                    favoriteAdapter.updateData(favoriteBookList);
                } else {
                    Toast.makeText(FavoriteActivity.this, "Không lấy được danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("FavoriteActivity", "Lỗi API: " + t.getMessage());
                Toast.makeText(FavoriteActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getCurrentNavItemId() {
        return R.id.nav_favorite;
    }

    private void searchBooks(String keyword) {
        apiService.searchFavoriteBooks(keyword, userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Book> bookList = response.body();
                    if (favoriteAdapter == null) {
                        favoriteAdapter = new BookAdapter(FavoriteActivity.this, bookList, false);
                        favoriteBooksRv.setAdapter(favoriteAdapter);
                    } else {
                        favoriteAdapter.updateData(bookList);
                    }
                } else {
                    Toast.makeText(FavoriteActivity.this, "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(FavoriteActivity.this, "Lỗi tìm kiếm", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
