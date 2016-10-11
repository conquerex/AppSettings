package com.jongkook.android.fragment_practice_2;

import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"1111","2222","3333"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1, LIST_MENU);
        ListFragment fragment
                = (ListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.setListAdapter(adapter);

    }
}
