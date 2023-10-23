package com.example.codevalley.myPage;

import static com.example.codevalley.LoginActivity.mAuth;
import static com.example.codevalley.LoginActivity.mUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.LoginActivity;
import com.example.codevalley.R;

public class LogoutPopup extends AppCompatActivity{
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoutpopup);

        Button imageButton = (Button) findViewById(R.id.yes_logout);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAuth.signOut();   //파이어베이스 인증 서비스의 로그아웃 기능
                Toast.makeText(getApplicationContext(),"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        Button imageButton1 = (Button) findViewById(R.id.no_logout);
        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });


    }
}
