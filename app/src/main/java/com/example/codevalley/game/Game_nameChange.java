package com.example.codevalley.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codevalley.R;

public class Game_nameChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_name_change);

        TextView tv_nameChange = (TextView) findViewById(R.id.tv_nameChange);
        EditText edt_nameChange = (EditText) findViewById(R.id.edt_nameChange);
        Button btn_alter = (Button) findViewById(R.id.btn_alter);

        btn_alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameChange = edt_nameChange.getText().toString(); // 변수에 저장된 객체에서 Text를 가져오고 String형식으로 변환 후 저장
                if (nameChange.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"이름을 입력해주세요!",Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Game_nameChange.this, PlantGame.class); // Activity사이에서 값을 전달하기 위해서는 intent를 사용한다.
                    intent.putExtra("plantname",nameChange); // intent생성시 현재 activity와 이동할 activity선언하고, putExtra메서드를 통해 키 값과 데이터를 저장
                    startActivity(intent); // Intent와 함께 다음 activity실행
                }
            }
        });
    }
}