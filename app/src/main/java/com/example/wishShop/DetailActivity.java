package com.example.wishShop;

import static com.example.adult.adult_LoginActivity.childID;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailStamp, detailTitle;
    Button editButton, deleteButton;
    String key = "";
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailStamp = findViewById(R.id.detailStamp);
        detailTitle = findViewById(R.id.detailTitle);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("wishDesc"));
            detailStamp.setText(bundle.getString("wishStamp"));
            detailTitle.setText(bundle.getString("wishTitle"));
            key = bundle.getString("Key");
            title = bundle.getString("wishTitle");
        }



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("wishTitle", detailTitle.getText().toString())
                        .putExtra("wishStamp", detailStamp.getText().toString())
                        .putExtra("wishDesc", detailDesc.getText().toString())
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }

    public void showdialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
//        dialog.setIcon(R.mipmap.ic_launcher);//알림창 아이콘 설정
//        dialog.setTitle("알림창");
        dialog.setMessage("소원권을 삭제하시겠어요?"); //알림창 메세지 설정

        //알림창의 닫기를 눌렀을때 발생 이벤트 설정
        dialog.setNeutralButton("취소",null); //아무 이벤트 발생하지 않게 하기위하여 null로 설정

        dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("wishManage").child(childID);
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//
//                StorageReference storageReference = storage.getReference(title);
//                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
                reference.child(title).removeValue();
//                        Toast.makeText(DetailActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(), WishShopActivity.class));
//                        finish();
//                    }
//                });
                Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), WishShopActivity.class));
                finish();
            }//예를 눌렀을때, 데이터가 삭제되고 토스트 알림메세지 뜸.
        });
        dialog.show();
    }
}