package com.example.activitesa;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Base activity qu'ont va utiliser pour eviter de tjr reecrire le code de traitement de boutons
 * et aussi afin de s'excuser vis a vis du prof a cause de notre absence
 * ceci n'a jamais etait tente auparavant je me fie a l'instint et a l'ai gratos
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        gestionSelectionMenu(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    /**
     * methode qui va gerer la selection du menu en fonction de l'item selectionne
     * devra etre implementer dans les classes filles / garcon jsp je manque de someil et j'ai mal
     * @param itemId
     */
    protected abstract void gestionSelectionMenu(int itemId);
}
