package com.example.tp1gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tp1gui.databinding.ActivityAccueuilBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class AccueilActivity extends AppCompatActivity {
    //set viewBinding
    public ActivityAccueuilBinding binding;
    public RecyclerView rv;
    public rvAdapter adapter;

    public ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;
    public NavigationView nv;


    private static final int NAV_ITEM_ACTIVITE_ACCUEIL = R.id.nav_item_ActiviteAccueil;
    private static final int NAV_ITEM_CREER_TACHE = R.id.nav_item_creerTache;
    private static final int NAV_ITEM_DECONNEXION = R.id.nav_item_deconnexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccueuilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.AccueilActivity_Title));
        gestionDrawerBar();
        initRecycler();
        remplirRecycler();

    }



    private void remplirRecycler() {
        //creer environs 200 taches
        ArrayList<Tache> taches = new ArrayList<>();
        //random obj pour randomiser quelques elements
        Random r = new Random();
        for (int i = 0; i < 200; i++) {
            //creer une tache
            Tache t = new Tache();
            //set le titre
            t.nom = "Tache " + i;
            //set le pourcentage
            t.pourcentage = r.nextInt(100);
            //set la date
            t.dateLimite = new Date();
            //set le statut
            t.tempEcoule =(long) r.nextInt(100);
            //ajouter la tache a la liste
            adapter.list.add(t);
        }
        adapter.notifyDataSetChanged();

    }

    private void initRecycler() {
        rv = binding.rvAccueuil;
        rv.setHasFixedSize(true);
        //usage du linearLayout manager
        rv.setLayoutManager(new LinearLayoutManager(this));

        //specifier l'adapter
        adapter = new rvAdapter();
        rv.setAdapter(adapter);
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
               if (item.getItemId() == NAV_ITEM_ACTIVITE_ACCUEIL) {
                  //rien a faire
                   Toast.makeText(AccueilActivity.this, R.string.err_activiteLayout, Toast.LENGTH_SHORT).show();
              }
              else if (item.getItemId() == NAV_ITEM_CREER_TACHE) {
                   //Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
                   //startActivity(i);
               }
               else if (item.getItemId() == NAV_ITEM_DECONNEXION) {
                   Intent i2 = new Intent(AccueilActivity.this,ConnectionActivity.class);
                   startActivity(i2);
               }
               else {
                   Toast.makeText(AccueilActivity.this, "item not found", Toast.LENGTH_SHORT).show();
               }
               return true;
           }
      });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            // If the hamburger icon is pressed, show a toast
            Toast.makeText(this, "Hamburger icon pressed!", Toast.LENGTH_SHORT).show();
            return true;
        }
        // Handle your other action bar items...
        int id = item.getItemId();

        // Other items can be handled here if any

        return super.onOptionsItemSelected(item);
    }


    //inflate un btn menu qui mene a l'activite de creation de tache
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

}