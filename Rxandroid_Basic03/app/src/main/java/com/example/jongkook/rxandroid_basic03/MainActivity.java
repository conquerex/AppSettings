package com.example.jongkook.rxandroid_basic03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doDeferAsync();
    }

    public void doDeferAsync() {

        Log.i("defer",Thread.currentThread().getName()+" : in MAIN -------");

        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Log.i("defer",Thread.currentThread().getName()+" : in defer -------");
                return Observable.just("Here I am !!!!!");
            }
        });

        observable
                .subscribeOn(Schedulers.computation()) // computation Thread에서 defer의 Func가 실행되고
                                                       // subscribeOn에서 지정한 thread에서 defer의 Func에 넘겨준 함수가 실행된다
                                                       // 발행자(Observable) Thread를 지정한다
                .observeOn(Schedulers.newThread())     // 구독이 새로운 thread에서 subscriber로 이벤트가 전달된고
                                                       // Subscriber가 실행되는 Thread를 지정한다
                                                       // 구독자(Observer, subscriber) Thread를 지정한다
                .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("basic03",Thread.currentThread().getName()+" : in Subscriber");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("basic03",Thread.currentThread().getName()+" : in Subscriber ---- " + s);
            }
        });
    }
}
