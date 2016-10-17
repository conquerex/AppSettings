package com.jongkook.android.threadbasic_tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    Button btnUp, btnDown, btnLeft, btnRight;
    FrameLayout ground;
    MainStage mainStage;

    // int stageMap[][];
    Stage stage = new Stage();
    Block block = new Block();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 기본 디자인 - 버튼과 스테이지
        btnUp = (Button)findViewById(R.id.btnUp);
        btnDown = (Button)findViewById(R.id.btnDown);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        ground = (FrameLayout)findViewById(R.id.stage);

        // stageMap 배열에 Stage 객체에 저의된 배열값을 세팅한다
        setStage(1);
        // 뷰 객체를 생성
        mainStage = new MainStage(this, stage, block);

    }

    public void setStage(int stageLevel){
        stage.setStage(stageLevel);
        block.setBlock();
    }
}
