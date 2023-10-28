package com.example.adult.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adult.HomeActivity;
import com.example.adult.post.BoardListActivity;
import com.example.codevalley.R;
import com.example.wishShop.WishShopActivity;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    //    하단 네비게이션 바 버튼 클릭
    public void homeButtonClicked(View v){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

    public void wishShopButtonClicked(View v){
        Intent wishShopIntent = new Intent(this, WishShopActivity.class);
        startActivity(wishShopIntent);
    }

    public void profileButtonClicked(View v){

    }

    public void communityButtonClicked(View v){
        Intent communityIntent = new Intent(this, BoardListActivity.class);
        startActivity(communityIntent);
    }
}
