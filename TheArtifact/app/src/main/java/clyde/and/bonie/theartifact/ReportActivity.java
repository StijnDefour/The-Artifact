package clyde.and.bonie.theartifact;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;

public class ReportActivity extends AppCompatActivity {
    private Auth0 auth0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getLocation();
    }

    private void getLocation() {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                EditText e_long = findViewById(R.id.txtLongtitude);
                EditText e_lat = findViewById(R.id.txtLatitude);
                e_long.setText(String.valueOf(longitude));
                e_lat.setText(String.valueOf(latitude));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void login() {
        WebAuthProvider.init(auth0)
                .withScheme("demo")
                .withAudience(String.format("https://hackthefuturemobilechallengecozmos.com", getString(R.string.com_auth0_domain)))
                .start(ReportActivity.this, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull final Dialog dialog) {
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                    }

                    @Override
                    public void onSuccess(@NonNull final Credentials credentials) {
                        getUsers(credentials.getAccessToken( ));
                    }
                });
    }

    private void getUsers(String user_id) {
        OkHttpCall call = new OkHttpCall();
        call.get("users", user_id);
    }
}
