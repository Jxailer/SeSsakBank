package com.example.codevalley.myPage;

import static com.example.codevalley.LoginActivity.userID;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserInfo extends AppCompatActivity {
//    DatabaseReference reference = FirebaseDatabase.getInstance().getReference(userID);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

//        getSupportActionBar().setTitle("< 계정정보");



        LinearLayout imageButton = (LinearLayout) findViewById(R.id.pwd_update);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PwdUpdate.class);
                startActivity(intent);
            }
        });

        LinearLayout imageButton2 = (LinearLayout) findViewById(R.id.ver_info);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VersionInfo.class);
                startActivity(intent);
            }
        });

        LinearLayout imageButton3 = (LinearLayout) findViewById(R.id.logout);
        imageButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogoutPopup.class);
                startActivity(intent);
            }
        });

    }
}
