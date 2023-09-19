package com.example.post_Adult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codevalley.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class BoardUpdateActivity extends AppCompatActivity {

    EditText updateTitleEdit, updateWriteEdit;

    String sKey, sTitle, sWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_update);

        DAOBoardWrite dao = new DAOBoardWrite();

        updateTitleEdit = findViewById(R.id.update_title_edit);
        updateWriteEdit = findViewById(R.id.update_write_edit);

        getAndSetIntentData();

        Button writeupdateBtn = findViewById(R.id.writeUpdate_btn);
        writeupdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //변경값
                String uTitle = updateTitleEdit.getText().toString();
                String uWrite = updateWriteEdit.getText().toString();

                // 파라미터 셋팅
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("user_title", uTitle);
                hashMap.put("user_text", uWrite);

                dao.update(sKey, hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(getApplicationContext(), "게시글 수정완료", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "게시글 수정실패: " +e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //데이터 받아서 화면에 보여주기
    private void getAndSetIntentData() {

        // 값 있는지 체크
        if(getIntent().hasExtra("key") && getIntent().hasExtra("title") &&
        getIntent().hasExtra("write")){

            //데이터 가져오기
            sKey = getIntent().getStringExtra("key");
            sTitle = getIntent().getStringExtra("title");
            sWrite = getIntent().getStringExtra("write");

            //데이터 넣기
            updateTitleEdit.setText(sTitle);
            updateWriteEdit.setText(sWrite);
        }
    }
}