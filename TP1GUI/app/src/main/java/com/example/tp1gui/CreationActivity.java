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


public class CreationActivity extends BaseActivity{
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
            if(item.getItemId() == R.id.nav_item_creerTache){
                //we are deja dans l'activite de creation de tache
                Toast.makeText(this, R.string.err_activiteLayout, Toast.LENGTH_SHORT).show();
                //close le drawer
                binding.drawerLayout.closeDrawers();
            }
            else if (item.getItemId() == R.id.nav_item_ActiviteAccueil) {
                //go to main activite
                startActivity(new Intent(this, AccueilActivity.class));
            }
            else if (item.getItemId() == R.id.nav_item_deconnexion) {
                //go to login
                startActivity(new Intent(this, ConnectionActivity.class));

            }
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




}