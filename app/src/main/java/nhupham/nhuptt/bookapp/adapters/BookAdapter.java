package nhupham.nhuptt.bookapp.adapters;

import android.content.Context;
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
        holder.categoryTv.setText(book.getAuthor());

        // Load ảnh
        Picasso.get()
                .load(book.getCover_url())
                .placeholder(com.denzcoskun.imageslider.R.drawable.placeholder)
                .into(holder.coverIv);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
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
