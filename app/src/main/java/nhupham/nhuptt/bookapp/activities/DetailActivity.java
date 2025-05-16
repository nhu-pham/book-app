package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.adapters.CommentAdapter;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.models.Category;
import nhupham.nhuptt.bookapp.models.Comment;
import nhupham.nhuptt.bookapp.responses.CommentResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView bookCover, favoriteIcon, addCommentIcon;
    private TextView bookTitle, bookAuthor, bookType, bookDescription, noCommentsText;
    private Button readNowButton;
    private RatingBar bookRating;
    private RecyclerView recyclerView;
    private Integer bookId;
    private Integer userId;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList = new ArrayList<>();
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        userId = getUserId();
        bookId = Integer.valueOf(getIntent().getStringExtra("bookId"));


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
        noCommentsText = findViewById(R.id.no_comments_text);


        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        Integer bookId = Integer.valueOf(intent.getStringExtra("bookId"));
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String type = intent.getStringExtra("type");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("coverUrl");
        String fileUrl = intent.getStringExtra("fileUrl");
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

        readNowButton.setOnClickListener(v -> {
            Intent intentRead = new Intent(DetailActivity.this, ReadActivity.class);
            intentRead.putExtra("title", title);
            intentRead.putExtra("fileUrl", fileUrl);
            startActivity(intentRead);
        });

        addCommentIcon.setOnClickListener(v -> {
            showAddCommentDialog();
        });

    }

    private void getComments(Integer bookId) {
        apiService.getComments(bookId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Comment> comments = response.body();
                    if (comments.isEmpty()) {
                        commentList.clear();
                        commentAdapter.notifyDataSetChanged();
                        noCommentsText.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        noCommentsText.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        commentList.clear();
                        commentList.addAll(comments);
                        commentAdapter.notifyDataSetChanged();
                    }
                } else {
                    noCommentsText.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
                noCommentsText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }


    private void showAddCommentDialog() {
        // Tạo dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate layout dialog
        View dialogView = getLayoutInflater().inflate(R.layout.activity_comment, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        // Lấy các view trong dialog
        ImageButton backBtn = dialogView.findViewById(R.id.backBtn);
        TextInputEditText commentEt = dialogView.findViewById(R.id.commentEt);
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
        Button submitBtn = dialogView.findViewById(R.id.submitBtn);

        backBtn.setOnClickListener(v -> dialog.dismiss());

        submitBtn.setOnClickListener(v -> {
            String comment = commentEt.getText().toString().trim();
            int rating = (int) ratingBar.getRating();

            if (comment.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đánh giá", Toast.LENGTH_SHORT).show();
                return;
            }

            if (rating == 0) {
                Toast.makeText(this, "Vui lòng chọn số sao", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gửi comment + rating lên server
            sendCommentToServer(comment, rating);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void sendCommentToServer(String comment, int rating) {
        apiService.addComment(bookId, userId, comment, rating).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailActivity.this, "Thêm đánh giá thành công!", Toast.LENGTH_SHORT).show();
                    getComments(bookId);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("bookId", bookId);
                    resultIntent.putExtra("newRating", rating);  // Đây là rating mới người dùng chọn
                    resultIntent.putExtra("position", getIntent().getIntExtra("position", -1)); // 👈 Lấy lại vị trí
                    setResult(RESULT_OK, resultIntent);

                } else {
                    Toast.makeText(DetailActivity.this, "Lỗi khi thêm đánh giá!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getUserId() {
        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        return sp.getInt("user_id", -1);
    }


}
