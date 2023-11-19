package com.example.codevalley.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adult.post.BoardWrite;
import com.example.codevalley.R;
import com.example.codevalley.wishStore.store_adapter;

import java.util.ArrayList;

public class communityAdapter extends RecyclerView.Adapter<communityAdapter.ViewHolder> {
    private ArrayList<BoardWrite> boardWrites = new ArrayList<>();
    private Context context;

    public communityAdapter(Context context, ArrayList<BoardWrite> boardWrites){
        this.context = context;
        this.boardWrites = boardWrites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.community_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoardWrite boardWrite = boardWrites.get(holder.getAdapterPosition());
        holder.adminCommunityText.setText(boardWrite.getUser_text());
    }

    @Override
    public int getItemCount() {
        return (boardWrites != null ? boardWrites.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView adminCommunityText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            adminCommunityText = itemView.findViewById(R.id.admin_community_text);
        }
    }
}
