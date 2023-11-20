package com.example.codevalley.admin;

import android.content.Context;
import android.telephony.ims.RcsUceAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adult.post.BoardWrite;
import com.example.codevalley.R;

import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private ArrayList<BoardWrite> boardList;
    private Context context;
    public CommunityAdapter(){

    }

    public interface  OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public CommunityAdapter(Context context, ArrayList<BoardWrite> boardList){
        this.context = context;
        this.boardList = boardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.community_list_item, parent, false);
        CommunityAdapter.ViewHolder holder = new CommunityAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cK.setText(boardList.get(position).getUser_key());
        holder.cTit.setText(boardList.get(position).getUser_title());
        holder.cTxt.setText(boardList.get(position).getUser_text());
        holder.cWtr.setText(boardList.get(position).getUser_nick());
        holder.cD.setText(boardList.get(position).getUser_time());

        holder.commuDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAbsoluteAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    if(mListener != null){
                        mListener.onItemClick(view, pos);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (boardList != null ? boardList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cK, cTit, cTxt, cWtr, cD;
        Button commuDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cK = itemView.findViewById(R.id.c_d);
            this.cTit = itemView.findViewById(R.id.c_tit);
            this.cTxt = itemView.findViewById(R.id.c_txt);
            this.cWtr = itemView.findViewById(R.id.c_wtr);
            this.cD = itemView.findViewById(R.id.c_d);

            this.commuDel = itemView.findViewById(R.id.commu_del);
        }
    }
}
