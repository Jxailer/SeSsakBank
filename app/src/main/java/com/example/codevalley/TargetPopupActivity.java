package com.example.codevalley;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TargetPopupActivity extends AppCompatActivity {

    public static Context context_TargetPopup;

    public EditText editText;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targetpopup);

//        기존에 설정된 목표 값을 팝업창 힌트로 불러오기
        editText = findViewById((R.id.targetBox));

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        editText.setText(data);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //        Toast.makeText(TargetPopupActivity.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TargetPopupActivity.this, MainActivity.class);
                editText = findViewById((R.id.targetBox));

                if (editText.getText().length() == 0) { // 목표설정란이 비어있는지 확인하기.
                    Toast.makeText(TargetPopupActivity.this, "목표가 설정되지 않았어요!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if( ((MainActivity)MainActivity.context_Main).target.getText().toString() != editText.getText().toString()){ // 기존의 목표와 값이 다르다면 값을 변경함.
                        String result = editText.getText().toString();
                        ((MainActivity)MainActivity.context_Main).target.setText(result);
//                        ((MainActivity)MainActivity.context_Main).targetChange = 1;
                        finish();
                    }

                    else {
                        startActivity(intent);
                        finish();
                    }

                    startActivity(intent);
                    finish();
                }
            }
        });


    }




    public void cancelButtonClicked(View v){
        Toast.makeText(TargetPopupActivity.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
        finish();
    }
}



