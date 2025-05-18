package nhupham.nhuptt.bookapp.activities;

import android.os.Bundle;

import nhupham.nhuptt.bookapp.R;

public class NewPasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
    }
    @Override
    protected int getCurrentNavItemId() {
        return 0;
    }
}
