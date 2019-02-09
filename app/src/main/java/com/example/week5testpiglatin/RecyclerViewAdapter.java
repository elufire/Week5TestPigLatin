package com.example.week5testpiglatin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week5testpiglatin.school.SAT;
import com.example.week5testpiglatin.school.School;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<School> schoolArrayList;
    Toast context;
    public RecyclerViewAdapter(ArrayList<School> schoolArrayList) {
        this.schoolArrayList = schoolArrayList;
    }

    @NonNull

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        School school = schoolArrayList.get(position);

        if(school != null){
            String name = school.getSchoolName();
            String email = school.getSchoolEmail();
            viewHolder.setSchool(school);
            viewHolder.tvemail.setText(email);
            viewHolder.tvname.setText(name);
        }
    }


    @Override
    public int getItemCount() {
        return schoolArrayList != null ? schoolArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname;
        TextView tvemail;
        School school;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            final Context context = itemView.getContext();
            tvname = itemView.findViewById(R.id.tvSchoolName);
            tvemail = itemView.findViewById(R.id.tvSchoolEmail);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String event = school.getDbn();
                    EventBus.getDefault().post(event);
                }
            });

        }
        public void setSchool(School school){ this.school = school;}

    }

}