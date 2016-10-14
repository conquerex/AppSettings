package com.jongkook.android.optimization_render;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.startMethodTracing("traceResult");

        Thread another01 = new Thread(){
            @Override
            public void run(){
                // 다른 쓰레드에서 돌아가는 로직
                print100000("another01");
            }
        };
        another01.start();

        Thread another02 = new Thread(){
            @Override
            public void run(){
                // 다른 쓰레드에서 돌아가는 로직
                print100000("another02");
            }
        };
        another02.start();

        print100000("main");

    }

    public void print100000(String tag){
        for(int i=0 ; i<2000; i++){
            // System.out.println("i = " + i);
            Log.i("PerformanceTest","i = " + i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }
}
