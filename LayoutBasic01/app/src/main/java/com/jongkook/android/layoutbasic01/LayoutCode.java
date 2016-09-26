package com.jongkook.android.layoutbasic01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LayoutCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_layout_code);

        // 1. 레이아웃을 생성
        RelativeLayout layout = new RelativeLayout(this);
        setContentView(layout);

        // 2. 내부에 들어가는 위젯들을 생성
        Button button = new Button(this);
        button.setText("Button A");
        button.setBackgroundColor(Color.BLUE);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(LayoutCode.this, "버튼클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. 위젯의 레이아웃을 세팅
        RelativeLayout.LayoutParams buttonParam =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT
                );
        // 3.1 위젯의 레이아웃을 설정한다
        buttonParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParam.addRule(RelativeLayout.CENTER_VERTICAL);

        // 3. 레이아웃에 위에서 생성한 위젯들을 더해줌
        layout.addView(button, buttonParam);

        // 9. 최종적으로 액티비티에 최상위레이아웃 개체를 세팅
        setContentView(layout);
    }
}
