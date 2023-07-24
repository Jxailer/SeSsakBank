package com.example.codevalley.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;


public class PlantGame extends AppCompatActivity {
    private int progress_num = 0;
    private int countFertilizer = 10; // 여기에 이제 DB에서 보유 갯수 가져와야 함.
    private int countWater = 0; // 여기에 이제 DB에서 보유 갯수 가져와야 함.
    private int countSynthesis = 5; // 여기에 이제 DB에서 보유 갯수 가져와야 함.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantgame);
        setTitle("bar");

        TextView tv_name = (TextView) findViewById(R.id.textView);
        Button btn_nameChange = (Button) findViewById(R.id.btn_nameChange);
        Button btn_fertilizer = (Button) findViewById(R.id.btn_fertilizer);
        Button btn_water = (Button) findViewById(R.id.btn_water);
        Button btn_synthesis = (Button) findViewById(R.id.btn_synthesis);
        ImageView imv_growingPlant = (ImageView) findViewById(R.id.imv_growingPlant);
        TextView tv_countFertilizer = (TextView) findViewById(R.id.tv_countFertilizer);
        TextView tv_countWater = (TextView) findViewById(R.id.tv_countWater);
        TextView tv_countSynthesis = (TextView) findViewById(R.id.tv_countSynthesis);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

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

        // 아이템 버튼 클릭 했을 시
        btn_fertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countFertilizer--;
                tv_countFertilizer.setText(countFertilizer+"");
                progress_num = progress_num + 20;
                try {
                    if (countFertilizer >= 0){
                        if (progress_num >= 200  && progress_num < 400){
                            imv_growingPlant.setImageResource(R.drawable.ssessak);
                        }
                        else if (progress_num >= 400  && progress_num < 600){
                            imv_growingPlant.setImageResource(R.drawable.small_tree);
                        }
                        else if (progress_num >= 600  && progress_num < 800){
                            imv_growingPlant.setImageResource(R.drawable.big_tree);
                        }
                        else if (progress_num >= 800  && progress_num <= 999){
                            imv_growingPlant.setImageResource(R.drawable.big_fruittree);
                        }
                        else if (progress_num < 0 || progress_num > 999){
                            Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBar.setProgress(progress_num);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"보유한 아이템이 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    tv_countFertilizer.setText(0);
                    Toast.makeText(getApplicationContext(),"잘못된 접근입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countWater--;
                tv_countWater.setText(countWater+"");
                progress_num = progress_num + 1;
                try {
                    if (countWater >= 0){
                        if (progress_num >= 200  && progress_num < 400){
                            imv_growingPlant.setImageResource(R.drawable.ssessak);
                        }
                        else if (progress_num >= 400  && progress_num < 600){
                            imv_growingPlant.setImageResource(R.drawable.small_tree);
                        }
                        else if (progress_num >= 600  && progress_num < 800){
                            imv_growingPlant.setImageResource(R.drawable.big_tree);
                        }
                        else if (progress_num >= 800  && progress_num <= 999){
                            imv_growingPlant.setImageResource(R.drawable.big_fruittree);
                        }
                        else if (progress_num < 0 || progress_num > 999){
                            Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBar.setProgress(progress_num);
                        }
                    }
                    else {
                        tv_countWater.setText(0);
                        Toast.makeText(getApplicationContext(),"보유한 아이템이 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"잘못된 접근입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_synthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countSynthesis--;
                tv_countSynthesis.setText(countSynthesis+"");
                progress_num = progress_num + 3;
                try {
                    if (countSynthesis >= 0){
                        if (progress_num >= 200  && progress_num < 400){
                            imv_growingPlant.setImageResource(R.drawable.ssessak);
                        }
                        else if (progress_num >= 400  && progress_num < 600){
                            imv_growingPlant.setImageResource(R.drawable.small_tree);
                        }
                        else if (progress_num >= 600  && progress_num < 800){
                            imv_growingPlant.setImageResource(R.drawable.big_tree);
                        }
                        else if (progress_num >= 800  && progress_num <= 999){
                            imv_growingPlant.setImageResource(R.drawable.big_fruittree);
                        }
                        else if (progress_num < 0 || progress_num > 999){
                            Toast.makeText(getApplicationContext(),"범위를 초과했습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBar.setProgress(progress_num);
                        }
                    }
                    else {
                        tv_countSynthesis.setText(0);
                        Toast.makeText(getApplicationContext(),"보유한 아이템이 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"잘못된 접근입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}