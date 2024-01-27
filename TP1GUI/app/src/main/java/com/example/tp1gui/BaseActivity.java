package com.example.tp1gui;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tp1gui.R;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseActivity extends AppCompatActivity {

    //stuff we want to implement in order to make other activities
    //that inherit this one more consistent and easy to write without repeating code:

    //activite accueuil / consultations / creation :
    // - gestion drawer bar
    // - gestion bouton drawer bar selon le contexte

    //activite accueuil seul activite avec le FAB pour creer une activite

    //la methode gestionDrawerBar necessite :
    // - un drawerLayout
    // - un actionBarDrawerToggle (init l'objet necessite le contexte de l'appli "this" ainsi
    protected ActionBarDrawerToggle actionBarDrawerToggle;

    //abstract methods qui retourne le nav view et le drawer layout
    protected abstract NavigationView getNavView();
    protected abstract DrawerLayout getDrawerLayout();

    //abstract method qui gere le nav item bouton de la drawer bar selon le contexte (va etre implementer dans chaque activite)
    protected abstract void gestionBoutonDrawerBar(@NonNull MenuItem item);
    public void gestionDrawerBar() {

        NavigationView  nv = getNavView();
        DrawerLayout drawerLayout = getDrawerLayout();
        //TODO : set le nom d'utilisateur ici en utilisant le singleton UserManager lors de l'implementation serveur
        //gestion drawer bar
         actionBarDrawerToggle = new ActionBarDrawerToggle(this ,
                drawerLayout,
                R.string.nav_open,
                R.string.nav_close);
        //syncstate
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                gestionBoutonDrawerBar(item);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
