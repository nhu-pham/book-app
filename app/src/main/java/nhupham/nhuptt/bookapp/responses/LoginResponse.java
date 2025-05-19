package nhupham.nhuptt.bookapp.responses;

import nhupham.nhuptt.bookapp.models.User;

public class LoginResponse extends ApiResponse {
    private String token;
    private String expires_at;
    private User user;

    public String getToken() { return token; }
    public String getExpires_at() { return expires_at; }
    public User getUser() { return user; }
}
