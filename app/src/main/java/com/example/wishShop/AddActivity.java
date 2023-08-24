package com.example.wishShop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codevalley.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText edt_wish = (EditText) findViewById(R.id.edt_wish);
        EditText edt_content = (EditText) findViewById(R.id.edt_content);
        Button btn_enroll = (Button) findViewById(R.id.btn_enroll);

        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wish = edt_wish.getText().toString();
                String content = edt_content.getText().toString();

                if (wish.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"소원을 입력해주세요!",Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(AddActivity.this, WishShopActivity.class); // Activity사이에서 값을 전달하기 위해서는 intent를 사용한다.
                    intent.putExtra("wish", wish); // intent생성시 현재 activity와 이동할 activity선언하고, putExtra메서드를 통해 키 값과 데이터를 저장
                    startActivity(intent); // Intent와 함께 다음 activity실행
                }
            }
        });
    }
}