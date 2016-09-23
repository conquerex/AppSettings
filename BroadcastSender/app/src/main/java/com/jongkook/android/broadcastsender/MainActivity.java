package com.jongkook.android.broadcastsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final String BROADCAST_ACTION = "com.jongkook.android.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadCast(View v){
        Intent intent = new Intent(BROADCAST_ACTION);
        intent.putExtra("msg","What??????");
        sendBroadcast(intent);
    }
}
