package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        getSupportActionBar().setTitle("< 비밀번호 변경");

        Button button = (Button) findViewById(R.id.비번확인버튼);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"비밀번호가 변경되었습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        EditText pwcheck = (EditText) findViewById(R.id.새비번입력란);
        EditText pwrecheck = (EditText) findViewById(R.id.새비번확인);
        Button pwcheckbutton = (Button) findViewById(R.id.비번확인버튼);

        pwrecheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pwcheck.length() > 5) {
                    if (pwcheck.getText().toString().equals(pwrecheck.getText().toString())){
                        pwcheckbutton.setVisibility(View.VISIBLE);
                        pwcheckbutton.setEnabled(true);
                    } else {
                        pwrecheck.setError("비밀번호가 일치하지 않습니다.");
                        pwcheckbutton.setVisibility(View.GONE);
                        pwcheckbutton.setEnabled(false);
                    }
                } else {
                    pwcheck.setError("6글자 이상 입력해주세요.");
                    pwcheckbutton.setVisibility(View.GONE);
                    pwcheckbutton.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

}
