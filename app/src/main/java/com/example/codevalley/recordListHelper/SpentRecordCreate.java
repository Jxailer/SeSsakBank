package com.example.codevalley.recordListHelper;

import static com.example.calendar.CalendarAdapter.day_info;
import static com.example.calendar.CalendarAdapter.month_info;
import static com.example.calendar.CalendarAdapter.year_info;
import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.RegisterActivity.ur_stamp;

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


public class SpentRecordCreate extends AppCompatActivity {
    DatabaseReference reference;
    DatabaseReference childRef = FirebaseDatabase.getInstance().getReference("users").child(userID);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spent_record_create);

        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        EditText memo = (EditText) findViewById(R.id.memo);
        EditText moneyAmount = (EditText) findViewById(R.id.moneyAmount);

//        저장버튼 눌림
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = categorySpinner.getSelectedItem().toString(); // 카테고리 스피너에서 선택된 값 가져오기
                String memoText = memo.getText().toString(); // 메모칸에 입력한 값 가져오기
                String Amount = moneyAmount.getText().toString(); // 용돈 금액 입력값을 String형으로 저장함.

                String categoryNum; // 카테고리 별 고유번호 부여

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("recordManage/"+ userID);


                //<!--    1	식사-->
                //<!--    2	의류-->
                //<!--    3	문구-->
                //<!--    4	간식-->
                //<!--    5	여가-->
                //<!--    6	취미-->
                //<!--    7	교재/책-->
                //<!--    8	교통-->
                //<!--    9	분실-->
                //<!--    10	기타-->
                if (category.equals("음식")) {
                    categoryNum = "1";
                }
                else if (category.equals("간식")) {
                    categoryNum = "2";
                }
                else if (category.equals("문구류")) {
                    categoryNum = "3";
                }
                else if (category.equals("옷")) {
                    categoryNum = "4";
                }
                else if (category.equals("여가")) {
                    categoryNum = "5";
                }else if (category.equals("취미")) {
                    categoryNum = "6";
                }else if (category.equals("문제집/책")) {
                    categoryNum = "7";
                }else if (category.equals("교통비")) {
                    categoryNum = "8";
                }else if (category.equals("분실")) {
                    categoryNum = "9";
                }else {
                    categoryNum = "10";
                }
                Log.i("tag1", String.valueOf(categoryNum));

//                데이터베이스에 저장하기
                //reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("사과나무");

                FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                    //addListenerForSingleValueEvent: 한 번만 반복
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            // 날짜 정보 불러오기
//                            Intent get_intent = getIntent();
//                            String year_info = get_intent.getStringExtra("year_info");
//                            String month_info = get_intent.getStringExtra("month_info");
//                            String day_info = get_intent.getStringExtra("day_info");
                        String dateInfo = year_info+","+month_info+","+day_info;

                        // 데이터를 불러올 때 처리
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//

                            String src = memoText + "/";
                            String categorySrc = src + "category";
                            String moneySrc = src + "moneyAmount";
                            String memoSrc = src + "memo";
                            String pmSrc = src + "pm"; // plus, minus 의 여부를 판별. 0이면 지출, 1이면 수입
                            String dateSrc = src + "date";

                            Map<String, Object> record = new HashMap<>();
                            record.put(categorySrc, categoryNum);
                            record.put(moneySrc, Amount);
                            record.put(memoSrc, memoText);
                            record.put(pmSrc, "0");
                            record.put(dateSrc, dateInfo);
                            ref.updateChildren(record);
                            Log.w("Spent date info", dateInfo+"에 지출 기록 저장됨");

                            stampUpdate();

//                          Toast.makeText(SpentRecordCreate.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(SpentRecordCreate.this, "저장 실패", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }


        }); // saveButton onClick event listener 끝

        //취소버튼 눌림
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(SpentRecordCreate.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // cancelButton onClick event listener 끝


    } // onCreate 클래스 끝.

    void stampUpdate(){
        Integer Stamp = ur_stamp;
        Stamp++;

        Map<String, Object> stampUpdates = new HashMap<>();
        stampUpdates.put("stamp", Stamp);
        childRef.updateChildren(stampUpdates);
        Log.w("SpentRecordCreate", "스탬프 업데이트");
    }


} // 메인 클래스 끝.
