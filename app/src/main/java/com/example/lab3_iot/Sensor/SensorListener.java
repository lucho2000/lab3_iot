package com.example.lab3_iot.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class SensorListener implements SensorEventListener {


    private SensorManager sensorManager;

    private Context context;

    private RecyclerView recyclerView;

    float aceleracion_max = 15.0f; //15 m/s 2

    public SensorListener(Context context, RecyclerView recyclerView) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public SensorListener() {
    }

    public void startAcelerometer(){

        Sensor sensorAcelerometer  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorAcelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void stopAcelerometer(){

        sensorManager.unregisterListener(this);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        if(sensorType == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            Log.d("msg-test","x: " + x + " | y: " + y + " | z: " + z);


            float aceleracionTotal = (float) Math.sqrt(x * x + y * y + z * z);

            if (aceleracionTotal > aceleracion_max) {
                // Realiza el scroll automático en la lista de contactos aquí
                recyclerView.smoothScrollBy(0, 200);

                // Muestra un Toast con la aceleración detectada
                String mensaje = "Su aceleración: " + aceleracionTotal + " m/s^2";
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
