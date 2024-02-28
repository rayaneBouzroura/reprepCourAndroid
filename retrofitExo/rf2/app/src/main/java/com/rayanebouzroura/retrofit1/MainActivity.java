package com.rayanebouzroura.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rayanebouzroura.retrofit1.databinding.ActivityMainBinding;
import com.rayanebouzroura.retrofit1.http.ComplexResponse;
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
            String userInput = binding.editText.getText().toString();
            service.getComplexData(userInput).enqueue(new Callback<ComplexResponse>() {
                @Override
                public void onResponse(Call<ComplexResponse> call, Response<ComplexResponse> response) {
                    //
                    if (response.isSuccessful()) {
                        ComplexResponse resultat = response.body();
                        //toast le resultat

                        binding.tvA.setText(resultat.a.toString());
                        binding.tvB.setText(resultat.b);
                        binding.tvC.setText(resultat.c.toString());
                        Log.i("RETROFIT",resultat.a+" "+resultat.b+" "+resultat.c);
                    }
                }

                @Override
                public void onFailure(Call<ComplexResponse> call, Throwable t) {

                }
            });

        });
    }
}