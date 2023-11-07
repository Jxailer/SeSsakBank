package com.example.codevalley.game;

import static com.example.codevalley.LoginActivity.userID;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class GameStart1 extends AppCompatActivity {
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start1);

        TextView tv_select = (TextView) findViewById(R.id.tv_selectplant);
        Button btn_apple = (Button) findViewById(R.id.btn_apple);
        Button btn_mandarin = (Button) findViewById(R.id.btn_mandarin);
        Button btn_banana = (Button) findViewById(R.id.btn_banana);

        // 처음 게임에 접속했을 시 키울 나무를 선택하는 페이지
        // 사과 나무를 선택 했을 시
        btn_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("사과나무");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("game").child(userID);
                Map<String, Object> plantInfo = new HashMap<>();
                plantInfo.put("plantType/type", "사과나무");
                ref.updateChildren(plantInfo);
                startActivity(new Intent(GameStart1.this, GameStart2.class)
                        .setAction(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_LAUNCHER)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        // 귤 나무를 선택 했을 시
        btn_mandarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("귤나무");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("game").child(userID);
                Map<String, Object> plantInfo = new HashMap<>();
                plantInfo.put("plantType/type", "귤나무");
                ref.updateChildren(plantInfo);
                startActivity(new Intent(GameStart1.this, GameStart2.class)
                        .setAction(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_LAUNCHER)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        // 바나나 나무를 선택 했을 시
        btn_banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("바나나나무");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("game").child(userID);
                Map<String, Object> plantInfo = new HashMap<>();
                plantInfo.put("plantType/type", "바나나나무");
                ref.updateChildren(plantInfo);
                startActivity(new Intent(GameStart1.this, GameStart2.class)
                        .setAction(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_LAUNCHER)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }
}