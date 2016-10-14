package com.jongkook.android.customview_pushpush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int deviceWidth = 0;
    private int unit = 0;
    private int playerX = 0;
    private int playerY = 0;
    private static final int GROUND_LIMIT = 10;
    // private int[][] map;
    private int map[][] = new int[GROUND_LIMIT][GROUND_LIMIT];

    Button btnUp;
    Button btnDown;
    Button btnLeft;
    Button btnRight;
    FrameLayout ground;
    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dp = getResources().getDisplayMetrics();
        deviceWidth = dp.widthPixels;
        unit = deviceWidth / GROUND_LIMIT;

        ground = (FrameLayout)findViewById(R.id.ground);
        btnUp = (Button)findViewById(R.id.btnUp);
        btnDown = (Button)findViewById(R.id.btnDown);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);

        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);

        cv = new CustomView(this);
        ground.addView(cv);
    }

    public void newGame(){
        for(int i = 0;i<GROUND_LIMIT;i++){
            for(int j = 0;j<GROUND_LIMIT;j++){
                if((i < 2 || i >= GROUND_LIMIT-2)&&(j < 2 || j >= GROUND_LIMIT - 2)) {
                    map[j][i] = 0;
                }else{
                    map[j][i] = randomBlock();
                }
            }
        }
    }

    public int randomBlock(){
        int random = 0;
        random = (int)(Math.random()*2);
        // Log.i("Random",">>>>>> "+random);
        return random;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUp:
                playerY = playerY + checkCollision("y",-1); break;
            case R.id.btnDown:
                playerY = playerY + checkCollision("y",+1); break;
            case R.id.btnLeft:
                playerX = playerX + checkCollision("x",-1); break;
            case R.id.btnRight:
                playerX = playerX + checkCollision("x",+1); break;
        }
        cv.invalidate();
    }

    // y축 혹은 x축에서 다음 이동하는 곳의 좌표가 0보다 작거나 GROUND_LIMIT(canvas)보다 크면
    // 0을 리턴해서 이동하지 않게 한다.
    private int checkCollision(String direction, int nextValue){

        // 외곽선 체크
        if(direction.equals("y")){
            if((playerY + nextValue) < 0 || (playerY + nextValue) >= GROUND_LIMIT){
                return 0;
            }
        }else{
            if((playerX + nextValue) < 0 || (playerX + nextValue) >= GROUND_LIMIT){
                return 0;
            }
        }

        // 장애물 체크
        if(direction.equals("y")){
            int temp_y = playerY + nextValue;
            if(map[temp_y][playerX] == 1){
                if(temp_y + nextValue < 0 || temp_y + nextValue >= GROUND_LIMIT
                        || map[temp_y+nextValue][playerX] != 0){
                    return 0;
                }else{
                    map[temp_y][playerX] = 0;
                    map[temp_y+nextValue][playerX] = 1;
                }
                return 0;
            }
        }else{
            int temp_x = playerX + nextValue;
            if(map[playerY][temp_x] == 1){
                if(temp_x + nextValue < 0 || temp_x + nextValue >= GROUND_LIMIT
                        || map[playerY][temp_x+nextValue] != 0){
                    return 0;
                }else{
                    map[playerY][temp_x] = 0;
                    map[playerY][temp_x+nextValue] = 1;
                }
                return 0;
            }
        }
        return nextValue;
    }

    class CustomView extends View {
        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);
            // paint.setColor(Color.MAGENTA);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 운동장 배경 그리기
            paint.setColor(Color.BLACK);
            canvas.drawRect(0,0,deviceWidth,deviceWidth,paint);

            paint.setColor(Color.CYAN);
            for(int i=0; i<GROUND_LIMIT ; i++){
                for(int j=0; j<GROUND_LIMIT ; j++){
                    if(map[i][j] == 1){
                        canvas.drawRect(j*unit,i*unit,j*unit+unit,i*unit+unit,paint);
                    }

                }
            }

            // 플레이어 그리기
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(playerX*unit,playerY*unit,playerX*unit+unit,playerY*unit+unit,paint);

            Log.i("Position",">>>> "+playerX+" ::: "+playerY);
            if(playerX == GROUND_LIMIT-1 && playerY == GROUND_LIMIT-1){
                Toast.makeText(getContext(), "목적지 도착 ::: 성공",Toast.LENGTH_SHORT).show();
                newGame();
                canvas.drawRect(playerX*unit,playerY*unit,playerX*unit+unit,playerY*unit+unit,paint);
            }
        }
    }
}
