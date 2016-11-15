package com.example.jongkook.rxandroid_basic07_rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    Button btnLeft;
    Button btnBind;
    Button btnRight;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Rxview binding
        RxView
                .clicks(findViewById(R.id.btnBind))
                .map(event -> new Random().nextInt())
                .subscribe(
                        rand -> ((TextView)findViewById(R.id.textView)).setText("value >>> "+rand)
                );

        // merge
        Observable<String> leftObs = RxView.clicks(findViewById(R.id.btnLeft))
                .map(event -> "left");
        Observable<String> rightObs = RxView.clicks(findViewById(R.id.btnRight))
                .map(event -> "right");

        Observable.merge(leftObs, rightObs)
                .subscribe(
                        text -> Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                );

        // text change event
        RxTextView.textChangeEvents((TextView) findViewById(R.id.editText))
                .subscribe(
                        word -> Log.i("Search","Search >>>>> " + word.text().toString())
                );
    }
}
