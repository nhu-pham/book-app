package nhupham.nhuptt.bookapp.api;

import java.util.List;

import nhupham.nhuptt.bookapp.models.Book;
import nhupham.nhuptt.bookapp.models.Category;
import nhupham.nhuptt.bookapp.responses.LoginResponse;
import nhupham.nhuptt.bookapp.responses.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @GET("get_new_books.php")
    Call<List<Book>> getNewBooks();

    @GET("get_popular_books.php")
    Call<List<Book>> getPopularBooks();

}


