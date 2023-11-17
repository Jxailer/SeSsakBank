package com.example.codevalley;

import static com.example.codevalley.RegisterActivity.ur_stamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codevalley.admin.AdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    CheckBox autoLoginBox;
    TextView signupRedirectText;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    public static FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    public static FirebaseUser mUser;
    public static String userID;
    DatabaseReference stampRef = FirebaseDatabase.getInstance().getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        autoLoginBox = findViewById(R.id.autoLogin_box);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton = findViewById(R.id.login_button);

        String adminID = "admin";
        String adminPW = "admin1234";

        mAuth = FirebaseAuth.getInstance();

        //로그인 버튼 누를 시
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUsername() & validatePassword()){
                    if(loginUsername.getText().toString().equals(adminID) && loginPassword.getText().toString().equals(adminPW)){
                        loginAdmin();
                    }
                    else{
                        loginUser(loginUsername.getText().toString(), loginPassword.getText().toString());
                    }
                }
            }
        });

        //회원가입 버튼 누를 시
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //로그인 저장 상태 리스너
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                mUser = firebaseAuth.getCurrentUser();
//                if(mUser != null){
//                    userID = mUser.getEmail().replace(".", ",");
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        };
    }

    public void onStart() { super.onStart(); }

    // 아이디칸이 비어있는 경우
    public Boolean validateUsername(){
        String val = loginUsername.getText().toString();
        if (val.isEmpty()){
            loginUsername.setError("아이디를 입력하세요!");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    // 비밀번호칸이 비어있는 경우
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("비밀번호를 입력하세요!");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    //로그인 실행
    public void loginUser(String username, String password){
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mUser = mAuth.getCurrentUser();
                            goMainPage(mUser);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //관리자 계정으로 로그인 시
    public void loginAdmin(){
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "당신 관리자 맞아요??????", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void goMainPage(FirebaseUser user){
        if(user != null){
            //typeSelect에서 체크박스 값 사용하기 위해 저장
            sharedPref = getSharedPreferences("autoLoginValue", Context.MODE_PRIVATE);
            editor = sharedPref.edit();
            //자동 로그인에 체크할 시
            if(autoLoginBox.isChecked()){
                editor.putInt("checkValue", 1);
                editor.commit();
            }else{
                editor.putInt("checkValue", 0);
                editor.commit();
            }
            userID = user.getEmail().replace(".", ",");
            //도장 개수 불러오기(앱 시작할 때 초기화하기 위해...)
            stampRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ur_stamp = snapshot.child(userID).child("stamp").getValue(Integer.class);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}