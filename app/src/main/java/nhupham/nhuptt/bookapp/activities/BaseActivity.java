package nhupham.nhuptt.bookapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import nhupham.nhuptt.bookapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(getCurrentNavItemId());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == getCurrentNavItemId()) {
                return true;
            }

            Intent intent = null;
            if (itemId == R.id.nav_home) {
                intent = new Intent(this, HomeActivity.class);
            } else if (itemId == R.id.nav_favorite) {
                intent = new Intent(this, FavoriteActivity.class);
            } else if (itemId == R.id.nav_history) {
                intent = new Intent(this, HistoryActivity.class);
            } else if (itemId == R.id.nav_profile) {
                intent = new Intent(this, ProfileActivity.class);
            }

            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });
    }

    protected abstract int getCurrentNavItemId();
}

