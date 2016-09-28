package com.jongkook.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class BasicGridActivity extends AppCompatActivity {

    String datas[] = {"백향목","김동진","김태원","임재민","김도형","석주영","장홍석","김해든"};
    ArrayAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_basic_grid);

        gridView = (GridView)findViewById(R.id.gridView);

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                datas);

        gridView.setAdapter(adapter);
    }
}
