package com.example.lab3_iot.entity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_iot.Pagina2Activity;
import com.example.lab3_iot.R;
import com.example.lab3_iot.RecyclerViewModel;
import com.example.lab3_iot.ResultAdapter;
import com.example.lab3_iot.databinding.FragmentBlank1Binding;

import java.util.ArrayList;
import java.util.List;

//para el magnetometro
public class BlankFragment1 extends Fragment implements SensorEventListener {

    FragmentBlank1Binding binding;

    ResultAdapter resultAdapter;

    private SensorManager sensorManager;

    private Sensor magnetometer;

    private Sensor acelerometer;


    private float[] gravityValues = new float[3];
    private float[] magneticValues = new float[3];
    private float[] rotationMatrix = new float[9];
    private float[] orientationValues = new float[3];

    private float lastVisibility = 1.0f; // 100% visible inicialmente

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBlank1Binding.inflate(inflater,container,false);

        //NavController navController = NavHostFragment.findNavController(BlankFragment1.this);

        RecyclerViewModel recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);

        recyclerViewModel.getListaParaMagnetometro().observe(getViewLifecycleOwner(), contactos -> {

            resultAdapter = new ResultAdapter(contactos, container.getContext());

            resultAdapter.setListaContactos(contactos);

            binding.recyclerView1.setAdapter(resultAdapter);
            resultAdapter.notifyDataSetChanged();
            resultAdapter.setOnItemClickListener(new ResultAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    contactos.remove(position);
                    resultAdapter.notifyItemRemoved(position);

                }
            });
            //asociando al layout, activity
            binding.recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));

        });
        //binding.recyclerView1.setLayoutManager(new LinearLayoutManager(Pagina2Activity.this));
        return binding.getRoot();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        acelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, acelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor == magnetometer) {
            System.arraycopy(sensorEvent.values, 0, magneticValues, 0, 3);
        } else if (sensorEvent.sensor == acelerometer) {
            System.arraycopy(sensorEvent.values, 0, gravityValues, 0, 3);
        }

        SensorManager.getRotationMatrix(rotationMatrix, null, gravityValues, magneticValues);
        SensorManager.getOrientation(rotationMatrix, orientationValues);

        // Calcula la visibilidad en función de la orientación
        float azimuth = Math.abs((float) Math.toDegrees(orientationValues[0]));
        float visibility = 1.0f - (azimuth / 90.0f); // 90° = Norte, 0° = Sur


        if (visibility < 0.0f) {
            visibility = 0.0f;
        } else if (visibility > 1.0f) {
            visibility = 1.0f;
        }

        if (Math.abs(lastVisibility - visibility) >= 0.05f) {
            // Actualiza la visibilidad de la lista de contactos
            actualizarVisibilidadListaContactos(  visibility, (View) resultAdapter.getListaContactos());
            lastVisibility = visibility;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void actualizarVisibilidadListaContactos(float visibility, View listaContactos) {
        // Aquí debes implementar la lógica para cambiar la visibilidad de la lista de contactos
        // Puedes usar una propiedad de alpha o escala para controlar la visibilidad gradualmente
        listaContactos.setAlpha(visibility);

    }
}