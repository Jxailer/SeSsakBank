package com.example.codevalley.game;

import static com.example.codevalley.LoginActivity.mUser;
import static com.example.codevalley.LoginActivity.userID;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.LoginActivity;
import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class GameStart1 extends AppCompatActivity {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("game").child(userID);
    TextView tv_select;
    Button btn_apple, btn_mandarin, btn_banana;
    String plantType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_start1);

        tv_select = findViewById(R.id.tv_selectplant);
        btn_apple = findViewById(R.id.btn_apple);
        btn_mandarin = findViewById(R.id.btn_mandarin);
        btn_banana = findViewById(R.id.btn_banana);

        // 처음 게임에 접속했을 시 키울 나무를 선택하는 페이지
        // 사과 나무를 선택 했을 시
        btn_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("사과나무");
                Map<String, Object> plantInfo = new HashMap<>();
                plantInfo.put("plantType/type", "사과나무");
                reference.updateChildren(plantInfo);
                startActivity(new Intent(GameStart1.this, GameStart2.class));
            }
        });

        // 귤 나무를 선택 했을 시
        btn_mandarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("귤나무");
                Map<String, Object> plantInfo = new HashMap<>();
                plantInfo.put("plantType/type", "귤나무");
                reference.updateChildren(plantInfo);
                startActivity(new Intent(GameStart1.this, GameStart2.class));
            }
        });

        // 바나나 나무를 선택 했을 시
        btn_banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("바나나나무");
                Map<String, Object> plantInfo = new HashMap<>();
                plantInfo.put("plantType/type", "바나나나무");
                reference.updateChildren(plantInfo);
                startActivity(new Intent(GameStart1.this, GameStart2.class));
            }
        });
    }

}