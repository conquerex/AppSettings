package com.jongkook.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecylcerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylcer);

        ArrayList<RecyclerData> datas = new ArrayList<>();
        for(int i = 0 ; i < 100 ; i++){
            RecyclerData data = new RecyclerData();

            data.title = "Hello";
            data.name = "Adele";
            data.image = R.mipmap.adele;
            datas.add(data);
        }
        RecyclerView listView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(datas, R.layout.recycler_item);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));


    }
}
