package com.example.codevalley.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adult.post.BoardAdapter;
import com.example.adult.post.BoardWrite;
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

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
    public static ArrayList arrayDay = new ArrayList<>();
    public static ArrayList arrayAnalytics = new ArrayList<>();
    ArrayList arrayXRange = new ArrayList<>();
    public static ArrayList<Entry> arrayLastWeek = new ArrayList<Entry>();
    public static ArrayList<Entry> arrayThisWeek = new ArrayList<Entry>();
    LineChart lineChart;
    CardView viewAllUsers, viewAllNotice, viewAllCommunity;
    ArrayList arrayPost;
    TextView totalUsers, nOne, nTwo, nThree, tOne, tTwo, tThree;
    String createDay, totalUserCnt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //사용자
        lineChart = findViewById(R.id.dailySignups_chart);
        viewAllUsers = findViewById(R.id.view_all_users);
        totalUsers = findViewById(R.id.total_users);
        //공지사항
        viewAllNotice = findViewById(R.id.view_all_notice);
        nOne = findViewById(R.id.n1);
        nTwo = findViewById(R.id.n2);
        nThree = findViewById(R.id.n3);
        //게시판
        viewAllCommunity = findViewById(R.id.view_all_community);
        tOne = findViewById(R.id.t1);
        tTwo = findViewById(R.id.t2);
        tThree = findViewById(R.id.t3);

        // 가입자 수 차트
        setChartForDailySignups();
        // 공지사항
        nOne.setText("시스템 점검 안내");
        nTwo.setText("커뮤니티 이용규칙 안내");
        nThree.setText("시스템 장애 안내");
        // 최근 게시판
        loadCommunityList();
        // 회원 관리 페이지로
        viewAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, MemberActivity.class));
            }
        });
        // 게시판 관리 페이지로
        viewAllCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, CommunityActivity.class));
            }
        });
    }

    private void setChartForDailySignups(){
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
                    arrayDay.add(createDay);  // -> 사용자들의 가입 날짜만 저장됨 -> (2일에 아무도 가입하지 않았으면 2는 들어가지 않음)
                }
                // 총 가입자 수
                totalUserCnt = String.valueOf(arrayDay.size());
                totalUsers.setText(totalUserCnt);

                //통계를 위해 1일부터 현재 일자에 대한 가입자 수 배열 생성
                Integer toDay = Integer.valueOf(getTodayDate()); // 오늘 일자 구하기
                for(Integer i=0; i<=toDay; i++){  // 0일부터 오늘까지
                    Integer cnt=0;  // 해당 날짜에 가입한 사람 수를 세기 위해
                    for(Integer j=0; j< arrayDay.size(); j++){  //arrayDay 배열 0번째부터 끝까지
                        Integer date = Integer.valueOf((String) arrayDay.get(j));  //배열에 저장된 가입 날짜는 문자이므로
                        if(i == date){  // 날짜와 '가입날짜'가 같으면
                            cnt += 1;
                        }
                    }
                    arrayAnalytics.add(cnt);   // -> 이번달의 일 수(11월은 1일부터 30일까지 있음)만큼 들어있고 가입한 사람 수가 해당 '일'자(인덱스와 동일)에 들어가있음
                }

                // 지난주 가입자수 담기(통계를 7일씩만 보여줄 것이기 때문)
                ArrayList list1 = new ArrayList<>();
                for(Integer j=13; j>6; j--){
                    Integer data = (Integer) arrayAnalytics.get(toDay - j);  // 만약 toDay(오늘)이 19일이면 (19-13)일부터 (19-7)일의 데이터
                    list1.add(data);  // 최종적으로 7개의 데이터가 들어감(날짜가 갱신되면 데이터도 갱신됨)
                }
                // 이번주 가입자수 담기
                ArrayList list2 = new ArrayList<>();
                for(Integer j=6; j>-1; j--){
                    Integer data = (Integer) arrayAnalytics.get(toDay - j);  // 만약 toDay가 19일이면 (19-6)일부터 (19-0)일의 데이터
                    list2.add(data);
                }
                //차트에 넣을 entry 데이터 생성
                for(Integer i=0; i<7; i++){
                    arrayLastWeek.add(new Entry(i, ((Number)list1.get(i)).floatValue()));  // Entry에는 float형만 들어가는데 Integer를 Number로 바꿔야 float형으로 변환할 수 있음(굳이?)
                    arrayThisWeek.add(new Entry(i, ((Number)list2.get(i)).floatValue()));
                }
                //x축의 범위 설정을 위한 리스트 생성
                for(Integer i=(toDay-6); i<=toDay; i++){
                    String j = String.valueOf(i);
                    arrayXRange.add(j + "일");
                }

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

                //애니메이션
                lineChart.animateX(300);
                //description 지우기
                Description description = lineChart.getDescription();
                description.setEnabled(false);
                //범례 설정
                Legend legend = lineChart.getLegend();
                legend.setTextSize(10f);
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);  // 상단에 위치
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);  // 오른쪽에 위치
                legend.setForm(Legend.LegendForm.LINE);  // 구분은 선 모양
                legend.setDrawInside(true);
                //x축 설정
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setTextSize(10f);  // 글자 크기
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // x축은 아래에 위치
                xAxis.setDrawAxisLine(false);  // x축 선 지우기
                xAxis.setDrawGridLines(false);  // 격자무늬 지우기
                xAxis.setValueFormatter(new IndexAxisValueFormatter(arrayXRange));  // x축 범위 설정
                //왼쪽 y축 설정
                YAxis yLAxis = lineChart.getAxisLeft();
                yLAxis.setTextSize(10f);
                yLAxis.setDrawAxisLine(false);
                yLAxis.setAxisMinimum(0f);  // y축 최솟값
                yLAxis.setGranularity(1f);  // y축 간격
                //오른쪽 y축 설정
                YAxis yRAxis = lineChart.getAxisRight();
                yRAxis.setDrawLabels(false);  // 레이블 지우기
                yRAxis.setDrawAxisLine(false);  // 축 선 지우기
                yRAxis.setDrawGridLines(false);  // 그리드 선 지우기

                lineChart.setDoubleTapToZoomEnabled(false);  // 확대 안됨
                lineChart.setDrawGridBackground(false);  // 격자배경 지우기

                lineChart.invalidate();  // 차트 다시 받아오기
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

    private void loadCommunityList(){
        DatabaseReference boardRef = FirebaseDatabase.getInstance().getReference("Post");
        boardRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayPost = new ArrayList<>();
                arrayPost.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    String writeText = data.child("user_text").getValue().toString();

                    arrayPost.add(writeText);
                }
                arrayPost.sort(Comparator.reverseOrder());

                tOne.setText((CharSequence) arrayPost.get(0));
                tTwo.setText((CharSequence) arrayPost.get(1));
                tThree.setText((CharSequence) arrayPost.get(2));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
