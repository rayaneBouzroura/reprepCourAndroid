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

public class AccueilActivity extends BaseActivity {
    //set viewBinding
    public ActivityAccueuilBinding binding;
    public RecyclerView rv;
    public rvAdapter adapter;

    //public ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;
    public NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccueuilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.AccueilActivity_Title));
        //cette method est dans BaseActivity
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
            //set le temps ecoule
            t.tempEcoule =(long) r.nextInt(1000000000);
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


    /**
     * @return
     */
    @Override
    protected NavigationView getNavView() {
        return binding.navigationView;
    }

    /**
     * @return
     */
    @Override
    protected DrawerLayout getDrawerLayout() {
        return binding.drawerLayout;

    }

    /**
     * @param item
     */
    @Override
    protected void gestionBoutonDrawerBar(@NonNull MenuItem item) {
     //if statements for each item
        if (item.getItemId() == R.id.nav_item_ActiviteAccueil) {
            //close the drawer
            binding.drawerLayout.closeDrawers();
            Toast.makeText(AccueilActivity.this, R.string.err_activiteLayout, Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.nav_item_creerTache) {
            //go to creer tache
            Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.nav_item_deconnexion) {
            //go to login
            Intent i = new Intent(AccueilActivity.this, ConnectionActivity.class);
            startActivity(i);
        }

    }



    //inflate un btn menu qui mene a l'activite de creation de tache (only present
    //do not confuse w drawer_menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



}