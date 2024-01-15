package com.example.tp1gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp1gui.databinding.ActivityConnectionBinding;
import com.example.tp1gui.http.RetrofitUtil;
import com.example.tp1gui.http.Service;
import com.example.tp1gui.singleton.UserManager;

import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionActivity extends AppCompatActivity {

    //set viewBinding
    public ActivityConnectionBinding binding;
    public Button btnConnection;

    public Button btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bind

        super.onCreate(savedInstanceState);
        binding = ActivityConnectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.Title_Inscription));
        //creatisation du service http (local) , wont work vu qu'on ne peut
        //pas utiliser un service dans le scope d'une methode override jps
        final Service serviceHttp = RetrofitUtil.get(true);
        gestionBtn(serviceHttp);

    }

    private void gestionBtn(Service serviceHttp){
        btnConnection = binding.btnConnection;
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieve the username and pw
                String username = binding.nomUtilisateurConnection.getText().toString();
                String password = binding.motDePasseConnection.getText().toString() ;
                //if any of them are empty throw appropriate toast and return
                if(username.isEmpty()){
                    Toast.makeText(ConnectionActivity.this, getString(R.string.Toast_UsernameEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(ConnectionActivity.this, getString(R.string.Toast_PasswordEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }
                //create the signin request
                SigninRequest request = new SigninRequest();
                request.username = username;
                request.password = password;
                //enqueue la requete et la gestion de la reponse
                serviceHttp.signin(request).enqueue(new Callback<SigninResponse>() {
                    @Override
                    public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                        //if the response is not successful throw a toast and return
                        if(!response.isSuccessful()){
                            Toast.makeText(ConnectionActivity.this, getString(R.string.Toast_ConnectionFailed), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //if the response is successful hold the user in the session via the singleton
                        UserManager userManager = UserManager.getInstance();
                        //recup the user in un string
                        String user = response.body().username;
                        //set the user in the singleton
                        userManager.setUsername(user);
                        //create intent to go to accueil
                        Intent intent = new Intent(ConnectionActivity.this, AccueilActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SigninResponse> call, Throwable t) {
                        //if the call fails throw a toast and return
                        Toast.makeText(ConnectionActivity.this, getString(R.string.Toast_ConnectionFailed), Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
                //create intent to go to accueil
//                Intent intent = new Intent(ConnectionActivity.this, AccueilActivity.class);
//                startActivity(intent);
            }
        });

        btnInscription = binding.btnInscription;
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent to go to inscription
                Intent intent = new Intent(ConnectionActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

    }
}