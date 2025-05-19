package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.models.User;
import nhupham.nhuptt.bookapp.utils.FileUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalInformationActivity extends BaseActivity {
    private TextInputEditText nameEt, emailEt;
    private TextView nameTv, emailTv;
    private ImageView avatarIv;
    private Button resetBtn;
    private ImageButton backBtn;
    private ApiService apiService;

    private String username, email;
    private int userId;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri avatarUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_information);
        setupBottomNavigation();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        apiService = ApiClient.getClient().create(ApiService.class);

        nameEt = findViewById(R.id.nameEt);
        nameTv = findViewById(R.id.nameTv);
        emailTv = findViewById(R.id.emailTv);
        emailEt = findViewById(R.id.emailEt);
        avatarIv = findViewById(R.id.avatarIv);
        resetBtn = findViewById(R.id.resetBtn);
        backBtn = findViewById(R.id.backBtn);

        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        username = sp.getString("username", "");
        email = sp.getString("email", "");
        userId = sp.getInt("user_id", -1);

        loadProfile();

        backBtn.setOnClickListener(v -> onBackPressed());

        avatarIv.setOnClickListener(v -> openImagePicker());

        resetBtn.setOnClickListener(v -> {
            username = nameEt.getText().toString().trim();
            email = emailEt.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Gửi dữ liệu cập nhật
                updateProfile(username , email, avatarUri);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            avatarUri = data.getData();
            avatarIv.setImageURI(avatarUri);
        }
    }


    @Override
    protected int getCurrentNavItemId() {
        return R.id.nav_profile;
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE_REQUEST);
    }

    private void updateProfile(String name, String email, Uri avatarUri) {
        try {
            RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), name);
            RequestBody emailBody = RequestBody.create(MediaType.parse("text/plain"), email);
            RequestBody userIdBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(userId));

            MultipartBody.Part avatarPart = null;
            if (avatarUri != null) {
                File avatarFile = FileUtils.getFileFromUri(this, avatarUri);
                RequestBody avatarRequest = RequestBody.create(MediaType.parse("image/*"), avatarFile);
                avatarPart = MultipartBody.Part.createFormData("avatar", avatarFile.getName(), avatarRequest);
            }

            apiService.updateProfile(userIdBody, nameBody, emailBody, avatarPart).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        loadProfile();
                        Toast.makeText(PersonalInformationActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PersonalInformationActivity.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(PersonalInformationActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi xử lý file ảnh", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadProfile() {
        apiService.getUserProfile(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    nameEt.setText(user.getUsername());
                    emailEt.setText(user.getEmail());
                    nameTv.setText(user.getUsername());
                    emailTv.setText(user.getEmail());
                    Picasso.get()
                            .load("http://192.168.1.13/api/" + user.getAvatarUrl())
                            .placeholder(R.drawable.avt_default)
                            .error(R.drawable.error)
                            .into(avatarIv);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(PersonalInformationActivity.this, "Không tải được thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
