package com.example.codevalley.myPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PwdUpdate extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdupdate);

        getSupportActionBar().setTitle("< 비밀번호 변경");

        Button button = (Button) findViewById(R.id.비번확인버튼);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"비밀번호가 변경되었습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        EditText pwcheck = (EditText) findViewById(R.id.새비번입력란);
        Button pwcheckbutton = (Button) findViewById(R.id.비번확인버튼);
        String repassword = pwcheck.getText().toString();
        String rgPattern = "^(?=.*[A-Za-z])(?=.*[!@#$%^&?])[A-Za-z!@#$%^&?]{8,15}$";



        pwcheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()>8){

                    pwcheckbutton.setEnabled(true);
                    pwcheckbutton.setBackgroundColor(Color.rgb(136,189,165));


                } else {
                    pwcheckbutton.setEnabled(false);
                    pwcheckbutton.setBackgroundColor(Color.rgb(199,199,199));
                }

            }
        });
//                    <---비번 변경 & 확인 부분----->
//        public boolean regularPW() {
//            String password = pwcheck.getText().toString();
//            String repassword = pwrecheck.getText().toString();
//            String rgPattern = "^(?=.*[A-Za-z])(?=.*[!@#$%^&?])[A-Za-z!@#$%^&?]{8,15}$";
//            Pattern pattern = Pattern.compile(rgPattern);
//            Matcher matcher = pattern.matcher(password);
//
//            if(matcher.find()){
//                if(password.equals(repassword)){
//                    pwcheckbutton.setVisibility(View.VISIBLE);
//                    pwcheckbutton.setEnabled(true);
//                }
//
//            }
//            else{
//                pwcheck.setError("비밀번호는 영문과 특수문자를 포함하여 8자 이상이어야 합니다.");
//                pwcheckbutton.setVisibility(View.GONE);
//                pwcheckbutton.setEnabled(false);
//
//            }
//        }

//        if (editable.length() >= 8) {
//            button.setClickable(true);
//            button.setBackgroundColor(Color.GREEN);
//        } else {
//            button.setClickable(false);
//            button.setBackgroundColor(Color.GRAY);
//        }

//        if (editable.length() >= 8){
//            if (password.equals(rgPattern)) {
//                pwcheckbutton.setClickable(true);
//                pwcheckbutton.setBackgroundColor(Color.GREEN);
//            } else {
//                pwcheck.setError("비밀번호는 영문과 특수문자를 포함하여야 합니다.");
//                pwcheckbutton.setClickable(false);
//                pwcheckbutton.setBackgroundColor(Color.GRAY);
//            }
//
//        } else {
//            pwcheck.setError("비밀번호는 8자 이상이어야 합니다.");
//            pwcheckbutton.setClickable(false);
//            pwcheckbutton.setBackgroundColor(Color.GRAY);
//        }


    }
}
