package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler splashHandler = new Handler();
        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(getApplicationContext(), TypeSelect.class);
                startActivity(splashIntent);
                finish();
            }
        },2000);
    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}
