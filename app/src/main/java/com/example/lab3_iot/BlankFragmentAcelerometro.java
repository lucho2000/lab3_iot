package com.example.lab3_iot;

import static android.content.Context.SENSOR_SERVICE;

import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_iot.Sensor.SensorListener;
import com.example.lab3_iot.databinding.FragmentBlankAcelerometroBinding;


public class BlankFragmentAcelerometro extends Fragment {

    FragmentBlankAcelerometroBinding binding;
    ResultAdapter resultAdapter;

    SensorManager sensorManager;

    SensorListener listener = new SensorListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBlankAcelerometroBinding.inflate(inflater, container, false);

        RecyclerViewModel recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);

        recyclerViewModel.getListaParaAcelerometro().observe(getViewLifecycleOwner(), contactos -> {

            resultAdapter = new ResultAdapter(contactos, container.getContext());

            resultAdapter.setListaContactos(contactos);
            binding.recyclerViewAcelero.setAdapter(resultAdapter);
            resultAdapter.notifyDataSetChanged();
            resultAdapter.setOnItemClickListener(position -> {
                contactos.remove(position);
                resultAdapter.notifyItemRemoved(position);
            });
        });

        //asociarlo al layout

        binding.recyclerViewAcelero.setLayoutManager(new LinearLayoutManager(getActivity()));

        //iniciando el listener del acelerometro
        listener = new SensorListener(getContext(), binding.recyclerViewAcelero);
        listener.startAcelerometer();
        return binding.getRoot();



    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //se desregistra el listener del sensor
    @Override
    public void onStop() {
        super.onStop();

        listener.stopAcelerometer();
    }
}