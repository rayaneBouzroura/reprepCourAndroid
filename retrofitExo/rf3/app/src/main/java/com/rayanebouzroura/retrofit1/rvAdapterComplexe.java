package com.rayanebouzroura.retrofit1;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rayanebouzroura.retrofit1.http.ComplexResponse;

import java.util.ArrayList;
import java.util.List;

public class rvAdapterComplexe extends RecyclerView.Adapter<rvAdapterComplexe.MyViewHolder>{
    List<ComplexResponse> list;


    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvA;
        public TextView tvB;
        public TextView tvC;
        public MyViewHolder(LinearLayout v) {
            super(v);
            tvA = v.findViewById(R.id.tvA);
            tvB = v.findViewById(R.id.tvB);
            tvC = v.findViewById(R.id.tvC);

        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public rvAdapterComplexe() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tache_item_complexe, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //get current item
        ComplexResponse current = list.get(position);
        holder.tvA.setText(current.a.toString());
        holder.tvB.setText(current.b);
        holder.tvC.setText(current.c.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
