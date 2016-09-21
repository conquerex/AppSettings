package com.jongkook.android.materialdesign_propertyanimation_spreadcube;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout ground;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    int x = 0;
    int y = 0;

    int cx = 0;
    int cy = 0;

    /*
        생각을 해보자. 여러개의 큐브를 만들 수 있다
        한 변의 큐브 갯수를 지정 - int cube
        갯수로 부터 좌표 선정 가능 (0,0)~(cube-1,cube-1)
        목적지를 공식화 : cube 한 변의 size * (cube-1) / 2
        본인 숫자를 cube로 나눈 몫과 나머지로 좌표 알 수 있다.
        move(View v, cube, i)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ground = (RelativeLayout) findViewById(R.id.ground);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

    }

    public void move(View player, int cube, int i){
        cx = button9.getWidth();
        cy = button9.getHeight();
        int movex = 0;
        int movey = 0;
        x = (i-1)%cube;
        y = (i-1)/cube;

        Log.i("MainActivity position","x= "+x+" / cx= "+cx+" / y= "+y+" / cy= "+cy);

        if((cube+1)/2 == 2*x && (cube+1)%x == 0 || i == 0){
            movex = 0;
        }else if((cube+1)/2 <= x){
            movex = cx*x/2;
        }else{
            movex = -cx*(cube-x-1)/2;
        }

        if((cube+1)/2 == 2*y && (cube+1)%y == 0 || i == 0){
            movey = 0;
        }else if((cube+1)/2 <= y){
            movey = cy*y/2;
        }else{
            movey = -cy*(cube-y-1)/2;
        }

        Log.i("MainActivity test","i= "+i+" / movex= "+movex+" / movey= "+movey);

        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player,"translationX",movex);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player,"translationY",movey);
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.setDuration(1000);
        aniSet.playTogether(ani1,ani2);
        aniSet.start();
    }

    boolean flag = true;

    public void button9(View v){
        // 펼치기
        if(flag == true){
            move(button1,3,1);
            move(button2,3,2);
            move(button3,3,3);
            move(button4,3,4);
            move(button5,3,5);
            move(button6,3,6);
            move(button7,3,7);
            move(button8,3,8);
            move(button9,3,9);
            flag = false;
        // 모으기
        }else{
            move(button1,3,5);
            move(button2,3,5);
            move(button3,3,5);
            move(button4,3,5);
            move(button5,3,5);
            move(button6,3,5);
            move(button7,3,5);
            move(button8,3,5);
            move(button9,3,5);
            flag = true;
        }
        Log.i("MainActivity test","flag= "+flag);
    }

}
