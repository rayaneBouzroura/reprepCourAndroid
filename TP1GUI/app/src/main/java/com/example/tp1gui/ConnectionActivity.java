package com.example.tp1gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tp1gui.databinding.ActivityConnectionBinding;

public class ConnectionActivity extends AppCompatActivity {

    //set viewBinding
    public ActivityConnectionBinding binding;
    public Button btnConnection;

    public Button btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bind

        super.onCreate(savedInstanceState);
        binding = ActivityConnectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.Title_Inscription));
        gestionBtn();

    }

    private void gestionBtn() {
        btnConnection = binding.btnConnection;
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent to go to accueil
                Intent intent = new Intent(ConnectionActivity.this, AccueilActivity.class);
                startActivity(intent);
            }
        });

        btnInscription = binding.btnInscription;
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent to go to inscription
                Intent intent = new Intent(ConnectionActivity.this, InscriptionActivity.class);
                startActivity(inte
            }
        });

    }
}