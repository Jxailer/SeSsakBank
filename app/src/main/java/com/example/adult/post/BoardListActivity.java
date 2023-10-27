package com.example.adult.post;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codevalley.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getBindingAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        String key = list.get(position).getUser_key();

                        DAOBoardWrite dao = new DAOBoardWrite();

                        dao.remove(key).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(BoardListActivity.this,
                                        "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BoardListActivity.this,
                                        "삭제 실패" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                }
            }


            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder,
                        dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(Color.RED)
                        .addSwipeLeftActionIcon(R.drawable.ic_delete)
                        .addSwipeLeftLabel("삭제")
                        .setSwipeLeftLabelColor(Color.WHITE)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }


    private void loadData() {

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot data : snapshot.getChildren()) {

                    BoardWrite boardwrite = data.getValue(BoardWrite.class);

                    //키 값 가져오기
                    key = data.getKey();
                    //title = boardwrite.getUser_title();
                    //key = userID;
                    //key = "";

                    //키 값 담기
                    boardwrite.setUser_key(key);
                    //boardwrite.setUser_title(title);

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
    //actingbar->boardWrite 화면 전환
    public void onClick(View view) {
        Intent intent = new Intent(this, BoardWirteMain.class);
        startActivity(intent);
    } //finish();



}