package com.example.codevalley.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PlantGame extends AppCompatActivity {
    private int progress_num = 0;
    private int countFertilizer = 21; // 여기에 이제 DB에서 보유 갯수 가져와야 함.
    private int countWater = 3; // 여기에 이제 DB에서 보유 갯수 가져와야 함.
    private int countSynthesis = 5; // 여기에 이제 DB에서 보유 갯수 가져와야 함.
    private int countLevel = 1;
    DatabaseReference reference;


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

        // GameStar2에서 받은 식물 이름 가져와서 출력
        Intent intent = getIntent(); // 넘어온 값을 받기 위해 intent객체를 생성하지만 getIntent()를 통해 넘어온 intent객체를 받아온다.
        Bundle bundle = intent.getExtras(); // Bundle을 통해 extra들을 모두 가져온다
        String plantname = bundle.getString("plantname"); // 키 값을 통해서 extras에 있는 값들을 얻는다.

        tv_name.setText(plantname); // PlantGame.xml에 있는 객체에 Text를 설정

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
                    if (countFertilizer > 0){
                        countFertilizer--;
                        progress_num = progress_num + 2;
                        if (progress_num % 20 == 0){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = 0;
                            progressBar.setProgress(progress_num);
                            reference = FirebaseDatabase.getInstance().getReference();
                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else if (progress_num > 20){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = progress_num - 20;
                            progressBar.setProgress(progress_num);
                            reference = FirebaseDatabase.getInstance().getReference();
                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else {
                            progressBar.setProgress(progress_num);
                        }
                        tv_countFertilizer.setText(countFertilizer+"");

                        // 식불 이미지 변경
                        if (countLevel >= 3 && countLevel < 20){
                            imv_growingPlant.setImageResource(R.drawable.ssessak);
                        }
                        else if (countLevel >= 20  && countLevel < 30){
                            imv_growingPlant.setImageResource(R.drawable.small_tree);
                        }
                        else if (countLevel >= 30  && countLevel < 40){
                            imv_growingPlant.setImageResource(R.drawable.big_tree);
                        }
                        else if (countLevel >= 40  && countLevel <= 50){
                            imv_growingPlant.setImageResource(R.drawable.big_fruittree);
                        }
                        else if (countLevel < 0 || countLevel > 50){
                            Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                        }
                    }
                    else {
                        tv_countFertilizer.setText(0);
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
                    if (countWater > 0){
                        countWater--;
                        progress_num = progress_num + 1;
                        if (progress_num % 20 == 0){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = 0;
                            progressBar.setProgress(progress_num);
                            reference = FirebaseDatabase.getInstance().getReference();
                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else if (progress_num > 20){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = progress_num - 20;
                            progressBar.setProgress(progress_num);
                            reference = FirebaseDatabase.getInstance().getReference();
                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else {
                            progressBar.setProgress(progress_num);
                        }
                        tv_countWater.setText(countWater+"");

                        // 식물 이미지 변경
                        if (countLevel >= 3 && countLevel < 20){
                            imv_growingPlant.setImageResource(R.drawable.ssessak);
                        }
                        else if (countLevel >= 20  && countLevel < 30){
                            imv_growingPlant.setImageResource(R.drawable.small_tree);
                        }
                        else if (countLevel >= 30  && countLevel < 40){
                            imv_growingPlant.setImageResource(R.drawable.big_tree);
                        }
                        else if (countLevel >= 40  && countLevel <= 50){
                            imv_growingPlant.setImageResource(R.drawable.big_fruittree);
                        }
                        else if (countLevel < 0 || countLevel > 50){
                            Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                        }
                    }
                    else {
                        tv_countWater.setText(0);
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
                    if (countSynthesis > 0){
                        countSynthesis--;
                        progress_num = progress_num + 3;
                        if (progress_num % 20 == 0){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = 0;
                            progressBar.setProgress(progress_num);
                            reference = FirebaseDatabase.getInstance().getReference();
                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else if (progress_num > 20){
                            countLevel++;
                            tv_countLevel.setText(countLevel+"");
                            progress_num = progress_num - 20;
                            progressBar.setProgress(progress_num);
                            reference = FirebaseDatabase.getInstance().getReference();
                            reference.child("users").child("username").child("level").setValue(countLevel);
                        }
                        else {
                            progressBar.setProgress(progress_num);
                        }
                        tv_countSynthesis.setText(countSynthesis+"");

                        // 식물 이미지 변경
                        if (countLevel >= 3 && countLevel < 20){
                            imv_growingPlant.setImageResource(R.drawable.ssessak);
                        }
                        else if (countLevel >= 20  && countLevel < 30){
                            imv_growingPlant.setImageResource(R.drawable.small_tree);
                        }
                        else if (countLevel >= 30  && countLevel < 40){
                            imv_growingPlant.setImageResource(R.drawable.big_tree);
                        }
                        else if (countLevel >= 40  && countLevel <= 50){
                            imv_growingPlant.setImageResource(R.drawable.big_fruittree);
                        }
                        else if (countLevel < 0 || countLevel > 50){
                            Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                        }
                    }
                    else {
                        tv_countSynthesis.setText(0);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"보유한 아이템이 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}