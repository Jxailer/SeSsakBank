package com.example.codevalley.wishStore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.codevalley.R;

import androidx.appcompat.app.AppCompatActivity;

public class store_confirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmactivity_store);
    }

    public void rewardConfirm_yes(View v){
        Toast.makeText(getApplicationContext(), "구매확인 버튼을 눌렀어요.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), store_complete.class);
        startActivity(intent);
    }

    public void rewardConfirm_no(View v){
        Toast.makeText(getApplicationContext(), "구매를 취소했어요.", Toast.LENGTH_LONG).show();
        finish();
    }
}