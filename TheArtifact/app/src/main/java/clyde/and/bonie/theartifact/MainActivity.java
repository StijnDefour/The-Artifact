package clyde.and.bonie.theartifact;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Auth0 auth0;
    private Button button;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();

        auth0 = new Auth0(this);
        auth0.setOIDCConformant(true);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        WebAuthProvider.init(auth0)
                .withScheme("demo")
                .withAudience(String.format("https://hackthefuturemobilechallengecozmos.com", getString(R.string.com_auth0_domain)))
                .start(MainActivity.this, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull final Dialog dialog) {
                        Log.d("test", "-------------------------------");
                        Log.d("test", "ook mislukt");
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                        Log.d("test", "-------------------------------");
                        Log.d("test", "mislukt");
                    }

                    @Override
                    public void onSuccess(@NonNull final Credentials credentials) {
                        Log.d("test", "-------------------------------");
                        Log.d("test", credentials.getAccessToken());
                        getUsers(credentials.getAccessToken( ));
                    }
                });
    }

    private void getUsers(String user_id) {
        OkHttpCall call = new OkHttpCall();
        call.get("users", user_id);
    }




}
