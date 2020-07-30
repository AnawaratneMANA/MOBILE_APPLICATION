package com.mad_2020_g08.tasktrackerversion2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList id, title, description;

    int position;

    CustomAdapter(Activity activity,Context context, ArrayList id, ArrayList title, ArrayList description){

        this.activity = activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.description = description;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        this.position = position;

        holder.id.setText(String.valueOf(id.get(position)));
        holder.title.setText(String.valueOf(title.get(position)));
        holder.description.setText(String.valueOf(description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("title",String.valueOf(title.get(position)));
                intent.putExtra("description",String.valueOf(description.get(position)));
                //context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, title, description;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_txt);
            title = itemView.findViewById(R.id.title_txt);
            description = itemView.findViewById(R.id.des_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
