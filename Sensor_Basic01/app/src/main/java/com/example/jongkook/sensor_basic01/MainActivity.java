package com.example.jongkook.sensor_basic01;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*
* - Sensor 동작 기본 흐름
*  1. SensorManager 생성
*  2. Sensor 객체 생성 (사용할 Sensor type 선택)
*  3. Sensor Listener 작성
*  4. Listener 등록 및 Sensor 값 받기
*  5. Listener 해제
*
* - 동작 속도
*   FASTEST, GAME, UI, NORMAL
*
*  - 정확도
*    HIGH, MEDIUM, LOW
*
* */

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager manager;
    Sensor acc;
    Sensor light;
    Sensor step;

    TextView tvAcc;
    TextView tvLight;
    TextView tvStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAcc = (TextView)findViewById(R.id.tvAcc);
        tvLight = (TextView)findViewById(R.id.tvLight);
        tvStep = (TextView)findViewById(R.id.tvStep);

        // 1
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 2
        acc = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        step = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this){
            float x = 0;
            float y = 0;
            float z = 0;

            // 이벤트를 방생시킨 센서타입을 체크
            switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_GRAVITY:
                    x = sensorEvent.values[0];
                    y = sensorEvent.values[1];
                    z = sensorEvent.values[2];
                    tvAcc.setText(x+"\n"+y+"\n"+z);
                    break;
                case Sensor.TYPE_LIGHT:
                    x = sensorEvent.values[0];
                    tvLight.setText(x+"\n"+y+"\n"+z);
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    x = sensorEvent.values[0];
                    tvStep.setText(x+"\n"+y+"\n"+z);
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, step, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
