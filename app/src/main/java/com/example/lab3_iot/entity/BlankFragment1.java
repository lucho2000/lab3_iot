package com.example.lab3_iot.entity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_iot.R;
import com.example.lab3_iot.databinding.FragmentBlank1Binding;

//para el magnetometro
public class BlankFragment1 extends Fragment {

    FragmentBlank1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBlank1Binding.inflate(inflater,container,false);

        //NavController navController = NavHostFragment.findNavController(BlankFragment1.this);


        return binding.getRoot();




    }
}