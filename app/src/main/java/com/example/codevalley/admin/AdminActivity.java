package com.example.codevalley.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
    public static ArrayList arrayDate = new ArrayList<>();;
    public static ArrayList arrayAnalytics = new ArrayList<>();;
    String createDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayDate.clear();
                arrayAnalytics.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String create_date = dataSnapshot.child("createDate").getValue().toString(); //"users"의 가입 날짜만 읽어오기
                    if(create_date.startsWith("0", 6)){ //만약 6번째에 0이 들어가면 -> 01, 02, ... 이면
                        createDate = create_date.substring(7, 8); //맨 뒷자리만 남기기 -> 1, 2, ...
                    }
                    else{
                        createDate = create_date.substring(6, 8); //뒤에서 두 자리 남기기 -> 11, 12, ...
                    }
                    arrayDate.add(createDate);  //리스트에 추가
                }

                //통계를 위해 1일부터 현재 일자에 대한 가입자 수 배열 생성
                Integer toDay = Integer.valueOf(getTodayDate()); // 오늘 일자 구하기
                for(Integer i=0; i<=toDay; i++){  // 0일부터 오늘까지
                    Integer cnt=0;
                    for(Integer j=0; j< arrayDate.size(); j++){  //파베에서 읽어온 가입 날짜를 저장한 배열 0번째부터 끝까지
                        Integer date = Integer.valueOf((String) arrayDate.get(j));  //배열에 저장된 가입 날짜는 문자이므로
                        if(i == date){  //날짜와 '가입날짜'가 같으면
                            cnt += 1;
                        }
                    }
                    arrayAnalytics.add(cnt);   //리스트에 추가
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getTodayDate(){
        Long today = System.currentTimeMillis();
        Date todayDate = new Date(today);
        SimpleDateFormat sd = new SimpleDateFormat("d");
        return sd.format(todayDate);
    }
}
