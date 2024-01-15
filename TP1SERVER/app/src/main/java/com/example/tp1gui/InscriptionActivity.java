package com.example.tp1gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp1gui.databinding.ActivityInscriptionBinding;
import com.example.tp1gui.http.RetrofitUtil;
import com.example.tp1gui.http.Service;
import com.example.tp1gui.singleton.UserManager;

import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {
    //sign up activity (no drawer)
    public ActivityInscriptionBinding binding;
    public Button btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getString(R.string.Title_InscriptionActivity));
        final Service service = RetrofitUtil.get(true);
        gestionBouton(service);

    }

    private void gestionBouton(Service service) {

        //gestion btn inscription
        btnInscription = binding.btnInscription;

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recuperation des champs
                String username = binding.nomUtilisateurInscription.getText().toString();
                String password = binding.motDePasseInscription.getText().toString();
                String passwordConfirmation = binding.confirmationMotDePasseInscription.getText().toString();
                //we couldve isolated teh check logic in a method but im too lazy to implement specific toasts/errors depending on the field that has an isssue
                //TODO fix the laziness that's en haut
                //check if fields are not empty
                if(username.isEmpty()){
                    Toast.makeText(InscriptionActivity.this, getString(R.string.Toast_UsernameEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(InscriptionActivity.this, getString(R.string.Toast_PasswordEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwordConfirmation.isEmpty()){
                    Toast.makeText(InscriptionActivity.this, getString(R.string.Toast_PasswordConfirmationEmpty), Toast.LENGTH_SHORT).show();
                    return;
                }
                //check if password and password confirmation are the same
                if(!password.equals(passwordConfirmation)){
                    Toast.makeText(InscriptionActivity.this, getString(R.string.Toast_PasswordsDontMatch), Toast.LENGTH_SHORT).show();
                    return;
                }
                //create le signin req


                //create the signup object
                SignupRequest signupRequest = new SignupRequest();
                signupRequest.username = username;
                signupRequest.password = password;
                //enqueue the signup request
                service.signup(signupRequest).enqueue(new Callback<SigninResponse>() {
                    @Override
                    public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                        //if response is not successful throw a toast with the response error
                        if(!response.isSuccessful()){
                            try {
                                Toast.makeText(InscriptionActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            return;
                        }
                        //if response successful retrieve the response answer (c supposer etre le username) set up le singleton
                        //throw a toast saying bjr monsieur x or som jsp
                        //then go to accueil all happy and stuff bcs u just signed up woohoo
                        String user = response.body().username;
                        UserManager.getInstance().setUsername(user);
                        Toast.makeText(InscriptionActivity.this, "Bonjour " + user, Toast.LENGTH_SHORT).show();
                        //go to accueil
                        Intent intent = new Intent(InscriptionActivity.this, AccueilActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<SigninResponse> call, Throwable t) {

                    }
                });

            }
        });

    }
}