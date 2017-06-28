package com.delta.saravanan.spidertask2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SensorManager sensor;
    Sensor proximitySensor;
    SensorEventListener proximitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.values[0] < proximitySensor.getMaximumRange()) {

                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensor.registerListener(proximitySensorListener,
                proximitySensor,  1000 * 1000);
    }
}
