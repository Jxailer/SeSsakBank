package com.example.codevalley.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codevalley.HelperClass;
import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemberActivity extends AppCompatActivity {
    RecyclerView memberRcv;
    MemberAdapter memberAdt = new MemberAdapter();
    ArrayList<HelperClass> helperList;
    DatabaseReference memberRef = FirebaseDatabase.getInstance().getReference("users");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        memberRcv = findViewById(R.id.member_data);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        memberRcv.setLayoutManager(linearLayoutManager);

        helperList = new ArrayList<>();

        memberRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                helperList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    HelperClass helperClass = itemSnapshot.getValue(HelperClass.class);
                    helperList.add(helperClass);
                }
                memberAdt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        memberAdt = new MemberAdapter(this, helperList);

        memberAdt.setOnItemClickListener(new MemberAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                HelperClass arrayList = helperList.get(pos);
                String userId = arrayList.getUsername().replace(".", ",");
                memberRef.child(userId).removeValue();
                helperList.remove(pos);
                memberAdt.notifyItemRemoved(pos);
            }
        });

        memberRcv.setAdapter(memberAdt);
    }
}
