package com.example.wishShop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codevalley.LoginActivity;
import com.example.codevalley.R;
import com.example.codevalley.RegisterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WishShopActivity extends AppCompatActivity {

    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_shop);

        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishShopActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
    }
}