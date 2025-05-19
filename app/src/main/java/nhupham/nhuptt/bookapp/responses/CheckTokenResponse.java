package nhupham.nhuptt.bookapp.responses;

public class CheckTokenResponse {
    private boolean valid;
    private int user_id;
    private String message; // Có thể có hoặc không, tùy response

    public boolean isValid() {
        return valid;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getMessage() {
        return message;
    }
}
