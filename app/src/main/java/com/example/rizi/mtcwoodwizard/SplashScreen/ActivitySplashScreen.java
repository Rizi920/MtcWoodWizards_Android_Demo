package com.example.rizi.mtcwoodwizard.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rizi.mtcwoodwizard.ActivityNormalSearch;
import com.example.rizi.mtcwoodwizard.R;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Thread splashThread= new Thread(){
            @Override
            public void run(){
                try {
                    sleep(500);
                    Intent i = new Intent(ActivitySplashScreen.this,ActivityNormalSearch.class);
                    startActivity(i);
                    finish();

                }catch (Exception x){
                    Log.d("error", "SPLASH ACTIVITY..... ");
                }
            }
        };
        splashThread.start();
    }
}
