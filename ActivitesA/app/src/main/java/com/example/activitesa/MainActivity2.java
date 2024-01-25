package com.example.activitesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void gestionSelectionMenu(int itemId) {

        if(itemId == R.id.activity1){
            Toast.makeText(this, "et je m'excuse de ne pas m'etre presenter", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }
        else if(itemId == R.id.activity2){
            Toast.makeText(this, "desoler par rapport a l'absence 😞", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "nous somme dans activity2", Toast.LENGTH_SHORT).show();
        }
        else if(itemId == R.id.activity3){
            Toast.makeText(this, "vraiment desole de m'ettre absente", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity3.class));
        }
    }
}