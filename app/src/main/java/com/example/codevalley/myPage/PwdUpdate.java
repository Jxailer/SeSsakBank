package com.example.codevalley.myPage;

import static com.example.codevalley.LoginActivity.mUser;
import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.RegisterActivity.regularPW;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PwdUpdate extends AppCompatActivity {
    String old_pwd, new_pwd, password;
    EditText oldPwd, newPwd;
    Button updateBtn;
    DatabaseReference pwdRef = FirebaseDatabase.getInstance().getReference("users").child(userID);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        getSupportActionBar().setTitle("< 비밀번호 변경");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdupdate);

        oldPwd = findViewById(R.id.pwd);
        newPwd = findViewById(R.id.newPwd);
        updateBtn = findViewById(R.id.update_btn);

        //변경하기 버튼 눌렀을 시
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPwd();
            }
        });
    }

    //현재 비밀번호 일치 여부와 정규식 만족 여부 확인
    public void checkPwd(){
        pwdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                password = snapshot.child("password").getValue(String.class);
                old_pwd = oldPwd.getText().toString();
                new_pwd = newPwd.getText().toString();

                if(oldPwd != null && password.equals(old_pwd)){
                    if(regularPW(new_pwd)) {
                        updatePwd();
                    }else{
                        newPwd.setError("비밀번호는 영문과 특수문자를 포함하여 8자 이상이어야 합니다.");
                    }
                }else{
                    oldPwd.setError("비밀번호가 일치하지 않습니다");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //최종적으로 비밀번호 업데이트
    public void updatePwd(){
        String new_pwd = newPwd.getText().toString();
        //파이어베이스 authentication 비번 업데이트
        mUser.updatePassword(new_pwd);
        //파이어베이스 리얼타임 비밀번호 업데이트
        Map<String, Object> pwUpdates = new HashMap<>();
        pwUpdates.put("password", new_pwd);
        pwdRef.updateChildren(pwUpdates);

        oldPwd.setError(null);
        newPwd.setError(null);

        Toast.makeText(getApplicationContext(), "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), UserInfo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

//        pwcheck.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (editable.length()>8){
//                    pwcheckbutton.setEnabled(true);
//                    pwcheckbutton.setBackgroundColor(Color.rgb(136,189,165));
//
//
//                } else {
//                    pwcheckbutton.setEnabled(false);
//                    pwcheckbutton.setBackgroundColor(Color.rgb(199,199,199));
//                }
//
//            }
//        });
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
