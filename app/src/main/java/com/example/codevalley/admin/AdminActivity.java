package com.example.codevalley.admin;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AdminActivity extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
    ArrayList arrayDate = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String createDate = dataSnapshot.child("createDate").getValue().toString(); //"users"의 가입날짜만 읽어오기
                    String createDate1 = createDate.substring(6, 8);  // 월 데이터만 가져오기
                    //리스트에 추가
                    arrayDate.add(createDate);
                    arrayList.add(createDate1);
                }
                //출력해보기
                System.out.println(arrayDate);
                System.out.println(arrayList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
