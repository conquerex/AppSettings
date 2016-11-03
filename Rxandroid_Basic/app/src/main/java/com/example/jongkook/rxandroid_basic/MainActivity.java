package com.example.jongkook.rxandroid_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Observable을 생성
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello Rxandroid!!!!!!!!!!");
                subscriber.onNext("Hello Jongkook!!!!!!!!!!");
                subscriber.onCompleted();
            }
        });

        // 2. Observable을 통해 데이터를 가져온다
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("Complete"," ----- Complete ----");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("onNext",s);
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ((TextView)findViewById(R.id.textView)).setText(s);
            }
        });
    }
}
