package com.example.codevalley.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;

public class MyPageActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

//        getSupportActionBar().setTitle("< 마이 페이지");

        Button imageButton = (Button) findViewById(R.id.user_info);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });

        Button imageButton1 = (Button) findViewById(R.id.data_send);
        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CycleSet.class);
                startActivity(intent);
            }
        });

        Button imageButton2 = (Button) findViewById(R.id.del_id);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteID.class);
                startActivity(intent);
            }
        });
        Button imageButton3 = (Button) findViewById(R.id.cus_service);
        imageButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomService.class);
                startActivity(intent);
            }

        });
        Button imageButton4 = (Button) findViewById(R.id.notice);
        imageButton4.setOnClickListener(new View.OnClickListener() {

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
}
