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

public class SpentRecordCreate extends AppCompatActivity {
    DatabaseReference reference;

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
                int Amount = Integer.parseInt(moneyAmount.getText().toString()); // 용돈 금액 입력값을 int형으로 저장함.

//                데이터베이스에 저장하기
                //                reference = FirebaseDatabase.getInstance().getReference();
//                reference.child("users").child("username").child("planttype").setValue("사과나무");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
                Map<String, Object> record = new HashMap<>();
                record.put("dream/userrecord/record1/category", category);
                record.put("dream/userrecord/record1/moneyAmount", Amount);
                record.put("dream/userrecord/record1/memo", memoText);
                ref.updateChildren(record);

                Toast.makeText(SpentRecordCreate.this, "저장 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // saveButton onClick event listener 끝

        //취소버튼 눌림
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SpentRecordCreate.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
                finish();

            }
        }); // cancelButton onClick event listener 끝


    } // onCreate 클래스 끝.




} // 메인 클래스 끝.
