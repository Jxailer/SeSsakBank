package com.example.adult.statistics;

import static com.example.adult.adult_LoginActivity.childID;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adult.HomeActivity;
import com.example.adult.post.BoardListActivity;
import com.example.adult.profile.Profile;
import com.example.codevalley.R;
import com.example.wishShop.WishShopActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ageStatsActivity extends AppCompatActivity {

    DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("users").child(childID);

    //나이대별 통계
    DatabaseReference cycleRef = FirebaseDatabase.getInstance().getReference("cycle").child(childID);
    DatabaseReference ageRef = FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference moneyRef = FirebaseDatabase.getInstance().getReference("recordManage");
    ArrayList arrayAge = new ArrayList<>();
    ArrayList arrayAllowance = new ArrayList<>();
    String age;
    String childAge;
    int allowance;

    TextView statsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agestats);

        statsName = findViewById(R.id.stats_name);
        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                statsName.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

  }






    // 다른 통계화면으로 이동
    public void statsButtonClicked(View v){
        Intent statsIntent = new Intent(this, HomeActivity.class);
        startActivity(statsIntent);
        overridePendingTransition(0, 0);
    }

    public void ageStatsButtonClicked(View v){

    }

    public void categoryStatsButtonClicked(View v) {
        Intent categoryStatsIntent = new Intent(this, categoryStatsActivity.class);
        startActivity(categoryStatsIntent);
        overridePendingTransition(0, 0);
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