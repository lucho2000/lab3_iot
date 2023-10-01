package com.example.lab3_iot.Sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.Toast;

public class SensorListener implements SensorEventListener {

    float aceleracion_max = 15.0f;
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
                // ...

                // Muestra un Toast con la aceleración detectada
                String mensaje = "Su aceleración: " + aceleracionTotal + " m/s^2";
                //Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
