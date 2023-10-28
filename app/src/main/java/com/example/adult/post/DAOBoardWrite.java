package com.example.adult.post;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

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

    // 데이터베이스 기능 추가 (게시물 리스트 조회)
    public Query get(){
        return databaseReference;
    }

    // 데이터베이스 기능 추가(수정)
    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    // 데이터베이스 기능 추가(삭제)
    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }

}
