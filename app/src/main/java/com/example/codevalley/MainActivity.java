package com.example.codevalley;

import static com.example.codevalley.TargetPopupActivity.context_TargetPopup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.codevalley.wishStore.store_confirm;
import com.example.codevalley.wishStore.store_main;

public class MainActivity extends AppCompatActivity {

    public static Context context_Main;
    public TextView target; // 캘린더 위에 표시되는 목표 내용 변수

    public int targetChange = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_Main = this;

        target = (TextView)findViewById(R.id.targetButton);

        if (targetChange == 1){
            String text = ((TargetPopupActivity)TargetPopupActivity.context_TargetPopup).editText.getText().toString();
            target.setText(text);
            targetChange = 0;
        }

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = getIntent();
        String newTarget = intent.getStringExtra("targetChange");
        target.setText(newTarget);
    }


//    목표 설정 버튼
    public void targetButtonClicked(View v){
        Toast.makeText(MainActivity.this, "목표 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TargetPopupActivity.class);
        intent.putExtra("data", target.getText());
        startActivity(intent);
        target.setText("변경중 ..."); // 여기에서 targetButton의 text가 변경되는 것은 확인함.
        finish();
    }

    //목표 설정 내용 변경하기
    protected void targetChange(String result){
        target.setText(result);
        String targetTest = ((MainActivity)MainActivity.context_Main).target.getText().toString();
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
        Toast.makeText(MainActivity.this, "소원궈 상점 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), store_main.class);
        startActivity(intent);
        finish();
    }

    public void mypageButtonClicked(View v){
        Toast.makeText(MainActivity.this, "마이페이지 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    public void plantgameButtonClicked(View v){
        Toast.makeText(MainActivity.this, "식물키우기 게임 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }



};
