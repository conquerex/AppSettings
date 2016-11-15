package com.example.jongkook.rxandroid_basic05_filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFilter;
    Button btnForeach;
    Button btnTake;
    Button btnFirst;
    Button btnLast;
    Button btnDistinct;
    Button btnGroupby;
    TextView textView;
    TextView textView2;
    Integer dataset[] = {1,2,3,5,3,7,4,6,9,4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilter = (Button)findViewById(R.id.btnFilter);
        btnForeach = (Button)findViewById(R.id.btnForeach);;
        btnTake = (Button)findViewById(R.id.btnTake);;
        btnFirst = (Button)findViewById(R.id.btnFirst);;
        btnLast = (Button)findViewById(R.id.btnLast);;
        btnDistinct = (Button)findViewById(R.id.btnDistnct);;
        btnGroupby = (Button)findViewById(R.id.btnGroupby);;
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);

        btnFilter.setOnClickListener(this);
        btnForeach.setOnClickListener(this);
        btnTake.setOnClickListener(this);
        btnFirst.setOnClickListener(this);
        btnLast.setOnClickListener(this);
        btnDistinct.setOnClickListener(this);
        btnGroupby.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFilter:
                doFilter();
                break;
            case R.id.btnForeach:
                doForeach();
                break;
            case R.id.btnTake:
                doTake(3);
                break;
            case R.id.btnFirst:
                doFirst();
                break;
            case R.id.btnLast:
                doLast();
                break;
            case R.id.btnDistnct:
                doDistinct();
                break;
            case R.id.btnGroupby:
                doGroupby();
                break;
        }
    }

    private void doGroupby() {
        Observable.from(dataset)
                .groupBy(item -> item%2 == 0)
                .subscribe(grouped -> grouped.toList().subscribe(
                        result -> System.out.println(result + " groupBy >>>" + grouped.getKey())
                ));
    }

    private void doDistinct() {
        Observable.from(dataset)
                .distinct()
                .subscribe(
                        result -> System.out.println("doDistinct >>>"+result)
                );
    }

    private void doLast() {
        Observable.from(dataset)
                .last()
                .subscribe(
                        result -> System.out.println("doLast >>>"+result)
                );
    }

    private void doFirst() {
        Observable.from(dataset)
                .first()
                .subscribe(
                        result -> System.out.println("doFirst >>>"+result)
                );
    }

    private void doTake(int takeCount) {
        Observable.from(dataset)
                .take(takeCount)
                .subscribe(
                        result -> System.out.println(result)
                );
    }

    private void doForeach() {
        Observable.from(dataset)
                .forEach(result -> System.out.println(result));
    }

    private void doFilter() {
        Observable.from(dataset)
                .filter(item -> item%2 == 0)
                .subscribe(
                        result -> System.out.println(result)
                );
    }


}
