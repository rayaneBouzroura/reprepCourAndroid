package com.example.recycleviewrecap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recycleviewrecap.databinding.ActivityMainBinding;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RecyclerView rv;
    KittenAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //fancy lil recycler view class to make the code nice n neat n comfy n cute n cuddly uwu
        initRecycler();
        remplirRecycler();
    }
    private void initRecycler() {
    rv = binding.rv;
    rv.setHasFixedSize(true);
    //use a linear layout manager
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    rv.setLayoutManager(layoutManager);

    //specify an adapter
    Adapter = new KittenAdapter();
    rv.setAdapter(Adapter);


    }
    private void remplirRecycler() {

        //create me a bunch of kittens w quirky names n attributes please :3
        Kitten kitten1 = new Kitten();
        kitten1.name = "Khemsa";
        kitten1.age = 1;
        kitten1.color = "Black";
        kitten1.breed = "Sphynx";
        Kitten kitten2 = new Kitten();
        kitten2.name = "tlata";
        kitten2.age = 2;
        kitten2.color = "White";
        kitten2.breed = "Furry";
        Kitten kitten3 = new Kitten();
        kitten3.name = "Arba";
        kitten3.age = 3;
        kitten3.color = "Grey";
        kitten3.breed = "Dragon";
        Collections.addAll(Adapter.list,kitten1,kitten2,kitten3);
        Adapter.notifyDataSetChanged();
    }


}