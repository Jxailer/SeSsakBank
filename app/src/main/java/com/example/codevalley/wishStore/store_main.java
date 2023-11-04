package com.example.codevalley.wishStore;

import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.RegisterActivity.fertilizer;
import static com.example.codevalley.RegisterActivity.synthesis;
import static com.example.codevalley.RegisterActivity.ur_stamp;
import static com.example.codevalley.RegisterActivity.water;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.game.GameStart1;
import com.example.codevalley.game.PlantGame;
import com.example.codevalley.myPage.MyPageActivity;
import com.example.wishShop.DataClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
import java.util.HashMap;
import java.util.Map;


public class store_main extends AppCompatActivity implements View.OnClickListener {
    TextView stampAmount;
    Button ferBtn, synBtn, waterBtn;
    View dialogView;
    RecyclerView wishRcv;
    RecyclerView.Adapter wishAdt;
    ArrayList<DataClass> dataList;
    DatabaseReference wishRef = FirebaseDatabase.getInstance().getReference("wishManage").child("alsdk@naver,com");
    DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference("game").child("alsdk@naver,com").child("item");
    DatabaseReference stampRef = FirebaseDatabase.getInstance().getReference("users").child("alsdk@naver,com");

    private int gameCheck; // gamestart1번만 실행하기 위해 옆에 이 코드 추가

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_store);
        wishRcv = findViewById(R.id.child_wishData);
        stampAmount = findViewById(R.id.stampAmount);

        // 가지고 있는 도장 개수 보여주기
        stampRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ur_stamp = snapshot.child("stamp").getValue(Integer.class);
                stampAmount.setText(String.valueOf(ur_stamp));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        // 부모가 등록한 소원권 목록 보여주기
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

        waterBtn = findViewById(R.id.water_btn);
        synBtn = findViewById(R.id.sun_btn);
        ferBtn = findViewById(R.id.fertile_btn);

        waterBtn.setOnClickListener(this);
        synBtn.setOnClickListener(this);
        ferBtn.setOnClickListener(this);
    }

//  도장으로 게임 아이템 구매
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.water_btn:
                if(ur_stamp>=1){
                    water++;
                    Map<String, Object> waterUpdates = new HashMap<>();
                    waterUpdates.put("water", water);
                    itemRef.updateChildren(waterUpdates);
                    ur_stamp -= 1;
                }else{Toast.makeText(store_main.this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.sun_btn:
                if(ur_stamp>=2){
                    synthesis++;
                    Map<String, Object> synthesisUpdates = new HashMap<>();
                    synthesisUpdates.put("synthesis", synthesis);
                    itemRef.updateChildren(synthesisUpdates);
                    ur_stamp-=2;
                }else{Toast.makeText(store_main.this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.fertile_btn:
                if(ur_stamp>=3){
                    fertilizer++;
                    Map<String, Object> fertilizerUpdates = new HashMap<>();
                    fertilizerUpdates.put("fertilizer", fertilizer);
                    itemRef.updateChildren(fertilizerUpdates);
                    ur_stamp-=3;
                }else{Toast.makeText(store_main.this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            default:
                break;
        }
        Map<String, Object> stampUpdates = new HashMap<>();
        stampUpdates.put("stamp", ur_stamp);
        stampRef.updateChildren(stampUpdates);
    }

//    네비게이션 하단바 버튼클릭 이벤트
    public void homeButtonClicked(View v){
        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeIntent);
        finish();
    }

    public void wishstoreButtonClicked(View v){
    }

    public void mypageButtonClicked(View v){
        Intent myPageIntent = new Intent(this, MyPageActivity.class);
        startActivity(myPageIntent);
        finish();
    }

    public void plantgameButtonClicked(View v){
        // gamestart1번만 실행하기 위해 아래 이 코드 추가
        FirebaseDatabase.getInstance().getReference("game").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    gameCheck = snapshot.child("gameCheck").getValue(Integer.class);
                    if (gameCheck == 1) {
                        Intent intent = new Intent(store_main.this, PlantGame.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(store_main.this, GameStart1.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e){
                    Intent intent = new Intent(store_main.this, GameStart1.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
