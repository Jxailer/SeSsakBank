package com.example.codevalley.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;


public class PlantGame extends AppCompatActivity {
    private Button btn_fertilizer;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantgame);
        setTitle("bar");

        TextView tv_name = (TextView) findViewById(R.id.textView);
        Button btn_nameChange = (Button) findViewById(R.id.btn_nameChange);
        btn_fertilizer = (Button) findViewById(R.id.btn_fertilizer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // GameStar2에서 받은 식물 이름 가져와서 출력
        Intent intent = getIntent(); // 넘어온 값을 받기 위해 intent객체를 생성하지만 getIntent()를 통해 넘어온 intent객체를 받아온다.
        Bundle bundle = intent.getExtras(); // Bundle을 통해 extra들을 모두 가져온다
        String plantname = bundle.getString("plantname"); // 키 값을 통해서 extras에 있는 값들을 얻는다.

        tv_name.setText(plantname); // PlantGame.xml에 있는 객체에 Text를 설정

        // 이름변경 버튼 눌렀을 시
        btn_nameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlantGame.this, Game_nameChange.class);
                startActivity(intent);
            }
        });

        // 아이템 버튼 클릭 했을 시
        btn_fertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int num = 0;
                    num = num + 20;
                    if(num < 0 || num > 100){
                        Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressBar.setProgress(num);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"잘못된 형식",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}