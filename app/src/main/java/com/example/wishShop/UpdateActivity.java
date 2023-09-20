package com.example.wishShop;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText updateTitle, updateStamp, updateDesc;
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

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                updateData();
                Intent intent = new Intent(UpdateActivity.this, WishShopActivity.class);
                startActivity(intent);
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
    public void updateData(){
        title = updateTitle.getText().toString().trim();
        stamp = updateStamp.getText().toString().trim();
        desc = updateDesc.getText().toString().trim();

        DataClass dataClass = new DataClass(title, stamp, desc);

        FirebaseDatabase.getInstance().getReference("wishManage").child(oldTitle)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReference("title");
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