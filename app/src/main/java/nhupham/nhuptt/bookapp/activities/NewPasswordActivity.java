package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordActivity extends AppCompatActivity {

    private TextInputEditText newPasswordEt, confirmPasswordEt;
    private Button resetBtn;
    private String token;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        newPasswordEt = findViewById(R.id.newPasswordEt);
        confirmPasswordEt = findViewById(R.id.confirmPasswordEt);
        resetBtn = findViewById(R.id.resetBtn);

        apiService = ApiClient.getClient().create(ApiService.class);

        // Lấy token từ deep link
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null && data.getQueryParameter("token") != null) {
            token = data.getQueryParameter("token");
        } else {
            Toast.makeText(this, "Token không hợp lệ!", Toast.LENGTH_SHORT).show();
            finish();
        }

        resetBtn.setOnClickListener(v -> {
            String newPassword = newPasswordEt.getText().toString().trim();
            String confirmPassword = confirmPasswordEt.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
            } else if (newPassword.length() < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 kí tự!", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            } else {
                resetPassword(token, newPassword);
            }
        });
    }

    private void resetPassword(String token, String newPassword) {
        Log.e("NEW", "Da vo");
        apiService.resetPassword(token, newPassword).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("NEW", "Nội dung: " + response.body());
                if (response.isSuccessful()) {
                    Log.e("NEW", "Thành công");
                    Toast.makeText(NewPasswordActivity.this, "Đặt lại mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewPasswordActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Log.e("NEW", "Không thành công");
                    Toast.makeText(NewPasswordActivity.this, "Thất bại! Token không hợp lệ hoặc hết hạn.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("NEW", "Lỗi");
                Toast.makeText(NewPasswordActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
