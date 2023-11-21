package com.example.codevalley;

import static com.example.codevalley.LoginActivity.userID;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
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

public class statistics extends AppCompatActivity {

    DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("users").child(userID);
    DatabaseReference recordRef = FirebaseDatabase.getInstance().getReference("recordManage").child(userID);

    TextView childCategoryStatsResult;
    TextView childStatsName;
    float m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;
    float lM1, lM2, lM3, lM4, lM5, lM6, lM7, lM8, lM9, lM10;

    PieChart pieChart;
    int[] colorArray2 = new int[] {Color.rgb(16, 35, 7), Color.rgb(30, 64, 13),
            Color.rgb(44, 93, 19), Color.rgb(57, 123, 24), Color.rgb(71, 152, 30),
            Color.rgb(84, 181, 36), Color.rgb(98, 210, 42), Color.rgb(165, 166, 164), Color.rgb(194, 195, 193),
            Color.rgb(223, 225, 222)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        childCategoryStatsResult = findViewById(R.id.child_categoryStats_result);
        childStatsName = findViewById(R.id.statistics_name);

        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                childStatsName.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recordRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                m1 = 0; m2 = 0; m3 = 0; m4 = 0; m5 = 0; m6 = 0; m7 = 0; m8 = 0; m9 = 0; m10 = 0;
                lM1 = 0; lM2 = 0; lM3 = 0; lM4 = 0; lM5 = 0; lM6 = 0; lM7 = 0; lM8 = 0; lM9 = 0; lM10 = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = dataSnapshot.child("date").getValue().toString();
                    Integer thisMonth = Integer.valueOf(getMonth2());
                    Integer lastMonth = Integer.valueOf(getLastMonth2());
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

                //저번달과 카테고리별 지출 비교
                String c1 = "", c2 = "", c3 = "", c4 = "", c5 = "",
                        c6 = "", c7 = "", c8 = "", c9 = "", c10 = "";
                if (m1 > lM1){
                    c1 = "\"음식\" ";
                } else if (m2 > lM2){
                    c2 = "\"간식\" ";
                } else if (m3 > lM3){
                    c3 = "\"문구\" ";
                } else if (m4 > lM4){
                    c4 = "\"의류\" ";
                } else if (m5 > lM5){
                    c5 = "\"여가\" ";
                } else if (m6 > lM6){
                    c6 = "\"취미\" ";
                } else if (m7 > lM7){
                    c7 = "\"교재/책\" ";
                } else if (m8 > lM8){
                    c8 = "\"교통\" ";
                } else if (m9 > lM9){
                    c9 = "\"분실\" ";
                } else if (m10 > lM10) {
                    c10 = "\"기타\" ";
                } else {
                    childCategoryStatsResult.setText("이번달은 저번달보다 적은 소비를 했어요! 참 잘했어요");
                }
                childCategoryStatsResult.setText("저번달보다 "+c1+c2+c3+c4+c5+c6+c7+c8+c9+c10+"항목에서 많이 소비했어요 \n " +
                        c1+c2+c3+c4+c5+c6+c7+c8+c9+c10+"소비를 조금 줄이는 것이 좋아요!");

                // 파이차트 만들기
                pieChart = findViewById(R.id.child_category_pie_Chart);

                PieDataSet pieDataSet2 = new PieDataSet(data2(), "");
                pieDataSet2.setColors(colorArray2);
                PieData pieData2 = new PieData(pieDataSet2);
                pieDataSet2.setValueTextColor(Color.BLACK);
                pieDataSet2.setSliceSpace(2f); // 항목별 사이 칸 띄우기
                pieChart.setDrawEntryLabels(true);
                pieChart.setUsePercentValues(true);
                pieData2.setValueTextSize(14);
                Legend legend = pieChart.getLegend(); // pieChart 범례 커스텀
                legend.setTextSize(14);
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                legend.setOrientation(Legend.LegendOrientation.VERTICAL);
                legend.setDrawInside(false);
                legend.setXEntrySpace(4f);
                legend.setYEntrySpace(0f);
                legend.setWordWrapEnabled(true);
                Description description = pieChart.getDescription(); // pieChart 설명 커스텀
                description.setEnabled(false); // 차트 설명 비활성화
                pieChart.setCenterText("카테고리\n통계");
                pieChart.setCenterTextSize(12);
                pieChart.setHoleRadius(30);
                pieChart.setData(pieData2);
                pieChart.invalidate();
            }

            private ArrayList<PieEntry> data2() {
                ArrayList<PieEntry> datavalue2 = new ArrayList<>();
                if (m1 != 0){
                    datavalue2.add(new PieEntry(m1,"식사"));
                }
                if (m2 != 0){
                    datavalue2.add(new PieEntry(m2,"간식"));
                }
                if (m3 != 0){
                    datavalue2.add(new PieEntry(m3,"문구"));
                }
                if (m4 != 0){
                    datavalue2.add(new PieEntry(m4,"의류"));
                }
                if (m5 != 0){
                    datavalue2.add(new PieEntry(m5,"여가"));
                }
                if (m6 != 0){
                    datavalue2.add(new PieEntry(m6,"취미"));
                }
                if (m7 != 0){
                    datavalue2.add(new PieEntry(m7,"교재/책"));
                }
                if (m8 != 0){
                    datavalue2.add(new PieEntry(m8,"교통"));
                }
                if (m9 != 0){
                    datavalue2.add(new PieEntry(m9,"분실"));
                }
                if (m10 != 0){
                    datavalue2.add(new PieEntry(m10,"기타"));
                }

                return datavalue2;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // 이번달 "월" 구하기
    public String getMonth2(){
        Long month2 = System.currentTimeMillis();
        Date monthDate2 = new Date(month2);
        SimpleDateFormat M = new SimpleDateFormat("M");
        return M.format(monthDate2);
    }

    // 한달 전 "월" 구하기
    public String getLastMonth2(){
        Calendar mon2 = Calendar.getInstance();
        mon2.add(Calendar.MONTH, -1);
        String lastMonth2 = new java.text.SimpleDateFormat("M").format(mon2.getTime());
        return lastMonth2;
    }
}