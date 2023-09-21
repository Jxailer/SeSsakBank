package com.example.adult;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.HelperClass;
import com.example.codevalley.LoginActivity;
import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class adult_LoginActivity extends AppCompatActivity {
    EditText loginUsername;
    public static String childID;
    EditText loginNickname;
    EditText loginName;
    Button loginButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_login);

        loginUsername = findViewById(R.id.login_username);
        loginNickname = findViewById(R.id.login_nickname);
        loginName = findViewById(R.id.login_name);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validateName()) {

                } else {
                    checkUsername();
                }
            }
        });
    }

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

    public Boolean validateName(){
        String val1 = loginNickname.getText().toString();
        String val2 = loginName.getText().toString();
        if (val1.isEmpty()){
            loginNickname.setError("닉네임을 입력하세요!");
            return false;
        } else if(val2.isEmpty()){
            loginName.setError("이름을 입력하세요!");
            return false;
        }
        else {
            loginNickname.setError(null);
            return true;
        }
    }

    public void checkUsername(){
        String kidUsername = loginUsername.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String usernameFromDB = snapshot.child(kidUsername.replace(".", ",")).child("username").getValue(String.class);
                if(Objects.equals(kidUsername, usernameFromDB)){
                    loginUsername.setError(null);
                    addAdultInfo();
                }else{
                    loginUsername.setError("유저가 존재하지 않아요!");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addAdultInfo(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("adults");

        childID = loginUsername.getText().toString().replace(".", ",");
        String adult_nickname = loginNickname.getText().toString();
        String adult_name = loginName.getText().toString();

        HelperClass helperClass = new HelperClass(adult_nickname, adult_name);
        reference.child(childID).setValue(helperClass);

        Intent intent = new Intent(adult_LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
