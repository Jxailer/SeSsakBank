package com.example.codevalley.wishStore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.codevalley.R;

import androidx.appcompat.app.AppCompatActivity;



public class store_complete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeactivity_store);

        Button complete_btn = findViewById(R.id.completeBtn);

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent completeIntent = new Intent(getApplicationContext(), store_main.class);
                startActivity(completeIntent);
                finish();
            }
        });

//        complete_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Handler completeHandler = new Handler();
//                completeHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent completeIntent = new Intent(getApplicationContext(), store_main.class);
//                        startActivity(completeIntent);
//                        finish();
//                    }
//                },1000);
//            }
//        });
    }

//    public void complete(View v){
//
//    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}

