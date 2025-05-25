package nhupham.nhuptt.bookapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import nhupham.nhuptt.bookapp.R;
import nhupham.nhuptt.bookapp.api.ApiClient;
import nhupham.nhuptt.bookapp.api.ApiService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ReadActivity extends AppCompatActivity {

    private PDFView pdfView;
    private LinearLayout loadingLayout;
    private TextView titleTv;
    private ImageView backIv;
    private ApiService apiService;
    private int userId, bookId, lastPage;
    private Handler handler;
    private Runnable saveProgressRunnable;
    private int currentPage = -1;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        titleTv = findViewById(R.id.titleTv);
        pdfView = findViewById(R.id.pdfView);
        backIv = findViewById(R.id.backIv);
        loadingLayout = findViewById(R.id.loadingLayout);

        handler = new Handler(Looper.getMainLooper());

        apiService = ApiClient.getClient().create(ApiService.class);
        String title = getIntent().getStringExtra("title");
        String fileUrl = getIntent().getStringExtra("fileUrl");
        lastPage = getIntent().getIntExtra("lastPage", 0);
        userId = getIntent().getIntExtra("userId", -1);
        bookId = getIntent().getIntExtra("bookId", 0);

        titleTv.setText(title);

        File cachedFile = new File(getCacheDir(), "book_" + bookId + ".pdf");
        if (cachedFile.exists() && cachedFile.length() > 0) {
            displayPdf(cachedFile);
        } else {
            downloadAndDisplayPdf(fileUrl, cachedFile);
        }

        backIv.setOnClickListener(v -> onBackPressed());
    }

    private void downloadAndDisplayPdf(String fileUrl, File outputFile) {
        Log.d("PDF_DOWNLOAD", "Bắt đầu tải file PDF từ URL: " + fileUrl);
        loadingLayout.setVisibility(View.VISIBLE);

        Request request = new Request.Builder().url(fileUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("PDF_DOWNLOAD", "Lỗi khi tải file: " + e.getMessage());
                runOnUiThread(() -> {
                    loadingLayout.setVisibility(View.GONE);
                    showError("Lỗi khi tải file PDF");
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("PDF_DOWNLOAD", "HTTP Error Code: " + response.code());
                    runOnUiThread(() -> {
                        loadingLayout.setVisibility(View.GONE);
                        showError("Không thể kết nối tới file PDF. Mã lỗi: " + response.code());
                    });
                    return;
                }

                try (ResponseBody body = response.body();
                     InputStream input = body.byteStream();
                     FileOutputStream output = new FileOutputStream(outputFile)) {

                    byte[] buffer = new byte[32 * 1024];
                    int count;
                    while ((count = input.read(buffer)) > 0) {
                        output.write(buffer, 0, count);
                    }

                    if (!outputFile.exists() || outputFile.length() == 0) {
                        Log.e("PDF_DOWNLOAD", "File PDF không tồn tại hoặc rỗng");
                        runOnUiThread(() -> {
                            loadingLayout.setVisibility(View.GONE);
                            showError("File PDF không tồn tại hoặc rỗng");
                        });
                        return;
                    }

                    runOnUiThread(() -> displayPdf(outputFile));
                } catch (IOException e) {
                    Log.e("PDF_DOWNLOAD", "Lỗi khi ghi file: " + e.getMessage());
                    runOnUiThread(() -> {
                        loadingLayout.setVisibility(View.GONE);
                        showError("Lỗi khi lưu file PDF");
                    });
                }
            }
        });
    }

    private void displayPdf(File pdfFile) {
        Log.d("READ", "Số trang: " + lastPage);
        try {
            loadingLayout.setVisibility(View.GONE);
            pdfView.setVisibility(View.VISIBLE);

            pdfView.fromFile(pdfFile)
                    .defaultPage(lastPage)
                    .onPageChange((page, pageCount) -> debounceSaveProgress(userId, bookId, page))
                    .onError(t -> {
                        Log.e("PDF_VIEW", "Lỗi khi load PDF: " + t.getMessage());
                        runOnUiThread(() -> showError("Lỗi khi load PDF"));
                    })
                    .onLoad(nbPages -> {
                        Log.d("PDF_VIEW", "PDF loaded with " + nbPages + " pages");
                        pdfView.jumpTo(lastPage, true);
                    })
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(0)
                    .autoSpacing(false)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .fitEachPage(false)
                    .pageSnap(false)
                    .pageFling(false)
                    .nightMode(false)
                    .load();
        } catch (Exception e) {
            Log.e("PDF_VIEW", "Lỗi khi hiển thị PDF: " + e.getMessage());
            runOnUiThread(() -> showError("Không thể hiển thị file PDF"));
        }
    }

    private void debounceSaveProgress(int userId, int bookId, int page) {
        currentPage = page;
        if (saveProgressRunnable != null) handler.removeCallbacks(saveProgressRunnable);

        saveProgressRunnable = () -> saveReadingProgress(userId, bookId, currentPage);
        handler.postDelayed(saveProgressRunnable, 5000); // 5s sau khi dừng lật trang
    }

    private void showError(String message) {
        runOnUiThread(() -> {
            loadingLayout.setVisibility(View.GONE);
            Toast.makeText(ReadActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }

    private void saveReadingProgress(int userId, int bookId, int lastPage) {
        Log.d("PDF_PAGE", "Đang lưu trang: " + lastPage + ", user: " + userId + ", book: " + bookId);
        apiService.saveReadingProgress(userId, bookId, lastPage).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("SAVE_PROGRESS", "Tiến độ đọc đã được lưu");
                } else {
                    Log.e("SAVE_PROGRESS", "Lỗi server: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e("SAVE_PROGRESS", "Lỗi mạng: " + t.getMessage());
            }
        });
    }
}
