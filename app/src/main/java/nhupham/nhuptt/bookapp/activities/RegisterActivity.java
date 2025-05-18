package nhupham.nhuptt.bookapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import nhupham.nhuptt.bookapp.databinding.ActivityRegisterBinding;
import nhupham.nhuptt.bookapp.models.User;
import nhupham.nhuptt.bookapp.responses.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatData();
            }
        });

        binding.haveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private String name = "", email = "", password = "";
    private void validatData() {
        name = binding.nameEt.getText().toString().trim();
        email = binding.emailEt.getText().toString().trim();
        password = binding.passwdEt.getText().toString();
        String confirmPassword = binding.cPasswdEt.getText().toString();

        // validate data
        if (TextUtils.isEmpty(name)) {
            binding.nameEt.setError("Vui lòng nhập tên người dùng");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEt.setError("Định dạng email không đúng");
        } else if (TextUtils.isEmpty(password)) {
            binding.passwdEt.setError("Vui lòng nhập mật khẩu");
        } else if (password.length() < 6) {
            binding.passwdEt.setError("Mật khẩu phải có ít nhất 6 kí tự");
        } else if (TextUtils.isEmpty(confirmPassword)) {
            binding.cPasswdEt.setError("Vui lòng xác nhận mật khẩu");
        } else if (!password.equals(confirmPassword)) {
            binding.cPasswdEt.setError("Mật khẩu không trùng khớp");
        } else {
            createUserAccount();
        }
    }

    private void createUserAccount() {
        progressDialog.setMessage("Creating account");
        progressDialog.show();

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.registerUser(name, email, password);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressDialog.setMessage("Saving user infor");
                if (response.isSuccessful() && response.body() != null) {
                    progressDialog.dismiss();
                    RegisterResponse res = response.body();
                    Toast.makeText(RegisterActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                    if (res.isStatus()) {
                        User user = res.getUser();
                        Log.d("Register", "User ID: " + user.getUserId());
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}