package com.example.codevalley.admin;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
    public static ArrayList arrayDay = new ArrayList<>();
    public static ArrayList arrayAnalytics = new ArrayList<>();
    ArrayList arrayXRange = new ArrayList<>();
    public static ArrayList<Entry> arrayLastWeek = new ArrayList<Entry>();
    public static ArrayList<Entry> arrayThisWeek = new ArrayList<Entry>();
    LineChart lineChart;
    String createDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lineChart = findViewById(R.id.dailySignups_chart);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayDay.clear();
                arrayAnalytics.clear();
                arrayLastWeek.clear();
                arrayThisWeek.clear();
                arrayXRange.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String create_date = dataSnapshot.child("createDate").getValue().toString(); //"users"의 가입 날짜만 읽어오기
                    if(create_date.startsWith("0", 6)){ //만약 6번째에 0이 들어가면 -> 01, 02, ... 이면
                        createDay = create_date.substring(7, 8); //맨 뒷자리만 남기기 -> 1, 2, ...
                    }
                    else{
                        createDay = create_date.substring(6, 8); //뒤에서 두 자리 남기기 -> 11, 12, ...
                    }
                    arrayDay.add(createDay);
                }

                //통계를 위해 1일부터 현재 일자에 대한 가입자 수 배열 생성
                Integer toDay = Integer.valueOf(getTodayDate()); // 오늘 일자 구하기
                for(Integer i=0; i<=toDay; i++){  // 0일부터 오늘까지
                    Integer cnt=0;
                    for(Integer j=0; j< arrayDay.size(); j++){  //파베에서 읽어온 가입 날짜를 저장한 배열 0번째부터 끝까지
                        Integer date = Integer.valueOf((String) arrayDay.get(j));  //배열에 저장된 가입 날짜는 문자이므로
                        if(i == date){  //날짜와 '가입날짜'가 같으면
                            cnt += 1;
                        }
                    }
                    arrayAnalytics.add(cnt);   //리스트에 추가
                }

                // 지난주 가입자수 담기(통계를 7일씩만 보여줄 것이기 때문)
                ArrayList list1 = new ArrayList<>();
                for(Integer j=13; j>6; j--){
                    Integer data = (Integer) arrayAnalytics.get(toDay - j);
                    list1.add(data);
                }
                // 이번주 가입자수 담기
                ArrayList list2 = new ArrayList<>();
                for(Integer j=6; j>-1; j--){
                    Integer data = (Integer) arrayAnalytics.get(toDay - j);
                    list2.add(data);
                }
                //y축에 넣을 데이터 entry 생성
                for(Integer i=0; i<7; i++){
                    arrayLastWeek.add(new Entry(i, ((Number)list1.get(i)).floatValue()));
                    arrayThisWeek.add(new Entry(i, ((Number)list2.get(i)).floatValue()));
                }
                //x축의 범위 설정을 위한 리스트 생성
                for(Integer i=(toDay-6); i<=toDay; i++){
                    String j = String.valueOf(i);
                    arrayXRange.add(j);
                }
                System.out.println(arrayXRange);

                LineData lineData = new LineData();

                LineDataSet lastWeekDataSet = new LineDataSet(arrayLastWeek, "지난주");
                lineData.addDataSet(lastWeekDataSet);
                //지난주 데이터 선 설정
                lastWeekDataSet.setLineWidth(2);  // 선 굵기
                lastWeekDataSet.enableDashedLine(5f, 10f, 0f);  //점선
                lastWeekDataSet.setDrawCircleHole(false);  // 원의 구멍 지우기
                lastWeekDataSet.setDrawCircles(false);  // 꼭짓점(데이터)의 원 지우기
                lastWeekDataSet.setColor(Color.parseColor("#397B18"));  // 선 색깔
                lastWeekDataSet.setDrawHighlightIndicators(false);  // 선을 클릭했을 때 효과 안 주기
                lastWeekDataSet.setForm(Legend.LegendForm.LINE);  // 범례도 점선으로
                lastWeekDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{5f, 3f}, 0f)); // 범례도 점선으로
                lastWeekDataSet.setDrawValues(false);  // 데이터 표시 안하기

                LineDataSet thisWeekDataSet = new LineDataSet(arrayThisWeek, "이번주");
                lineData.addDataSet(thisWeekDataSet);
                //이번주 데이터 선 설정
                thisWeekDataSet.setLineWidth(2);
                thisWeekDataSet.setDrawCircleHole(false);
                thisWeekDataSet.setDrawCircles(false);
                thisWeekDataSet.setColor(Color.parseColor("#397B18"));
                thisWeekDataSet.setDrawHighlightIndicators(false);
                thisWeekDataSet.setDrawValues(false);

                lineChart.setData(lineData);

                //description 지우기
                Description description = lineChart.getDescription();
                description.setEnabled(false);
                //범례 설정
                Legend legend = lineChart.getLegend();
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);  // 상단에 위치
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);  // 오른쪽에 위치
                legend.setForm(Legend.LegendForm.LINE);  // 구분은 선 모양
                //x축 설정
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // x축은 아래에 위치
                xAxis.setDrawAxisLine(false);  // x축 선 지우기
                xAxis.setDrawGridLines(false);  // 격자무늬 지우기
                xAxis.setTextSize(10f);  // x축 글자 크기
                xAxis.setValueFormatter(new IndexAxisValueFormatter(arrayXRange));  // x축 범위 설정
                //왼쪽 y축 설정
                YAxis yLAxis = lineChart.getAxisLeft();
                yLAxis.setTextSize(10f);
                yLAxis.setDrawAxisLine(false);
                yLAxis.setAxisMinimum(0f);  // y축 최솟값
                yLAxis.setGranularity(0.5f);  // y축 간격
                //오른쪽 y축 설정
                YAxis yRAxis = lineChart.getAxisRight();
                yRAxis.setDrawLabels(false);  // 레이블 지우기
                yRAxis.setDrawAxisLine(false);
                yRAxis.setDrawGridLines(false);

                lineChart.setDoubleTapToZoomEnabled(false);
                lineChart.setDrawGridBackground(false);

                lineChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getTodayDate(){
        Long today = System.currentTimeMillis();
        Date todayDate = new Date(today);
        SimpleDateFormat sd = new SimpleDateFormat("d");
        return sd.format(todayDate);
    }

}
