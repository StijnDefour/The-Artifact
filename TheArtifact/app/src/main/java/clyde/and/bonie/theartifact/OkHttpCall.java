package clyde.and.bonie.theartifact;

import android.util.Log;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpCall {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    private String responseStr = "";

    enum RequestStatus {
        Successful,
        Unsuccessful,
        Undefined
    }

    private RequestStatus status = RequestStatus.Undefined;

    private Call get(String url, String token, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + token)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public void get(final String route, String token) {
        OkHttpCall call = new OkHttpCall();
        Call myCall = call.get("https://htf2018.now.sh/" + route, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                status = RequestStatus.Unsuccessful;
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    responseStr = response.body().string();
                    Log.d("test", "Successful response: " + responseStr);
                    status = RequestStatus.Successful;
                } else {
                    status = RequestStatus.Unsuccessful;
                }
            }
        });
    }
}
