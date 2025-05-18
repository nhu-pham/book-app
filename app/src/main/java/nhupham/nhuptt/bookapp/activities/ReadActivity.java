package nhupham.nhuptt.bookapp.activities;

import android.os.Bundle;
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
import nhupham.nhuptt.bookapp.models.ReadingProgress;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ReadActivity extends AppCompatActivity {

    private PDFView pdfView;
    private LinearLayout loadingLayout;  // Thêm biến cho loadingLayout
    private TextView titleTv;
    private ImageView backIv;
    private ApiService apiService;
    private int userId, bookId, lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        titleTv = findViewById(R.id.titleTv);
        pdfView = findViewById(R.id.pdfView);
        backIv = findViewById(R.id.backIv);
        loadingLayout = findViewById(R.id.loadingLayout);  // Khởi tạo loadingLayout

        apiService = ApiClient.getClient().create(ApiService.class);
        String title = getIntent().getStringExtra("title");
        String fileUrl = getIntent().getStringExtra("fileUrl");
        lastPage = getIntent().getIntExtra("lastPage", 0);
        userId = getIntent().getIntExtra("userId", -1);
        bookId = getIntent().getIntExtra("bookId", 0);

        titleTv.setText(title);

        downloadAndDisplayPdf(fileUrl);

        backIv.setOnClickListener(v -> onBackPressed());

    }

    private void downloadAndDisplayPdf(String fileUrl) {
        Log.d("PDF_DOWNLOAD", "Bắt đầu tải file PDF từ URL: " + fileUrl);
        loadingLayout.setVisibility(View.VISIBLE);  // Hiển thị loadingLayout khi bắt đầu tải

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(fileUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("PDF_DOWNLOAD", "Lỗi khi tải file: " + e.getMessage());
                runOnUiThread(() -> {
                    loadingLayout.setVisibility(View.GONE);  // Ẩn khi lỗi
                    showError("Lỗi khi tải file PDF");
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("PDF_DOWNLOAD", "HTTP Error Code: " + response.code());
                    runOnUiThread(() -> {
                        loadingLayout.setVisibility(View.GONE);  // Ẩn khi lỗi
                        showError("Không thể kết nối tới file PDF. Mã lỗi: " + response.code());
                    });
                    return;
                }

                File tempPdfFile = File.createTempFile("book_", ".pdf", getCacheDir());
                Log.d("PDF_DOWNLOAD", "Tạo file tạm: " + tempPdfFile.getAbsolutePath());

                try (InputStream input = response.body().byteStream();
                     FileOutputStream output = new FileOutputStream(tempPdfFile)) {

                    byte[] buffer = new byte[8192];
                    int count;
                    int total = 0;
                    while ((count = input.read(buffer)) > 0) {
                        output.write(buffer, 0, count);
                        total += count;
                    }
                    Log.d("PDF_DOWNLOAD", "Đã ghi file, tổng bytes: " + total);

                    if (!tempPdfFile.exists() || tempPdfFile.length() == 0) {
                        Log.e("PDF_DOWNLOAD", "File PDF không tồn tại hoặc rỗng");
                        runOnUiThread(() -> {
                            loadingLayout.setVisibility(View.GONE);  // Ẩn khi lỗi
                            showError("File PDF không tồn tại hoặc rỗng");
                        });
                        return;
                    }

                    runOnUiThread(() -> displayPdf(tempPdfFile));
                } catch (IOException e) {
                    Log.e("PDF_DOWNLOAD", "Lỗi khi ghi file: " + e.getMessage());
                    runOnUiThread(() -> {
                        loadingLayout.setVisibility(View.GONE);  // Ẩn khi lỗi
                        showError("Lỗi khi lưu file PDF");
                    });
                }
            }
        });
    }

    private void displayPdf(File pdfFile) {
        Log.e("READ", "Số trang:" + lastPage);
        try {
            loadingLayout.setVisibility(View.GONE);  // Ẩn khi hiển thị PDF
            pdfView.setVisibility(View.VISIBLE);

            pdfView.fromFile(pdfFile)
                    .defaultPage(lastPage)
                    .onPageChange((page, pageCount) -> {
                        saveReadingProgress(userId, bookId, page);
                    })
                    .onError(t -> {
                        Log.e("PDF_VIEW", "Lỗi khi load PDF: " + t.getMessage());
                        runOnUiThread(() -> showError("Lỗi khi load PDF"));
                    })
                    .onLoad(nbPages -> Log.d("PDF_VIEW", "PDF loaded with " + nbPages + " pages"))
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
            Log.d("PDF_VIEW", "Hiển thị PDF thành công");
        } catch (Exception e) {
            Log.e("PDF_VIEW", "Lỗi khi hiển thị PDF: " + e.getMessage());
            runOnUiThread(() -> showError("Không thể hiển thị file PDF"));
        }
    }

    private void showError(String message) {
        runOnUiThread(() -> {
            loadingLayout.setVisibility(View.GONE);  // Ẩn khi có lỗi
            Toast.makeText(ReadActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }

    private void saveReadingProgress(int userId, int bookId, int lastPage) {
        Log.d("PDF_PAGE", "Đang ở trang: " + lastPage + userId + bookId);
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