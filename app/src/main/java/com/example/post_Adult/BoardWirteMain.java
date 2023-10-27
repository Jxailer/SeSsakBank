package com.example.post_Adult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class BoardWirteMain extends AppCompatActivity implements View.OnClickListener{

    Button listBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write_main);

        EditText title_edit = findViewById(R.id.title_edit);
        EditText write_edit = findViewById(R.id.write_edit);
        Button addBtn = findViewById(R.id.writeAdd_btn);

        listBtn = (Button) findViewById(R.id.writeList_btn);
        listBtn.setOnClickListener(this);


        DAOBoardWrite dao = new DAOBoardWrite();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //입력값 변수에 담기
                String title = title_edit.getText().toString(); //글 제목
                String write = write_edit.getText().toString();//글 내용

                BoardWrite boardWrite = new BoardWrite("", title, write);

                //데이터베이스 사용자 등록
                dao.add(boardWrite).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "게시완료",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "실패:" +e.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
                title_edit.setText(null);
                write_edit.setText(null);
            } //onClick
        });

        //모든 게시글 목록(리스트) 버튼
//        Button listBtn = findViewById(R.id.writeList_btn);
//        listBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BoardWirteMain.this, BoardListActivity.class);
//                startActivity(intent);
//            }
//        });




    }//onCreate

    //모든 게시글 목록(리스트) 버튼
    @Override
    public void onClick(View view) {
        intent = new Intent(this, BoardListActivity.class);  //BoardListActivity
        startActivity(intent);
    }
}