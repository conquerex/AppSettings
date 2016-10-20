package com.jongkook.android.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    int deviceWidth = 0;
    int deviceHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceHeight = metrics.heightPixels;
        deviceWidth = metrics.widthPixels;

        CustomSufaceView sufaceView = new CustomSufaceView(this);
        setContentView(sufaceView);
    }

    public class CustomSufaceView extends SurfaceView implements SurfaceHolder.Callback{

        private SurfaceThread thread;

        public CustomSufaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
            thread = new SurfaceThread(getHolder()); //
            thread.setDaemon(true); // true가 맞나??

        }

        // 뷰에 포커스가 생성됬다. 뷰에 서피스가 보여지고 있다
        // 뷰가 화면에 보여지는 시
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread.running = true;
            thread.start();

        }

        // 뷰에 변경사항이 생겼다
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        // 화면이 생명주기가 끝났다
        // 화면에서 뷰가 보여지지 않는 시점
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            thread.running = false;
            while (retry){
                try {
                    thread.join(); // 서브스레드를 메인과 동기화 시켜줌
                    retry = false;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            // thread.interrupt();

        }
    }

    // 무한반복하면서 위에서 정의한 surface뷰에 그림을 그려주는 역할을 한다
    public class SurfaceThread extends Thread{

        private SurfaceHolder surfaceHolder;
        Paint paint = new Paint();
        int x, y = 0;
        boolean running;

        public SurfaceThread(SurfaceHolder holder){
            surfaceHolder = holder;

        }

        @Override
        public void run() {
            // 그림을 그릴 캔버스를 가져오고
            Canvas canvas = surfaceHolder.lockCanvas();

            // 무한 반복하면서 그림을 그려준다
            while (true){
                try{
                    // 그림을 그릴
                    synchronized (surfaceHolder) {
                        paint.setColor(Color.WHITE);
                        canvas.drawRect(0,0,deviceWidth,deviceHeight, paint);
                        paint.setColor(Color.BLUE);
                        canvas.drawRect(x,y,50+x,50+y,paint);
                    }

                    x++; y++;

                    // 화면의 끝으로 가면 다시 원점
                    if(x > deviceWidth){
                        x = 0; y = 0;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    // 여기서 실제 디스플레이에 그려주게 된다
                    // 캔버스의 락을 해제하면 실제 뷰에 그림을 그리게 된
                    if(canvas != null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
