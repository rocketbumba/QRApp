package rocketbumba.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rocketbumba.com.myapplication.api.ApiService;
import rocketbumba.com.myapplication.model.Book;
import rocketbumba.com.myapplication.model.BookAuthen;

public class AuthenActivity extends AppCompatActivity {
    private TextView tvAuthen;
    private BookAuthen bookAuthen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);
        tvAuthen = findViewById(R.id.tv_Authen);
        Intent intent = getIntent();
        String maID = intent.getStringExtra("MaID");

        loadBook(maID);
        updateBook(bookAuthen.getId());
    }
    private void updateBook(String id) {
        if(bookAuthen.getAuthen() == 0) {
            tvAuthen.setText("Mã QR vẫn còn hiệu lực");
            DBAuthen dbAuthen = new DBAuthen(AuthenActivity.this);
            dbAuthen.update(id);
        }
        else {
            tvAuthen.setText("Mã QR đã hết hiệu lực");
        }

    }

    private BookAuthen loadBook(String id) {
        DBAuthen dbAuthen = new DBAuthen(AuthenActivity.this);

        return bookAuthen = dbAuthen.load(id);
    }
}