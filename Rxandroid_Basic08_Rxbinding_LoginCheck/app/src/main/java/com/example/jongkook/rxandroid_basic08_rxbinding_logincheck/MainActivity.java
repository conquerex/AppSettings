package com.example.jongkook.rxandroid_basic08_rxbinding_logincheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

import static com.jakewharton.rxbinding.widget.RxTextView.textChangeEvents;

public class MainActivity extends AppCompatActivity {

    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignin = (Button)findViewById(R.id.btnSignin);

        Observable<TextViewTextChangeEvent> idObs
                = textChangeEvents((EditText)findViewById(R.id.editText));
        Observable<TextViewTextChangeEvent> pwObs
                = textChangeEvents((EditText)findViewById(R.id.editText2));
        Observable.combineLatest(idObs, pwObs,
                (idChanges, pwChanges) -> {
                    boolean idCheck = idChanges.text().length() >= 5;
                    boolean pwCheck = pwChanges.text().length() >= 8;
                    return idCheck && pwCheck;
                }
        ).subscribe(
                checkFlag -> btnSignin.setEnabled(checkFlag)
        );
    }
}
