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

import org.kickmyb.transfer.HomeItemResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MyViewHolder>{
    List<HomeItemResponse> list;

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
        HomeItemResponse tacheCourante = list.get(position);
        holder.tvNomTache.setText(tacheCourante.name);
        holder.tvDateLimite.setText(tacheCourante.deadline.toString());
        //afin d'ecrire le temps ecoule pnt doit
        //recup current time pour avoir le total de temps ecoule (en millisecondes)
        Date now = new Date();
        //we take it for granted that deadlineis always after now
        //TODO ehh maybe not so in case total time span is negative check ici
        long totalTimeSpan = tacheCourante.deadline.getTime() - now.getTime();
        //get elapsed time en miliseconds
        //perte de precision mais on s'en fout
        long elapsedTimeInMili = (long)(totalTimeSpan * tacheCourante.percentageDone / 100);
        //call la method
        String formatedElapsedTime = formatDuration(holder.itemView.getContext(),elapsedTimeInMili);
        holder.tvTempEcoule.setText(formatedElapsedTime);
        holder.progressBar.setProgress(tacheCourante.percentageDone);
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

    /**
     * returns a string representing the duration in days, hours, minutes and seconds
     * @param context : context of the activity calling this method pour la localization des string
     * @param elapsedTime : elapsed time in milliseconds
     * @return
     */
    public static String formatDuration(Context context,long elapsedTime) {
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        long elapsedHours = elapsedMinutes / 60;
        long elapsedDays = elapsedHours / 24;
        return (elapsedDays + " " + context.getString(R.string.days) + " " +
                elapsedHours % 24 + " " + context.getString(R.string.hours) + " " +
                elapsedMinutes % 60 + " " + context.getString(R.string.minutes) + " " +
                elapsedSeconds % 60 + " " + context.getString(R.string.seconds));
    }
}
