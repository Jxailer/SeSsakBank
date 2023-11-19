package com.example.adult;

import static com.example.adult.adult_LoginActivity.childID;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adult.post.BoardListActivity;
import com.example.adult.profile.Profile;
import com.example.adult.statistics.ageStatsActivity;
import com.example.codevalley.R;
import com.example.wishShop.WishShopActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("users").child(childID);
    DatabaseReference recordRef = FirebaseDatabase.getInstance().getReference("recordManage").child(childID);
    ArrayList arrayMoney = new ArrayList<>();
//    ArrayList arrayStats = new ArrayList<>();

    TextView statsResult;
    TextView statsName;
    Integer pMoney;
    Integer mMoney;
    float pTotalValue;
    float mTotalValue;

    PieChart pieChart;
    int[] colorArray = new int[] {Color.rgb(118, 151, 135), Color.rgb(247, 247, 247)};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_home);
        statsResult = findViewById(R.id.stats_result);

        statsName = findViewById(R.id.stats_name);
        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                statsName.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recordRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayMoney.clear();
                pMoney = 0;
                mMoney = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer pmInfo = Integer.parseInt(dataSnapshot.child("pm").getValue().toString().trim());

                    if(pmInfo == 1){
                        Integer money = Integer.parseInt(dataSnapshot.child("moneyAmount").getValue().toString().trim());
                        pMoney += money;
                    }
                    else{
                        Integer money = Integer.parseInt(dataSnapshot.child("moneyAmount").getValue().toString().trim());
                        mMoney += money;
                    }
                }
                Integer Money = pMoney + mMoney;
                float pValue = pMoney.floatValue() / Money;
                float mValue = mMoney.floatValue() / Money;
                pTotalValue = pValue * 100;
                mTotalValue = mValue * 100;
                System.out.println(pTotalValue);
                System.out.println(mTotalValue);
//                arrayMoney.add(pTotalValue);
//                arrayMoney.add(mTotalValue);

                if (pTotalValue >= 30){
                    statsResult.setText("자녀에게 참 잘했다고 칭찬해 주세요!");
                }
                else if (pTotalValue >= 20){
                    statsResult.setText("자녀에게 조금만 더 화이팅 해보자고 응원을 해주세요!");
                }
                else if (pTotalValue >= 10){
                    statsResult.setText("할 수 있다고 격려해주세요!");
                }
                else {
                    statsResult.setText("자녀와 함께 슬기로운 용돈관리 하는 시간을 가져보는건 어떨까요?");
                }

                pieChart = findViewById(R.id.pie_Chart);

                PieDataSet pieDataSet = new PieDataSet(data1(), "");
                pieDataSet.setColors(colorArray);
                PieData pieData = new PieData(pieDataSet);
                pieChart.setDrawEntryLabels(true);
                pieChart.setUsePercentValues(true);
                pieData.setValueTextSize(20);
                pieChart.setCenterText("수입/지출 통계");
                pieChart.setCenterTextSize(16);
                pieChart.setHoleRadius(30);
                pieChart.setData(pieData);
                pieChart.invalidate();
            }
            private ArrayList<PieEntry> data1() {
                ArrayList<PieEntry> datavalue = new ArrayList<>();
                datavalue.add(new PieEntry(mTotalValue,"지출"));
                datavalue.add(new PieEntry(pTotalValue,"수입"));

                return datavalue;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    // 다른 통계화면으로 이동
    public void ageStatsButtonClicked(View v){
        Intent statsIntent = new Intent(this, ageStatsActivity.class);
        startActivity(statsIntent);
    }



    //    하단 네비게이션 바 버튼 클릭
    public void homeButtonClicked(View v){

    }

    public void wishShopButtonClicked(View v){
        Intent wishShopIntent = new Intent(this, WishShopActivity.class);
        startActivity(wishShopIntent);
    }

    public void profileButtonClicked(View v){
        Intent profileIntent = new Intent(this, Profile.class);
        startActivity(profileIntent);
    }

    public void communityButtonClicked(View v){
        Intent communityIntent = new Intent(this, BoardListActivity.class);
        startActivity(communityIntent);
    }
}