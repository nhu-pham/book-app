package nhupham.nhuptt.bookapp.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.activities.DetailActivity;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Activity activity;
    private List<Book> bookList;
    private int userId;
    private ApiService apiService;
    private boolean isFavoriteMode = false;

    public BookAdapter(Activity activity, List<Book> bookList, boolean isFavoriteMode) {
        this.activity = activity;
        this.bookList = new ArrayList<>(bookList);
        this.isFavoriteMode = isFavoriteMode;

        apiService = ApiClient.getClient().create(ApiService.class);

        SharedPreferences sp = activity.getSharedPreferences("user_session", Activity.MODE_PRIVATE);
        userId = sp.getInt("user_id", -1);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        Log.d("BookAdapter", "Book at position " + position + ": " +
                "title=" + book.getTitle() + ", " +
                "category=" + book.getCategoryName() + ", " +
                "coverUrl=" + book.getCover_url());
        holder.titleTv.setText(book.getTitle());
        holder.categoryTv.setText(book.getCategoryName());

        Picasso.get()
                .load(book.getCover_url())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.coverIv);

        holder.itemView.setOnClickListener(v -> {
            Log.e("BookAdapter", "Clicked book: " + book.getBook_id());
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra("bookId", book.getBook_id());
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("type", book.getCategoryName());
            intent.putExtra("description", book.getDescription());
            intent.putExtra("coverUrl", book.getCover_url());
            intent.putExtra("fileUrl", book.getFile_url());
            intent.putExtra("rating", book.getAvg_rating());
            intent.putExtra("isFavorite", book.isFavorite());
            activity.startActivity(intent);
        });

        if (book.isFavorite()) {
            holder.favoriteIv.setImageResource(R.drawable.ic_favorite_red);
        } else {
            holder.favoriteIv.setImageResource(R.drawable.ic_favorite_border);
        }

        holder.favoriteIv.setOnClickListener(v -> {
            boolean currentlyFavorite = book.isFavorite();
            if (userId == -1) {
                Toast.makeText(activity, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                return;
            }

            if (currentlyFavorite) {
                removeFavorite(userId, book.getBook_id(), holder, position);
            } else {
                addFavorite(userId, book.getBook_id(), holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void updateData(List<Book> newBooks) {
        this.bookList = new ArrayList<>(newBooks);
        notifyDataSetChanged();
    }

    private void addFavorite(int userId, int bookId, BookViewHolder holder, int position) {
        apiService.addToFavorites(userId, bookId).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookList.get(position).setFavorite(true);
                    notifyItemChanged(position);
                    Toast.makeText(activity, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Thêm yêu thích thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(activity, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void removeFavorite(int userId, int bookId, BookViewHolder holder, int position) {
        apiService.removeFromFavorites(userId, bookId).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (isFavoriteMode) {
                        bookList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        bookList.get(position).setFavorite(false);
                        notifyItemChanged(position);
                    }
                    Toast.makeText(activity, "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Xóa yêu thích thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(activity, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView coverIv, favoriteIv;
        TextView titleTv, categoryTv;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = itemView.findViewById(R.id.bookImageIv);
            titleTv = itemView.findViewById(R.id.bookTitleTv);
            categoryTv = itemView.findViewById(R.id.categoryTv);
            favoriteIv = itemView.findViewById(R.id.favoriteIcon);
        }
    }
}
