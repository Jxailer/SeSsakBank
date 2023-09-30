package com.example.myapplication1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SixthActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        getSupportActionBar().setTitle("< 회원탈퇴");

        Button ebt;
        ebt = findViewById(R.id.탈퇴버튼);
        ebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"탈퇴되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        CheckBox echeck = findViewById(R.id.삭제약관);
        echeck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    ebt.setBackgroundColor(Color.rgb(136,189,165));
                    ebt.setEnabled(true);
                }else {
                    ebt.setBackgroundColor(Color.rgb(199,199,199));
                    ebt.setEnabled(false);
                }
            }
        });

    }

}
