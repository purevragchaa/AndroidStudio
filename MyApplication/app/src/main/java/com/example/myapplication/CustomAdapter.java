package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, title, instruction, deadline, state;
    private int position;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList title, ArrayList instruction,
                  ArrayList deadline,ArrayList state){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.instruction = instruction;
        this.deadline = deadline;
        this.state = state;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        this.position = position;
        holder.todo_id_txt.setText(String.valueOf(id.get(position)));
        holder.todo_title_txt.setText(String.valueOf(title.get(position)));
        holder.todo_instruction_txt.setText(String.valueOf(instruction.get(position)));
        holder.todo_deadline_txt.setText(String.valueOf(deadline.get(position)));
        holder.todo_state_txt.setText(String.valueOf(state.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Activity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("title", String.valueOf(title.get(position)));
                intent.putExtra("instruction", String.valueOf(instruction.get(position)));
                intent.putExtra("deadline", String.valueOf(deadline.get(position)));
                intent.putExtra("isdone", String.valueOf(state.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView todo_id_txt, todo_title_txt, todo_instruction_txt, todo_deadline_txt,todo_state_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            todo_id_txt = itemView.findViewById(R.id.todo_id_txt);
            todo_title_txt = itemView.findViewById(R.id.todo_title_txt);
            todo_instruction_txt = itemView.findViewById(R.id.todo_instruction_txt);
            todo_deadline_txt = itemView.findViewById(R.id.todo_deadline_txt);
            todo_state_txt = itemView.findViewById(R.id.todo_state_txt);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}



