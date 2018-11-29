package clyde.and.bonie.theartifact;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import clyde.and.bonie.theartifact.qrscanner.QrScanner;


public class Dashboard extends AppCompatActivity {

    QrScanner qrscanner;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        qrscanner = new QrScanner();

        Button btn_scan = findViewById(R.id.btn_scan);
        activity = this;
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrscanner.startQRScan(activity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String scanned = qrscanner.QrOnActivityResult(requestCode,resultCode,data);
        if (!scanned.equals("")) {
            Log.d("test", scanned);
        }
    }
}
