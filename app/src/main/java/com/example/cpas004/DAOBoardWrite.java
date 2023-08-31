package com.example.cpas004;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOBoardWrite {

    private DatabaseReference databaseReference;

    DAOBoardWrite(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Post");
    }

    // 게시물 등록
    public Task<Void> add(BoardWrite boardWrite){
        return databaseReference.push().setValue(boardWrite);
    }

    // 게시물 리스트(조회)
    public Query get(){
        return databaseReference;
    }

}
