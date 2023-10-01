package com.example.lab3_iot.entity;

import java.util.ArrayList;

public class Respuesta {


    private ArrayList<Result> results;
    private Info info;


    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
