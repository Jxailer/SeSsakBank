package com.example.codevalley.wishStore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codevalley.R;
import com.example.wishShop.DataClass;

import java.util.ArrayList;

public class store_adapter extends RecyclerView.Adapter<store_adapter.ViewHolder> {
    private ArrayList<DataClass> dataList;
    private Context context;

    public store_adapter(Context context, ArrayList<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_wishlist_item, parent, false);
        store_adapter.ViewHolder holder = new store_adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull store_adapter.ViewHolder holder, int position) {
        holder.wishTitle.setText(dataList.get(position).getDataTitle());
        holder.stampCnt.setText(String.valueOf(dataList.get(position).getDataStamp()));

        holder.stampCnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wish_title = holder.wishTitle.getText().toString();
                String stamp_price = holder.stampCnt.getText().toString();

                Intent confirmIntent = new Intent(context, store_confirm.class);
                confirmIntent.putExtra("wish_title", wish_title);
                confirmIntent.putExtra("stamp_price", Integer.valueOf(stamp_price));
                context.startActivity(confirmIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null ? dataList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wishTitle;
        Button stampCnt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.wishTitle = itemView.findViewById(R.id.ur_wish);
            this.stampCnt = itemView.findViewById(R.id.need_stamp);
        }
    }
}
