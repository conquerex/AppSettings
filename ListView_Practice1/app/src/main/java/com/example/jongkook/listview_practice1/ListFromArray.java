package com.example.jongkook.listview_practice1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by jongkook on 2016. 10. 27..
 */

public class ListFromArray extends Activity{
    ArrayAdapter<CharSequence> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = ArrayAdapter.createFromResource(this, R.array.country,
                android.R.layout.simple_list_item_1);

        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
