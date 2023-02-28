package com.example.shalgalt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList asg_id, asg_title, asg_owner, asg_count, asg_done;

    CustomAdapter(Activity activity, Context context, ArrayList asg_id, ArrayList asg_title, ArrayList asg_owner,
                  ArrayList asg_count,ArrayList asg_done){
        this.activity = activity;
        this.context = context;
        this.asg_id = asg_id;
        this.asg_title = asg_title;
        this.asg_owner = asg_owner;
        this.asg_count = asg_count;
        this.asg_done = asg_done;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_item, parent, false);
        return new MyViewHolder(view);
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.item_id_txt.setText(String.valueOf(asg_id.get(position)));
        holder.item_title_txt.setText(String.valueOf(asg_title.get(position)));
        holder.item_owner_txt.setText(String.valueOf(asg_owner.get(position)));
        holder.item_count_txt.setText(String.valueOf(asg_count.get(position)));
        holder.item_done_txt.setText(String.valueOf(asg_done.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, activity_update.class);
                intent.putExtra("id", String.valueOf(asg_id.get(position)));
                intent.putExtra("title", String.valueOf(asg_title.get(position)));
                intent.putExtra("owner", String.valueOf(asg_owner.get(position)));
                intent.putExtra("count", String.valueOf(asg_count.get(position)));
                intent.putExtra("isdone", String.valueOf(asg_done.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return asg_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_id_txt, item_title_txt, item_owner_txt, item_count_txt,item_done_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_id_txt = itemView.findViewById(R.id.asg_id_txt);
            item_title_txt = itemView.findViewById(R.id.asg_title_txt);
            item_owner_txt = itemView.findViewById(R.id.asg_owner_txt);
            item_count_txt = itemView.findViewById(R.id.asg_count_txt);
            item_done_txt = itemView.findViewById(R.id.asg_done_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

    }

}

