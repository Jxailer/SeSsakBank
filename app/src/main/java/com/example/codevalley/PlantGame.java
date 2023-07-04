package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class PlantGame extends AppCompatActivity {
    private Button button7;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantgame);
        setTitle("bar");

        TextView tv_name = (TextView) findViewById(R.id.textView);
        button7 = (Button) findViewById(R.id.button7);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // GameStar2에서 받은 식물 이름 가져와서 출력
        Intent intent = getIntent(); // 넘어온 값을 받기 위해 intent객체를 생성하지만 getIntent()를 통해 넘어온 intent객체를 받아온다.
        Bundle bundle = intent.getExtras(); // Bundle을 통해 extra들을 모두 가져온다
        String plantname = bundle.getString("plantname"); // 키 값을 통해서 extras에 있는 값들을 얻는다.

        tv_name.setText(plantname); // PlantGame.xml에 있는 객체에 Text를 설정

        // 아이템 버튼 클릭 했을 시
        button7.setOnClickListener(new View.OnClickListener() {
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