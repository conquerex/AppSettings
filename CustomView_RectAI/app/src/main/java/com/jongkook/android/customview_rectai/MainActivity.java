package com.jongkook.android.customview_rectai;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int GROUND_LIMIT = 10;

    // 1. View 생성
    Button btnUp;
    Button btnDown;
    Button btnLeft;
    Button btnRight;
    Button btnStart;
    FrameLayout ground;
    CustomView cv;

    // 2-3. 디바이스 가로길이 변수
    DisplayMetrics dp;
    int groundWidth;

    // 2-5. 플렝이어 현재 위치 변수
    int playerX;
    int playerY;
    int unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2-3. 디바이스 가로길이 구하기
        dp = getResources().getDisplayMetrics();
        groundWidth = dp.widthPixels;

        // 2-6. 플레이어 현재위치 구하기
        // 플레이어는 가장 왼쪽 위로
        unit = groundWidth/GROUND_LIMIT;
        playerX = 0;
        playerY = 0;

        btnUp = (Button)findViewById(R.id.btnUp);
        btnDown = (Button)findViewById(R.id.btnDown);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        btnStart = (Button)findViewById(R.id.btnStart);

        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        ground = (FrameLayout)findViewById(R.id.ground);

        // 2-1. CustomView 불러오고 ground에 cv 적용
        cv = new CustomView(this);
        ground.addView(cv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 3. 버튼 구
            case R.id.btnUp:
                playerY = playerY - 1; break;
            case R.id.btnDown:
                playerY = playerY + 1; break;
            case R.id.btnLeft:
                playerX = playerX - 1; break;
            case R.id.btnRight:
                playerX = playerX + 1; break;
            case R.id.btnStart:
                // 4-7. 버튼 클릭시 Enemy 생성
                new Enemy(cv).start();
                break;
        }
        cv.invalidate();
    }

    // 4. 유도 미사일 클래스
    class Enemy extends Thread{
        int x = 0;
        int y = 0;
        int size = unit/2;
        CustomView cv;

        // 4-1. CustomView를 변수로 받는 생성자
        public Enemy(CustomView cv){
            this.cv = cv;
            Random random = new Random();
            // 4-2. Enemy의 초기 위치 (범위 : groundWidth)
            x = random.nextInt(2)*groundWidth;
            y = random.nextInt(groundWidth);
            // 4-8. 클릭시 add 수행
            cv.add(this);
        }

        @Override
        public void run() {
            int distanceX = 0;
            int distnaceY = 0;
            while(true){
                // 4-2. 플레이어가 있는 곳으로 좌표 이동
                distanceX = playerX*unit - x;
                distnaceY = playerY*unit - y;
                if(distanceX > 0){
                    x = x + 1;
                }else if(distanceX < 0){
                    x = x - 1;
                }
                if(distnaceY > 0){
                    y = y + 1;
                }else if(distnaceY < 0){
                    y = y - 1;
                }
                // 4-3. 좌표 변경 후 화면에서 다시 그리기
                cv.postInvalidate();
                try{
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    // 2. 뷰의 움직임에 대한 클래스 정의
    class CustomView extends View {
        Paint paint = new Paint();

        // 4-4. Enemy를 담을 변수
        ArrayList<Enemy> enemies = new ArrayList<>();
        // 4-5. 개별 enemy를 담아주기
        public void add(Enemy enemy){
            enemies.add(enemy);
        }

        public CustomView(Context context) {
            super(context);
        }
        // 2-1. 그림을 그릴 onDraw
        @Override
        protected void onDraw(Canvas canvas) {
            // 배경 그리기
            // 2-2. 디바이스의 가로길이 입력
            paint.setColor(Color.BLACK);
            canvas.drawRect(0,0,groundWidth,groundWidth,paint);

            // 플레이어 그리기
            // 2-4. 현재위치 수치화 입력
            paint.setColor(Color.CYAN);
            // 3-1. 버튼 구현 후, 유닛 단위로 이동하도록 작성
            canvas.drawRect(playerX*unit,playerY*unit,
                    playerX*unit+unit,playerY*unit+unit,paint);

            // 4-6. 적군 그리기
            for(Enemy enemy:enemies){
                paint.setColor(Color.RED);
                canvas.drawCircle(enemy.x+enemy.size, enemy.y+enemy.size, enemy.size,paint);
            }
        }
    }
}