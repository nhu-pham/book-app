package nhupham.nhuptt.bookapp.models;

public class Comment {
    private String username;
    private String comment;
    private String cmDate;
    private float rating;

    private String avatar_url;

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return cmDate;
    }

    public float getRating() {
        return rating;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

}
