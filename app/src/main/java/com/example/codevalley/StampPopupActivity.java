package com.example.codevalley;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StampPopupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_popup);
    }

    public void closeButtonClicked(View v){
        Toast.makeText(StampPopupActivity.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
