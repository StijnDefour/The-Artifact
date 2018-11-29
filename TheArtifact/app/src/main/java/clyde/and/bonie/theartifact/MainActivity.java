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

public class MainActivity extends AppCompatActivity {

    private Auth0 auth0;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // Create URL
                                try {
                                    URL apiEndpoint = new URL("https://htf2018.now.sh/users");

                                    // Create connection
                                    HttpsURLConnection myConnection =
                                            (HttpsURLConnection) apiEndpoint.openConnection();
                                    myConnection.addRequestProperty("Authorization", "Bearer " + credentials.getAccessToken());

                                    if (myConnection.getResponseCode() == 200) {
                                        Log.d("test", "gelukt");

                                        InputStream responseBody = myConnection.getInputStream();
                                        InputStreamReader responseBodyReader =
                                                new InputStreamReader(responseBody, "UTF-8");
                                        JsonReader jsonReader = new JsonReader(responseBodyReader);

                                        

                                        /*jsonReader.beginArray();
                                        while (jsonReader.hasNext()) {
                                            jsonReader.beginObject();
                                            while (jsonReader.hasNext()) {
                                                String name = jsonReader.toString();
                                                String email = "";
                                                if (name.equals("email")) {
                                                    email = name;
                                                } else {
                                                    jsonReader.skipValue();
                                                }
                                                Log.d("test", name);
                                            }
                                            jsonReader.endObject();
                                        }
                                        jsonReader.endArray();

                                        jsonReader.close();*/
                                        myConnection.disconnect();
                                    } else {
                                        Log.d("test", "error");
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    }
                });
    }


}
