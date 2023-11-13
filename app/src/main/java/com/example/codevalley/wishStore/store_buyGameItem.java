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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        //DB에 저장된 값을 다시 전역변수에 저장(게임에서 사용하기 위해) -> 안하면 앱을 종료했을 떄 값이 사라짐
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                water = snapshot.child("water").getValue(Integer.class);
                synthesis = snapshot.child("synthesis").getValue(Integer.class);
                fertilizer = snapshot.child("fertilizer").getValue(Integer.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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
    //네 버튼 눌렀을 시 게임 아이템 구매
    public void setYesBuyBtn(){
        ur_stamp = getIntent().getIntExtra("ur_stamp", ur_stamp);
        Integer itemID = getIntent().getIntExtra("itemID", 0);
        switch(itemID){
            case 1:
                water = getIntent().getIntExtra("water", water);
                if(ur_stamp>=1){
                    water++;
                    ur_stamp -= 1;
                }else{
                    Toast.makeText(this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            case 2:
                synthesis = getIntent().getIntExtra("sun", synthesis);
                if(ur_stamp>=2){
                    synthesis++;
                    ur_stamp-=2;
                }else{Toast.makeText(this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            case 3:
                fertilizer = getIntent().getIntExtra("fertilizer", fertilizer);
                if(ur_stamp>=3){
                    fertilizer++;
                    ur_stamp-=3;
                }else{Toast.makeText(this, "도장 개수가 모자라요!", Toast.LENGTH_SHORT).show();}
                break;
            default:
                break;
        }
        //아이템 DB에 없데이트
        Map<String, Object> itemCountUpdates = new HashMap<>();
        itemCountUpdates.put("water", water);
        itemCountUpdates.put("synthesis", synthesis);
        itemCountUpdates.put("fertilizer", fertilizer);
        itemRef.updateChildren(itemCountUpdates);

        //최종 도장 개수 store_main으로 값 전달
        Intent buyCompleteIntent = new Intent(this, store_main.class);
        buyCompleteIntent.putExtra("resultStamp", ur_stamp);
        setResult(0, buyCompleteIntent);

        finish();
    }



}
