package com.example.codevalley;

import static com.example.codevalley.TargetPopupActivity.context_TargetPopup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;


public class MainActivity extends AppCompatActivity {

    public static Context context_Main;
    public TextView target; // 캘린더 위에 표시되는 목표 내용 변수
    public EditText editText;
    private Button saveButton;

    View targetChangeBox;

    View calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_Main = this;

        target = (TextView)findViewById(R.id.targetButton);
        targetChangeBox = (View)findViewById(R.id.targetChangeBox);
        editText = findViewById((R.id.targetBox));

        calendar = (View)findViewById(R.id.calendar);

        saveButton = findViewById(R.id.saveButton);

//        저장버튼 눌렀을 시
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                if (editText.getText().length() == 0) { // 목표설정란이 비어있는지 확인하기.
                    Toast.makeText(MainActivity.this, "목표가 설정되지 않았어요!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(target.getText().toString() != editText.getText().toString()){ // 기존의 목표와 값이 다르다면 값을 변경함.
                        String result = editText.getText().toString();
                        target.setText(result);
                        calendar.bringToFront(); // finish();
                    }

                    else {
                        calendar.bringToFront(); // finish();
                    }
                    calendar.bringToFront(); // finish();
                }
            }
        });


    }

    public void cancelButtonClicked(View v){
        Toast.makeText(MainActivity.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
        calendar.bringToFront(); // finish();
    }

//    @Override
//    protected void onRestart(){
//        super.onRestart();
//        Intent intent = getIntent();
//        String newTarget = intent.getStringExtra("targetChange");
//        target.setText(newTarget);
//    }


//    목표 설정 버튼을 눌렀을 시
    public void targetButtonClicked(View v){
        Toast.makeText(MainActivity.this, "목표 버튼 눌림.", Toast.LENGTH_SHORT).show();
        editText.setHint(target.getText().toString());
        targetChangeBox.bringToFront();
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
    }

    public void mypageButtonClicked(View v){
        Toast.makeText(MainActivity.this, "마이페이지 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    public void plantgameButtonClicked(View v){
        Toast.makeText(MainActivity.this, "식물키우기 게임 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }



};
