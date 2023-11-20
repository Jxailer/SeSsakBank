package com.example.codevalley.admin;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adult.post.BoardWrite;
import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {
    RecyclerView commuRcv;
    CommunityAdapter commuAdt = new CommunityAdapter();
    ArrayList<BoardWrite> boardList;
    DatabaseReference commuRef = FirebaseDatabase.getInstance().getReference("Post");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        commuRcv = findViewById(R.id.community_data);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        commuRcv.setLayoutManager(linearLayoutManager);

        boardList = new ArrayList<>();

        commuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boardList.clear();
                for(DataSnapshot itemSnapshot : snapshot.getChildren()){
                    BoardWrite boardWrite = itemSnapshot.getValue(BoardWrite.class);
                    boardList.add(boardWrite);
                }
                commuAdt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        commuAdt = new CommunityAdapter(this, boardList);

        commuAdt.setOnItemClickListener(new CommunityAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View v, int pos) {
                BoardWrite array = boardList.get(pos);
                String userKey = array.getUser_key();
                commuRef.child(userKey).removeValue();
                boardList.remove(pos);
                commuAdt.notifyItemRemoved(pos);
            }
        });

        commuRcv.setAdapter(commuAdt);
    }
}
