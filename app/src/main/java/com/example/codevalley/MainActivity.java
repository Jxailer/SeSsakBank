package com.example.codevalley;

import static com.example.calendar.CalendarAdapter.day_info;
import static com.example.calendar.CalendarAdapter.month_info;
import static com.example.calendar.CalendarAdapter.year_info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.CalendarAdapter;
import com.example.calendar.CalendarUtil;
import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.game.GameStart1;
import com.example.codevalley.game.PlantGame;
import com.example.codevalley.myPage.MyPageActivity;
import com.example.codevalley.recordListHelper.CustomAdapter_RecordList;
import com.example.codevalley.recordListHelper.HelperClass_RecordList;
import com.example.codevalley.recordListHelper.IncomeRecordCreate;
import com.example.codevalley.recordListHelper.SpentRecordCreate;
import com.example.codevalley.wishStore.store_main;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.example.codevalley.game.GameStart1;
import com.example.codevalley.myPage.MyPageActivity;
import com.example.codevalley.wishStore.store_complete;
import com.example.codevalley.wishStore.store_main;

public class MainActivity extends AppCompatActivity {

    ViewGroup CalendarRecord;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HelperClass_RecordList> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private View view;

    private int gameCheck; // gamestart1번만 실행하기 위해 옆에 이 코드 추가

    public static Context context_Main;

    Button recordCreate_Spent;
    Button recordCreate_Income;
    View mainParent;

    View targetChangeBox;
    EditText editText;
    View calendar;
    RecyclerView recordRcv;
    RecyclerView.Adapter recordAdt;
    ArrayList<HelperClass_RecordList> incomeList;
    ArrayList<HelperClass_RecordList> spentList;
    Button saveButton;
    Button cancelButton;
    Button dayButton;
    Button target;

//    캘린더 커스텀뷰 관련 변수 선언
    TextView monthYearText; //년월 텍스트뷰
    TextView selectedDate; // 커스텀 캘린더에서 선택된 날짜
    RecyclerView day_recyclerView;


//    주간 용돈 사용 내역 합산 변수 선언
//    int incomeSum, spentSum;

    DatabaseReference recordRef;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context_Main = this;

        recordCreate_Spent = (Button) findViewById(R.id.recordCreateButton_spent);
        recordCreate_Income = (Button) findViewById(R.id.recordCreateButton_income);
        mainParent = findViewById(R.id.mainParent);

        targetChangeBox = (View) findViewById(R.id.targetChangeBox);
        editText = findViewById((R.id.targetBox));

        calendar = (View) findViewById(R.id.calendar);

        View calendar = (View) findViewById(R.id.calendar);


        CalendarRecord = findViewById(R.id.CalendarRecord);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        //Button targetButton = findViewById(R.id.targetButton);

        recordRef = FirebaseDatabase.getInstance().getReference("recordManage").child(userID);
        target = findViewById(R.id.targetButton);

        recordRcv = findViewById(R.id.recordRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recordRcv.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

//        달력커스텀뷰 위젯 초기화
        monthYearText = findViewById(R.id.monthYearText);
        ImageButton preBtn = findViewById(R.id.pre_btn);
        ImageButton nextBtn = findViewById(R.id.next_btn);
        day_recyclerView = findViewById(R.id.calendar_recyclerView);

        //현재 날짜
        CalendarUtil.selectedDate = LocalDate.now();

        //화면 설정
        setMonthView();

        //이전 달 버튼 이벤트
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //-1한 월을 넣어준다..(2월 -> 1월)
                CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        //다음 달 버튼 이벤트
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //+1한 월을 넣어준다.(2월 -> 3월)
                CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
                setMonthView();
            }
        });

//        수입지출 합계 쿼리
//        incomeList = new ArrayList<>();
//        spentList = new ArrayList<>();

//        DatabaseReference incomeDatabase = FirebaseDatabase.getInstance().getReference("recordManage").child(userID);
//        Query incomeSum = incomeDatabase.orderByChild("pm").equalTo("1");
//        incomeSum.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                incomeList.clear();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    HelperClass_RecordList incomeListData = dataSnapshot.getValue(HelperClass_RecordList.class);
//                    incomeList.add(incomeListData);
//                }
//                Log.w("MainActivity", "incomeList = "+ incomeList.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.e("MainActivity", "onCancelled");
//            }
//        });


        // 용돈 기입 내역 보여주기
        recordRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    HelperClass_RecordList dataClass = itemSnapshot.getValue(HelperClass_RecordList.class);
                    arrayList.add(dataClass);
                }
                recordAdt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "용돈기입 데이터 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });

        recordAdt = new CustomAdapter_RecordList(arrayList,this);
        recordRcv.setAdapter(recordAdt);



        // 목표 버튼 눌렀을 시
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetChangeBox.setVisibility(View.VISIBLE);
                targetChangeBox.bringToFront();
            }
        });


//        지출 내용 작성 버튼 눌렀을 시
        recordCreate_Spent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpentRecordCreate.class);
                startActivity(intent);
            }
        });

        //수입내용 작성버튼 눌렀을 시
        recordCreate_Income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IncomeRecordCreate.class);
                startActivity(intent);
            }
        });


//        저장버튼 눌렀을 시 onClickListener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) { // 목표설정란이 비어있는지 확인하기.
                    Toast.makeText(MainActivity.this, "목표가 설정되지 않았어요!", Toast.LENGTH_SHORT).show();
                } else {
                    if (target.getText().toString() != editText.getText().toString()) { // 기존의 목표와 값이 다르다면 값을 변경함.
                        String result = editText.getText().toString();
                        target.setText(result);
                        Toast.makeText(MainActivity.this, "목표를 바꿨어요!", Toast.LENGTH_SHORT).show();
                        targetChangeBox.setVisibility(View.INVISIBLE);

                    } else {
                        targetChangeBox.setVisibility(View.INVISIBLE);

                    }

                }
            }
        });

//        취소 버튼 눌렀을 시 onClickListener
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "취소 버튼 눌림.", Toast.LENGTH_SHORT).show();
                targetChangeBox.setVisibility(View.INVISIBLE);

            }
        });


    } // onCreate class 끝

    //날짜 타입 설정
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월");

        return  date.format(formatter);
    }


    //화면 설정
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView(){

        //년월 텍스트뷰 셋팅
        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));

        //해당 월 날짜 가져오기
        ArrayList<LocalDate> dayList = daysInMonthArray(CalendarUtil.selectedDate);

        //어뎁터 데이터 적용
        CalendarAdapter adapter = new CalendarAdapter(dayList, this);

        //레이아웃 설정(열 7개)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),7);

        //레이아웃 적용
        day_recyclerView.setLayoutManager(manager);

        //어뎁터 적용
        day_recyclerView.setAdapter(adapter);
    }

    //날짜 생성
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<LocalDate> daysInMonthArray(LocalDate date){

        ArrayList<LocalDate> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        // 해당 월 마지막 날짜 가져오기기
        int lastDay = YearMonth.now().lengthOfMonth();

        // 해당 월의 첫 번째 날 가져오기
        LocalDate firstDay = CalendarUtil.selectedDate.withDayOfMonth(1);

        //첫 번째 날 요일 가져오기
        int dayofWeek = firstDay.getDayOfWeek().getValue();

        //날짜 생성
        for (int i = 1; i < 42; i++) {
            if (i <= dayofWeek || i > lastDay + dayofWeek) {
                dayList.add(null);
            } else {
                dayList.add(LocalDate.of(CalendarUtil.selectedDate.getYear(), CalendarUtil.selectedDate.getMonth(),
                        i - dayofWeek));
            }
        }
        return dayList;
    }


    public void stampButtonClicked(View v) {
        //Toast.makeText(MainActivity.this, "스템프 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StampPopupActivity.class);
        startActivity(intent);
    }

    public void statisticsButtonClicked(View v) {
        //Toast.makeText(MainActivity.this, "통계 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    //    일일 캘린더 버튼 눌림(리싸이클러뷰 visible하게 만들기)
    public void setRecyclerVisible() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter<String> adapter;

        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;

        ListView listView;

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("wishManage");

//        getValue();

        //Toast.makeText(MainActivity.this, "날짜 버튼 눌림.", Toast.LENGTH_SHORT).show();
        CalendarRecord = (ViewGroup) findViewById(R.id.CalendarRecord);
        selectedDate = (TextView) findViewById(R.id.selectedDate);
        selectedDate.setText(year_info + "년 " + month_info + "월 " + day_info + "일");
        CalendarRecord.setVisibility(View.VISIBLE);

    }





//    private void getValue() {
//
//        TextView record = findViewById(R.id.recordList);
//        TextView amount = findViewById(R.id.moneyAmountRecord);
//
////        DatabaseReference recordRef = FirebaseDatabase.getInstance().getReference("users/"+userID+"/userrecord/record1");
//        DatabaseReference recordRef = FirebaseDatabase.getInstance().getReference("recordManage").child(userID);
//        recordRef.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////              String memoData = snapshot.child(userID).child("memo").getValue(String.class);
//                String memoData = snapshot.child("record1").child("memo").getValue(String.class);
//                Integer amountData = snapshot.child("record1").child("moneyAmount").getValue(Integer.class);
//
//                if (memoData != null && amountData != null) {
//                    record.setText(memoData);
//                    amount.setText("금액 : " + amountData);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }

    //    하단 네비게이션 바 버튼 클릭
    public void homeButtonClicked(View v) {
    }

    public void wishstoreButtonClicked(View v) {
        Intent intent = new Intent(this, store_main.class);
        startActivity(intent);
    }

    public void mypageButtonClicked(View v) {
        Intent intent = new Intent(this, MyPageActivity.class);
        startActivity(intent);
    }

    public void plantgameButtonClicked(View v) {
        Intent gameIntent = new Intent(this, GameStart1.class);
        startActivity(gameIntent);
        finish();
    }


    // gamestart1번만 실행하기 위해 아래 이 코드 추가
//        FirebaseDatabase.getInstance().getReference("game").child(userID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                try{
//                    gameCheck = snapshot.child("gameCheck").getValue(Integer.class);
//                    if (gameCheck == 1) {
//                        Intent intent = new Intent(MainActivity.this, PlantGame.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    else {
//                        Intent intent = new Intent(MainActivity.this, GameStart1.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                } catch (Exception e){
//                    Intent intent = new Intent(MainActivity.this, GameStart1.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }

//    public class Fraglike extends Fragment {
//        private RecyclerView recyclerView;
//        private RecyclerView.Adapter adapter;
//        private RecyclerView.LayoutManager layoutManager;
//        private ArrayList<HelperClass_RecordList> arrayList;
//        private FirebaseDatabase database;
//        private DatabaseReference databaseReference;
//
//        private View view;
//
//        @Nullable
//        @Override
//        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            view = inflater.inflate(R.layout.activity_main, container, false);
//            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//            recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
//            layoutManager = new LinearLayoutManager(getContext());
//            recyclerView.setLayoutManager(layoutManager);
//            arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트 (어댑터쪽으로)
//
//            database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
//            databaseReference = database.getReference("users/"+userID+"/userrecord"); // DB 테이블 연결
//            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
//                    arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
//                        HelperClass_RecordList HelperClass_RecordList = snapshot.getValue(HelperClass_RecordList.class); // 만들어뒀던 User 객체에 데이터를 담는다.
//                        arrayList.add(HelperClass_RecordList); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
//                    }
//                    adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침해야 반영이 됨
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    // 디비를 가져오던중 에러 발생 시
//                    Log.e("Fraglike", String.valueOf(databaseError.toException())); // 에러문 출력
//                }
//            });
//            adapter = new CustomAdapter_RecordList(arrayList, getContext());
//            recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결
//
//            return view;
//        }
//    }
}

