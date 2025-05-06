package nhupham.nhuptt.bookapp.responses;

import nhupham.nhuptt.bookapp.models.User;

public class RegisterResponse {
    private boolean status;
    private String message;
    private User user;

    public boolean isStatus() { return status; }
    public String getMessage() { return message; }
    public User getUser() { return user; }
}
