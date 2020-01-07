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
    MainActivity ma;
    int lastId = -1;
    String dayOfWeek;

    //Custom Dialog
    String courseCode, className, teacherName;
    int classNo;
    LectureDate l;

    public RecyclerViewAdapter(Context context, ArrayList<Lecture> values) {
        this.context = context;
        this.recyclerItemValues = values;
        ma = (MainActivity)context;
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

        myRecyclerViewItemHolder.courseName.setText(sm.getName());
        myRecyclerViewItemHolder.classHours.setText(sm.getHours());
        if(lastId < 0 || recyclerItemValues.get(lastId).getDates().day != recyclerItemValues.get(i).getDates().day){
            if(sm.getDates().day ==(1)){dayOfWeek = "Mon";}
            if(sm.getDates().day ==(2)){dayOfWeek = "Tue";}
            if(sm.getDates().day ==(3)){dayOfWeek = "Wed";}
            if(sm.getDates().day ==(4)){dayOfWeek = "Thu";}
            if(sm.getDates().day ==(5)){dayOfWeek = "Fri";}
            if(sm.getDates().day ==(6)){dayOfWeek = "Sat";}
            if(sm.getDates().day ==(7)){dayOfWeek = "Sun";}

            myRecyclerViewItemHolder.day.setText(dayOfWeek);
        }
        lastId = i;
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {
        TextView courseName, classHours, day;

        public RecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            courseName  = itemView.findViewById(R.id.courseName);
            classHours = itemView.findViewById(R.id.classHours);
            day = itemView.findViewById(R.id.tvDay);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Lecture lecture = recyclerItemValues.get(getLayoutPosition());
                    ma.displayDialog(getLayoutPosition(), lecture );
                    return false;
                }
            });
        }
    }
}
