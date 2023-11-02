package com.example.wishShop;

import static com.example.adult.adult_LoginActivity.childID;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UpdateActivity extends AppCompatActivity {

    Button updateButton;
    EditText updateStamp, updateDesc;
    TextView updateTitle;
    String title, stamp, desc;
    String key, oldTitle;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateButton);
        updateTitle = findViewById(R.id.updateTitle);
        updateStamp = findViewById(R.id.updateStamp);
        updateDesc = findViewById(R.id.updateDesc);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            updateTitle.setText(bundle.getString("wishTitle"));
            updateStamp.setText(bundle.getString("wishStamp"));
            updateDesc.setText(bundle.getString("wishDesc"));
            key = bundle.getString("key");
            oldTitle = bundle.getString("wishTitle");
        }

        // 메소드 실행
        stampMinMax();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Info()){
                    saveData();
                    updateData();
                    Intent intent = new Intent(UpdateActivity.this, WishShopActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void saveData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // 필수 입력 사항 미기재시
    public Boolean Info(){
        String stamp = updateStamp.getText().toString();
        String desc = updateDesc.getText().toString();

        if(stamp.isEmpty()) {
            updateStamp.setError("스탬프 갯수를 설정해 주세요.");
            return false;
        } else {
            updateTitle.setError(null);
            updateStamp.setError(null);
            return true;
        }
    }

    // 스탬프 개수 최소, 최대 제한
    public void stampMinMax(){
        //스탬프 적는 et의 텍스트가 변경될 때
        updateStamp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            // @SuppressLint("ResourceType")
            @Override
            //텍스트가 변경될 때마다 함수 호출
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 작성되어 있던 값을 지울 때 pareInt(" ") 에서 에러가 나기 때문에 위와 같은 과정 필요
                if(updateStamp.getText().toString().length()>0) {
                    // 120 초과일 때
                    if (Integer.parseInt(updateStamp.getText().toString()) > 150) {
                        //et가 150으로 변경됨
                        updateStamp.setText("150");
                    }
                    // 1 미만일 때
                    if (Integer.parseInt(updateStamp.getText().toString()) < 1) {
                        // et가 1로 변경됨
                        updateStamp.setText("1");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void updateData(){
        title = updateTitle.getText().toString().trim();
        stamp = updateStamp.getText().toString().trim();
        desc = updateDesc.getText().toString().trim();

        DataClass dataClass = new DataClass(title, stamp, desc);

        FirebaseDatabase.getInstance().getReference("wishManage").child(childID).child(oldTitle)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReference("wishManage").child(childID).child(title);
                    reference.delete();
                    Toast.makeText(UpdateActivity.this, "수정 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}