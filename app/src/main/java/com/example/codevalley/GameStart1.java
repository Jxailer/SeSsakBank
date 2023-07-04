package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameStart1 extends AppCompatActivity {

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
                Intent intent = new Intent(GameStart1.this, GameStart2.class);
                startActivity(intent);
            }
        });

        // 귤 나무를 선택 했을 시
        btn_mandarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameStart1.this, GameStart2.class);
                startActivity(intent);
            }
        });

        // 바나나 나무를 선택 했을 시
        btn_banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameStart1.this, GameStart2.class);
                startActivity(intent);
            }
        });
    }
}