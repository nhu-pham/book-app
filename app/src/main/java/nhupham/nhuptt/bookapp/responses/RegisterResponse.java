package nhupham.nhuptt.bookapp.responses;

import nhupham.nhuptt.bookapp.models.User;

public class RegisterResponse extends ApiResponse {
    private User user;
    public User getUser() { return user; }
}
