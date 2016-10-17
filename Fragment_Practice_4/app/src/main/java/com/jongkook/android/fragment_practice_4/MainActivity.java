package com.jongkook.android.fragment_practice_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String[][] contents = new String[3][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0;i < contents.length; i++){
            for (int j = 0;j < contents[i].length; j++){
                if(j == 0) contents[i][j] = "Title-" +(i+1);
                else contents[i][j] = "This is Details of Title-" +(i+1);
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1, new ArrayList());
        adapter.add(contents[0][0]);
        adapter.add(contents[0][1]);
        adapter.add(contents[0][2]);

        TitleFragment titleFragment = (TitleFragment)getSupportFragmentManager()
                .findFragmentById(R.id.title_fragment);
        titleFragment.setListAdapter(adapter);
    }
}
