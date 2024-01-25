package com.example.activitesb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.activitesb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gestionBoutons();
    }

    private void gestionBoutons() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recup du text ecrit dans le champ
                String texte = binding.editText.getText().toString();
                //creer un intent et ajouter le texte dans le ehhh...dictionary?
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("texte", texte);
                startActivity(i);
            }
        });
    }
}