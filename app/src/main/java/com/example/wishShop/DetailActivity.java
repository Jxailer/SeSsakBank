package com.example.wishShop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codevalley.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailStamp, detailTitle;
    Button editButton;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailStamp = findViewById(R.id.detailStamp);
        detailTitle = findViewById(R.id.detailTitle);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("wishDesc"));
            detailStamp.setText(bundle.getString("wishStamp"));
            detailTitle.setText(bundle.getString("wishTitle"));
            key = bundle.getString("Key");
        }



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("wishTitle", detailTitle.getText().toString())
                        .putExtra("wishStamp", detailStamp.getText().toString())
                        .putExtra("wishDesc", detailDesc.getText().toString())
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}