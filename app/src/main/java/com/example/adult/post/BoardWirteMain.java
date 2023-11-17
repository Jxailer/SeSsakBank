package com.example.adult.post;

import static com.example.adult.adult_LoginActivity.nickName;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardWirteMain extends AppCompatActivity implements View.OnClickListener{

    Button listBtn;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write_main);

        EditText title_edit = findViewById(R.id.title_edit);
        EditText write_edit = findViewById(R.id.write_edit);
        Button addBtn = findViewById(R.id.writeAdd_btn);

        listBtn = findViewById(R.id.writeList_btn1);
        listBtn.setOnClickListener(this);


        DAOBoardWrite dao = new DAOBoardWrite();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //입력값 변수에 담기
                String time = getTime();
                String title = title_edit.getText().toString(); //글 제목
                String text = write_edit.getText().toString();//글 내용

                BoardWrite boardWrite = new BoardWrite(nickName, time, title, text);

                //데이터베이스 사용자 등록
                dao.add(boardWrite).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        goList();
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

    public String getTime(){
        Long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public void goList(){
        Intent listIntent = new Intent(this, BoardListActivity.class);
        startActivity(listIntent);
    }

    //모든 게시글 목록(리스트) 버튼
    @Override
    public void onClick(View view) {
        intent = new Intent(this, BoardListActivity.class);  //BoardListActivity
        startActivity(intent);
    }
}