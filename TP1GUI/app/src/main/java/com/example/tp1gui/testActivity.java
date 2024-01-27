package com.example.tp1gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tp1gui.R;
import com.example.tp1gui.databinding.ActivityAccueuilBinding;
import com.example.tp1gui.databinding.ActivityTestBinding;
import com.google.android.material.navigation.NavigationView;

public class testActivity extends BaseActivity {
    public ActivityTestBinding binding;

    public rvAdapter adapter;

    public ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;
    public NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Test");
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

        if (item.getItemId() == R.id.nav_item_ActiviteAccueil) {
            //go to main activite
            startActivity(new Intent(this, AccueilActivity.class));
        } else if (item.getItemId() == R.id.nav_item_creerTache) {
            //go to creer tache
            startActivity(new Intent(this, CreationActivity.class));
        } else if (item.getItemId() == R.id.nav_item_deconnexion) {
            //go to login
            startActivity(new Intent(this, ConnectionActivity.class));
        }



    }
}