package com.example.codevalley.wishStore;

import static com.example.codevalley.LoginActivity.userID;
import static com.example.codevalley.wishStore.store_main.ur_stamp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.codevalley.R;
import com.example.wishShop.DataClass;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class store_confirm extends AppCompatActivity {
    DatabaseReference childRef = FirebaseDatabase.getInstance().getReference("users").child("33@naver,com");
    DatabaseReference adultRef = FirebaseDatabase.getInstance().getReference("wishManage").child("33@naver,com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmactivity_store);
    }

    //"네" 버튼 선택 시
    public void rewardConfirm_yes(View v){
        Intent get_intent = getIntent();
        String wish_title = get_intent.getStringExtra("wish_title");
        Integer stamp_price = get_intent.getIntExtra("stamp_price", 0);

        if(ur_stamp >= stamp_price){
            upDateWishList(wish_title, stamp_price);

            Toast.makeText(getApplicationContext(), "구매확인 버튼을 눌렀어요.", Toast.LENGTH_LONG).show();
            Intent yesIntent = new Intent(getApplicationContext(), store_complete.class);
            startActivity(yesIntent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "도장 개수가 모자라요!", Toast.LENGTH_LONG).show();
            Intent failIntent = new Intent(getApplicationContext(), store_main.class);
            startActivity(failIntent);
            finish();
        }
    }

    //"아니오" 버튼 선택 시
    public void rewardConfirm_no(View v){
        Toast.makeText(getApplicationContext(), "구매를 취소했어요.", Toast.LENGTH_LONG).show();
        Intent noIntent = new Intent(getApplicationContext(), store_main.class);
        startActivity(noIntent);
        finish();
    }

    //소원권 리스트 업데이트
    public void upDateWishList(String wishTitle, Integer stampPrice){
        Integer urStamp = ur_stamp - stampPrice;
        //도장 차감
        Map<String, Object> wishUpdates = new HashMap<>();
        wishUpdates.put("stamp", urStamp);
        childRef.updateChildren(wishUpdates);
        //교환한 소원권은 삭제
        adultRef.child(wishTitle).removeValue();
    }
}