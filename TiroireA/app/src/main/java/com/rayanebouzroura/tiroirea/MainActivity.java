package com.rayanebouzroura.tiroirea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rayanebouzroura.tiroirea.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        gestionDrawerBar();
    }

    private void gestionDrawerBar() {
        NavigationView nv = binding.navigationView;
        DrawerLayout drawerLayout = binding.drawerLayout;

        abdt = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //syncstate (sync l'etat du drawer bar avec le drawer layout)
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //gestions des item du menu drawerbar
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_item_ActiviteAccueil) {
                    //close the whole app
                    finish();
                }
                else if (item.getItemId() == R.id.nav_item_profil) {
                    //toast avec mon propre ptit nom
                    Toast.makeText(MainActivity.this, "rayane bouzroura", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.nav_item_deconnexion) {
                    //Show un log
                    Log.d("MainActivity", "deconnexion");
                }
                return false;
            }
        });

    }

    //uncomment to make le hamburger menu work

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (abdt.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}