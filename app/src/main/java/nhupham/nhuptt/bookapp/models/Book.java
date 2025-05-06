package nhupham.nhuptt.bookapp.models;

public class Book {
    private String book_id;
    private String category_id;
    private String title;
    private String author;
    private String description;
    private String cover_url;
    private String file_url;
    private float avg_rating;

    // Getters
    public String getBook_id() { return book_id; }
    public String getCategory_id() { return category_id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getCover_url() { return cover_url; }
    public String getFile_url() { return file_url; }
    public float getAvg_rating() { return avg_rating; }
}
