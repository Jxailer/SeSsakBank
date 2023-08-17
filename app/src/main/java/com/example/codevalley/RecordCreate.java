package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class RecordCreate extends AppCompatActivity {

    Button saveButton;

    Button cancelButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_create);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

//        저장버튼 눌림
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecordCreate.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // saveButton onClick event listener 끝

        //취소버튼 눌림
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecordCreate.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // saveButton onClick event listener 끝


    } // onCreate 클래스 끝.




} // 메인 클래스 끝.
