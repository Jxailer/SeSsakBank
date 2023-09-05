package com.example.codevalley;

import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import java.util.HashMap;
import java.util.Map;
>>>>>>> cb12e23 (.)

public class IncomeRecordCreate extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_record_create);

        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        EditText moneyAmount = (EditText) findViewById(R.id.moneyAmount);

//        저장버튼 눌림
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                String category = categorySpinner.getSelectedItem().toString(); // 카테고리 스피너에서 선택된 값 가져오기
                int categoryNum; // 카테고리 별 고유번호 부여
                int Amount = Integer.parseInt(moneyAmount.getText().toString()); // 용돈 금액 입력값을 int형으로 저장함.

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users/dream/userrecord/");

                // 카테고리 고유번호 부여
                // 21	정기적 용돈
                // 22	세뱃돈
                // 23	비정기적인 보너스
                // 24	기타
                if (category.equals("정기적 용돈")) {
                    categoryNum = 21;
                }
                else if (category.equals("세뱃돈")) {
                    categoryNum = 22;
                }
                else if (category.equals("비정기적인 보너스")) {
                    categoryNum = 23;
                } else {
                    categoryNum = 24;
                }
                Log.i("tag1", String.valueOf(categoryNum));


//                데이터베이스에 저장하기
                //reference = FirebaseDatabase.getInstance().getReference();
                //reference.child("users").child("username").child("planttype").setValue("사과나무");

                //record 순차저장
                FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                    //addListenerForSingleValueEvent: 한 번만 반복
                    // record들을 구분하기 위한 번호
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int recordNum = 1;
                        // 데이터를 불러올 때 처리
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                            record를 순차 저장함
                            while(true){
                                if (Objects.equals(postSnapshot.child("users/dream/userrecord/record" + recordNum).getValue(String.class), null)) { // 해당 경로의 데이터가 비어있는지 확인
                                    String src = "record" + recordNum + "/";String categorySrc = src + "category";
                                    String moneySrc = src + "moneyAmount";

                                    Log.i("tag2", src);
                                    Log.i("tag3", categorySrc);
                                    Log.i("tag4", moneySrc);

                                    Map<String, Object> record = new HashMap<>();
                                    record.put(categorySrc, categoryNum);
                                    record.put(moneySrc, Amount);
                                    ref.updateChildren(record);

                                    Toast.makeText(IncomeRecordCreate.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                                }
                                else {
                                    Log.i("tag1", String.valueOf(recordNum));
                                    recordNum++ ;
                                    continue;
                                }
                            }

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
=======

                String category = categorySpinner.getSelectedItem().toString(); // 카테고리 스피너에서 선택된 값 가져오기
                int Amount = Integer.parseInt(moneyAmount.getText().toString()); // 용돈 금액 입력값을 int형으로 저장함.

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users/dream");
                String src = "userrecord/";

//                데이터베이스에 저장하기
                //reference = FirebaseDatabase.getInstance().getReference();

                int i = 1;

                while(true){
                    if (ref.child("users").child("record"+i).get() != null){
                        continue;
                    }
                    else{

//                reference.child("users").child("username").child("planttype").setValue("사과나무");

                        Map<String, Object> record = new HashMap<>();
                        record.put(src, category);
                        record.put("dream/userrecord/record2/moneyAmount", Amount);
                        ref.updateChildren(record);

                        Toast.makeText(IncomeRecordCreate.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }



            }
        }); // saveButton onClick event listener 끝
>>>>>>> cb12e23 (.)

        //취소버튼 눌림
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IncomeRecordCreate.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // cancelButton onClick event listener 끝


    }; // onCreate 클래스 끝.




} // 메인 클래스 끝.

