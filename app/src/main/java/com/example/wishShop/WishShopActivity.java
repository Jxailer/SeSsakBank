package com.example.wishShop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.codevalley.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WishShopActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_shop);

        fab = findViewById(R.id.back);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishShopActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
    }
}