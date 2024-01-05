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
                //recup current tache and send it to activite consultation
                Intent intent = new Intent(holder.itemView.getContext(), ConsultationActivity.class);
                //oft instead of sending the whole object (by implementing some stupid interface to make the object sendable via intent(cringe))
                //we do the second logical thing and send all the object's data
                //through the intent dictionary and recreate the object
                //PROBLEM : pourra pas modifier l'avancement then update it dans le RV etant donner qu'on a pas a persistent collection of taches mais plutot des taches statiques
                //SOLUTION : ask le prof when le time comes
                //creation des valeur du dico de la tache
                intent.putExtra("nom",tacheCourante.nom);
                intent.putExtra("pourcentage",tacheCourante.pourcentage);
                intent.putExtra("dateLimite",tacheCourante.dateLimite.toString());
                intent.putExtra("tempEcoule",formatDuration(tacheCourante.tempEcoule));
                //go to ze new activity du context present
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //cette method takes a long (in milliseconds)
    // and returns a string representing the duration in days, hours, minutes and seconds
    public static String formatDuration(long elapsedTime) {
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        long elapsedHours = elapsedMinutes / 60;
        long elapsedDays = elapsedHours / 24;
        return (elapsedDays + " days " + elapsedHours % 24  + " hours " + elapsedMinutes % 60 + " minutes " + elapsedSeconds % 60+ " seconds");
        //return String.format("%d jours %d heurs %d minutes %d seconds", elapsedDays, elapsedHours % 24, elapsedMinutes % 60, elapsedSeconds % 60);
    }
}
