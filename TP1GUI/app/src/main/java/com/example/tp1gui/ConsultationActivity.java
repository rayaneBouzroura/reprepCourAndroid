package com.example.tp1gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class ConsultationActivity extends BaseActivity {
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
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConsultationActivity.this , AccueilActivity.class));
            }
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
          }
          else if (item.getItemId() == R.id.nav_item_creerTache) {
                //go to creer tache
                Intent i = new Intent(this, CreationActivity.class);
                startActivity(i);
          }
          else if (item.getItemId() == R.id.nav_item_deconnexion) {
                //go to login
                Intent i = new Intent(this, ConnectionActivity.class);
                startActivity(i);
          }
    }
}

