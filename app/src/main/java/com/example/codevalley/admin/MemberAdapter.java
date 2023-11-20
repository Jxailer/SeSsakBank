package com.example.codevalley.admin;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codevalley.HelperClass;
import com.example.codevalley.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    private ArrayList<HelperClass> helperList;
    private Context context;

    public MemberAdapter() {

    }

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public MemberAdapter(Context context, ArrayList<HelperClass> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_item, parent, false);
        MemberAdapter.ViewHolder holder = new MemberAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.ViewHolder holder, int position) {
        holder.userId.setText(helperList.get(position).getUsername());
        holder.userName.setText(helperList.get(position).getName());
        holder.userBirth.setText(helperList.get(position).getBirth());
        holder.userPhone.setText(helperList.get(position).getPhone());
        holder.userDate.setText(helperList.get(position).getCreateDate());

        holder.delUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAbsoluteAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    if(mListener!= null){
                        mListener.onItemClick(view, pos);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (helperList != null ? helperList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView userId, userName, userBirth, userPhone, userDate;
        Button delUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.userId = itemView.findViewById(R.id.id);
            this.userName = itemView.findViewById(R.id.name);
            this.userBirth = itemView.findViewById(R.id.birth);
            this.userPhone = itemView.findViewById(R.id.phone_numbaer);
            this.userDate = itemView.findViewById(R.id.create_account_date);

            this.delUser = itemView.findViewById(R.id.del_user);
        }
    }
}
