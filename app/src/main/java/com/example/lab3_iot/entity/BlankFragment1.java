package com.example.lab3_iot.entity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab3_iot.Pagina2Activity;
import com.example.lab3_iot.R;
import com.example.lab3_iot.RecyclerViewModel;
import com.example.lab3_iot.ResultAdapter;
import com.example.lab3_iot.databinding.FragmentBlank1Binding;

import java.util.ArrayList;

//para el magnetometro
public class BlankFragment1 extends Fragment {

    FragmentBlank1Binding binding;

    ResultAdapter resultAdapter;


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

}