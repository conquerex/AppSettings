package com.jongkook.android.layoutbasic01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class DynamicGrid extends AppCompatActivity {

    GridLayout grid;
    int cnt = 0;
    Button newButton;
    final static String TAG = "Dynamic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid);

        Button button3 = (Button)findViewById(R.id.button23);
        grid = (GridLayout)findViewById(R.id.gridLayout);
        Log.i(TAG,"------------- grid : "+grid);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newButton = new Button(DynamicGrid.this);
                cnt++;
                newButton.setText(""+cnt);
                Log.i(TAG,"------------- cnt : "+cnt);
                newButton.setId(cnt);
                newButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG,"------------- remove : "+v);
                        grid.removeView(v);
                    }
                });
                grid.addView(newButton);
            }
        });



    }
}
