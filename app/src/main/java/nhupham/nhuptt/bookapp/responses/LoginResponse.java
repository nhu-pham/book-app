package nhupham.nhuptt.bookapp.responses;

import nhupham.nhuptt.bookapp.models.User;

public class LoginResponse {
    private boolean success;
    private String message;
    private User user;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public User getUser() { return user; }
}

