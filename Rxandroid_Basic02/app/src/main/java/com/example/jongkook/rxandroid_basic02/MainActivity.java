package com.example.jongkook.rxandroid_basic02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnJust;
    Button btnFrom;
    Button btnDefer;
    TextView textView;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJust = (Button)findViewById(R.id.btnJust);
        btnFrom = (Button)findViewById(R.id.btnFrom);
        btnDefer = (Button)findViewById(R.id.btnDefer);
        textView = (TextView)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listView);

        btnJust.setOnClickListener(this);
        btnFrom.setOnClickListener(this);
        btnDefer.setOnClickListener(this);


        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnJust:
                doJust();
                break;
            case R.id.btnFrom:
                doFrom();
                break;
            case R.id.btnDefer:
                doDefer();
                break;

        }
    }

    // 지연처리 함수를 제공하고
    // 호출할때마다 옵저버블 객체를 매번 생성한다.
    private void doDefer() {
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("bird");
            }
        });

        observable.delaySubscription(3, TimeUnit.SECONDS).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

            }
        });
    }

    private void doFrom() {
        Observable<String> observable = Observable.from(new String[]{"dog","bird","chicken","lion","yongkachu"});
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                datas.add(s);
            }
        }, new Action1<Throwable>() {

            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void doJust() {
        Observable<String> observable = Observable.just("dddd");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        });

    }
}