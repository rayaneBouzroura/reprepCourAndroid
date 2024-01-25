package com.example.activitesb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.activitesb.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    //binding
    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //recup du text bundled dans l'intent
        recupText();
    }

    private void recupText() {
        String texte = getIntent().getStringExtra("texte");
        binding.tv.setText(texte);
    }
}