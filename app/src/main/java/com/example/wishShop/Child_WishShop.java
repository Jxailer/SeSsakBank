package com.example.wishShop;

import static com.example.codevalley.LoginActivity.userID;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Child_WishShop extends AppCompatActivity {
    TextView ur_wish, need_stamp;
    DatabaseReference mRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_wishshop_test);

        ur_wish = findViewById(R.id.ur_wish);
        need_stamp = findViewById(R.id.need_stamp);
        mRef = FirebaseDatabase.getInstance().getReference("wishManage");

        mRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String wishData = snapshot.child(userID).child("dataDesc").getValue(String.class);
                ur_wish.setText(wishData);
                Integer stampData = snapshot.child(userID).child("dataStamp").getValue(Integer.class);
                need_stamp.setText("필요한 도장 개수 : " + stampData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Child_WishShop.this, "누구세요", Toast.LENGTH_SHORT).show();
                ur_wish.setText("로그인하고오세요");
                need_stamp.setText("오라니까요");
            }
        });

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("adults");
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String ur_child = snapshot.child("username").getValue(String.class);
//                if(ur_child == userID){
//                    String adultNick = snapshot.child("adult_nickname").getValue(String.class);
//                    String wishData = snapshot.child(adultNick).child("wishManage").getValue(String.class);
//                    ur_wish.setText(wishData);
//                    Integer stampData = snapshot.child(adultNick).child("dataStamp").getValue(Integer.class);
//                    need_stamp.setText("필요한 도장 개수 : " + stampData);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}
