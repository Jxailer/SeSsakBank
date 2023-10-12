package com.example.post_Adult;

import static com.example.codevalley.R.id.commentBtn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codevalley.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class commentActivity extends AppCompatActivity {


    //1. 댓글 리스트(33L까지)
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listView;

    TextView commentTitle;
    TextView commentText;
    String sKey, sTitle, sText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // 1. 기존 글목록 불러오기 부분
        DAOBoardWrite dao = new DAOBoardWrite();

        commentTitle = findViewById(R.id.comment_title);
        commentText = findViewById(R.id.comment_text);
        
        getAndSetIntentData();

        // 2. 댓글 기능
        // 컴포넌트 변수에 담기
        EditText commentEdit = findViewById(R.id.comment_edit);
        ImageButton comment_addBtn = findViewById(R.id.comment_addBtn);
        listView = findViewById(R.id.comment_list_view);


        //어뎁터 초기화
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayList);

        //데이터베이스 초기화
        firebaseDatabase = FirebaseDatabase.getInstance();
        //레퍼런스 초기화
        //databaseReference = firebaseDatabase.getReference("Post/comment").child(sKey);
        databaseReference = firebaseDatabase.getReference("Post").child(sKey).child("comment");

        //데이터 조회
        getValue();
        // 데이터 등록
        comment_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력값 변수에 담기
                String sComment = commentEdit.getText().toString();
                // 키 생성
                String sKey = databaseReference.push().getKey();

                // skey가 null이 아니면 skey 값으로 저장한다.
                if(sKey != null){
                    //databaseReference.child(sKey).child("value").setValue(sComment);
                    databaseReference.child(sKey).child("value").setValue(sComment);

                    //입력창 초기화화
                    commentEdit.setText("");
                }
           }
        });

    
    } //onCreate

    // 파이어베이스에서 댓글 데이터 가져오기
    private void getValue() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //리스트 초기화
                arrayList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    //데이터 가져오기
                    String sValue = dataSnapshot.child("value").getValue(String.class);

                    //리스트에 변수를 담느다.
                    arrayList.add(sValue);
                }
                //리스트뷰 어뎁터 설정
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(commentActivity.this, "error:" + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 2. comment화면에 보일 데이터 받아서 화면에 보여주기기
   private void getAndSetIntentData() {

        //값 있는지 체크
      if(getIntent().hasExtra("key") && getIntent().hasExtra("title") &&
               getIntent().hasExtra("write")){

           //데이터 가져오기
           sKey = getIntent().getStringExtra("key");
           sTitle = getIntent().getStringExtra("title");
           sText = getIntent().getStringExtra("write");

           //데이터 넣기
           commentTitle.setText(sTitle);
           commentText.setText(sText);
       }
    }


}