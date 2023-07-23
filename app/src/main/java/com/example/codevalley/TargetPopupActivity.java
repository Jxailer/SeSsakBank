package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TargetPopupActivity extends AppCompatActivity {

    TextView targetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targetpopup);

        // UI 객체 생성
        targetText = (EditText)findViewById(R.id.targetBox);

        // 데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        targetText.setText(data);
    }


    public void saveButtonClicked(View v){
        Toast.makeText(TargetPopupActivity.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);
        finish();
        }
    public void cancelButtonClicked(View v){
        Toast.makeText(TargetPopupActivity.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
        finish();
    }
}


