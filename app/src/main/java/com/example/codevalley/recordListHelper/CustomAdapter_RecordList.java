package com.example.codevalley.recordListHelper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.codevalley.R;
import com.example.codevalley.recordListHelper.HelperClass_RecordList;





public class CustomAdapter_RecordList extends RecyclerView.Adapter<CustomAdapter_RecordList.CustomViewHolder> {

    private ArrayList<CustomAdapter_RecordList> arrayList;
    private Context context;
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

    public CustomAdapter_RecordList(ArrayList<CustomAdapter_RecordList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordlist_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.recordMemo.setText(arrayList.get(position).getMemo());
        holder.recordAmount.setText(arrayList.get(position).getMoneyAmount());
    }

//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
////        Glide.with(context).load(dataList.get(position).getDataTitle()).into(holder.recTitle);
//        holder.recordMemo.setText(arrayList.get(position).getMemo());
//
//        holder.recordAmount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DailyRecordManagePopup.class);
//                intent.putExtra("wishDesc", arrayList.get(holder.getAdapterPosition()).getDataDesc());
//                intent.putExtra("wishStamp", arrayList.get(holder.getAdapterPosition()).getDataStamp());
//                intent.putExtra("wishTitle", arrayList.get(holder.getAdapterPosition()).getDataTitle());
//                intent.putExtra("key", arrayList.get(holder.getAdapterPosition()).getKey());
//
//                context.startActivity(intent);
//            }
//        });
//    }


    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView recordMemo;
        TextView recordAmount;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recordMemo = itemView.findViewById(R.id.recordMemo);
            this.recordAmount = itemView.findViewById(R.id.recordAmount);
        }

    }
}