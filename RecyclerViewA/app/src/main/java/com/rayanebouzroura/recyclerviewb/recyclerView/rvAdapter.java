package com.rayanebouzroura.recyclerviewb.recyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rayanebouzroura.recyclerviewb.R;

import java.util.ArrayList;
import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MyViewHolder> {

    //list of secrts
    public List<Secret> list = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Secret currentSecret = list.get(position);
        holder.tvName.setText(currentSecret.nom);
        holder.tvDate.setText(currentSecret.date.toString());
        holder.tvNbr.setText(currentSecret.nbGrand.toString() + " %");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder{

        //each data item we shall be implementing from the kitten class
        public TextView tvName;
        public TextView tvDate;

        public ProgressBar pb;

        public TextView tvNbr;
        //constructor for the itty witty view holder uwu
        public MyViewHolder(LinearLayout v){
            super(v);
            tvName = v.findViewById(R.id.tvNom);
            tvDate = v. findViewById(R.id.tvDate);
            tvNbr = v.findViewById(R.id.tvProgress);
        }
    }
}
