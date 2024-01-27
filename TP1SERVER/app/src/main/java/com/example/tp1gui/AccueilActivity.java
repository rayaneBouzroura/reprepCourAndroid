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
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1gui.databinding.ActivityAccueuilBinding;
import com.example.tp1gui.http.RetrofitUtil;
import com.example.tp1gui.http.Service;
import com.example.tp1gui.singleton.UserManager;
import com.google.android.material.navigation.NavigationView;

import org.kickmyb.transfer.HomeItemResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    //homeitemresponse list
    public List<HomeItemResponse> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccueuilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.AccueilActivity_Title));
        final Service service = RetrofitUtil.get(true);
        setUpGreetingText(binding.greetingText);
        gestionDrawerBar(service);
        list = getHomeItems(service);
        initRecycler();
        //add delay
        remplirRecycler();

        //toast with the username
        Toast.makeText(this, "Hello :" + UserManager.getInstance().getUsername(), Toast.LENGTH_SHORT).show();

    }

    private void setUpGreetingText(TextView tv) {
        // array of 10 weird emojis
        String[] emojis = {"( ͡° ͜ʖ ͡°)", "( ͡~ ͜ʖ ͡°)", "( ͡ʘ ͜ʖ ͡ʘ)", "( ͡o ͜ʖ ͡o)",
                "( ͡ʘ ͜ʖ ͡ʘ)", "( ͡ಠ ͜ʖ ͡ಠ)", "( ͡ಠ ʖ̯ ͡ಠ)", "( ͡ಠ ͜ʖ ͡ಠ)", "( ͡° ʖ̯ ͡°)",
                "( ͡ಥ ͜ʖ ͡ಥ)"};
        //random object
        java.util.Random random = new java.util.Random();
        //generate random number between 0 and 9
        int randomNumber = random.nextInt(10);
        tv.setText("Hello " + UserManager.getInstance().getUsername() + " " + emojis[randomNumber]);
    }

    //method that takes the service and returns a list of homeitemresponse
    private List<HomeItemResponse> getHomeItems(Service service) {
        List<HomeItemResponse> list = new ArrayList<>();
        //api call to retrieve the list of taches
        service.getHomeTasks().enqueue(new Callback<List<HomeItemResponse>>() {
            @Override
            public void onResponse(Call<List<HomeItemResponse>> call, Response<List<HomeItemResponse>> response) {
                //if reponse unsuccessful throw error and return
                if(!response.isSuccessful())
                {
                    Toast.makeText(AccueilActivity.this, "error :/", Toast.LENGTH_SHORT).show();
                }
                //if response successful throw a toast empty the singleton instance then go to signin activity
                if(response.isSuccessful()){
                    //throw a toast
                    Toast.makeText(AccueilActivity.this, "Taches retrieved", Toast.LENGTH_SHORT).show();
                    //empty le singleton
                    //go to signin activity
                    List<HomeItemResponse> homeItemResponses = response.body();
                    list.addAll(homeItemResponses);
                }
            }
            @Override
            public void onFailure(Call<List<HomeItemResponse>> call, Throwable t) {
                //our code is perfect and fault proof we are never wrong so this will never happen
            }
        });

        return list;
    }



    @Override
    protected void onResume() {
        super.onResume();
        //delay le api call pour test
        new Handler().postDelayed(this::remplirRecycler , 2000);

    }

    private void remplirRecycler() {

        //update adapter list
        adapter.list.addAll(list);
        //notify adapter
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


    private void gestionDrawerBar(Service service) {
        nv = binding.navigationView;
       drawerLayout = binding.drawerLayout;
       //mettre le username dans le header
        View viewHeader = nv.getHeaderView(0);
        //find by id since databin
        TextView tvHeader =  viewHeader.findViewById(R.id.tvUsername);
        //
        tvHeader.setText(UserManager.getInstance().getUsername());
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
                   Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
                   startActivity(i);
               }
               else if (item.getItemId() == NAV_ITEM_DECONNEXION) {


                   service.signout().enqueue(new Callback<Void>() {
                       @Override
                       public void onResponse(Call<Void> call, Response<Void> response) {
                           //if reponse unsuccessful throw error and return
                           if(!response.isSuccessful())
                           {
                               try {
                                   Toast.makeText(AccueilActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                               } catch (IOException e) {
                                   throw new RuntimeException(e);
                               }
                           }
                           //if response successful throw a toast empty the singleton instance then go to signin activity
                           if(response.isSuccessful()){
                               //throw a toast
                               Toast.makeText(AccueilActivity.this, R.string.vous_etes_deconnecte, Toast.LENGTH_SHORT).show();
                               //empty le singleton
                               UserManager.getInstance().clearUsername();
                               //go to signin activity
                               Intent i2 = new Intent(AccueilActivity.this,ConnectionActivity.class);
                               startActivity(i2);
                           }
                       }

                       @Override
                       public void onFailure(Call<Void> call, Throwable t) {

                       }
                   });

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
        int itemId = item.getItemId();
        if (itemId == R.id.nav_item_creerTache){
            Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
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