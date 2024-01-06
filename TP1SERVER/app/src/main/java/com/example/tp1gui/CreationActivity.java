package com.example.tp1gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp1gui.databinding.ActivityCreationBinding;
import com.google.android.material.navigation.NavigationView;


public class CreationActivity extends AppCompatActivity {
    public ActivityCreationBinding binding;
    public Button btnAjoutTache;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView nv;

    public DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCreationBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        gestionBouton();
        gestionDrawerBar();
    }
    private void gestionDrawerBar() {
        nv = binding.navigationView;
        drawerLayout = binding.drawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.nav_open,
                R.string.nav_close);
        drawerLayout.addDrawerListener((actionBarDrawerToggle));
        actionBarDrawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        else {
            Toast.makeText(this, "actionBar is null", Toast.LENGTH_SHORT).show();
        }
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //if statements for each item
                if (item.getItemId() == R.id.nav_item_ActiviteAccueil) {
                    //move to activite accueil
                    Intent i = new Intent(CreationActivity.this, AccueilActivity.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.nav_item_creerTache) {
                    Toast.makeText(CreationActivity.this, R.string.err_activiteLayout, Toast.LENGTH_SHORT).show();

                }
                else if (item.getItemId() == R.id.nav_item_deconnexion) {
                    //move to activite connection
                    Intent i2 = new Intent(CreationActivity.this,ConnectionActivity.class);
                    startActivity(i2);
                }
                else {
                    //Toast.makeText(ConsultationActivity.this, "item not found", Toast.LENGTH_SHORT).show();
                }
                return true;
            }



        });

    }
    private void gestionBouton() {
        btnAjoutTache = binding.btnAjoutTache;
        btnAjoutTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo implement creation de tache dans backend
                //for now we go back a l'activite accueil
                //toast
                Toast.makeText(CreationActivity.this, "hehe", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CreationActivity.this, AccueilActivity.class);
                startActivity(i);
            }
        });
    }

    //inflate un btn menu qui mene a l'activite de creation de tache
    //do not confuse w drawer_menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //on click sur le btn menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_item_creerTache){
            //we are deja dans l'activite de creation de tache
            Toast.makeText(this, R.string.err_activiteLayout, Toast.LENGTH_SHORT).show();
        }
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}