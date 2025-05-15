package nhupham.nhuptt.bookapp.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import nhupham.nhuptt.bookapp.R;

public class ReadActivity extends AppCompatActivity {

    private TextView bookTitleTv, bookContentTv;
    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        bookTitleTv = findViewById(R.id.titleTv);
//        bookContentTv = findViewById(R.id.); // TextView hiện nội dung
        backIv = findViewById(R.id.backIv);

        String title = getIntent().getStringExtra("title");
        String fileUrl = getIntent().getStringExtra("fileUrl");

        bookTitleTv.setText(title);

        loadBookContent(fileUrl);

        // Xử lý nút back
        backIv.setOnClickListener(v -> onBackPressed());
    }

    private void loadBookContent(String fileUrl) {
        new Thread(() -> {
            try {
                URL url = new URL(fileUrl);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                reader.close();

                runOnUiThread(() -> bookContentTv.setText(builder.toString()));
            } catch (Exception e) {
                runOnUiThread(() -> bookContentTv.setText("Không thể tải nội dung sách."));
            }
        });
    }
}
