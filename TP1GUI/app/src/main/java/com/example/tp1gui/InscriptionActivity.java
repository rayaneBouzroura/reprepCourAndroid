package com.example.tp1gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp1gui.databinding.ActivityInscriptionBinding;

public class InscriptionActivity extends AppCompatActivity {
    //sign up activity (no drawer)
    public ActivityInscriptionBinding binding;
    public Button btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.Title_InscriptionActivity));
        gestionBouton();

    }

    private void gestionBouton() {

        //gestion btn inscription
        btnInscription = binding.btnInscription;
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to accueil
                Toast.makeText(InscriptionActivity.this, "clicked btn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InscriptionActivity.this, AccueilActivity.class);
                startActivity(intent);
            }
        });

    }
}