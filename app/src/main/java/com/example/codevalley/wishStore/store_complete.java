package com.example.codevalley.wishStore;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class store_complete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeactivity_store);

    }

    public void complete(View v){
        finish();
    }


}

