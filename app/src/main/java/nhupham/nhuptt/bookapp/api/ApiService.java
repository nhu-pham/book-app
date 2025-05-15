package nhupham.nhuptt.bookapp.api;

import java.util.List;

import nhupham.nhuptt.bookapp.models.Book;
import nhupham.nhuptt.bookapp.models.Category;
import nhupham.nhuptt.bookapp.models.Comment;
import nhupham.nhuptt.bookapp.responses.LoginResponse;
import nhupham.nhuptt.bookapp.responses.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Call<List<Book>> getNewBooks();
    @GET("get_all_new_books.php")
    Call<List<Book>> getAllNewBooks();

    @GET("get_popular_books.php")
    Call<List<Book>> getPopularBooks();

    @GET("get_all_popular_books.php")
    Call<List<Book>> getAllPopularBooks();

    @GET("get_books_by_category.php")
    Call<List<Book>> getBooksByCategory(@Query("category_id") int categoryId);

    @GET("get_comments.php")
    Call<List<Comment>> getComments(@Query("book_id") int bookId);

    @POST("add_comment.php")
    @FormUrlEncoded
    Call<Void> addComment(
            @Field("user_id") String name,
            @Field("comment") String comment,
            @Field("rating") float rating,
            @Field("book_id") int bookId
    );



}


