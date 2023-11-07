package com.example.codevalley.wishStore;

import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.RegisterActivity.fertilizer;
import static com.example.codevalley.RegisterActivity.synthesis;
import static com.example.codevalley.RegisterActivity.ur_stamp;
import static com.example.codevalley.RegisterActivity.water;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class store_buyGameItem extends Activity {
    DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference("game").child(userID).child("item");
    Button noBuyBtn, yesBuyBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_buygameitem);

        //배경 흐리게 만들기
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        noBuyBtn = findViewById(R.id.noBuyButton);
        yesBuyBtn = findViewById(R.id.yesBuyButton);

        yesBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setYesBuyBtn();
            }
        });
        //아니오 버튼 눌렀을 시 액티비티 종료
        noBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyFailedIntent = new Intent(store_buyGameItem.this, store_main.class);
                buyFailedIntent.putExtra("resultStamp", ur_stamp);
                setResult(1, buyFailedIntent);
                finish();
            }
        });


    }
    //네 버튼 눌렀을 시 게임 아이템 데이터 없데이트
    public void setYesBuyBtn(){
        ur_stamp = getIntent().getIntExtra("ur_stamp", ur_stamp);
        Integer itemID = getIntent().getIntExtra("itemID", 0);
        switch(itemID){
            case 1:
                water = getIntent().getIntExtra("water", water);
                if(ur_stamp>=1){
                    water++;
                    Map<String, Object> waterUpdates = new HashMap<>();
                    waterUpdates.put("water", water);
                    itemRef.updateChildren(waterUpdates);
                    ur_stamp -= 1;
                }else{
                    Toast.makeText(this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            case 2:
                synthesis = getIntent().getIntExtra("sun", synthesis);
                if(ur_stamp>=2){
                    synthesis++;
                    Map<String, Object> synthesisUpdates = new HashMap<>();
                    synthesisUpdates.put("synthesis", synthesis);
                    itemRef.updateChildren(synthesisUpdates);
                    ur_stamp-=2;
                }else{Toast.makeText(this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            case 3:
                fertilizer = getIntent().getIntExtra("fertilizer", fertilizer);
                if(ur_stamp>=3){
                    fertilizer++;
                    Map<String, Object> fertilizerUpdates = new HashMap<>();
                    fertilizerUpdates.put("fertilizer", fertilizer);
                    itemRef.updateChildren(fertilizerUpdates);
                    ur_stamp-=3;
                }else{Toast.makeText(this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            default:
                break;
        }
        Intent buyCompleteIntent = new Intent(this, store_main.class);
        buyCompleteIntent.putExtra("resultStamp", ur_stamp);
        setResult(0, buyCompleteIntent);

        finish();
    }



}
