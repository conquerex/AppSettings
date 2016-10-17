package com.jongkook.android.fragment_practice_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        // 타블렛의 경우를 위한 소스
        Intent intent = getIntent();

        TextView detailText = (TextView)findViewById(R.id.detail);
        detailText.setText(intent.getExtras().getString("detail"));

        TextView titleText = (TextView)findViewById(R.id.title);
        titleText.setText(intent.getExtras().getString("title"));

    }
}
