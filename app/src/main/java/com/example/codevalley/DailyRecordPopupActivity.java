package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DailyRecordPopupActivity extends AppCompatActivity {

    private Button recordCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyrecordpopup);

        recordCreate = (Button)findViewById(R.id.recordCreateButton);

        recordCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DailyRecordPopupActivity.this, RecordCreate.class);
                startActivity(intent);
            }
        });



    }

    public void recordCreateButtonClicked(View v){
        Toast.makeText(DailyRecordPopupActivity.this, "기록 작성 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RecordCreate.class);
    }

}


