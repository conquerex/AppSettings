package com.example.jongkook.listview_practice1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arGeneral = new ArrayList<>();
    ArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);

        arGeneral.add("손종국");
        arGeneral.add("구자욱");
        arGeneral.add("나성범");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arGeneral);
        listView.setAdapter(adapter);

    }
}
