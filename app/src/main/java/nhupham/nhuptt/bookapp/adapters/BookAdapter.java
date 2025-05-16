package nhupham.nhuptt.bookapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.activities.DetailActivity;
import nhupham.nhuptt.bookapp.models.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.titleTv.setText(book.getTitle());
        holder.categoryTv.setText(book.getCategoryName());

        // Load ảnh
        Picasso.get()
                .load(book.getCover_url())
                .placeholder(R.drawable.loading) // Hình ảnh placeholder khi ảnh chưa được tải
                .error(R.drawable.error) // Hình ảnh khi xảy ra lỗi
                .into(holder.coverIv);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("bookId", book.getBook_id());
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("type", book.getCategoryName());
            intent.putExtra("description", book.getDescription());
            intent.putExtra("coverUrl", book.getCover_url());
            intent.putExtra("fileUrl", book.getFile_url());
            intent.putExtra("rating", book.getAvg_rating());
            intent.putExtra("position", holder.getAdapterPosition());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView coverIv;
        TextView titleTv, categoryTv;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = itemView.findViewById(R.id.bookImageIv);
            titleTv = itemView.findViewById(R.id.bookTitleTv);
            categoryTv = itemView.findViewById(R.id.categoryTv);
        }
    }
}
