package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.wishStore.store_confirm;
import com.example.codevalley.wishStore.store_main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void gotoWishstore(View v){
        Intent intent = new Intent(getApplicationContext(), store_main.class);
        startActivity(intent);
    }
};