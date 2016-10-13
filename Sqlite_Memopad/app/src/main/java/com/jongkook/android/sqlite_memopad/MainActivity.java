package com.jongkook.android.sqlite_memopad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jongkook.android.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        Log.i("DBHelper","version >>>> "+dbHelper.getReadableDatabase().getVersion());

        Memo memo = new Memo();
        memo.contents = "내용 입력입니다. ver2";
        memo.ndate = dbHelper.getTimeStamp();

        dbHelper.dbInsert(memo);

        ArrayList<Memo> datas = dbHelper.dbSelectAll();
        for(Memo data:datas){
            Log.i("Memo","no = "+data.no);
            Log.i("Memo","content = "+data.contents);
            Log.i("Memo","date = "+data.ndate);
            Log.i("Memo","image = "+data.image);
        }
    }
}
