package com.example.codevalley.myPage;

import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.LoginActivity.mUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.LoginActivity;
import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteID extends AppCompatActivity {
    TextView userName;
    Button ebt;
    CheckBox echeck;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(userID);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteid);

        userName = findViewById(R.id.user_name);
        echeck = findViewById(R.id.del_agree);
        ebt = findViewById(R.id.deleteBtn);

//        getSupportActionBar().setTitle("< 회원탈퇴");

        //회원 이름 보여주기
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                userName.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //탈퇴 약관 동의 체크 여부
        echeck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    ebt.setBackgroundColor(Color.rgb(136,189,165));
                    ebt.setEnabled(true);
                }else {
                    ebt.setBackgroundColor(Color.rgb(199,199,199));
                    ebt.setEnabled(false);
                }
            }
        });

        //탈퇴 버튼 눌렀을 시
        ebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteID();

                Toast.makeText(getApplicationContext(),"탈퇴되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //사용자 데이터 삭제
    public void deleteID(){
        mUser.delete();
        ref.removeValue();
    }
}
