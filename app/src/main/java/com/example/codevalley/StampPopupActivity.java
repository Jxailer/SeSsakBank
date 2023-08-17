package com.example.codevalley;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

        stampIcon.bringToFront();



    }


}
