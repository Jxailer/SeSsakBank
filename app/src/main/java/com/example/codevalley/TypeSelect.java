package com.example.codevalley;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adult.adult_LoginActivity;

public class TypeSelect extends AppCompatActivity {
    Button typeChild, typeAdult, selectBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_select);

        typeChild = findViewById(R.id.typeChild);
        typeAdult = findViewById(R.id.typeAdult);
        selectBtn = findViewById(R.id.selectBtn);

        //'어린이' 선택시 색 변경
        typeChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
//                    typeChild.setOnClickListener(this);
                    if(typeAdult.isSelected()){
                        typeAdult.callOnClick();
                    }
                }
            }
        });
        //'보호자' 선택 시 색 변경
        typeAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
//                    typeAdult.setOnClickListener(this);
                    if(typeChild.isSelected()){
                        typeChild.callOnClick();
                    }
                }
            }
        });
        //'선택 완료' 버튼 눌렀을 시
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(typeChild.isSelected()){
                    Intent childSelect = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(childSelect);
                }
                else if(typeAdult.isSelected()){
                    Intent adultSelect = new Intent(getApplicationContext(), adult_LoginActivity.class);
                    startActivity(adultSelect);
                }
            }
        });
    }

    //        select.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view) {
//                switch (rg.getCheckedRadioButtonId()){
//                    case R.id.radio_kid:
//                        Toast.makeText(getApplicationContext(),"어린이타입 선택완료", Toast.LENGTH_SHORT).show();
//                        Intent intent_kidUI = new Intent(getApplicationContext(), LoginActivity.class);
//                        startActivity(intent_kidUI);
//                        break;
//                    default:
//                        Toast.makeText(getApplicationContext(),"보호자타입 선택완료", Toast.LENGTH_SHORT).show();
//                        Intent intent_adultUI = new Intent(getApplicationContext(), adult_LoginActivity.class);
//                        startActivity((intent_adultUI));
//                }
//            }
//
//        });
}