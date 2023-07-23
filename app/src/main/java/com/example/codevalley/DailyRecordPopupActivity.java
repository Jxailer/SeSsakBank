package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DailyRecordPopupActivity extends AppCompatActivity {

    public int revised;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyrecordpopup);
    }

    public void recordCreateButtonClicked(View v){
        Toast.makeText(DailyRecordPopupActivity.this, "기록 작성 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RecordCreate.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
    }

}


