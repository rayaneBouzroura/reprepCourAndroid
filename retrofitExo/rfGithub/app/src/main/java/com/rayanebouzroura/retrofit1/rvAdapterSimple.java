package com.rayanebouzroura.retrofit1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class rvAdapterSimple extends RecyclerView.Adapter<rvAdapterSimple.MyViewHolder>{
    List<Long> list;


    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvLong;
        public MyViewHolder(LinearLayout v) {
            super(v);
            tvLong = v.findViewById(R.id.tvLong);

        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public rvAdapterSimple() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tache_item_simple, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvLong.setText(String.valueOf(list.get(position)));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
