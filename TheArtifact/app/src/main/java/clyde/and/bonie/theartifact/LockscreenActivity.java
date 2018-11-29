package clyde.and.bonie.theartifact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LockscreenActivity extends AppCompatActivity {
    private String pincode = "";
    private String correctPin = "1111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockscreen);
    }

    private boolean checkPin() {
        boolean result = false;

        if (pincode.equals(correctPin)) {
            result = true;
        }

        return result;
    }

    public void addNumber(View v ) {
        TextView tv = (TextView)findViewById(R.id.txtPincode);

        switch (v.getId()) {
            case (R.id.btnNumeric0):
                pincode += "0";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric1):
                pincode += "1";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric2):
                pincode += "2";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric3):
                pincode += "3";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric4):
                pincode += "4";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric5):
                pincode += "5";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric6):
                pincode += "6";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric7):
                pincode += "7";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric8):
                pincode += "8";
                tv.setText(pincode);
                break;
            case (R.id.btnNumeric9):
                pincode += "9";
                tv.setText(pincode);
                break;
        }

        if (checkPin()) {
            Log.d("Succes", "Pin correct!");
        }
    }

    public void deleteChar(View v ) {
        TextView tv = (TextView)findViewById(R.id.txtPincode);

        if (pincode != null && pincode.length() > 0) {
            pincode = pincode.substring(0, pincode.length() - 1);
            tv.setText(pincode);
        }
        else if (pincode == null) {
            //terug naar vorig scherm
        }
    }
}
