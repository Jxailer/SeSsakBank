package com.example.adult.statistics;

import static com.example.adult.adult_LoginActivity.childID;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ageStatsActivity extends AppCompatActivity {

    //나이대별 통계
    DatabaseReference cycleRef = FirebaseDatabase.getInstance().getReference("cycle").child(childID);
    DatabaseReference ageRef = FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference moneyRef = FirebaseDatabase.getInstance().getReference("recordManage");
    ArrayList arrayAge = new ArrayList<>();
    ArrayList arrayAllowance = new ArrayList<>();
    String age;
    String childAge;
    int allowance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agestats);

        ageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                moneyRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String categoryInfo = dataSnapshot.child("category").getValue().toString().trim();
                            if(categoryInfo == "21"){
                                Integer money = Integer.parseInt(dataSnapshot.child("moneyAmount").getValue().toString().trim());
                                int Amoney = 0;
                                Amoney += money;
                                arrayAllowance.add(Amoney);
                            }
                            else{
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                arrayAge.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    String childAge = dataSnapshot.child(childID).child("birth").getValue().toString().substring(0,2);
                    String ageSet = dataSnapshot.child("birth").getValue().toString();
                    age = ageSet.substring(0, 2);
                    arrayAge.add(age);
                }

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    String childAge = dataSnapshot.child(childID).child("birth").getValue().toString().substring(0,2);
                    String ageSet = dataSnapshot.child("birth").getValue().toString();
                    age = ageSet.substring(0, 2);
                    arrayAge.add(age);
                }

//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    String create_date = dataSnapshot.child("createDate").getValue().toString(); //"users"의 가입 날짜만 읽어오기
//                    if(create_date.startsWith("0", 6)){ //만약 6번째에 0이 들어가면 -> 01, 02, ... 이면
//                        createDay = create_date.substring(7, 8); //맨 뒷자리만 남기기 -> 1, 2, ...
//                    }
//                    else{
//                        createDay = create_date.substring(6, 8); //뒤에서 두 자리 남기기 -> 11, 12, ...
//                    }
//                    arrayDay.add(createDay);  // -> 사용자들의 가입 날짜만 저장됨 -> (2일에 아무도 가입하지 않았으면 2는 들어가지 않음)
//                }

                //통계를 위해 1일부터 현재 일자에 대한 가입자 수 배열 생성
//                Integer toDay = Integer.valueOf(getTodayDate()); // 오늘 일자 구하기
//                for(Integer i=0; i<=toDay; i++){  // 0일부터 오늘까지
//                    Integer cnt=0;
//                    for(Integer j=0; j< arrayDate.size(); j++){  //파베에서 읽어온 가입 날짜를 저장한 배열 0번째부터 끝까지
//                        Integer date = Integer.valueOf((String) arrayDate.get(j));  //배열에 저장된 가입 날짜는 문자이므로
//                        if(i == date){  //날짜와 '가입날짜'가 같으면
//                            cnt += 1;
//                        }
//                    }
//                    arrayAnalytics.add(cnt);   //리스트에 추가
//                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        moneyRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrayAllowance.clear();
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    int pmInfo = Integer.parseInt(dataSnapshot.child("pm").getValue().toString().trim());
//                    if(pmInfo == 1){
//                        Integer money = Integer.parseInt(dataSnapshot.child("moneyAmount").getValue().toString().trim());
//                        pMoney += money;
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//
//        if(age == dataSnapshot.child(childID).child("birth").getValue().toString().substring(0,2)){
//            int pmInfo = Integer.parseInt(dataSnapshot.child("recordManage").child("pm").getValue().toString());
//            if(pmInfo == 1)
//                allowance = Integer.parseInt(dataSnapshot.child("recordManage").child("moneyAmount").getValue().toString());
//            arrayAgeStats.add(allowance);
//        }
//        else{
//        }
//        System.out.println(arrayAgeStats);
  }



//    public String getTodayDate(){
//        Long today = System.currentTimeMillis();
//        Date todayDate = new Date(today);
//        SimpleDateFormat sd = new SimpleDateFormat("d");
//        return sd.format(todayDate);
//    }
//    }
}