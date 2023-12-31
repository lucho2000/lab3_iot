package com.example.lab3_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lab3_iot.Sensor.SensorListener;
import com.example.lab3_iot.databinding.ActivityPagina2Binding;
import com.example.lab3_iot.entity.BlankFragment1;
import com.example.lab3_iot.entity.Respuesta;
import com.example.lab3_iot.entity.Result;
import com.example.lab3_iot.retrofit.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pagina2Activity extends AppCompatActivity {

    Result contacto;
    boolean esFragmentVisible= true;
    ActivityPagina2Binding binding;
    Button botonIrA;

    Button botonAnadir;

    NavController navController;

    SensorManager sensorManager;

    SensorListener listener = new SensorListener();

    //listas en cada fragment

    ArrayList<Result> listaMagneto = new ArrayList<>();

    ArrayList<Result> listaAcelero = new ArrayList<>();

    Api typicodeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagina2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //botonIrA = findViewById(R.id.buttonIrASensor);
        //botonAnadir = findViewById(R.id.buttonAnadir);



        typicodeService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
        //NavController navController = Navigation.findNavController(Pagina2Activity.this, R.id.fragmentContainerView4);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView4);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();


        //para la informacion en el recycler view del magnetometro
        RecyclerViewModel recyclerViewModel1 = new ViewModelProvider(Pagina2Activity.this
        ).get(RecyclerViewModel.class);

        recyclerViewModel1.getListaParaMagnetometro().setValue(listaMagneto);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView4, BlankFragment1.class, null).commit();


        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView4, BlankFragment1.class, null)
                    .commit();
        }*/

        /*if(sensorManager != null){
            Sensor mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(mAccelerometer != null){
                Log.d("msg-test","Sí tiene acelerómetro");
            }else{
                Log.d("msg-test","No tiene acelerómetro");
            }
        }*/



        //para alternar entre fragments
        binding.buttonIrASensor.setOnClickListener(view -> {

            if (navController.getCurrentDestination().getId() == R.id.blankFragment1) {
                //estamos en acelerometro
                //navController.popBackStack();
                //esFragmentVisible =false;
                binding.buttonIrASensor.setText("Ir a Magnetometro");
                recyclerViewModel1.getListaParaAcelerometro().setValue(listaAcelero);
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView4, BlankFragmentAcelerometro.class, null).commit();
                navController.navigate(R.id.action_blankFragment1_to_blankFragmentAcelerometro);

                //para el alert dialog haciendo click en la imagen del ojito
                binding.imageView2.setOnClickListener(view1 -> {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Pagina2Activity.this);
                    // Establece el título del diálogo
                    builder.setTitle("Detalles - Acelerómetro");

                    // Establece el mensaje del diálogo
                    builder.setMessage("Haga CLICK en 'Añadir' para añadir contactos a su lista." +
                            "Esta aplicacion esta utilizando el ACELEROMETRO de su telefono. " +
                            "De esta forma, la lista hara scroll hacia abajo " +
                            "cuando agite su dispositivo.      ");


                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Código a ejecutar cuando se hace clic en el botón "Aceptar"
                            // Puedes poner aquí la lógica que desees.
                        }
                    });

                    // Crea el AlertDialog
                    AlertDialog dialog = builder.create();

                    // Muestra el AlertDialog
                    dialog.show();
                });


            } else {

                //estamos en magnetometro

                //navController.popBackStack();
                //esFragmentVisible=true;
                binding.buttonIrASensor.setText("IR a Acelerometro");

                recyclerViewModel1.getListaParaMagnetometro().setValue(listaMagneto);
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView4, BlankFragment1.class, null).commit();
                navController.navigate(R.id.action_blankFragmentAcelerometro_to_blankFragment1);

                binding.imageView2.setOnClickListener(view1 -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Pagina2Activity.this);
                    // Establece el título del diálogo
                    builder.setTitle("Detalles - Magnetómetro");

                    // Establece el mensaje del diálogo
                    builder.setMessage("Haga CLICK en 'Añadir' para añadir contactos a su lista." +
                            "Esta aplicacion esta utilizando el MAGNETOMETRO de su telefono. " +
                            "De esta forma, se mostrará el 100% cuando se apunte al NORTE  " +
                            "Caso contrario se desvanecerá.      ");


                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Código a ejecutar cuando se hace clic en el botón "Aceptar"
                            // Puedes poner aquí la lógica que desees.
                        }
                    });

                    // Crea el AlertDialog
                    AlertDialog dialog = builder.create();

                    // Muestra el AlertDialog
                    dialog.show();
                });

            }

            //navController.popBackStack();
        });

        binding.buttonAnadir.setOnClickListener(view -> {

            //barra de progreso al cargar el añadir contacto
            binding.loadingProgressBar.setVisibility(View.VISIBLE);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    obtenerWs(); //meotod para llamar a la API

                    try {
                        Thread.sleep(2000);// Dura 2 segundos
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.loadingProgressBar.setVisibility(View.GONE);//se oculta el progress bar
                        }
                    });
                }
            }).start();


        });


    }

    //obtener datos de la api
    public void obtenerWs(){

        typicodeService.getResult().enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.isSuccessful()){
                    Respuesta respuesta = response.body();

                    contacto = respuesta.getResults().get(0);
                    Log.d("msg-contact", "llega la info de api");

                    if (navController.getCurrentDestination().getId() == R.id.blankFragment1){

                        listaMagneto.add(contacto);

                        RecyclerViewModel recyclerViewModel1 = new ViewModelProvider(Pagina2Activity.this
                        ).get(RecyclerViewModel.class);
                        recyclerViewModel1.getListaParaMagnetometro().setValue(listaMagneto);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView4, BlankFragment1.class, null).commit();
                    } else {

                        listaAcelero.add(contacto);
                        RecyclerViewModel recyclerViewModel1 = new ViewModelProvider(Pagina2Activity.this
                        ).get(RecyclerViewModel.class);
                        recyclerViewModel1.getListaParaAcelerometro().setValue(listaAcelero);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView4, BlankFragmentAcelerometro.class, null).commit();
                    }


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


    @Override
    protected void onResume() {
        super.onResume();
    }


}