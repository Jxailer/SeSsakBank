package com.example.codevalley;

import static com.example.codevalley.LoginActivity.mAuth;
import static com.example.codevalley.LoginActivity.mUser;
import static com.example.codevalley.LoginActivity.userID;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adult.adult_LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TypeSelect extends AppCompatActivity {
    Button typeChild, typeAdult, selectBtn;
    SharedPreferences mShared;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_select);

        typeChild = findViewById(R.id.typeChild);
        typeAdult = findViewById(R.id.typeAdult);
        selectBtn = findViewById(R.id.selectBtn);

        mAuth  = FirebaseAuth.getInstance();
        mShared = getSharedPreferences("autoLoginValue", Context.MODE_PRIVATE);
        Integer checkValue = mShared.getInt("checkValue", 0);

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
                    mUser = mAuth.getCurrentUser();
                    goMainPage(mUser, checkValue);
                }
                else if(typeAdult.isSelected()){
                    Intent adultSelect = new Intent(getApplicationContext(), adult_LoginActivity.class);
                    startActivity(adultSelect);
                }
            }
        });
    }

    public void goMainPage(FirebaseUser firebaseUser, Integer value) {
        if (firebaseUser != null) {
            if(value == 1){
                userID = firebaseUser.getEmail().replace(".", ",");
                startActivity(new Intent(this, MainActivity.class));
            }
            else if(value == 0){
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
        else if(firebaseUser == null){
            startActivity(new Intent(this, LoginActivity.class));
        }
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