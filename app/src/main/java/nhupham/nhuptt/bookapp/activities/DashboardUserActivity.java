package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.databinding.ActivityDashboardUserBinding;

public class DashboardUserActivity extends AppCompatActivity {
    private ActivityDashboardUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkUser();

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void checkUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        if (email != null && !email.isEmpty()) {
            binding.subTitleTv.setText(email);
        } else {
            binding.subTitleTv.setText("Guest");
        }
    }

    private void logoutUser() {
        Intent intent = new Intent(DashboardUserActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        SharedPreferences.Editor editor = getSharedPreferences("user_session", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }
}
