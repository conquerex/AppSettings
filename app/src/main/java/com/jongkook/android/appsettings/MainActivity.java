package com.jongkook.android.appsettings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressWarnings("Corrections")

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 화면 요소에 접근하기
        // 1. 요소 타입선언 - 레이아웃.xml 파일을 text 모드로 보면 해당 요소의 제일 첫 단어가 타입
        // 2. findViewById(R.id.아이디)로 해당 요소를 찾아서 할당
        // 3. 찾은 요소를 타입에 맞게 캐스팅

//        Button btn = (Button)findViewById(R.id.button);
//        // 4. 버튼의 경우 클릭 같은 이벤트를 감지하기 위해서 리스너라는 것을 달아야 한다.
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { // 5. 버튼이 클릭되면 onClick 함수를 호출
//                changeText();
//            }
//        });
    }

    // 뷰 요소에서 함수를 호출하기 위해서는
    // 1. 함수를 void 타입으로 작성하고
    // 2.
    public void changeText(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        // textView.setText("값변경 v3");
        textView.setText(BuildConfig.MYURL);
    }

}
