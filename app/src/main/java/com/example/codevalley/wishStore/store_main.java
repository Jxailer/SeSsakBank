package com.example.codevalley.wishStore;

import static com.example.codevalley.LoginActivity.userID;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.TargetPopupActivity;
import com.example.wishShop.DataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class store_main extends AppCompatActivity {
    RecyclerView wishRcv;
    RecyclerView.Adapter wishAdt;
    ArrayList<DataClass> dataList;
    DatabaseReference wishRef = FirebaseDatabase.getInstance().getReference("wishManage").child("22@naver,com");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_store);
        wishRcv = findViewById(R.id.child_wishData);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        wishRcv.setLayoutManager(linearLayoutManager);

        dataList = new ArrayList<>();

        wishRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);
                }
                wishAdt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        wishAdt = new store_adapter(this, dataList);
        wishRcv.setAdapter(wishAdt);
    }



//    네비게이션 하단바 버튼클릭 이벤트
    public void homeButtonClicked(View v){
        Toast.makeText(store_main.this, "홈 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void wishstoreButtonClicked(View v){
        Toast.makeText(store_main.this, "소원권 상점 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    public void mypageButtonClicked(View v){
        Toast.makeText(store_main.this, "마이페이지 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    public void plantgameButtonClicked(View v){
        Toast.makeText(store_main.this, "식물키우기 게임 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

}
