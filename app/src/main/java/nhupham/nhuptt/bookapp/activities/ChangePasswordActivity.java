package nhupham.nhuptt.bookapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.models.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {
    private TextInputEditText oldPasswordEt, newPasswordEt, confirmPasswordEt;
    private Button resetBtn;
    private ImageButton backBtn;
    private ApiService apiService;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        apiService = ApiClient.getClient().create(ApiService.class);
        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        userId = sp.getInt("user_id", -1);

        oldPasswordEt = findViewById(R.id.oldPasswordEt);
        newPasswordEt = findViewById(R.id.newPasswordEt);
        confirmPasswordEt = findViewById(R.id.confirmPasswordEt);
        resetBtn = findViewById(R.id.resetBtn);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(v -> finish());
        resetBtn.setOnClickListener(v -> validateAndChangePassword());
    }
    @Override
    protected int getCurrentNavItemId() {
        return R.id.nav_profile;
    }

    private void validateAndChangePassword() {
        String oldPass = oldPasswordEt.getText().toString().trim();
        String newPass = newPasswordEt.getText().toString().trim();
        String confirmPass = confirmPasswordEt.getText().toString().trim();

        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPass.length() < 6) {
            newPasswordEt.setError("Mật khẩu phải có ít nhất 6 kí tự");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            confirmPasswordEt.setError("Mật khẩu không trùng khớp");
            return;
        }

        changePassword(oldPass, newPass);
    }

    private void changePassword(String oldPassword, String newPassword) {
        apiService.changePassword(userId, oldPassword, newPassword).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse res = response.body();
                    if (res.getSuccess()) {
                        String hashedPwd = res.getResponsePwd();
                        Log.e("HashedPwd", "New hashed password: " + hashedPwd);
                        Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
