package clyde.and.bonie.theartifact;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class MockScreen extends AppCompatActivity {

    Activity activity;
    private AtomicInteger mCounter = new AtomicInteger();
    private Handler handler = new Handler();
    private Runnable mRunnable = new Runnable(){
        @Override
        public void run(){
            mCounter = new AtomicInteger();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_screen);


        TextView tv = (TextView)findViewById(R.id.txtMock);
        tv.setText("Welcome!");

        ImageView myImage = (ImageView) findViewById(R.id.imgMock);
        addClickToImage(myImage);
    }

    public void addClickToImage(ImageView image){
        activity = this;
        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                handler.removeCallbacks(mRunnable);
                handler.postDelayed(mRunnable, 3000);
                if(mCounter.incrementAndGet() == 8){
                    Intent intent = new Intent(activity, LockscreenActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
