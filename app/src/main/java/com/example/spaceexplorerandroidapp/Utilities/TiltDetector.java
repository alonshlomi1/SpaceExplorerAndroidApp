package com.example.spaceexplorerandroidapp.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.example.spaceexplorerandroidapp.Interfaces.TiltCallback;

public class TiltDetector {

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    private float stepCountX = 0;
    private float stepCountZ = 0;
    private long timeStamp = 0l;
    private TiltCallback tiltCallback;



    public TiltDetector(Context context, TiltCallback tiltCallback) {
        sensorManager =(SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.tiltCallback = tiltCallback;
        initEventListener();


    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float z = event.values[2];
                calculateStep(x, z);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //pass
            }
        };
    }

    private void calculateStep(float x, float z) {
        if(System.currentTimeMillis() - timeStamp > 400){
            timeStamp = System.currentTimeMillis();
             if(x > 3.0){
                if(tiltCallback != null)
                    tiltCallback.moveRight();
             }
             else if(x < -3.0){
            if(tiltCallback != null)
                tiltCallback.moveLeft();
             }
             if(z > 5){
                 if(tiltCallback != null)
                     tiltCallback.moveFaster();
             }
            else if(z < -3){
                if(tiltCallback != null)
                    tiltCallback.moveSlower();
            }
        }
    }

    public float getStepCountX() {
        return stepCountX;
    }

    public float getStepCountZ() {
        return stepCountZ;
    }
    public void start(){
        sensorManager.registerListener(sensorEventListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void stop(){
        sensorManager.unregisterListener(sensorEventListener, sensor);
    }


}
