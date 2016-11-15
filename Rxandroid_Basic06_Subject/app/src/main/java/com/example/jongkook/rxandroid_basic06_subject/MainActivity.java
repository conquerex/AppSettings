package com.example.jongkook.rxandroid_basic06_subject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.PublicKey;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPublish;
    Button btnBehavior;
    Button btnReplay;
    Button btnAsync;
    Button btnAsyncCompl;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPublish = (Button)findViewById(R.id.btnPublish);
        btnBehavior = (Button)findViewById(R.id.btnBehavior);
        btnReplay = (Button)findViewById(R.id.btnReplay);
        btnAsync = (Button)findViewById(R.id.btnAsync);
        btnAsyncCompl = (Button)findViewById(R.id.btnAsyncCompl);
        textView = (TextView) findViewById(R.id.textView);

        btnPublish.setOnClickListener(this);
        btnBehavior.setOnClickListener(this);
        btnReplay.setOnClickListener(this);
        btnAsync.setOnClickListener(this);
        btnAsyncCompl.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPublish:
                doPublish();
                break;
            case R.id.btnBehavior:
                doBehavior();
                break;
            case R.id.btnReplay:
                doReplay();
                break;
            case R.id.btnAsync:
                doAsync();
                break;
            case R.id.btnAsyncCompl:
                doAsyncComl();
                break;
        }
    }

    private void doAsyncComl() {
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e("AsyncComl","item >>>> "+item)
        );
        subject.onNext("E");
        subject.onNext("F");
        subject.onCompleted();
    }

    private void doAsync() {
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e("Async","item >>>> "+item)
        );
        subject.onNext("E");
        subject.onNext("F");
    }

    private void doReplay() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e("Replay","item >>>> "+item)
        );
        subject.onNext("E");
        subject.onNext("F");
    }

    private void doBehavior() {
        // 가장 최근에 관찰된 아이템부터 구독한다
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e("Behavior","item >>>> "+item)
        );
        subject.onNext("E");
        subject.onNext("F");
    }

    private void doPublish() {
        // 구독한 시점부터 발행된 아이템을 받는다
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e("Publish","item >>>> "+item)
        );
        subject.onNext("E");
        subject.onNext("F");
    }
}
