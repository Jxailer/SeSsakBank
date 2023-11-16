package com.example.codevalley;

import static com.example.codevalley.LoginActivity.userID;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.game.GameStart1;
import com.example.codevalley.game.PlantGame;
import com.example.codevalley.myPage.MyPageActivity;
import com.example.codevalley.recordListHelper.CustomAdapter_RecordList;
import com.example.codevalley.recordListHelper.HelperClass_RecordList;
import com.example.codevalley.recordListHelper.IncomeRecordCreate;
import com.example.codevalley.recordListHelper.SpentRecordCreate;
import com.example.wishShop.DataClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    Context context_Main = this;
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

    int incomeSum, spentSum;

    DatabaseReference recordRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        dayButton = findViewById(R.id.day1);
        //Button targetButton = findViewById(R.id.targetButton);

        recordRef = FirebaseDatabase.getInstance().getReference("recordManage").child(userID);
        target = findViewById(R.id.targetButton);

        recordRcv = findViewById(R.id.recordRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recordRcv.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
//        incomeList = new ArrayList<>();
//        spentList = new ArrayList<>();

        DatabaseReference incomeDatabase = FirebaseDatabase.getInstance().getReference("recordManage").child(userID);
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


        // day1 버튼 클릭시
        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                getValue();


                CalendarRecord.setVisibility(View.VISIBLE);


            }
        });

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


    //    정보바 버튼 클릭
    public void monthButtonClicked(View v) {
        //Toast.makeText(MainActivity.this, "월 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    public void stampButtonClicked(View v) {
        //Toast.makeText(MainActivity.this, "스템프 버튼 눌림.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StampPopupActivity.class);
        startActivity(intent);
    }

    public void statisticsButtonClicked(View v) {
        //Toast.makeText(MainActivity.this, "통계 버튼 눌림.", Toast.LENGTH_SHORT).show();
    }

    //    일일 캘린더 버튼 눌림
    public void dayButtonClicked(View v) {
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

