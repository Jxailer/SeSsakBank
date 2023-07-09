package com.example.codevalley.wishStore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.codevalley.R;

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

}