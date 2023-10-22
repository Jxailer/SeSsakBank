package com.example.codevalley.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;

import java.util.Objects;

public class UserInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        getSupportActionBar().setTitle("< 계정정보");

        Button imageButton = (Button) findViewById(R.id.비번변경);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PwdUpdate.class);
                startActivity(intent);
            }
        });

        Button imageButton2 = (Button) findViewById(R.id.버전정보);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VersionInfo.class);
                startActivity(intent);
            }
        });

        Button imageButton3 = (Button) findViewById(R.id.로그아웃);
        imageButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogoutPopup.class);
                startActivity(intent);
            }
        });

    }
}
