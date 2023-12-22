package com.example.recycleviewrecap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KittenAdapter extends RecyclerView.Adapter<KittenAdapter.MyViewHolder> {

    //list of kittens uwu
    public List<Kitten> list = new ArrayList<>();


    //creating the itty witty view holder
    public class MyViewHolder  extends RecyclerView.ViewHolder{

        //each data item we shall be implementing from the kitten class
        public TextView tvName;
        public TextView tvAge;
        public TextView tvColor;
        public TextView tvBreed;
        //the two buttons
        public ImageButton btnUp;
        public ImageButton btnDown;
        //constructor for the itty witty view holder uwu
        public MyViewHolder(LinearLayout v){
            super(v);
            tvName = v.findViewById(R.id.tvName);
            tvAge = v.findViewById(R.id.tvAge);
            tvColor = v.findViewById(R.id.tvColor);
            tvBreed = v.findViewById(R.id.tvBreed);
            btnUp = v.findViewById(R.id.btnUp);
            btnDown = v.findViewById(R.id.btnDown);
        }
    }
    //remind me from time to time to scream KHEMSAAAAAAAAAAA
    @NonNull
    @Override
    public KittenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //BIND it using viewBinder for future
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kitten_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull KittenAdapter.MyViewHolder holder, int position) {
        Kitten currentKitten = list.get(position);
        holder.tvName.setText(currentKitten.name);
        holder.tvAge.setText(currentKitten.age+"");
        holder.tvColor.setText(currentKitten.color);
        holder.tvBreed.setText(currentKitten.breed);
        //the two buttons logic (up and down)
        //up
        holder.btnUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //if current kitten top of the list then do nothing
                if(position == 0){
                    return;
                }
                //else , swap the current kitten with the one above it
                //the current kitten
                Kitten currentKitten = list.get(position);
                //the one above it
                Kitten aboveKitten = list.get(position-1);
                //swapping the above kitten to the current position
                list.set(position,aboveKitten);
                //swapping the current kitten to the above position
                list.set(position-1,currentKitten);
                //notify the adapter that the data has changed
                notifyDataSetChanged();


            }
        });

        //down
        holder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if current kitten is at the bottom of the list then do nothing
                if(position == list.size()-1){
                    return;
                }
                //else , swap the current kitten with the one below it
                //the current kitten
                Kitten currentKitten = list.get(position);
                //the one below it
                Kitten belowKitten = list.get(position+1);
                //swapping the below kitten to the current position
                list.set(position,belowKitten);
                //swapping the current kitten to the below position
                list.set(position+1,currentKitten);
                //notify the adapter that the data has changed
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    //
}
