package com.jongkook.android.sqlite_memopad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jongkook.android.sqlite_memopad.com.jongkook.android.sqlite_memopad.domain.Memo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        Memo memo = new Memo();

        dbHelper.dbInsert();
    }
}
