package com.example.jongkook.butterknife;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindBitmap;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvResult)
    TextView tv;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindString(R.string.butterknife)
    String value;
    @BindBitmap(R.mipmap.ic_launcher)
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnClick)
    public void setOnClickBind(){
        tv.setText("클릭했음");
    }

    @OnClick(R.id.btnString)
    public void setOnStringBind(){
        tv.setText(value);
    }

    @OnClick(R.id.btnImage)
    public void setOnImageBind(){
        imageView.setImageBitmap(image);
    }
}
