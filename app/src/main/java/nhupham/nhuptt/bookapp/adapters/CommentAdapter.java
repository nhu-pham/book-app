package nhupham.nhuptt.bookapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.models.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> commentsList;

    public CommentAdapter(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = commentsList.get(position);
        holder.userName.setText(comment.getUsername());
        holder.content.setText(comment.getComment());
        holder.date.setText(comment.getDate());
        holder.ratingBar.setRating(comment.getRating());

        // Load ảnh
        Picasso.get()
                .load(comment.getAvatar_url())
                .placeholder(R.drawable.avt_default) // Hình ảnh placeholder khi ảnh chưa được tải
                .error(R.drawable.avt_default) // Hình ảnh khi xảy ra lỗi
                .into(holder.avatarUrl);
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView userName, content, date;
        RatingBar ratingBar;
        ImageView avatarUrl;

        public CommentViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.comment_user_name);
            content = itemView.findViewById(R.id.comment_content);
            date = itemView.findViewById(R.id.comment_date);
            ratingBar = itemView.findViewById(R.id.comment_rating_bar);
            avatarUrl = itemView.findViewById(R.id.comment_user_avt);
        }
    }
}

