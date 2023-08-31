package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DailyRecordManagePopup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyrecordpopup);
    }

    public void recordCreateButtonClicked(View v){
        Toast.makeText(DailyRecordManagePopup.this, "기록 작성 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SpentRecordCreate.class);
    }

}


