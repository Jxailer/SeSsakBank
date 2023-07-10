package com.example.codevalley.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;

public class GameStart2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start2);

        TextView tv_plantName = (TextView) findViewById(R.id.tv_plantName);
        EditText edt_plantName = (EditText) findViewById(R.id.edt_plantName);
        Button btn_create = (Button) findViewById(R.id.btn_create);


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plantName = edt_plantName.getText().toString(); // 변수에 저장된 객체에서 Text를 가져오고 String형식으로 변환 후 저장
                if (plantName.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"이름을 정해주세요!",Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(GameStart2.this, PlantGame.class); // Activity사이에서 값을 전달하기 위해서는 intent를 사용한다.
                    intent.putExtra("plantname",plantName); // intent생성시 현재 activity와 이동할 activity선언하고, putExtra메서드를 통해 키 값과 데이터를 저장
                    startActivity(intent); // Intent와 함께 다음 activity실행
                }
            }
        });
    }
}