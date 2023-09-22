package com.example.codevalley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.wishShop.Child_WishShop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    static FirebaseAuth.AuthStateListener mAuthListener;
    static FirebaseUser mUser;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton = findViewById(R.id.login_button);

        //로그인 버튼 누를 시
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!validateUsername() | !validatePassword()) {
//
//                } else {
////                    checkUsername();
//                }
                if(validateUsername() & validatePassword()){
                    loginUser(loginUsername.getText().toString(), loginPassword.getText().toString());
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

        //로그인 상태 저장 리스너
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null) {
                    userID = mUser.getEmail().replace(".", ",");
//                    Intent intent = new Intent(LoginActivity.this, Child_WishShop.class);
//                    startActivity(intent);
                    finish();
                } else {
                }
            }
        };
    }

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
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            mAuth.addAuthStateListener(mAuthListener);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }




//    public void checkUsername(){
//        String userUsername = loginUsername.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
//
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    loginUsername.setError(null);
//                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
//
//                    if (!Objects.equals(usernameFromDB, userUsername)) {
//                        checkPassword();
//                    }
//                }else {
//                    loginUsername.setError("유저가 존재하지 않아요!");
//                    loginUsername.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    private void checkPassword() {
//        String userPassword = loginPassword.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkPwDatabase = reference.orderByChild("password").equalTo(userPassword);
//
//        checkPwDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    loginPassword.setError(null);
//                    String passwordFromDB = snapshot.child(userPassword).child("password").getValue(String.class);
//
//                    if(!Objects.equals(passwordFromDB, userPassword)) {
//                        loginUsername.setError(null);
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//                }else {
//                    loginPassword.setError("비밀번호가 일치하지 않아요!");
//                    loginPassword.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

}