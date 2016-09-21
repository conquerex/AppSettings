package com.jongkook.android.materialdesign_propertyanimation01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

/*
    ObjectAnimator 사용법
    1. 애니메이터를 정의한다                          대상객체,  개체의 속성명  ,속성값(숫자)
    ObjectAnimator ani = ObjectAnimator.ofFloat(player,"translationY",y);
    2. 정의된 애니메이터를 실행한다.
    ani.start();
 */

public class MainActivity extends AppCompatActivity {

    RelativeLayout ground;
    ImageButton player;
    int x = 0;
    int y = 0;

    int gx = 0;
    int gy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ground = (RelativeLayout)findViewById(R.id.ground);
        player = (ImageButton)findViewById(R.id.player);
    }

    protected void setGroundSize() {
        gx = ground.getWidth()/2;
        gy = ground.getHeight()/2;
        // Log.i("MainActivity onResume","gx= "+gx+" / gy= "+gy);
        //       태그                     로그내용
    }

    public void up(View v){
        setGroundSize();
        Log.i("MainActivity up    / ","x= "+x+" / gx= "+gx+" / y= "+y+" / gy= "+gy);
        y = y - 50;

        if(gy >= Math.abs(y) && gx >= Math.abs(x)){
            ObjectAnimator ani = ObjectAnimator.ofFloat(player,"translationY",y);
            ani.start();
        }else{
            y = y + 50;
        }
    }
    public void down(View v){
        setGroundSize();
        Log.i("MainActivity down  / ","x= "+x+" / gx= "+gx+" / y= "+y+" / gy= "+gy);
        y = y + 50;

        if(gy >= Math.abs(y) && gx >= Math.abs(x)){
            ObjectAnimator ani = ObjectAnimator.ofFloat(player,"translationY",y);
            ani.start();
        }else{
            y = y - 50;
        }
    }
    public void left(View v){
        setGroundSize();
        Log.i("MainActivity left  / ","x= "+x+" / gx= "+gx+" / y= "+y+" / gy= "+gy);
        x = x - 50;

        if(gy >= Math.abs(y) && gx >= Math.abs(x)){
            ObjectAnimator ani = ObjectAnimator.ofFloat(player,"translationX",x);
            ani.start();
        }else{
            x = x + 50;
        }
    }

    public void right(View v){
        setGroundSize();
        Log.i("MainActivity right / ","x= "+x+" / gx= "+gx+" / y= "+y+" / gy= "+gy);
        x = x + 50;
        if(gy >= Math.abs(y) && gx >= Math.abs(x)){
            ObjectAnimator ani = ObjectAnimator.ofFloat(player,"translationX",x);
            ani.start();
        }else{
            x = x - 50;
        }
    }

    public void showMessage(View v){
        Toast.makeText(this,"I am Here!!!",Toast.LENGTH_SHORT).show();
    }

    int r = 0;
    public void rotate(View v){
        r = r + 90;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player,"rotation",r);
        ani.start();
    }

    float size = 1;
    public void smaller(View v){
        size = size/2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player,"scaleX",size); // 배수
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player,"scaleY",size);

        // 여러개의 애니메이션 동시에 사용하기
        // 1. AnimatorSet을 초기화
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.setDuration(1000);
        // 2. playTogether에 애니메이션을 담아준다.
        aniSet.playTogether(ani1,ani2);
        // 3. 애니메이션 실행
        aniSet.start();
    }

    public void bigger(View v){
        size = size*2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player,"scaleX",size); // 배수
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player,"scaleY",size);

        // 여러개의 애니메이션 동시에 사용하기
        // 1. AnimatorSet을 초기화
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.setDuration(1000);
        // 2. playTogether에 애니메이션을 담아준다.
        aniSet.playTogether(ani1,ani2);
        // 3. 애니메이션 실행
        aniSet.start();
    }

}
