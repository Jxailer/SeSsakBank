package com.example.codevalley;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class StampPopupActivity extends AppCompatActivity {

    ViewGroup stampIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_popup);

        stampIcon = findViewById(R.id.stampIcon);
        Button storeButton = findViewById(R.id.storeButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        stampIcon.bringToFront();

        // 상점가기 버튼 클릭시
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StampPopupActivity.this, "상점 가기 버튼 눌림.", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StampPopupActivity.this, "닫기 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }


}
