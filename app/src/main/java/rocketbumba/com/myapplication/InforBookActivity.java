package rocketbumba.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rocketbumba.com.myapplication.api.ApiService;
import rocketbumba.com.myapplication.model.Book;

public class InforBookActivity extends AppCompatActivity {
    private TextView tvAuthor;
    private TextView tvId;
    private TextView tvNgayXuaTban;
    private TextView tvTaiBan;
    private TextView tvTenSach;
    private Button btnCheckQR;
    private ImageView imgAnh;
    String maID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_book);
        setControl();
        Intent intent = getIntent();
        maID = intent.getStringExtra("MaID");
        //Log.d("onMAID", maID);
        clickCallAPI(maID);
    }

    private void setControl() {
        tvAuthor = findViewById(R.id.tv_Author);
        imgAnh = findViewById(R.id.imgAnh);
        tvId = findViewById(R.id.tv_ID);
        tvNgayXuaTban = findViewById(R.id.tv_NgayXuanBan);
        tvTaiBan = findViewById(R.id.tv_TaiBan);
        tvTenSach = findViewById(R.id.tv_TenSach);
        btnCheckQR = findViewById(R.id.btn_CheckAuthen);
        btnCheckQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
    }
    private void clickCallAPI(String id) {

        ApiService.apiService.getBook(id).enqueue(new Callback<Book>() {

            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Book toReturn;
                Toast.makeText(InforBookActivity.this,"Call API SUCCESS", Toast.LENGTH_LONG).show();
                toReturn = response.body();
                if(toReturn !=null) {
                    //tvAuthor.setTextSize();
                    tvAuthor.setText("Tên tác giả " + toReturn.getAuthor());
                    //tvId.setText(toReturn.getId());
                    tvNgayXuaTban.setText("Ngày xuất bản " + toReturn.getAisle());
                    tvTenSach.setText("Tên sách " + toReturn.getBook_NAME());
                    tvTaiBan.setText("Ngày tái bản " + toReturn.getIsbn());
                }
                else {
                    tvAuthor.setText("Bi Rong");
                    imgAnh.setImageResource(R.drawable.red);
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(InforBookActivity.this,"Call API ERROR", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null ) {
            if(result.getContents() !=null) {
                String ketQua;
                ketQua = result.getContents();
                Toast.makeText(InforBookActivity.this,ketQua,Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getApplicationContext(), InforBookActivity.class);

                Intent intent = new Intent(getApplicationContext(), AuthenActivity.class);
                intent.putExtra("MaID", maID);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "No Result", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
}