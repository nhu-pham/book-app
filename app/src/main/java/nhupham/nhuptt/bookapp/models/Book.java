package nhupham.nhuptt.bookapp.models;

public class Book {
    private int user_id;
    private int book_id;
    private int category_id;
    private String title;
    private String author;
    private String description;
    private String cover_url;
    private String file_url;
    private float avg_rating;
    private String category_name;
    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // Getters
    public int getBook_id() { return book_id; }
    public int getCategory_id() { return category_id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getCover_url() { return cover_url; }
    public String getFile_url() { return file_url; }
    public float getAvg_rating() { return avg_rating; }
    public String getCategoryName() { return category_name; }
    public void setCategoryName(String categoryName) { this.category_name = categoryName; }
}
