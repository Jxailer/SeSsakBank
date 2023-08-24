package com.example.codevalley;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;


public class MainActivity extends AppCompatActivity {

    public static Context context_Main;
    public TextView target; // 캘린더 위에 표시되는 목표 내용 변수
    private Button recordCreate;
    public int targetChange = 0;
    public EditText editText;
    private Button saveButton;
    private Button cancelButton;
    private TextView targetButton;

    View targetChangeBox;

    View calendar;
    View mainParent;


    ViewGroup CalendarRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_Main = this;
        target = (TextView)findViewById(R.id.targetButton);
        recordCreate = (Button)findViewById(R.id.recordCreateButton);
        CalendarRecord = (ViewGroup)findViewById(R.id.CalendarRecord);
        mainParent = findViewById(R.id.mainParent);

        target = (TextView) findViewById(R.id.targetButton);
        targetChangeBox = (View) findViewById(R.id.targetChangeBox);
        editText = findViewById((R.id.targetBox));

        calendar = (View) findViewById(R.id.calendar);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        targetButton = findViewById(R.id.targetButton);

        // targetBox(목표 수정창) 높이 증가(펼치기)용 소스코드
//        LinearLayout.LayoutParams targetBoxSpreadParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                380
//        );

        // targetBox(목표 수정창) 높이 감소(접기)용 소스코드
//        LinearLayout.LayoutParams targetBoxFoldParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                22
//        );

        // 목표 버튼 눌렀을 시
        targetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetChangeBox.setVisibility(View.VISIBLE);
                targetChangeBox.bringToFront();

        recordCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordCreate.class);
                startActivity(intent);
            }
        });


//        저장버튼 눌렀을 시 onClickListener
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().length() == 0) { // 목표설정란이 비어있는지 확인하기.
                            Toast.makeText(MainActivity.this, "목표가 설정되지 않았어요!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (target.getText().toString() != editText.getText().toString()) { // 기존의 목표와 값이 다르다면 값을 변경함.
                                String result = editText.getText().toString();
                                target.setText(result);
                                Toast.makeText(MainActivity.this, "목표를 바꿨어요!", Toast.LENGTH_SHORT).show();
                                targetChangeBox.setVisibility(View.INVISIBLE);

                            } else {
                                targetChangeBox.setVisibility(View.INVISIBLE);

                            }

                        }
                    }
                });

//        취소 버튼 눌렀을 시 onClickListener
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
//                setContentView(targetChangeBox, targetBoxFoldParams); // finish();

                    }
                });

    } // onCreate class 끝



//    목표 설정 버튼



//    목표 설정 버튼을 눌렀을 시
//    public void targetButtonClicked(View v){
//        Toast.makeText(MainActivity.this, "목표 버튼 눌림.", Toast.LENGTH_SHORT).show();
////        if (targetChangeBox.getParent() != null)
////            ((ViewGroup) targetChangeBox.getParent()).removeView(targetChangeBox);
////        builder.setView(targetChangeBox);
//        editText.setHint(target.getText().toString());
//        targetChangeBox.bringToFront();
//    }




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

    //일일 캘린더 버튼 눌림
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


    }
