package com.example.lab3_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.lab3_iot.databinding.ActivityPagina2Binding;
import com.example.lab3_iot.entity.Respuesta;
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

    Button botonAnadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagina2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        botonIrA = findViewById(R.id.buttonIrASensor);
        botonAnadir = findViewById(R.id.buttonAnadir);


        //NavController navController = Navigation.findNavController(Pagina2Activity.this, R.id.fragmentContainerView4);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView4);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();


        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView4, BlankFragment1.class, null)
                    .commit();
        }*/

        //para alternar entre fragments
        botonIrA.setOnClickListener(view -> {

            if (navController.getCurrentDestination().getId() == R.id.blankFragment1) {
                navController.navigate(R.id.action_blankFragment1_to_blankFragmentAcelerometro);
                //navController.popBackStack();
                //esFragmentVisible =false;
                botonIrA.setText("IR A MAGNETOMETRO");
            } else {

                navController.navigate(R.id.action_blankFragmentAcelerometro_to_blankFragment1);
                //navController.popBackStack();
                //esFragmentVisible=true;
                botonIrA.setText("IR A ACELEROMETRO");
            }


            //navController.popBackStack();

        });


        botonAnadir.setOnClickListener(view -> {

            obtenerWs();

        });


    }



    //obtener datos de la api
    public void obtenerWs(){

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


                    ResultAdapter resultAdapter = new ResultAdapter();
                    resultAdapter.setContext(Pagina2Activity.this);
                    resultAdapter.setListaContactos(respuesta.getResults());



                } else {
                    Log.d( "msg-test", "error de consulta" );
                }

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}