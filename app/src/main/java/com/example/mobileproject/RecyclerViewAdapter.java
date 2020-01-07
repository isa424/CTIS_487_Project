package com.example.mobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Lecture> recyclerItemValues;

    //Custom Dialog
    String courseCode, className, teacherName;
    int classNo;
    LectureDate l;

    public RecyclerViewAdapter(Context context, ArrayList<Lecture> values) {
        this.context = context;
        this.recyclerItemValues = values;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recycler_layout, viewGroup, false);

        RecyclerViewItemHolder mViewHolder = new RecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder myRecyclerViewItemHolder, int i) {
        final Lecture sm = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.courseCode.setText(sm.getName());
        myRecyclerViewItemHolder.classHours.setText(sm.getDates());
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {
        TextView courseCode, classHours;

        public RecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            courseCode  = itemView.findViewById(R.id.courseCode);
            classHours = itemView.findViewById(R.id.classHours);

        }
    }
}
