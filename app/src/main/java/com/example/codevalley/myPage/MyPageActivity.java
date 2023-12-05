package com.example.codevalley.myPage;

import static com.example.codevalley.LoginActivity.userID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.game.GameStart1;
import com.example.codevalley.wishStore.store_main;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageActivity extends AppCompatActivity {
    LinearLayout userInfo, pushSet, dataSend, noticeInfo, cusService, termsInfo, verInfo, defID;
    TextView profileName;

    private int gameCheck; // gamestart1번만 실행하기 위해 옆에 이 코드 추가

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        profileName = findViewById(R.id.profile_name);
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference("users").child(userID);

        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                profileName.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        getSupportActionBar().setTitle("< 마이 페이지");

        userInfo = findViewById(R.id.user_info);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });

        pushSet = findViewById(R.id.data_send);
        pushSet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CycleSet.class);
                startActivity(intent);
            }
        });

        defID = findViewById(R.id.del_id);
        defID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteID.class);
                startActivity(intent);
            }
        });
        cusService = findViewById(R.id.cus_service);
        cusService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomService.class);
                startActivity(intent);
            }

        });
        noticeInfo = findViewById(R.id.notice_info);
        noticeInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maintainance.class);
                startActivity(intent);
            }

        });

        Switch onoffswitch = findViewById(R.id.push_onOff);
        onoffswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Toast.makeText(getApplicationContext(), "알림이 설정되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "알림이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //    네비게이션 하단바 버튼클릭 이벤트
    public void homeButtonClicked(View v){
        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeIntent);
        finish();
    }

    public void wishstoreButtonClicked(View v){
        Intent wishIntent = new Intent(this, store_main.class);
        startActivity(wishIntent);
        finish();
    }

    public void mypageButtonClicked(View v){

    }

    public void plantgameButtonClicked(View v){
        // gamestart1번만 실행하기 위해 아래 이 코드 추가
//        FirebaseDatabase.getInstance().getReference("game").child(userID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                try{
//                    gameCheck = snapshot.child("gameCheck").getValue(Integer.class);
//                    if (gameCheck == 1) {
//                        Intent intent = new Intent(MyPageActivity.this, PlantGame.class);
//                        startActivity(intent);
//                        overridePendingTransition(0, 0);
//                        finish();
//                    }
//                    else {
//                        Intent intent = new Intent(MyPageActivity.this, GameStart1.class);
//                        startActivity(intent);
//                        overridePendingTransition(0, 0);
//                        finish();
//                    }
//                } catch (Exception e){
//                    Intent intent = new Intent(MyPageActivity.this, GameStart1.class);
//                    startActivity(intent);
//                    overridePendingTransition(0, 0);
//                    finish();
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
        Intent gameIntent = new Intent(this, GameStart1.class);
        startActivity(gameIntent);
        finish();
    }
}
