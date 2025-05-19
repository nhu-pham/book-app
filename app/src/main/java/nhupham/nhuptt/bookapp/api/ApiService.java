package nhupham.nhuptt.bookapp.api;

import java.util.List;

import nhupham.nhuptt.bookapp.responses.ApiResponse;
import nhupham.nhuptt.bookapp.models.Book;
import nhupham.nhuptt.bookapp.models.Category;
import nhupham.nhuptt.bookapp.models.Comment;
import nhupham.nhuptt.bookapp.models.ReadingProgress;
import nhupham.nhuptt.bookapp.models.User;
import nhupham.nhuptt.bookapp.responses.CheckTokenResponse;
import nhupham.nhuptt.bookapp.responses.CommentResponse;
import nhupham.nhuptt.bookapp.responses.LoginResponse;
import nhupham.nhuptt.bookapp.responses.RegisterResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("get_categories.php")
    Call<List<Category>> getCategories();

    @GET("get_all_categories.php")
    Call<List<Category>> getAllCategories();

    @GET("get_new_books.php")
    Call<List<Book>> getNewBooks(@Query("user_id") int userId);

    @GET("get_all_new_books.php")
    Call<List<Book>> getAllNewBooks(@Query("user_id") int userId);

    @GET("get_popular_books.php")
    Call<List<Book>> getPopularBooks(@Query("user_id") int userId);

    @GET("get_all_popular_books.php")
    Call<List<Book>> getAllPopularBooks(@Query("user_id") int userId);

    @GET("get_books_by_category.php")
    Call<List<Book>> getBooksByCategory(
            @Query("category_id") int categoryId,
            @Query("user_id") int userId
    );

    @GET("get_comments.php")
    Call<List<Comment>> getComments(@Query("book_id") int bookId);

    @FormUrlEncoded
    @POST("add_comment.php")
    Call<CommentResponse> addComment(
            @Field("book_id") int bookId,
            @Field("user_id") int userId,
            @Field("comment") String comment,
            @Field("rating") float rating
    );

    @GET("search_books.php")
    Call<List<Book>> searchBooks(
            @Query("keyword") String keyword,
            @Query("user_id") int userId
    );

    @GET("search_favorite_books.php")
    Call<List<Book>> searchFavoriteBooks(
            @Query("keyword") String keyword,
            @Query("user_id") int userId
    );

    @GET("search_read_books.php")
    Call<List<Book>> searchReadBooks(
            @Query("keyword") String keyword,
            @Query("user_id") int userId
    );

    @FormUrlEncoded
    @POST("add_favorite.php")
    Call<Book> addToFavorites(@Field("user_id") int userId, @Field("book_id") int bookId);

    @FormUrlEncoded
    @POST("remove_favorite.php")
    Call<Book> removeFromFavorites(@Field("user_id") int userId, @Field("book_id") int bookId);

    @GET("get_favorite_books.php")
    Call<List<Book>> getFavoriteBooks(@Query("user_id") int userId);

    @FormUrlEncoded
    @POST("save_reading_progress.php")
    Call<ResponseBody> saveReadingProgress(
            @Field("user_id") int userId,
            @Field("book_id") int bookId,
            @Field("last_page") int lastPage
    );

    @GET("get_reading_progress.php")
    Call<ReadingProgress> getReadingProgress(
            @Query("user_id") int userId,
            @Query("book_id") int bookId
    );

    @GET("get_read_books.php")
    Call<List<Book>> getReadBooks(@Query("user_id") int userId);

    @GET("get_profile.php")
    Call<User> getUserProfile(@Query("user_id") int userId);

    @Multipart
    @POST("update_profile.php")
    Call<ResponseBody> updateProfile(
            @Part("user_id") RequestBody id,
            @Part("username") RequestBody name,
            @Part("email") RequestBody email,
            @Part MultipartBody.Part avatar
    );

    @FormUrlEncoded
    @POST("change_password.php")
    Call<ApiResponse> changePassword(
            @Field("user_id") int userId,
            @Field("old_password") String oldPassword,
            @Field("new_password") String newPassword
    );

    @FormUrlEncoded
    @POST("send_reset_link.php")
    Call<ApiResponse> sendResetPasswordEmail(@Field("email") String email);

    @FormUrlEncoded
    @POST("check_token.php")
    Call<CheckTokenResponse> checkToken(@Field("token") String token);

    @FormUrlEncoded
    @POST("logout.php")
    Call<ApiResponse> logout(@Field("token") String token);

    @FormUrlEncoded
    @POST("reset_password.php")
    Call<ResponseBody> resetPassword(
            @Field("token") String token,
            @Field("new_password") String newPassword
    );

}
