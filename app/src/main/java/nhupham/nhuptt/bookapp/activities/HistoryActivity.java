package nhupham.nhuptt.bookapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

public class HistoryActivity extends BaseActivity {

    private RecyclerView historyBooksRv;
    private BookAdapter historyAdapter;
    private List<Book> historyBookList = new ArrayList<>();
    private ApiService apiService;
    private int userId;

    private EditText searchEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setupBottomNavigation();

        // Lấy user_id từ SharedPreferences
        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        userId = sp.getInt("user_id", -1);

        historyBooksRv = findViewById(R.id.historyBooksRv);
        historyBooksRv.setLayoutManager(new GridLayoutManager(this, 2));
        historyAdapter = new BookAdapter(this, historyBookList, true);
        historyBooksRv.setAdapter(historyAdapter);
        searchEt = findViewById(R.id.searchEt);

        // Khởi tạo Retrofit
        apiService = ApiClient.getClient().create(ApiService.class);

        // Tải danh sách sách đã đọc
        loadHistoryBooks();

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
                    if (historyAdapter != null) {
                        historyAdapter.updateData(historyBookList);
                    }
                }
            }
        });
    }

    private void loadHistoryBooks() {
        if (userId == -1) {
            Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.getReadBooks(userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    historyBookList.clear();
                    historyBookList.addAll(response.body());
                    historyAdapter.updateData(historyBookList);
                } else {
                    Toast.makeText(HistoryActivity.this, "Không lấy được danh sách đã đọc", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("FavoriteActivity", "Lỗi API: " + t.getMessage());
                Toast.makeText(HistoryActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getCurrentNavItemId() {
        return R.id.nav_history;
    }

    private void searchBooks(String keyword) {
        apiService.searchReadBooks(keyword, userId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Book> bookList = response.body();
                    if (historyAdapter == null) {
                        historyAdapter = new BookAdapter(HistoryActivity.this, bookList, false);
                        historyBooksRv.setAdapter(historyAdapter);
                    } else {
                        historyAdapter.updateData(bookList);
                    }
                } else {
                    Toast.makeText(HistoryActivity.this, "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Lỗi tìm kiếm", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
