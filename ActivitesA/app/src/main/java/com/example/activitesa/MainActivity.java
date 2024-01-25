package com.example.activitesa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.activitesa.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    //binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    @Override
    protected void gestionSelectionMenu(int itemId) {

        if(itemId == R.id.activity1){
            Toast.makeText(this, "nous somme dans la bonne activite", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "et je m'excuse de ne pas m'etre presenter", Toast.LENGTH_LONG).show();
        }
        else if(itemId == R.id.activity2){
            Toast.makeText(this, "desoler par rapport a l'absence 😞", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity2.class));
        }
        else if(itemId == R.id.activity3){
            Toast.makeText(this, "vraiment desole de m'ettre absente", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity3.class));
        }
    }
}