package com.example.codevalley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText signupUsername, signupPassword, signupName, signupBirth, signupPhone;
    TextView loginRedirectText;
    Button signupButton, usernameDuplicate;
    DatabaseReference reference;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupName = findViewById(R.id.signup_name);
        signupBirth = findViewById(R.id.signup_birth);
        signupPhone = findViewById(R.id.signup_phone);
        signupButton = findViewById(R.id.signup_button);
        usernameDuplicate = findViewById(R.id.username_duplicate);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        //중복확인 버튼을 눌렀을 시
        usernameDuplicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRegister()){
                    //회원가입 버튼 눌렀을 시
                    signupButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (essentialInfo() & regularPW()){
                                addUserInfo();
                            }
                        }
                    });
                }
            }
        });

        //이미 유저이신가요? login을 눌렀을 시
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //필수 입력 사항 미기재시
    public Boolean essentialInfo(){
        String id = signupUsername.getText().toString();
        String pw = signupPassword.getText().toString();
        String name = signupName.getText().toString();
        String bir = signupBirth.getText().toString();
        String phone = signupPhone.getText().toString();

        if(id.isEmpty()) {
            signupUsername.setError("아이디는 필수사항입니다");
            return false;
        } else if(pw.isEmpty()) {
            signupPassword.setError("비밀번호는 필수사항입니다");
            return false;
        } else if(name.isEmpty()) {
            signupName.setError("이름은 필수사항입니다");
            return false;
        } else if(bir.isEmpty()) {
            signupBirth.setError("생년월일은 필수사항입니다");
            return false;
        } else if(phone.isEmpty()) {
            signupPhone.setError("핸드폰번호는 필수사항입니다");
            return false;
        } else {
            signupUsername.setError(null);
            signupPassword.setError(null);
            signupName.setError(null);
            signupBirth.setError(null);
            signupPhone.setError(null);
            return true;
        }
    }

    //아이디 중복검사
    public boolean checkRegister(){
        String username = signupUsername.getText().toString().trim();

        reference = FirebaseDatabase.getInstance().getReference("users");
        Query duplicateDatabase = reference.orderByChild("username").equalTo(username);

        final boolean[] result = {true};
        duplicateDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    signupUsername.setError("이미 존재하는 아이디입니다!");
                    signupUsername.setText(null);
                    result[0] = false;
                } else {
                    signupUsername.setError("사용가능한 아이디입니다!");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return result[0];
    }

    //사용자 정보 파이어베이스에 저장
    public void addUserInfo(){
        String id = signupUsername.getText().toString();
        String pw = signupPassword.getText().toString();
        String name = signupName.getText().toString();
        String birth = signupBirth.getText().toString();
        String phone = signupPhone.getText().toString();

        mAuth.createUserWithEmailAndPassword(id, pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    HelperClass helperClass = new HelperClass(pw, name, birth, phone);
                    reference.child(id.replace(".", ",")).setValue(helperClass);
                    Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //비밀번호 정규식(영문, 특수문자 8~15자)
    public boolean regularPW() {
        String password = signupPassword.getText().toString();
        String rgPattern = "^(?=.*[A-Za-z])(?=.*[!@#$%^&?])[A-Za-z!@#$%^&?]{8,15}$";
        Pattern pattern = Pattern.compile(rgPattern);
        Matcher matcher = pattern.matcher(password);

        if(matcher.find()){
            return true;
        }
        else{
            signupPassword.setError("비밀번호는 영문과 특수문자를 포함하여 8자 이상이어야 합니다.");
            return false;
        }
    }


}