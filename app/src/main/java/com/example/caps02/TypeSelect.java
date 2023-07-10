package com.example.caps02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TypeSelect extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_select);

        RadioGroup rg = findViewById(R.id.radio_group);
        RadioButton rk = findViewById(R.id.radio_kid);
        RadioButton rp = findViewById(R.id.radio_parents);
        Button select = findViewById(R.id.typeBtn);

        select.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.radio_kid:
                        Toast.makeText(getApplicationContext(),"어린이타입 선택완료", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"보호자타입 선택완료", Toast.LENGTH_SHORT).show();
                }
            }

        });



    }
}