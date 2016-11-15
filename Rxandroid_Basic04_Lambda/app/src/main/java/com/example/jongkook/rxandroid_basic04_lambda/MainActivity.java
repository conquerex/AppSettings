package com.example.jongkook.rxandroid_basic04_lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLambda;
    Button btnMap;
    Button btnFlatmap;
    Button btnZip;
    TextView textView;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLambda = (Button)findViewById(R.id.btnLambda);
        btnMap = (Button)findViewById(R.id.btnMap);
        btnFlatmap = (Button)findViewById(R.id.btnFlatmap);
        btnZip = (Button)findViewById(R.id.btnZip);
        textView = (TextView)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listView);

        btnLambda.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnFlatmap.setOnClickListener(this);
        btnZip.setOnClickListener(this);

        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLambda:
                doLambda();
                break;
            case R.id.btnMap:
                doMap();
                break;
            case R.id.btnFlatmap:
                doFlatmap();
                break;
            case R.id.btnZip:
                doZip();
                break;
        }
    }

    private void doZip() {
        Observable.zip(
                Observable.just("jongkook"),
                Observable.just("image.jpg"),
                (item1, item2) -> "Name : "+ item1 +" , Prof image : " + item2
        ).subscribe(
                ziped -> Log.w("ZIP","Next item = " + ziped)
        );
    }

    private void doFlatmap() {
        Observable.from(new String[]{"aaa","bbb","ccc","ddd"})
                .flatMap(item -> Observable.from(new String[]{"name : "+item, item.getBytes()+""}))
                .subscribe(
                        item -> datas.add(item),
                        e -> e.printStackTrace(),
                        () -> adapter.notifyDataSetChanged()
                );
    }

    private void doMap() {
        Observable.from(new String[]{"aaa","bbb","ccc","ddd"})
            .map(item -> "I "+item+" you")
            .subscribe(
                    item -> datas.add(item),
                    e -> e.printStackTrace(),
                    () -> adapter.notifyDataSetChanged()
            );
    }

    private void doLambda() {
        Observable<String> observable = Observable.just("I am Lambda !!!!");

        observable.subscribe(
            item -> textView.setText(item),
            e -> e.printStackTrace(),
            () -> Log.i("Lambda","Completed !!!!!!")
        );
    }
}
