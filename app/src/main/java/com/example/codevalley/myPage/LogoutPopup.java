package com.example.codevalley.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;

public class LogoutPopup extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoutpopup);

        Button imageButton = (Button) findViewById(R.id.로그아웃함);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        Button imageButton1 = (Button) findViewById(R.id.로그아웃안함);
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
