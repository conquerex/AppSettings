package com.jongkook.android.materialdesign_viewanimation01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAlpha;
    Button btnRotate;
    Button btnScale;
    Button btnTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlpha = (Button)findViewById(R.id.button);
        btnAlpha.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                btnAlpha.startAnimation(animation);
            }
        });
        btnRotate = (Button)findViewById(R.id.button2);
        btnRotate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                btnRotate.startAnimation(animation);
            }
        });
        btnScale = (Button)findViewById(R.id.button3);
        btnScale.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                btnScale.startAnimation(animation);
            }
        });
        btnTranslate = (Button)findViewById(R.id.button4);
        btnTranslate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
                btnTranslate.startAnimation(animation);
            }
        });

    }
}
