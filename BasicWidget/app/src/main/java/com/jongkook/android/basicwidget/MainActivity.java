package com.jongkook.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    TextView tv;
    TextView tv2;
    TextView tv3;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    Switch sw;
    ToggleButton tb;
    ProgressBar pb;
    SeekBar sb;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = (TextView)findViewById(R.id.textView2);
        tv2 = (TextView)findViewById(R.id.textView9);
        tv3 = (TextView)findViewById(R.id.textView11);

        rg = (RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 현재 체크된 라디오 버튼아이디를 가져온다
                // int checked = rg.getCheckedRadioButtonId();
                switch (checkedId){
                    case R.id.rdApple:
                        tv.setText("사과가 선택됨");
                        break;
                    case R.id.rdOrange:
                        tv.setText("오렌지가 선택됨");
                        break;
                    case R.id.rdBanana:
                        tv.setText("바나나가 선택됨");
                        break;
                }
            }
        });

        cb1 = (CheckBox)findViewById(R.id.checkBox);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);
        cb3 = (CheckBox)findViewById(R.id.checkBox3);

        cb1.setOnCheckedChangeListener(checkedChangeListener);
        cb2.setOnCheckedChangeListener(checkedChangeListener);
        cb3.setOnCheckedChangeListener(checkedChangeListener);

        // 스위치
        sw = (Switch)findViewById(R.id.switch1);
        // 프로그레스 바
        pb = (ProgressBar)findViewById(R.id.progressBar);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                if(view.isChecked()){
                    tv.setText("On ------");
                    pb.setVisibility(View.VISIBLE);
                }else{
                    tv.setText("Off -----");
                    pb.setVisibility(View.GONE);
                }
            }
        });

        // 토글 버튼
        tb = (ToggleButton)findViewById(R.id.toggleButton);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast toast;
                if(buttonView.isChecked()){
                    toast = Toast.makeText(MainActivity.this, "토글 ON", Toast.LENGTH_SHORT);
                }else{
                    toast = Toast.makeText(MainActivity.this, "토글 OFF", Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });

        sb = (SeekBar)findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv2.setText(progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,seekBar.getProgress()+" 위치에서 터치가 시작됨",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,seekBar.getProgress()+" 위치에서 터치가 종료됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

        rb = (RatingBar)findViewById(R.id.ratingBar);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tv3.setText(rating+"/5");
            }
        });

    }

    // 컴파운드 계열 버튼에서 사용되는 체크변화를 감지하는 리스너
    // 컴파운드 계열 - 체크박스, 토글, 스위치 등
    CompoundButton.OnCheckedChangeListener checkedChangeListener
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            StringBuilder sb = new StringBuilder();
            if(cb1.isChecked()){
                sb.append("Dog ");
            }
            if(cb2.isChecked()){
                sb.append("Pig ");
            }
            if(cb3.isChecked()){
                sb.append("Chichen");
            }
            tv.setText(sb.toString());
        }
    };
}
