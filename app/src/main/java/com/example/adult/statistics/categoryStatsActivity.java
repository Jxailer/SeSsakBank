package com.example.adult.statistics;

import static com.example.adult.adult_LoginActivity.childID;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adult.HomeActivity;
import com.example.adult.post.BoardListActivity;
import com.example.adult.profile.Profile;
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

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class categoryStatsActivity extends AppCompatActivity {

    DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("users").child(childID);
    DatabaseReference recordRef = FirebaseDatabase.getInstance().getReference("recordManage").child(childID);

//    ArrayList arrayCategory = new ArrayList<>();
//    ArrayList arrayLastCategory = new ArrayList<>();
    ArrayList arrayMonthMonth = new ArrayList<>();
    TextView categoryStatsResult;
    TextView statsName;
    float m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;
    float lM1, lM2, lM3, lM4, lM5, lM6, lM7, lM8, lM9, lM10;

    PieChart pieChart;
    int[] colorArray2 = new int[] {Color.rgb(118, 151, 135), Color.rgb(124, 150, 117),
            Color.rgb(150, 150, 117), Color.rgb(150, 142, 117), Color.rgb(150, 133, 117),
            Color.rgb(150, 122, 117), Color.rgb(224, 224, 202), Color.rgb(224, 218, 191), Color.rgb(223, 224, 222),
            Color.rgb(247, 247, 247)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_stats);

        categoryStatsResult = findViewById(R.id.categoryStats_result);

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
//                arrayCategory.clear();
//                arrayLastCategory.clear();
                arrayMonthMonth.clear();
                m1 = 0; m2 = 0; m3 = 0; m4 = 0; m5 = 0; m6 = 0; m7 = 0; m8 = 0; m9 = 0; m10 = 0;
                lM1 = 0; lM2 = 0; lM3 = 0; lM4 = 0; lM5 = 0; lM6 = 0; lM7 = 0; lM8 = 0; lM9 = 0; lM10 = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = dataSnapshot.child("date").getValue().toString();
                    Integer thisMonth = Integer.valueOf(getMonth());
                    Integer lastMonth = Integer.valueOf(getLastMonth());
                    Integer category = Integer.parseInt(dataSnapshot.child("category").getValue().toString());
                    Integer allowance = Integer.parseInt(dataSnapshot.child("moneyAmount").getValue().toString());
                    Integer Month = Integer.valueOf(date.substring(5,7));

                    if(Month == thisMonth){
                        if (category == 1) {
                            m1 = m1+ allowance.floatValue();
                        } else if (category == 2) {
                            m2 = m2+ allowance.floatValue();
                        } else if (category == 3) {
                            m3 = m3+ allowance.floatValue();
                        } else if (category == 4) {
                            m4 = m4+ allowance.floatValue();
                        } else if (category == 5) {
                            m5 = m5+ allowance.floatValue();
                        }else if (category == 6) {
                            m6 = m6+ allowance.floatValue();
                        }else if (category == 7) {
                            m7 = m7+ allowance.floatValue();
                        }else if (category == 8) {
                            m8 = m8+ allowance.floatValue();
                        }else if (category == 9) {
                            m9 = m9+ allowance.floatValue();
                        }else {
                            m10 = m10+ allowance.floatValue();
                        }
                    }

                    else{
                        if (Month == lastMonth){
                            if (category == 1) {
                                lM1 = lM1+ allowance.floatValue();
                            } else if (category == 2) {
                                lM2 = lM2+ allowance.floatValue();
                            } else if (category == 3) {
                                lM3 = lM3+ allowance.floatValue();
                            } else if (category == 4) {
                                lM4 = lM4+ allowance.floatValue();
                            } else if (category == 5) {
                                lM5 = lM5+ allowance.floatValue();
                            }else if (category == 6) {
                                lM6 = lM6+ allowance.floatValue();
                            }else if (category == 7) {
                                lM7 = lM7+ allowance.floatValue();
                            }else if (category == 8) {
                                lM8 = lM8+ allowance.floatValue();
                            }else if (category == 9) {
                                lM9 = lM9+ allowance.floatValue();
                            }else {
                                lM10 = lM10+ allowance.floatValue();
                            }
                        }
                    }
                }

                // 이번달 카테고리별 비율 구하기
                float hapM = m1+m2+m3+m4+m5+m6+m7+m8+m9+m10;
                m1 = m1 / hapM * 100;
                m2 = m2 / hapM * 100;
                m3 = m3 / hapM * 100;
                m4 = m4 / hapM * 100;
                m5 = m5 / hapM * 100;
                m6 = m6 / hapM * 100;
                m7 = m7 / hapM * 100;
                m8 = m8 / hapM * 100;
                m9 = m9 / hapM * 100;
                m10 = m10 / hapM * 100;

                // 저번달 카테고리별 비율 구하기
                float hapLM = lM1+lM2+lM3+lM4+lM5+lM6+lM7+lM8+lM9+lM10;
                lM1 = lM1 / hapLM * 100;
                lM2 = lM2 / hapLM * 100;
                lM3 = lM3 / hapLM * 100;
                lM4 = lM4 / hapLM * 100;
                lM5 = lM5 / hapLM * 100;
                lM6 = lM6 / hapLM * 100;
                lM7 = lM7 / hapLM * 100;
                lM8 = lM8 / hapLM * 100;
                lM9 = lM9 / hapLM * 100;
                lM10 = lM10 / hapLM * 100;

                if (m1 > lM1){
                    categoryStatsResult.setText("이번달은 저번달보다 음식에 많이 소비했어요!");
                }
                else{
                    categoryStatsResult.setText("이번달은 저번달보다 음식에 많이 소비하지 않았어요!");
                }


                // 파이차트 만들기
                pieChart = findViewById(R.id.category_pie_Chart);

                PieDataSet pieDataSet2 = new PieDataSet(data2(), "");
                pieDataSet2.setColors(colorArray2);
                PieData pieData2 = new PieData(pieDataSet2);
                pieChart.setDrawEntryLabels(true);
                pieChart.setUsePercentValues(true);
                pieData2.setValueTextSize(16);
                pieChart.setCenterText("카테고리별 통계");
                pieChart.setCenterTextSize(16);
                pieChart.setHoleRadius(30);
                pieChart.setData(pieData2);
                pieChart.invalidate();
            }

            private ArrayList<PieEntry> data2() {
                ArrayList<PieEntry> datavalue2 = new ArrayList<>();
                datavalue2.add(new PieEntry(m1,"식사"));
                datavalue2.add(new PieEntry(m2,"간식"));
                datavalue2.add(new PieEntry(m3,"문구"));
                datavalue2.add(new PieEntry(m4,"의류"));
                datavalue2.add(new PieEntry(m5,"여가"));
                datavalue2.add(new PieEntry(m6,"취미"));
                datavalue2.add(new PieEntry(m7,"교재/책"));
                datavalue2.add(new PieEntry(m8,"교통"));
                datavalue2.add(new PieEntry(m9,"분실"));
                datavalue2.add(new PieEntry(m10,"기타"));

                return datavalue2;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    // 이번달 "월" 구하기
    public String getMonth(){
        Long month = System.currentTimeMillis();
        Date monthDate = new Date(month);
        SimpleDateFormat M = new SimpleDateFormat("M");
        return M.format(monthDate);
    }

    // 한달 전 "월" 구하기
    public String getLastMonth(){
        Calendar mon = Calendar.getInstance();
        mon.add(Calendar.MONTH, -1);
        String lastMonth = new java.text.SimpleDateFormat("M").format(mon.getTime());
        return lastMonth;
    }

    // 다른 통계화면으로 이동
    public void statsButtonClicked(View v){
        Intent statsIntent = new Intent(this, HomeActivity.class);
        startActivity(statsIntent);
        overridePendingTransition(0, 0);
    }

    public void ageStatsButtonClicked(View v){
        Intent ageStatsIntent = new Intent(this, ageStatsActivity.class);
        startActivity(ageStatsIntent);
        overridePendingTransition(0, 0);
    }

    public void categoryStatsButtonClicked(View v) {

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