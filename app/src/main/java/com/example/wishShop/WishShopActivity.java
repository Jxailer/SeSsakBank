package com.example.wishShop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.codevalley.R;

import java.util.ArrayList;

public class WishShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_shop);

        TextView btn_add = (TextView) findViewById(R.id.btn_add);

        // [빈 배열 생성]
        final ArrayList<String> items = new ArrayList<String>();
        // [ArrayAdapter 생성] : 리스트 View 는 single choice 지정
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items) ;
        // [listview 생성 및 adapter 지정]
        final ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

        Intent intent = getIntent(); // 넘어온 값을 받기 위해 intent객체를 생성하지만 getIntent()를 통해 넘어온 intent객체를 받아온다.
        Bundle bundle = intent.getExtras(); // Bundle을 통해 extra들을 모두 가져온다
        String wish = bundle.getString("wish"); // 키 값을 통해서 extras에 있는 값들을 얻는다.

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishShopActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        // 아이템 추가
        items.add(wish);

        // listview 갱신
        adapter.notifyDataSetChanged();
    }
}