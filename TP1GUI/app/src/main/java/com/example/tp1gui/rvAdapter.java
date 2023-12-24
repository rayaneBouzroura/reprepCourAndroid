package com.example.tp1gui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MyViewHolder>{
    List<Tache> list;

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public static class MyViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView tvNomTache;
    public TextView tvTempEcoule;
    public TextView tvDateLimite;
    public ProgressBar progressBar;
    public Context context;
    public MyViewHolder(LinearLayout v) {
        super(v);
        tvNomTache = v.findViewById(R.id.tvTaskName);
        tvTempEcoule = v.findViewById(R.id.tvElapsedTime);
        tvDateLimite = v.findViewById(R.id.tvDeadline);
        progressBar = v.findViewById(R.id.progresTache);
        context = v.getContext();
    }
}
    // Provide a suitable constructor (depends on the kind of dataset)
    public rvAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tache_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tache tacheCourante = list.get(position);
        holder.tvNomTache.setText(tacheCourante.nom);
        //TODO : la traduire en francais
        holder.tvDateLimite.setText(tacheCourante.dateLimite.toString());
        //transformer le temps ecoule en dateTime
        holder.tvTempEcoule.setText(formatDuration(tacheCourante.tempEcoule));
        holder.progressBar.setProgress(tacheCourante.pourcentage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("BTN", "JMEFAIT CLICKER SUR LA TACHE :"+tacheCourante.nom);
//                Intent i = new Intent(holder.itemView.getContext(),ConsultationActivity.class);
//                i.putExtra("nomTache",tacheCourante.nom);
//                holder.itemView.getContext().startActivity(i);
                Toast.makeText(holder.itemView.getContext(), "woohoo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static String formatDuration(long elapsedTime) {
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        long elapsedHours = elapsedMinutes / 60;
        long elapsedDays = elapsedHours / 24;

        return String.format("%d jours %d heurs %d minutes %d seconds", elapsedDays, elapsedHours % 24, elapsedMinutes % 60, elapsedSeconds % 60);
    }
}
