package com.example.finalmadproject.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.finalmadproject.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    private Context context;
    Activity activity;
    private ArrayList id, title, description;
    ArrayList idFiltered, titleFiltered, descriptionFiltered;
    ArrayList<String> arrayList;
    Button viewTaskBtn;

    Animation translate_anim;

    public static final String EXTRA_ID = "com.example.finalmadproject.List.EXTRA_ID";

    int position;

    public CustomAdapter(Activity activity,Context context, ArrayList id, ArrayList title, ArrayList description){

        this.activity = activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.description = description;
        this.idFiltered = id;
        this.titleFiltered = title;
        this.descriptionFiltered = description;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.position = position;
        View view = inflater.inflate(R.layout.my_row,parent,false);
        viewTaskBtn = view.findViewById(R.id.btnView);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        this.position = position;
        //final String msg = String.valueOf(idFiltered.get(position));

        holder.id.setText(String.valueOf(idFiltered.get(position)));
        holder.title.setText(String.valueOf(titleFiltered.get(position)));
        holder.description.setText(String.valueOf(descriptionFiltered.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(idFiltered.get(position)));
                intent.putExtra("title",String.valueOf(titleFiltered.get(position)));
                intent.putExtra("description",String.valueOf(descriptionFiltered.get(position)));
                //context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });


        final String msg = String.valueOf(idFiltered.get(position));
        viewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context.getApplicationContext(), ViewAllTasks.class);
                intent1.putExtra(EXTRA_ID,msg).toString();
                System.out.println("ll : " + msg);
                view.getContext().startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return idFiltered.size();
    }

    //Search data
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    idFiltered = id;
                    titleFiltered = title;
                    descriptionFiltered = description;
                } else {
                    ArrayList filteredId = new ArrayList();
                    ArrayList filteredTitle = new ArrayList();
                    ArrayList filteredDescription = new ArrayList();
                    for (int i = 0; i < title.size(); i++) {
                        if (((String) title.get(i)).toLowerCase().contains(charString.toLowerCase())) {
                            filteredId.add((String) id.get(i));
                            filteredTitle.add((String) title.get(i));
                            filteredDescription.add((String) description.get(i));
                        }
                    }
                    idFiltered = filteredId;
                    titleFiltered = filteredTitle;
                    descriptionFiltered = filteredDescription;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = titleFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
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

            //Animate RecyclerView
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }


}
