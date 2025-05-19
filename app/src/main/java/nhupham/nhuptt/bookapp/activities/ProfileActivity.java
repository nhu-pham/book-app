package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.responses.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity {
    private LinearLayout personalInfoLayout, changePasswordLayout;
    private Button signOutBtn;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        setupBottomNavigation();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        apiService = ApiClient.getClient().create(ApiService.class);

        // Ánh xạ view
        personalInfoLayout = findViewById(R.id.personalInfoLayout);
        changePasswordLayout = findViewById(R.id.changePasswordLayout);
        signOutBtn = findViewById(R.id.signOutBtn);

        // Chuyển sang PersonalInformationActivity
        personalInfoLayout.setOnClickListener(v -> {
            Log.e("PERSONAL", "PClicked");
            Intent intent = new Intent(ProfileActivity.this, PersonalInformationActivity.class);
            startActivity(intent);
        });

        // Chuyển sang ChangePasswordActivity
        changePasswordLayout.setOnClickListener(v -> {
            Log.e("PERSONAL", "CClicked");
            Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        });

        // Sign out → quay về LoginActivity
        signOutBtn.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
            String token = preferences.getString("access_token", null);
            if (token == null) {
                // Token không có, trực tiếp xóa và chuyển về login
                logoutLocalAndGoToLogin(preferences);
                return;
            }

            apiService.logout(token).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    // Bất kể server trả gì cũng xóa local và chuyển login
                    logoutLocalAndGoToLogin(preferences);
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    // Nếu gọi API thất bại, vẫn xóa local và chuyển login (hoặc báo lỗi tùy bạn)
                    logoutLocalAndGoToLogin(preferences);
                }
            });
        });

    }

    @Override
    protected int getCurrentNavItemId() {
        return R.id.nav_profile;
    }

    private void logoutLocalAndGoToLogin(SharedPreferences preferences) {
        preferences.edit().clear().apply();

        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}