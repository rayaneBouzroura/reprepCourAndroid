package com.example.tp1gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tp1gui.databinding.ActivityConsultationBinding;
import com.google.android.material.navigation.NavigationView;

public class ConsultationActivity extends AppCompatActivity {
     private ActivityConsultationBinding binding;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;
    public NavigationView nv;
    public SeekBar sb;
    public TextView seekBarPercentage;
    public Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityConsultationBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        gestionDrawerBar();
        montrerTache();
        gestionSeekBar();
        gestionBouton();
    }

    private void gestionBouton() {
        btnUpdate = binding.btnUpdate;
        btnUpdate.setOnClickListener(v -> {
            //TODO call api pour update la tache when we implement the back end
            //for now we just go back to activite accueil
            Intent i = new Intent(ConsultationActivity.this, AccueilActivity.class);
            startActivity(i);
        });

    }

    private void gestionSeekBar() {
        //when seekbar value gets updated , we update the pourcentage textvue to show
        //TODO call api pour update la tache when we implement the back end
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int pourcentage, boolean fromUser) {
                //ont update le textview
                seekBarPercentage = binding.tvProgressPercentage;
                seekBarPercentage.setText(pourcentage + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //rien a faire (jsp what to update)
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //rien a faire (jsp what to update)
            }
        });
    }

    private void montrerTache() {
        //recuperer donner tache de l'intent et les showcase dans les textview
        Intent i = getIntent();
        //les data present dans le intent sont :
//        intent.putExtra("nom",tacheCourante.nom);
//        intent.putExtra("pourcentage",tacheCourante.pourcentage);
//        intent.putExtra("dateLimite",tacheCourante.dateLimite);
//        intent.putExtra("tempEcoule",tacheCourante.tempEcoule);
        //recup les data et les afficher
        String nom = i.getStringExtra("nom");
        int pourcentage = i.getIntExtra("pourcentage",0);
        String dateLimite = i.getStringExtra("dateLimite");
        String tempEcoule = i.getStringExtra("tempEcoule");
        //afficher tache
        binding.tvConsultation.setText(nom);
        binding.tvProgressPercentage.setText(pourcentage + "%");
        binding.tvDate.setText(dateLimite);
        binding.tvTempsEcoule.setText(tempEcoule);
        //afficher info progressbar
        sb = binding.seekBar;
        sb.setProgress(pourcentage);
        //gestion de changement de valeur de seekbar
        //TODO : changement pourcentage tache ask prof
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(ConsultationActivity.this, "not implemented 😞😞😞", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
                    Intent i3 = new Intent(ConsultationActivity.this, AccueilActivity.class);
                    startActivity(i3);
                }
                else if (item.getItemId() == R.id.nav_item_creerTache) {
                    //move to activite creation
                    Intent i = new Intent(ConsultationActivity.this, CreationActivity.class);
                    startActivity(i);

                }
                else if (item.getItemId() == R.id.nav_item_deconnexion) {
                    //move to activite connection
                    Intent i2 = new Intent(ConsultationActivity.this,ConnectionActivity.class);
                    startActivity(i2);
                }
                else {
                    //Toast.makeText(ConsultationActivity.this, "item not found", Toast.LENGTH_SHORT).show();
                }
                return true;
            }



        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_item_creerTache){
            Intent i = new Intent(ConsultationActivity.this, CreationActivity.class);
            startActivity(i);
            return true;
        }
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    //inflate un btn menu qui mene a l'activite de creation de tache
    //do not confuse w drawer_menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }




}

