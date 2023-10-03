package com.example.codevalley;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.wishStore.store_complete;
import com.example.codevalley.wishStore.store_main;

public class MainActivity extends AppCompatActivity {

    public static Context context_Main;
    public TextView target; // 캘린더 위에 표시되는 목표 내용 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_Main = this;

        target = (TextView)findViewById(R.id.targetButton);

    }

//    목표 설정 버튼
    public void targetButtonClicked(View v){
        Toast.makeText(MainActivity.this, "목표 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TargetPopupActivity.class);
        intent.putExtra("data", target.getText());
        startActivity(intent);
    }

    //목표 설정 내용 변경하기
    protected void targetChange(){
        String text = ((TargetPopupActivity)TargetPopupActivity.context_TargetPopup).editText.getText().toString();
        target.setText(text);
    }




//    정보바 버튼 클릭
    public void monthButtonClicked(View v){
        Toast.makeText(MainActivity.this, "월 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }
    public void stampButtonClicked(View v){
        Toast.makeText(MainActivity.this, "스템프 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StampPopupActivity.class);
        startActivity(intent);
    }

    public void statisticsButtonClicked(View v){
        Toast.makeText(MainActivity.this, "통계 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    //캘린더 버튼 눌림
    public void dayButtonClicked(View v){
        Toast.makeText(MainActivity.this, "날짜 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DailyRecordPopupActivity.class);
        intent.putExtra("data", "Test Popup");
        startActivity(intent);
    }

//    하단 네비게이션 바 버튼 클릭
    public void homeButtonClicked(View v){
        Toast.makeText(MainActivity.this, "홈 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    public void wishstoreButtonClicked(View v){
        Toast.makeText(MainActivity.this, "소원권 상점 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, store_main.class);
        startActivity(intent);
    }

    public void mypageButtonClicked(View v){
        Toast.makeText(MainActivity.this, "마이페이지 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    public void plantgameButtonClicked(View v){
        Toast.makeText(MainActivity.this, "식물키우기 게임 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }



};