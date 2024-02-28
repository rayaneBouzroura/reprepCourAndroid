package com.rayanebouzroura.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.rayanebouzroura.retrofit1.databinding.ActivityMainBinding;
import com.rayanebouzroura.retrofit1.http.RetrofitUtil;
import com.rayanebouzroura.retrofit1.http.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        final Service service = RetrofitUtil.get();
        //recup chiffre du edit text


        binding.button.setOnClickListener(v -> {
            Long number = Long.parseLong(binding.editText.getText().toString());
            //TODO
            service.testEndpoint(number).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        // http 200 http tout s'est bien passé
                        String resultat = response.body().toString();
                        //mettre a jour le textview
                        binding.tv.setText(resultat);
                        Log.i("RETROFIT", resultat);
                    } else {
                        // cas d'erreur http 400 404
                        Log.i("RETROFIT", response.code()+"");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    // erreur accès réseau ou alors GSON
                    Log.i("RETROFIT", t.getMessage());
                }
            });

        });
    }
}