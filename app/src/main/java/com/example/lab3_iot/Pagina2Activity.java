package com.example.lab3_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.lab3_iot.databinding.ActivityMainBinding;
import com.example.lab3_iot.databinding.ActivityPagina2Binding;
import com.example.lab3_iot.entity.BlankFragment1;
import com.example.lab3_iot.entity.Respuesta;
import com.example.lab3_iot.entity.Result;
import com.example.lab3_iot.retrofit.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pagina2Activity extends AppCompatActivity {

    boolean esFragmentVisible= true;
    ActivityPagina2Binding binding;
    Button botonIrA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagina2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        botonIrA = findViewById(R.id.buttonIrASensor);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerViewMagnetometro, BlankFragment1.class, null)
                    .commit();
        }

        botonIrA.setOnClickListener(view -> {

            if (esFragmentVisible) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainerViewMagnetometro, BlankFragmentAcelerometro.class, null)
                        .commit();
                esFragmentVisible =false;
                botonIrA.setText("IR A MAGNETOMETRO");
            } else {

                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainerViewMagnetometro, BlankFragment1.class, null)
                        .commit();
                esFragmentVisible=true;
                botonIrA.setText("IR A ACELEROMETRO");
            }



        });



    }



    //obtener datos de la api
    /*public void obtenerWs(){

        Api typicodeService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);

        typicodeService.getResult().enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.isSuccessful()){
                    Respuesta respuesta = response.body();

                    Result result = respuesta.getResults().get(0);
                    textViewUsername.setText(result.getLogin().getUsername() );
                    textViewNombApellido.setText( result.getName().getFirst() + " " + result.getName().getLast() );

                } else {
                    Log.d( "msg-test", "error de consulta" );
                }

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }*/
}