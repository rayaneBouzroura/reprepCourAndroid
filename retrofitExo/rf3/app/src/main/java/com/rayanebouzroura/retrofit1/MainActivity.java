package com.rayanebouzroura.retrofit1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rayanebouzroura.retrofit1.databinding.ActivityMainBinding;
import com.rayanebouzroura.retrofit1.http.ComplexResponse;
import com.rayanebouzroura.retrofit1.http.RetrofitUtil;
import com.rayanebouzroura.retrofit1.http.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    rvAdapterSimple adapterSimple;
    rvAdapterComplexe adapterComplexe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Service service = RetrofitUtil.get();
        //recup chiffre du edit text
        initRecyclerSimple();
        remplirRecyclerSimple(service);
        initRecyclerComplex();
        remplirRecyclerComplex(service);



    }

    private void remplirRecyclerComplex(Service service) {
        service.getComplexList().enqueue(new Callback<List<ComplexResponse>>() {
            @Override
            public void onResponse(Call<List<ComplexResponse>> call, Response<List<ComplexResponse>> response) {
                adapterComplexe.list = response.body();
                adapterComplexe.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ComplexResponse>> call, Throwable t) {

            }
        });
    }

    private void initRecyclerComplex() {

        RecyclerView rvComplexe = binding.rvComplexe;

        rvComplexe.setHasFixedSize(true);
        //usage du linearLayout manager
        rvComplexe.setLayoutManager(new LinearLayoutManager(this));

        //specifier l'adapter
        adapterComplexe = new rvAdapterComplexe();
        rvComplexe.setAdapter(adapterComplexe);
    }

    public void remplirRecyclerSimple(Service service) {

        service.getLongList().enqueue(new Callback<List<Long>>() {
            @Override
            public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                adapterSimple.list = response.body();
                adapterSimple.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Long>> call, Throwable t) {

            }
        });


    }

    private void initRecyclerSimple() {
        RecyclerView rvSimple = binding.rvSimple;

        rvSimple.setHasFixedSize(true);
        //usage du linearLayout manager
        rvSimple.setLayoutManager(new LinearLayoutManager(this));

        //specifier l'adapter
        adapterSimple = new rvAdapterSimple();
        rvSimple.setAdapter(adapterSimple);
    }
}