package com.example.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        binding.topAppBar.setNavigationOnClickListener(v -> {
            binding.drawerLayout.open();
        });
        binding.navigationView.setNavigationItemSelectedListener(
                item -> {
                    item.setChecked(true);
                    binding.drawerLayout.close();
                    return true;

                }
        );
    }
}