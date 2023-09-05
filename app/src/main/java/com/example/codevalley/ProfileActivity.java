package com.example.codevalley;

import static com.example.codevalley.LoginActivity.mUser;  //얘는 로그인한 상태인지 알려주는 변수  로그인해있으면 null 값 아님 로그인 안하면 null값
import static com.example.codevalley.LoginActivity.userID;  //이렇게 임포트하면 쓸 수 있어   로그인한 사용자의 아이디(이메일)들어있는 변수 -> 리얼타임에서 쓸 수 있음

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends AppCompatActivity {
    Button mBtn;
    TextView mProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mProfile = findViewById(R.id.ur_profile);
        mBtn = findViewById(R.id.profile_btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUser != null){
                    mResult(true); //로그인 상태
                }
                else {
                    mResult(false);  //로그인 상태 아님
                }
            }
        });
    }

    public void mResult(boolean result) {
        if (result) {
            Toast.makeText(ProfileActivity.this, "너구나", Toast.LENGTH_SHORT).show();
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("users");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String urData = snapshot.child(userID).child("username").getValue(String.class);
                    mProfile.setText(urData);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            Toast.makeText(ProfileActivity.this, "누구세요", Toast.LENGTH_SHORT).show();
            mProfile.setText("로그인하고오세요");
        }
    }
}
