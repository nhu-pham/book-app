package nhupham.nhuptt.bookapp.models;

public class Category {
    private int category_id;
    private String category_name;
    private String image_url;

    public Category(int category_id, String category_name, String imageUrl) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.image_url = imageUrl;
    }

    public int getCategoryId() {
        return category_id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public String getImageUrl() {
        return image_url;
    }
}

