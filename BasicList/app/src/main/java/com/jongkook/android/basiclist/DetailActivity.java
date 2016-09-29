package com.jongkook.android.basiclist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Intent intent;
    ImageView img;
    TextView tv1;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intent = getIntent();
        int position = intent.getExtras().getInt("position");

        if(RecyclerAnimationActivity.datas != null){
            RecyclerData data = RecyclerAnimationActivity.datas.get(position);
            img = (ImageView)findViewById(R.id.imageView2);
            img.setBackgroundResource(data.image);
            tv1 = (TextView)findViewById(R.id.textView);
            tv1.setText(data.title);
            tv2 = (TextView)findViewById(R.id.textView2);
            tv2.setText(data.name);
        }


    }
}
