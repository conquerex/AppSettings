package com.jongkook.android.threadbasic_rain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static boolean running = true;
    public static int deviceWidth = 0;
    public static int deviceHeight = 0;

    FrameLayout ground;
    Button btnStart;
    Button btnStop;
    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceHeight = metrics.heightPixels; // 화면 세로 픽셀 개수
        deviceWidth = metrics.widthPixels;   // 화면 가로 픽셀 개수
        Random rnd = new Random();

        ground = (FrameLayout)findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MakeDrop(cv).start();
            }
        });

        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
    }

    class CustomView extends View{
        ArrayList<RainDrop> rainDrops = new ArrayList<>();
        Paint paint = new Paint();
        public CustomView(Context context) {
            super(context);
            //paint.setColor(Color.BLUE);

        }

        public void add(RainDrop rd){
            rainDrops.add(rd);
        }

        public void remove(RainDrop rd){
            rainDrops.remove(rd);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Random rnd = new Random();
            try {
                 for(RainDrop rd : rainDrops){
                     paint.setColor(Color.rgb(rd.color1,rd.color2,rd.color3));
                    // 하나씩 꺼내서 circle을 그려준다
                    canvas.drawCircle(rd.x,rd.y,rd.size,paint);
                }
            }catch (Exception e){
                // e.printStackTrace();
            }
        }
    }

    // 빗방울 1개
    class RainDrop implements Runnable {
        int x ;
        int y ;
        int size ;
        int speed ;
        int size_limit;
        int speed_limit;
        CustomView cv;
        int color1;
        int color2;
        int color3;

        public RainDrop(CustomView cv) {
            this.cv = cv;
            Random random = new Random();
            x = random.nextInt(deviceWidth);
            y = 0;
            color1 = random.nextInt(255);
            color2 = random.nextInt(255);
            color3 = random.nextInt(255);
            size_limit = deviceWidth / 4; //  빗방울의 최대크기가 화면사이즈의 1/20보다 작게
            size = random.nextInt(size_limit);
            speed_limit = deviceHeight / 100;
            speed = random.nextInt(speed_limit)+1;
            cv.add(this);
        }

        @Override
        public void run() {
            // 화면 밖으로 벗어나면 while문을 빠져나가기 때문에 Thread는 종료
            while (y <= deviceHeight){
                if(running) y = y + speed;
                try {
                    // 0.1초에 한번 이동
                    Thread.sleep(20);  // y축으로 이동후 그려지는 간격을 조절한다
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            cv.remove(this);
        }
    }

    // 빗방울을 만들고 화면을 그려주는 Thread를 동작시키는 서브 Thread
    class MakeDrop extends Thread{
        CustomView cv;
        public MakeDrop(CustomView cv) {
            this.cv = cv;
        }

        public void run(){
            // 화면을 그리는 쓰레드 생성 후 동작
            CallDraw callDraw = new CallDraw(cv, 10);
            new Thread(callDraw).start();

            while (running){
                // 빗방울 쓰레드 생성 후 동작
                RainDrop rainDrop = new RainDrop(cv);
                // cv.add(rainDrop);
                new Thread(rainDrop).start();
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    // 화면을 갱신하는 쓰레드. 화면을 그려주는 thread
    class CallDraw implements Runnable{
        // boolean running;
        CustomView cv;
        int interval;

        public CallDraw(CustomView cv, int interval) {
            this.cv = cv;
            this.interval = interval;
        }

//        public void setStatus(boolean flag){
//            running = flag;
//        }

        @Override
        public void run() {
            // interval에 입력된 값만큼 쉰 후에 화면을 반복해서 그려준다
            while (running){
                cv.postInvalidate();
                try {
                    Thread.sleep(interval);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
