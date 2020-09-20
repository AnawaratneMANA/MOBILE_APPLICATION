package com.mad_2020_g08.tasktrackerversion2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    private Context context;
    Activity activity;
    private ArrayList id, title, description;
    ArrayList<String> arrayList;

    Animation translate_anim;

    int position;

    public CustomAdapter(Activity activity,Context context, ArrayList id, ArrayList title, ArrayList description){

        this.activity = activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.description = description;
        arrayList = new ArrayList<>(title);
        //this.arrayList = new ArrayList<>(title);
        //this.arrayList = new ArrayList<>(description);


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

    //Search data
    @Override
    public Filter getFilter() {
        return filter;
    }

    final Filter filter = new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<String> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(arrayList);
            } else {
                for (String result : arrayList) {
                    if (result.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(result);
                    }

                }


            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        //run on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            arrayList.clear();
            arrayList.addAll((ArrayList<String>) filterResults.values);
            //arrayList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, title, description;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_txt);
            title = itemView.findViewById(R.id.title_txt);
            description = itemView.findViewById(R.id.des_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animate RecyclerView
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
