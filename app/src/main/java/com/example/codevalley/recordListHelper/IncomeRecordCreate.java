package com.example.codevalley.recordListHelper;

import static com.example.adult.adult_LoginActivity.nickName;
import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.RegisterActivity.ur_stamp;
import static com.example.calendar.CalendarAdapter.year_info;
import static com.example.calendar.CalendarAdapter.month_info;
import static com.example.calendar.CalendarAdapter.day_info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class IncomeRecordCreate extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference childRef = FirebaseDatabase.getInstance().getReference("users").child(userID);

        setContentView(R.layout.income_record_create);

        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        EditText moneyAmount = (EditText) findViewById(R.id.moneyAmount);
        EditText memo = (EditText) findViewById(R.id.memo);

//        저장버튼 눌림
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = categorySpinner.getSelectedItem().toString(); // 카테고리 스피너에서 선택된 값 가져오기
                String categoryNum; // 카테고리 별 고유번호 부여
                String memoText = memo.getText().toString(); // 메모칸에 입력한 값 가져오기
                String Amount = moneyAmount.getText().toString(); // 용돈 금액 입력값을 int형으로 저장함.

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("recordManage/"+ userID);

                // 카테고리 고유번호 부여
                // 21	정기적 용돈
                // 22	세뱃돈
                // 23	비정기적인 보너스
                // 24	기타
                if (category.equals("정기적 용돈")) {
                    categoryNum = "21";
                }
                else if (category.equals("세뱃돈")) {
                    categoryNum = "22";
                }
                else if (category.equals("비정기적인 보너스")) {
                    categoryNum = "23";
                } else {
                    categoryNum = "24";
                }


//                데이터베이스에 저장하기
                //reference = FirebaseDatabase.getInstance().getReference();
                //reference.child("users").child("username").child("planttype").setValue("사과나무");
                FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // 날짜 정보 불러오기
//                        Intent get_intent = getIntent();
//                        Integer year_info = get_intent.getIntExtra("year_info", 1);
//                        Integer month_info = get_intent.getIntExtra("month_info", 1);
//                        Integer day_info = get_intent.getIntExtra("day_info", 1);
//
                        String dateInfo = year_info+","+month_info+","+day_info;


                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                            record를 category를 기준으로 경로 설정하여 저장함.
                            String src = memoText+ "/";
                            String categorySrc = src + "category";
                            String moneySrc = src + "moneyAmount";
                            String memoSrc = src + "memo";
                            String pmSrc = src + "pm"; // plus, minus 의 여부를 판별. 0이면 지출, 1이면 수입
                            String dateSrc = src + "date";

                            Map<String, Object> record = new HashMap<>();
                            record.put(categorySrc, categoryNum);
                            record.put(moneySrc, Amount);
                            record.put(memoSrc, memoText);
                            record.put(dateSrc, dateInfo);
                            record.put(pmSrc, "1");

                            ref.updateChildren(record);
                            Log.w("Income date info", dateInfo+"에 수입 기록됨");

//                            stampUpdate();

                            Integer stamp = ur_stamp;
                            stamp++;

                            Map<String, Object> stampUpdates = new HashMap<>();
                            stampUpdates.put("stamp", stamp);
                            childRef.updateChildren(stampUpdates);
                            Log.w("IncomeRecordCreate", "스탬프 업데이트");



                            // 완료된 것을 알리기
//                            Toast.makeText(IncomeRecordCreate.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(IncomeRecordCreate.this, "저장 실패", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });// saveButton onClick event listener 끝

        //취소버튼 눌림
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(IncomeRecordCreate.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // cancelButton onClick event listener 끝


    }; // onCreate 클래스 끝.

    // 기록 작성할 때 마다 도장 갯수가 늘어나게 하기
//    void stampUpdate(){
//        DatabaseReference childRef = FirebaseDatabase.getInstance().getReference("users").child(userID);
//        Integer stamp = ur_stamp + 1;
//
//        Map<String, Object> stampUpdates = new HashMap<>();
//        stampUpdates.put("stamp", stamp);
//        childRef.updateChildren(stampUpdates);
//        Log.w("IncomeRecordCreate", "스탬프 업데이트");
//    }



}// 메인 클래스 끝.