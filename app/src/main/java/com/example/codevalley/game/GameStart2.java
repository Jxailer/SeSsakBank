package com.example.codevalley.game;

import static com.example.codevalley.LoginActivity.userID;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class GameStart2 extends AppCompatActivity {

    SharedPreferences gShared;
    SharedPreferences.Editor gEditor;

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
                Integer level = 1;
                Integer progress_num = 0;
//                Integer gameCheck = 1; // gamestart1번만 실행하기 위해 옆에 이 코드 추가

                if (plantName.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"이름을 정해주세요!",Toast.LENGTH_LONG).show();
                } else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("game").child(userID);
                    Map<String, Object> plantNameInfo = new HashMap<>();
                    plantNameInfo.put("plantName", plantName);
                    ref.updateChildren(plantNameInfo);

                    Map<String, Object> plantLevelInfo = new HashMap<>();
                    plantLevelInfo.put("plantType/level", level);
                    ref.updateChildren(plantLevelInfo);

                    Map<String, Object> plantProgressInfo = new HashMap<>();
                    plantProgressInfo.put("progress", progress_num);
                    ref.updateChildren(plantProgressInfo);

//                    Map<String, Object> gameCheckInfo = new HashMap<>(); // gamestart1번만 실행하기 위해 옆에 이 코드 추가
//                    gameCheckInfo.put("gameCheck", gameCheck);
//                    ref.updateChildren(gameCheckInfo);



//                    Intent intent = new Intent(GameStart2.this, PlantGame.class); // Activity사이에서 값을 전달하기 위해서는 intent를 사용한다.
////                    intent.putExtra("plantname",plantName); // intent생성시 현재 activity와 이동할 activity선언하고, putExtra메서드를 통해 키 값과 데이터를 저장
//                    startActivity(intent); // Intent와 함께 다음 activity실행

                    goGamePage(plantName);
                }
            }
        });
    }

    public void onStart() { super.onStart(); }

    public void goGamePage(String plantName){
        if(plantName != null){
            gShared = getSharedPreferences("gameValue", Context.MODE_PRIVATE);
            gEditor = gShared.edit();
            //식물이름 쓰고 생성하기 버튼 누를 시
            gEditor.putInt("gameCheck", 1);
            gEditor.commit();

            startActivity(new Intent(this, PlantGame.class));
            finish();
        }
    }
}