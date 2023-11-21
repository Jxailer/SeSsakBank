package com.example.adult.statistics;

import static com.example.adult.adult_LoginActivity.childID;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SymbolTable;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class weekStatsActivity extends AppCompatActivity {

    DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("users").child(childID);
    DatabaseReference moneyRef = FirebaseDatabase.getInstance().getReference("recordManage").child(childID);
    TextView statsName;
    ArrayList<String> weekLabel = new ArrayList<>();
    Integer firstWeek, secondWeek, thirdWeek, lastWeek;
    ArrayList weekData = new ArrayList<>();
    BarChart barChart;
    ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekstats);

        barChart = findViewById(R.id.bar_Chart);
        //사용자 이름 표시
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

        setChartForWeekSpents();
    }

    private void setChartForWeekSpents(){
        moneyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                weekData.clear();
                barEntries.clear();
                weekLabel.clear();
                firstWeek = 0;
                secondWeek = 0;
                thirdWeek = 0;
                lastWeek = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String record_date = dataSnapshot.child("date").getValue().toString();
                    String record_month = record_date.substring(5, 7);
                    Integer record_day = Integer.valueOf(record_date.substring(8));
                    String spentType = dataSnapshot.child("pm").getValue().toString();
                    Integer moneyAmount = Integer.valueOf(dataSnapshot.child("moneyAmount").getValue().toString());
                    if(spentType.equals("0") && record_month.equals(getMonth())){
                        if(1 <= record_day && record_day < 8){
                            firstWeek += moneyAmount;
                        }
                        else if(8 <= record_day && record_day < 15){
                            secondWeek += moneyAmount;
                        }
                        else if(15 <= record_day && record_day < 22){
                            thirdWeek += moneyAmount;
                        }
                        else {
                            lastWeek += moneyAmount;
                        }

                    }
                }

                // x축 레이블 설정
                weekLabel.add("1~7");
                weekLabel.add("8~14");
                weekLabel.add("15~21");
                weekLabel.add("22~30");

                // 막대 엔트리 생성
                barEntries.add(new BarEntry(0, ((Number)firstWeek).floatValue()));
                barEntries.add(new BarEntry(1, ((Number)secondWeek).floatValue()));
                barEntries.add(new BarEntry(2, ((Number)thirdWeek).floatValue()));
                barEntries.add(new BarEntry(3, ((Number)lastWeek).floatValue()));

                BarData weekData = new BarData();

                BarDataSet weekDataSet = new BarDataSet(barEntries, "주간 지출");
                weekDataSet.setColors(new int[] {Color.rgb(58, 62, 69), Color.rgb(58, 62, 69), Color.rgb(58, 62, 69), Color.rgb(118, 151, 135)});
                weekData.addDataSet(weekDataSet);

                weekData.setBarWidth(0.5f);

                //차트 디자인
                barChart.setData(weekData);

                barChart.animateY(500);
                barChart.setPinchZoom(false);

                Description description = barChart.getDescription();
                description.setEnabled(false);
                barChart.setMaxVisibleValueCount(4);
                Legend legend = barChart.getLegend();
                legend.setEnabled(false);

                XAxis xAxis = barChart.getXAxis();
                xAxis.setGranularity(1f);
                xAxis.setTextSize(10f);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawAxisLine(false);
                xAxis.setDrawGridLines(false);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(weekLabel));

                YAxis yAxis = barChart.getAxisLeft();
                yAxis.setAxisMinimum(0f);
                yAxis.setDrawAxisLine(false);
                yAxis.setDrawLabels(true);
                yAxis.setGranularity(1f);

                YAxis yRAxis = barChart.getAxisRight();
                yRAxis.setDrawLabels(false);
                yRAxis.setDrawAxisLine(false);
                yRAxis.setDrawGridLines(false);

                barChart.setDoubleTapToZoomEnabled(false);
                barChart.setDrawGridBackground(false);
                barChart.setVisibleXRangeMaximum(4);
                barChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

  public String getLastDate(){
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
      SimpleDateFormat sp = new SimpleDateFormat("d");
      return sp.format(calendar.getTime());
  }
  public String getMonth(){
        Long now = System.currentTimeMillis();
        Date month = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("M");
        return sdf.format(month);
  }






    // 다른 통계화면으로 이동
    public void statsButtonClicked(View v){
        Intent statsIntent = new Intent(this, HomeActivity.class);
        startActivity(statsIntent);
        overridePendingTransition(0, 0);
    }

    public void weekStatsButtonClicked(View v){

    }

    public void categoryStatsButtonClicked(View v) {
        Intent categoryStatsIntent = new Intent(this, categoryStatsActivity.class);
        startActivity(categoryStatsIntent);
        overridePendingTransition(0, 0);
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