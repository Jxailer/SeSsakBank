package com.example.codevalley.game;

import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.RegisterActivity.fertilizer;
import static com.example.codevalley.RegisterActivity.synthesis;
import static com.example.codevalley.RegisterActivity.ur_stamp;
import static com.example.codevalley.RegisterActivity.water;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.myPage.MyPageActivity;
import com.example.codevalley.wishStore.store_main;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class PlantGame extends AppCompatActivity {
    private int progress_num;

    private int countLevel = 1;
    DatabaseReference reference;
    DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference("game").child(userID).child("item");
    DatabaseReference gameRef = FirebaseDatabase.getInstance().getReference("game").child(userID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantgame);
        setTitle("bar");

        TextView tv_name = (TextView) findViewById(R.id.textView);
        Button btn_nameChange = (Button) findViewById(R.id.btn_nameChange);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
        Button btn_fertilizer = (Button) findViewById(R.id.btn_fertilizer);
        Button btn_water = (Button) findViewById(R.id.btn_water);
        Button btn_synthesis = (Button) findViewById(R.id.btn_synthesis);
        ImageView imv_growingPlant = (ImageView) findViewById(R.id.imv_growingPlant);
        TextView tv_countFertilizer = (TextView) findViewById(R.id.tv_countFertilizer);
        TextView tv_countWater = (TextView) findViewById(R.id.tv_countWater);
        TextView tv_countSynthesis = (TextView) findViewById(R.id.tv_countSynthesis);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TextView tv_countLevel = (TextView) findViewById(R.id.tv_countLevel);

        // 게임 세팅 DB에서 가져오기
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fertilizer = snapshot.child("fertilizer").getValue(Integer.class);
                tv_countFertilizer.setText(String.valueOf(fertilizer));
                water = snapshot.child("water").getValue(Integer.class);
                tv_countWater.setText(String.valueOf(water));
                synthesis = snapshot.child("synthesis").getValue(Integer.class);
                tv_countSynthesis.setText(String.valueOf(synthesis));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tv_name.setText(String.valueOf(snapshot.child("plantName").getValue(String.class)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                countLevel = snapshot.child("plantType").child("level").getValue(Integer.class);
                tv_countLevel.setText(String.valueOf(snapshot.child("plantType").child("level").getValue(Integer.class)));

                if (countLevel >= 2 && countLevel < 3){
                    imv_growingPlant.setImageResource(R.drawable.ssessak);
                }
                else if (countLevel >= 3 && countLevel < 4){
                    imv_growingPlant.setImageResource(R.drawable.small_tree);
                }
                else if (countLevel >= 4  && countLevel < 5){
                    imv_growingPlant.setImageResource(R.drawable.big_tree);
                }
                else if (countLevel >= 5  && countLevel <= 50){
                    FirebaseDatabase.getInstance().getReference("game").child(userID).child("plantType").addValueEventListener(new ValueEventListener(){
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // 저장된 데이터를 하나씩 얻어옴
                            if (Objects.equals(dataSnapshot.child("type").getValue(String.class), "사과나무")) {
                                imv_growingPlant.setImageResource(R.drawable.appletree);
                            }
                            else if (Objects.equals(dataSnapshot.child("type").getValue(String.class), "귤나무")) {
                                imv_growingPlant.setImageResource(R.drawable.mandarintree);
                            }
                            else {
                                imv_growingPlant.setImageResource(R.drawable.bananatree);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // 데이터 불러오기 실패 시 처리
                        }
                    });
                }
                else if (countLevel < 0 || countLevel > 50){
                    Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    imv_growingPlant.setImageResource(R.drawable.seed);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progress_num = snapshot.child("progress").getValue(Integer.class);
                progressBar.setProgress(progress_num);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });





//        // GameStar2에서 받은 식물 이름 가져와서 출력
//        Intent intent = getIntent(); // 넘어온 값을 받기 위해 intent객체를 생성하지만 getIntent()를 통해 넘어온 intent객체를 받아온다.
//        Bundle bundle = intent.getExtras(); // Bundle을 통해 extra들을 모두 가져온다
//        String plantname = bundle.getString("plantname"); // 키 값을 통해서 extras에 있는 값들을 얻는다.
//
//        tv_name.setText(plantname); // PlantGame.xml에 있는 객체에 Text를 설정

        // 이름변경 버튼 눌렀을 시
        btn_nameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlantGame.this, Game_nameChange.class);
                startActivity(intent);
            }
        });

        // 하단바 홈버튼 눌렀을 시
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlantGame.this, MainActivity.class);
                startActivity(intent);
            }
        });



        // 아이템 버튼 클릭 했을 시
        btn_fertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (fertilizer > 0){
                        fertilizer--;
                        Map<String, Object> fertilizerInfo = new HashMap<>();
                        fertilizerInfo.put("item/fertilizer", fertilizer);
                        gameRef.updateChildren(fertilizerInfo);
                        progress_num = progress_num + 2;

                        if (progress_num % 20 == 0){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = 0;
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progress1Info = new HashMap<>();
                            progress1Info.put("progress", progress_num);
                            gameRef.updateChildren(progress1Info);

                            Map<String, Object> plantLevelInfo = new HashMap<>();
                            plantLevelInfo.put("plantType/level", countLevel);
                            gameRef.updateChildren(plantLevelInfo);
//                            reference = FirebaseDatabase.getInstance().getReference();
//                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else if (progress_num > 20){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = progress_num - 20;
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progress1Info = new HashMap<>();
                            progress1Info.put("progress", progress_num);
                            gameRef.updateChildren(progress1Info);

                            Map<String, Object> plantLevelInfo = new HashMap<>();
                            plantLevelInfo.put("plantType/level", countLevel);
                            gameRef.updateChildren(plantLevelInfo);
//                            reference = FirebaseDatabase.getInstance().getReference();
//                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else {
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progressInfo = new HashMap<>();
                            progressInfo.put("progress", progress_num);
                            gameRef.updateChildren(progressInfo);
                        }
                        tv_countFertilizer.setText(fertilizer+"");

//                        // 식물 이미지 변경
//                        if (countLevel >= 3 && countLevel < 20){
//                            FirebaseDatabase.getInstance().getReference("game").child(userID).child("plantType").addValueEventListener(new ValueEventListener(){
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    // 저장된 데이터를 하나씩 얻어옴
//                                    if (Objects.equals(dataSnapshot.child("type").getValue(String.class), "사과나무")) {
//                                        imv_growingPlant.setImageResource(R.drawable.appletree);
//                                    }
//                                    else if (Objects.equals(dataSnapshot.child("type").getValue(String.class), "귤나무")) {
//                                        imv_growingPlant.setImageResource(R.drawable.mandarintree);
//                                    }
//                                    else {
//                                        imv_growingPlant.setImageResource(R.drawable.bananatree);
//                                    }
//                                }
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//                                    // 데이터 불러오기 실패 시 처리
//                                }
//                            });
////                            imv_growingPlant.setImageResource(R.drawable.ssessak);
//                        }
//                        else if (countLevel >= 20  && countLevel < 30){
//                            imv_growingPlant.setImageResource(R.drawable.small_tree);
//                        }
//                        else if (countLevel >= 30  && countLevel < 40){
//                            imv_growingPlant.setImageResource(R.drawable.big_tree);
//                        }
//                        else if (countLevel >= 40  && countLevel <= 50){
////                            private void readTree(){
//                            FirebaseDatabase.getInstance().getReference("game").child(userID).child("plantType").addValueEventListener(new ValueEventListener(){
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    // 저장된 데이터를 하나씩 얻어옴
//                                    if (Objects.equals(dataSnapshot.child("type").getValue(String.class), "사과나무")) {
//                                        imv_growingPlant.setImageResource(R.drawable.appletree);
//                                    }
//                                    else if (Objects.equals(dataSnapshot.child("type").getValue(String.class), "귤나무")) {
//                                        imv_growingPlant.setImageResource(R.drawable.mandarintree);
//                                    }
//                                    else {
//                                        imv_growingPlant.setImageResource(R.drawable.bananatree);
//                                    }
//                                }
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//                                        // 데이터 불러오기 실패 시 처리
//                                    }
//                            });
////                            }
//                        }
//                        else if (countLevel < 0 || countLevel > 50){
//                            Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//                        }
                    }
                    else {
                        tv_countFertilizer.setText(0);
                        Map<String, Object> fertilizerInfo = new HashMap<>();
                        fertilizerInfo.put("item/fertilizer", fertilizer);
                        gameRef.updateChildren(fertilizerInfo);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"보유한 아이템이 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (water > 0){
                        water--;
                        Map<String, Object> waterInfo = new HashMap<>();
                        waterInfo.put("item/water", water);
                        gameRef.updateChildren(waterInfo);
                        progress_num = progress_num + 1;

                        if (progress_num % 20 == 0){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = 0;
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progress1Info = new HashMap<>();
                            progress1Info.put("progress", progress_num);
                            gameRef.updateChildren(progress1Info);

                            Map<String, Object> plantLevelInfo = new HashMap<>();
                            plantLevelInfo.put("plantType/level", countLevel);
                            gameRef.updateChildren(plantLevelInfo);
//                            reference = FirebaseDatabase.getInstance().getReference();
//                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else if (progress_num > 20){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = progress_num - 20;
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progress1Info = new HashMap<>();
                            progress1Info.put("progress", progress_num);
                            gameRef.updateChildren(progress1Info);

                            Map<String, Object> plantLevelInfo = new HashMap<>();
                            plantLevelInfo.put("plantType/level", countLevel);
                            gameRef.updateChildren(plantLevelInfo);
//                            reference = FirebaseDatabase.getInstance().getReference();
//                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else {
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progressInfo = new HashMap<>();
                            progressInfo.put("progress", progress_num);
                            gameRef.updateChildren(progressInfo);
                        }
                        tv_countWater.setText(water+"");
                    }
                    else {
                        tv_countWater.setText(0);
                        Map<String, Object> waterInfo = new HashMap<>();
                        waterInfo.put("item/water", water);
                        gameRef.updateChildren(waterInfo);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"보유한 아이템이 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_synthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (synthesis > 0){
                        synthesis--;
                        Map<String, Object> synthesisInfo = new HashMap<>();
                        synthesisInfo.put("item/synthesis", synthesis);
                        gameRef.updateChildren(synthesisInfo);
                        progress_num = progress_num + 3;

                        if (progress_num % 20 == 0){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = 0;
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progress1Info = new HashMap<>();
                            progress1Info.put("progress", progress_num);
                            gameRef.updateChildren(progress1Info);
                            Map<String, Object> plantLevelInfo = new HashMap<>();
                            plantLevelInfo.put("plantType/level", countLevel);
                            gameRef.updateChildren(plantLevelInfo);
//                            reference = FirebaseDatabase.getInstance().getReference();
//                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else if (progress_num > 20){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = progress_num - 20;
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progress1Info = new HashMap<>();
                            progress1Info.put("progress", progress_num);
                            gameRef.updateChildren(progress1Info);
                            Map<String, Object> plantLevelInfo = new HashMap<>();
                            plantLevelInfo.put("plantType/level", countLevel);
                            gameRef.updateChildren(plantLevelInfo);
//                            reference = FirebaseDatabase.getInstance().getReference();
//                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else {
                            progressBar.setProgress(progress_num);
                            Map<String, Object> progressInfo = new HashMap<>();
                            progressInfo.put("progress", progress_num);
                            gameRef.updateChildren(progressInfo);
                        }
                        tv_countSynthesis.setText(synthesis+"");
                    }
                    else {
                        tv_countSynthesis.setText(0);
                        Map<String, Object> synthesisInfo = new HashMap<>();
                        synthesisInfo.put("item/synthesis", synthesis);
                        gameRef.updateChildren(synthesisInfo);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"보유한 아이템이 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //    네비게이션 하단바 버튼클릭 이벤트
    public void homeButtonClicked(View v){
        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(homeIntent);
        finish();
    }

    public void wishstoreButtonClicked(View v){
        Intent wishIntent = new Intent(this, store_main.class);
        startActivity(wishIntent);
        finish();
    }

    public void mypageButtonClicked(View v){
        Intent myPageIntent = new Intent(this, MyPageActivity.class);
        startActivity(myPageIntent);
        finish();
    }

    public void plantgameButtonClicked(View v){

    }
}