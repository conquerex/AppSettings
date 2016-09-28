package com.jongkook.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {

    Spinner sp;
    TextView tv;

    String datas[] = {"월","화","수","목","금"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        sp = (Spinner)findViewById(R.id.spinner);
        tv = (TextView)findViewById(R.id.spinnerText);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, datas
        );
        /*
        첫번째 : 컨텍스트
        두번째 : 줄 당 레이아웃
        세번째 : 데이터 배열
         */

        // 스피너에 값이 세팅된 어답터를 넣어준다
        sp.setAdapter(adapter);

        // 스피너에 리스너를 등록한다
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(datas[position]+"요일");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tv.setText("해당없음");
            }
        });

    }
}
