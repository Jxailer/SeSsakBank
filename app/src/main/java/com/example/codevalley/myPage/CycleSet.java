package com.example.codevalley.myPage;

import static com.example.codevalley.LoginActivity.userID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codevalley.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CycleSet extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("cycle").child(userID);

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycleset);

//        getSupportActionBar().setTitle("< 데이터 전송 주기 변경");

        EditText et2 = (EditText) findViewById(R.id.week_set);
        et2.setFilters(new InputFilter[]{ new InputFilterMinMax("0","4")});

        Button button = (Button) findViewById(R.id.cycleBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //주기 DB에 저장
                Map<String, Object> cycleInfo = new HashMap<>();
                cycleInfo.put("week", et2.getText().toString());
                ref.updateChildren(cycleInfo);
                Toast.makeText(getApplicationContext(),"저장되었습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MyPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
