package com.example.wishShop;

import static com.example.adult.adult_LoginActivity.childID;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class UploadActivity extends AppCompatActivity {

    Button saveButton;
    EditText uploadTopic, uploadDesc, uploadStamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadDesc = findViewById(R.id.uploadDesc);
        uploadTopic = findViewById(R.id.uploadTopic);
        uploadStamp = findViewById(R.id.uploadStamp);
        saveButton = findViewById(R.id.saveButton);

        // 메소드 실행
        stampMinMax();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Info()){
                        saveData();
                        uploadData();
                }
            }
        });
    }

    public void saveData(){
        // 저장될 때 로딩 표시 나오는 코드
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // 필수 입력 사항 미기재시
    public Boolean Info(){
        String title = uploadTopic.getText().toString();
        String stamp = uploadStamp.getText().toString();
        String desc = uploadDesc.getText().toString();

        if(title.isEmpty()) {
            uploadTopic.setError("소원권 제목을 입력해 주세요.");
            return false;
        } else if(stamp.isEmpty()) {
            uploadStamp.setError("스탬프 갯수를 설정해 주세요.");
            return false;
        } else {
            uploadTopic.setError(null);
            uploadStamp.setError(null);
            return true;
        }
    }

    // 스탬프 개수 최소, 최대 제한
    public void stampMinMax(){
        //스탬프 적는 et의 텍스트가 변경될 때
        uploadStamp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            // @SuppressLint("ResourceType")
            @Override
            //텍스트가 변경될 때마다 함수 호출
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 작성되어 있던 값을 지울 때 pareInt(" ") 에서 에러가 나기 때문에 위와 같은 과정 필요
                if(uploadStamp.getText().toString().length()>0) {
                    // 120 초과일 때
                    if (Integer.parseInt(uploadStamp.getText().toString()) > 150) {
                        //et가 150으로 변경됨
                        uploadStamp.setText("150");
                    }
                    // 1 미만일 때
                    if (Integer.parseInt(uploadStamp.getText().toString()) < 1) {
                        // et가 1로 변경됨
                        uploadStamp.setText("1");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    // 아래 코드는 topic, 설명, stamp 저장하기 위한 코드
    public void uploadData(){
        String title = uploadTopic.getText().toString();
        String stamp = uploadStamp.getText().toString();
        String desc = uploadDesc.getText().toString();

        DataClass dataClass = new DataClass(title, stamp, desc);

//        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("wishManage").child(childID).child(title)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UploadActivity.this, "추가되었습니다.",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}