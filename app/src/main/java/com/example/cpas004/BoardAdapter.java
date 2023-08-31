package com.example.cpas004;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codevalley.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardVH>{

    private Context context;

    ArrayList<BoardWrite> list = new ArrayList<>();

    public BoardAdapter(Context context, ArrayList<BoardWrite> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BoardVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new BoardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardVH holder, int position) {

        BoardWrite boardwrite = list.get(holder.getAdapterPosition());

        // 글쓴이
        holder.writeUser.setText(boardwrite.getUser_key());

        //제목
        holder.titleText.setText(boardwrite.getUser_title());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BoardVH extends RecyclerView.ViewHolder{

        TextView titleText;

        TextView writeUser;

        CardView cardView;

        public BoardVH(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.title_text);

            writeUser = itemView.findViewById(R.id.title_user);

            cardView = itemView.findViewById(R.id.board_card_view);
        }
    }
}
