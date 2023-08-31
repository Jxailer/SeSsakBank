package com.example.cpas004;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codevalley.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardListActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;

    BoardAdapter adapter;

    // 데이터 베이스 객체
    DAOBoardWrite dao;

    //키 변수
    String key = "";
    String title = "";


    ArrayList<BoardWrite> list = new ArrayList<>();

    FloatingActionButton boardWriteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

        boardWriteBtn = (FloatingActionButton) findViewById(R.id.board_write_btn);
        boardWriteBtn.setOnClickListener(this);

        recyclerView = findViewById(R.id.rv);

        recyclerView.setHasFixedSize(true);

        //화면 설정
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //어뎁터 설정
        adapter = new BoardAdapter(this, list);

        //리사이클러뷰 어뎁터 설정
        recyclerView.setAdapter(adapter);

        //데이터베이스 초기화
        dao = new DAOBoardWrite();

        //데이터 가져오기
        loadData();

    }


    private void loadData() {

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot data : snapshot.getChildren()){

                    BoardWrite boardwrite = data.getValue(BoardWrite.class);

                    //키 값 가져오기
                    key = data.getKey();
                    title = boardwrite.getUser_title();

                    //키 값 담기
                    boardwrite.setUser_key(key);
                    boardwrite.setUser_title(title);

                    //리스트에 담기
                    list.add(boardwrite);
                }

                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    @Override
    public void onClick(View view) {
        finish();
    }
}