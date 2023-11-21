package com.example.calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codevalley.MainActivity;
import com.example.codevalley.R;
import com.example.codevalley.recordListHelper.IncomeRecordCreate;
import com.example.codevalley.recordListHelper.SpentRecordCreate;
import com.example.codevalley.wishStore.store_confirm;

import java.time.LocalDate;
import java.util.ArrayList;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    TextView dayText;
    public static Integer year_info, month_info, day_info;
    ArrayList<LocalDate> dayList;
    private Context context;
    ViewGroup CalendarRecord;


    public CalendarAdapter(ArrayList<LocalDate> dayList, Context context) {
        this.dayList = dayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        return new CalendarViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        //날짜 변수에 담기기
        LocalDate day = dayList.get(position);

        if(day == null){
            holder.dayText.setText("");
        } else {
            holder.dayText.setText(String.valueOf(day.getDayOfMonth()));

            //오늘 날짜 색상 칠하기
            if (day.equals(CalendarUtil.selectedDate)){
                holder.parentView.setBackgroundColor(Color.parseColor("#FF98BC86"));
            }
        }


        //텍스트 색상 지정(토,일)
        if ((position+1) % 7 == 0){//토요일
            holder.dayText.setTextColor(Color.parseColor("#008EDA"));
        }else if( position == 0 || position % 7 == 0){ //일요일
            holder.dayText.setTextColor(Color.parseColor("#FFCC0000"));

        }

        //날짜 클릭 이벤트
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iYear = day.getYear(); //년
                int iMonth = day.getMonthValue(); //월
                int iDay = day.getDayOfMonth(); //일

                String yearMonDay = iYear + "년" + iMonth + "월" + iDay + "일";

                Toast.makeText(holder.itemView.getContext(), yearMonDay, Toast.LENGTH_SHORT).show();


                year_info= iYear;
                month_info = iMonth;
                day_info = iDay;

//                Intent IncomeIntent = new Intent(context, IncomeRecordCreate.class);
//                IncomeIntent.putExtra("year_info", iYear);
//                IncomeIntent.putExtra("month_info", iMonth);
//                IncomeIntent.putExtra("day_info", iDay);
//
//                Intent SpentIntent = new Intent(context, SpentRecordCreate.class);
//                SpentIntent.putExtra("year_info", iYear);
//                SpentIntent.putExtra("month_info", iMonth);
//                SpentIntent.putExtra("day_info", iDay);

                Log.w("date info", iYear+","+iMonth+","+iDay);
                ((MainActivity)MainActivity.context_Main).setRecyclerVisible();



            }
        });
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }


    class CalendarViewHolder extends RecyclerView.ViewHolder{

        TextView dayText;
        View parentView;



        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayText);

            parentView = itemView.findViewById(R.id.parentView);
        }
    }
}
