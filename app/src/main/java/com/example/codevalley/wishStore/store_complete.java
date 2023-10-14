package com.example.codevalley.wishStore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.codevalley.R;

import androidx.appcompat.app.AppCompatActivity;



public class store_complete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeactivity_store);

    }

    public void complete(View v){
        Handler splashHandler = new Handler();
        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent completeIntent = new Intent(getApplicationContext(), store_main.class);
                startActivity(completeIntent);
                finish();
            }
        },500);
    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}

