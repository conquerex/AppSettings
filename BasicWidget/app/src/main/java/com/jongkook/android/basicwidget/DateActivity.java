package com.jongkook.android.basicwidget;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Timer;

public class DateActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button start;
    Button stop;
    Button pause;
    long pauseTime;

    // 현재 일시정지 버튼이 눌렀는지 체크
    boolean pauseFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        start = (Button)findViewById(R.id.button);
        stop = (Button)findViewById(R.id.button2);
        pause = (Button)findViewById(R.id.button3);

        start.setOnClickListener(onClickListener);
        stop.setOnClickListener(onClickListener);
        pause.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    break;
                case R.id.button2:
                    chronometer.stop();
                    break;
                case R.id.button3:
                    if(pauseFlag){
                        long startTime = SystemClock.elapsedRealtime();
                        long gap = startTime - pauseTime;
                        Toast.makeText(DateActivity.this,gap+"",Toast.LENGTH_SHORT).show();
                        chronometer.setBase(chronometer.getBase() + gap);
                        chronometer.start();
                        pause.setText("Pause");
                        pauseFlag = false;
                    }else{
                        chronometer.stop();
                        pauseTime = SystemClock.elapsedRealtime();
                        pause.setText("Restart");
                        pauseFlag = true;
                    }
                    break;
            }
        }
    };
}
