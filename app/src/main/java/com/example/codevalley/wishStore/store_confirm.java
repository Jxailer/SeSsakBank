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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmactivity_store);
    }

    public void rewardConfirm_yes(View v){
        Intent get_intent = getIntent();
        Integer stamp_price = get_intent.getIntExtra("stamp_price", 0);
        if(ur_stamp >= stamp_price){
            Integer urStamp = ur_stamp - stamp_price;

            Map<String, Object> wishUpdates = new HashMap<>();
            wishUpdates.put("stamp", urStamp);
            childRef.updateChildren(wishUpdates);

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
//    public void wishDelete(String key){
//        adultRef.child(key).removeValue();
//    }

    public void rewardConfirm_no(View v){
        Toast.makeText(getApplicationContext(), "구매를 취소했어요.", Toast.LENGTH_LONG).show();
        Intent noIntent = new Intent(getApplicationContext(), store_main.class);
        startActivity(noIntent);
        finish();
    }
}