package com.example.lab3_iot;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab3_iot.entity.Result;

import java.util.ArrayList;

public class RecyclerViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Result>> listaParaMagnetometro = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Result>> listaParaAcelerometro = new MutableLiveData<>();
    private MutableLiveData<Result> resultadoMagnetometro = new MutableLiveData<>();

    private MutableLiveData<Result> resultadoAcelerometro = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Result>> getListaParaMagnetometro() {
        return listaParaMagnetometro;
    }

    public MutableLiveData<ArrayList<Result>> getListaParaAcelerometro() {
        return listaParaAcelerometro;
    }

    public MutableLiveData<Result> getResultadoMagnetometro() {
        return resultadoMagnetometro;
    }

    public MutableLiveData<Result> getResultadoAcelerometro() {
        return resultadoAcelerometro;
    }
}
