package com.example.lab3_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.lab3_iot.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Button botonIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        botonIngresar = findViewById(R.id.button);

        botonIngresar.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, Pagina2Activity.class);
            startActivity(intent);

        });


    }
}