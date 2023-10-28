package com.example.adult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adult.post.BoardListActivity;
import com.example.adult.profile.Profile;
import com.example.codevalley.R;
import com.example.codevalley.game.GameStart1;
import com.example.wishShop.WishShopActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_home);
    }

    //    하단 네비게이션 바 버튼 클릭
    public void homeButtonClicked(View v){

    }

    public void wishShopButtonClicked(View v){
        Intent wishShopIntent = new Intent(this, WishShopActivity.class);
        startActivity(wishShopIntent);
    }

    public void profileButtonClicked(View v){
        Intent profileIntent = new Intent(this, Profile.class);
        startActivity(profileIntent);
    }

    public void communityButtonClicked(View v){
        Intent communityIntent = new Intent(this, BoardListActivity.class);
        startActivity(communityIntent);
    }
}
