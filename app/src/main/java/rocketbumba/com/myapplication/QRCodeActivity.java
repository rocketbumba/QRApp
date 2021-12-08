package rocketbumba.com.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rocketbumba.com.myapplication.api.ApiService;
import rocketbumba.com.myapplication.model.Book;

public class QRCodeActivity extends AppCompatActivity {
    Button scanBtn;
    Button scanAuthen;
    private Button btnCallAPI;
    Book toReturn;
    String ketQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
//        scanAuthen = findViewById(R.id.scanBtn_Authen);
//        scanAuthen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AuthenActivity.class);
//                startActivity(intent);
//            }
//        });

    }



//    @Override
//    public void onClick(View v) {
//       scanCode();
//    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null ) {
            if(result.getContents() !=null) {

                ketQua = result.getContents();
                Toast.makeText(QRCodeActivity.this,ketQua,Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getApplicationContext(), InforBookActivity.class);
                Intent intent = new Intent(getApplicationContext(), InforBookActivity.class);
                intent.putExtra("MaID",ketQua);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "No Result", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}