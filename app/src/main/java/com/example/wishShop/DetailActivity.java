package com.example.wishShop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codevalley.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailStamp, detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailStamp = findViewById(R.id.detailStamp);
        detailTitle = findViewById(R.id.detailTitle);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("wishDesc"));
            detailStamp.setText(bundle.getString("wishStamp"));
            detailTitle.setText(bundle.getString("wishTitle"));
        }

    }
}