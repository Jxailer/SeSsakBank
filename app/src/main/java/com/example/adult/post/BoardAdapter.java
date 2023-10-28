package com.example.adult.post;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        //holder.writeUser.setText(boardwrite.getUser_key());
        holder.writeUser.setText("익명"); // 홀더 설정 => 닉네임을 바꾸기



        //제목
        holder.titleText.setText(boardwrite.getUser_title());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, BoardUpdateActivity.class); //BoardUpdateActivity.class
                //Intent intent2 = new Intent(context, commentActivity.class); //commentActivity
                intent.putExtra("key", boardwrite.getUser_key());
                intent.putExtra("title", boardwrite.getUser_title());
                intent.putExtra("write", boardwrite.getUser_text());
                context.startActivity(intent);
                //context.startActivity(intent2);
            }


        });

        holder.imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, commentActivity.class);
                intent2.putExtra("key", boardwrite.getUser_key());
                intent2.putExtra("title", boardwrite.getUser_title());
                intent2.putExtra("write", boardwrite.getUser_text());
                context.startActivity(intent2);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class BoardVH extends RecyclerView.ViewHolder{

        TextView titleText;

        TextView writeUser;

        CardView cardView;

        ImageButton imgBtn;

        public BoardVH(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.title_text);

            writeUser = itemView.findViewById(R.id.title_user);

            cardView = itemView.findViewById(R.id.board_card_view);

            imgBtn = itemView.findViewById(R.id.commentBtn);


        }
    }
}
