package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TargetPopupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targetpopup);
    }


    public void saveButtonClicked(View v){
        Toast.makeText(TargetPopupActivity.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
        finish();
        }
    public void cancelButtonClicked(View v){
        Toast.makeText(TargetPopupActivity.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
        finish();
    }
}


