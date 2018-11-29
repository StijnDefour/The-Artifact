package clyde.and.bonie.theartifact;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;

public class MainActivity extends AppCompatActivity {

    private Auth0 auth0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth0 = new Auth0(this);
        auth0.setOIDCConformant(true);

        login();

    }

    private void login() {
        WebAuthProvider.init(auth0)
                .withScheme("demo")
                .withAudience(String.format("https://hackthefuturemobilechallengecozmos.com", getString(R.string.com_auth0_domain)))
                .start(MainActivity.this, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull final Dialog dialog) {
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                    }

                    @Override
                    public void onSuccess(@NonNull final Credentials credentials) {
                    }
                });
    }
}
