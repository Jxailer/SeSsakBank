package com.example.codevalley;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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

        //취소버튼 눌림
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IncomeRecordCreate.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // cancelButton onClick event listener 끝


    } // onCreate 클래스 끝.




} // 메인 클래스 끝.
