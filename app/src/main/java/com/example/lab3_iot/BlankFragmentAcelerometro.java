package com.example.lab3_iot;

import static android.content.Context.SENSOR_SERVICE;

import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_iot.databinding.FragmentBlank1Binding;
import com.example.lab3_iot.databinding.FragmentBlankAcelerometroBinding;


public class BlankFragmentAcelerometro extends Fragment {

    FragmentBlankAcelerometroBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBlankAcelerometroBinding.inflate(inflater, container, false);

        return binding.getRoot();




    }


}