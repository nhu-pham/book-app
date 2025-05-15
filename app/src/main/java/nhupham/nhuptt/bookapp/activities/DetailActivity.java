package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.adapters.CommentAdapter;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.models.Category;
import nhupham.nhuptt.bookapp.models.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView bookCover, favoriteIcon, addCommentIcon;
    private TextView bookTitle, bookAuthor, bookType, bookDescription;
    private Button readNowButton;
    private RatingBar bookRating;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList = new ArrayList<>();
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.comments_recycler_view);
        addCommentIcon = findViewById(R.id.add_comment_icon);

        // Khởi tạo Retrofit
        apiService = ApiClient.getClient().create(ApiService.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(commentAdapter);

        bookCover = findViewById(R.id.book_cover);
        bookTitle = findViewById(R.id.book_title);
        bookAuthor = findViewById(R.id.book_author);
        bookType = findViewById(R.id.book_type);
        bookDescription = findViewById(R.id.book_description);
        readNowButton = findViewById(R.id.read_now_button);
        bookRating = findViewById(R.id.book_rating);


        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        Integer bookId = Integer.valueOf(intent.getStringExtra("bookId"));
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String type = intent.getStringExtra("type");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("coverUrl");
        float rating = intent.getFloatExtra("rating", 0f);

        // Gán dữ liệu vào view
        bookTitle.setText(title);
        bookAuthor.setText("Tác giả: " + author);
        bookType.setText("Thể loại: " + type);
        bookDescription.setText(description);

        // Load ảnh
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.loading) // Hình ảnh placeholder khi ảnh chưa được tải
                .error(R.drawable.error) // Hình ảnh khi xảy ra lỗi
                .into(bookCover);

        bookRating.setRating(rating);

        ImageView backIv = findViewById(R.id.back_icon);
        backIv.setOnClickListener(v -> onBackPressed());

        getComments(bookId);

        addCommentIcon.setOnClickListener(v -> {
            openAddCommentDialog();
        });

    }

    private void getComments(Integer bookId) {
        apiService.getComments(bookId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    commentList.clear();
                    commentList.addAll(response.body());
                    commentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openAddCommentDialog() {
        // Mở Dialog để người dùng nhập bình luận mới
        // Gửi dữ liệu lên server sau khi người dùng nhập xong
    }
}
