package com.rayanebouzroura.recyclerviewb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rayanebouzroura.recyclerviewb.databinding.ActivityMainBinding;
import com.rayanebouzroura.recyclerviewb.recyclerView.Secret;
import com.rayanebouzroura.recyclerviewb.recyclerView.rvAdapter;

import java.time.LocalDateTime;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private rvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initRecycler();
        remplirRecycler();
    }

    private void remplirRecycler() {
        //create genre 10 secret obj
        for (int i = 0 ; i < 10 ; i++){
            Secret s = new Secret();
            s.date = LocalDateTime.now();
            s.nom = "Secret " + i;
            s.nbGrand = (long) i;
            adapter.list.add(s);
        }
        adapter.notifyDataSetChanged();
    }

    private void initRecycler() {
        RecyclerView rv = binding.rv;
        rv.setHasFixedSize(true);
        //use linearlayoutmngr
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        //specifie l'adapter
        adapter = new rvAdapter();
        rv.setAdapter(adapter);
    }
}