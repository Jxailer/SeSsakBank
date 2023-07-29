package com.example.codevalley.wishStore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.TargetPopupActivity;

import androidx.appcompat.app.AppCompatActivity;


public class store_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_store);
    }

    public void buyButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), store_confirm.class);
        startActivity(intent);
    }
    public void hamburgerMenu_Click(View v){

    }
    public void wishAddButtonClicked(View v){
        Toast.makeText(store_main.this, "소원추가 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TargetPopupActivity.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
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
