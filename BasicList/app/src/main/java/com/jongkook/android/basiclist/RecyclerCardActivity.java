package com.jongkook.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCardActivity extends AppCompatActivity {

    public static ArrayList<RecyclerData> datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);


        datas = new ArrayList<>();

        for(int i = 0 ; i < 100 ; i++){
            RecyclerData data = new RecyclerData();

            data.title = i + " Hello";
            data.name = "Adele";
            data.image = R.mipmap.adele;
            datas.add(data);
        }

        RecyclerView view = (RecyclerView)findViewById(R.id.recyclerCardView);

        RecyclerCardAdapter adapter = new RecyclerCardAdapter(datas,
                R.layout.activity_recycler_card_item, this);

        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

    }
}
