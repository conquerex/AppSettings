package com.jongkook.android.fragment_practice_4;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TitleFragment.OnTitleSelectedListener {

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

        // 7인치 이상 가로모드의 경우
        if(getResources().getConfiguration().isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_LARGE)
                && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            DetailFragment fragment = new DetailFragment();
            Bundle args = new Bundle();

            args.putString("title", contents[0][0]);
            args.putString("detail", contents[0][1]);
            fragment.setArguments(args);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.detail_container, fragment);
            ft.commit();
        }


    }

    @Override
    public void onTitleSelected(int position) {
        if(getResources().getConfiguration().isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_LARGE)
                && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            DetailFragment df = new DetailFragment();
            Bundle args = new Bundle();

            args.putString("title",contents[position][0]);
            args.putString("detail", contents[position][1]);
            df.setArguments(args);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.detail_container,df);
            ft.commit();
        }else{
            Intent intent = new Intent();
            intent.setClass(this, DetailActivity.class);

            intent.putExtra("title",contents[position][0]);
            intent.putExtra("detail", contents[position][1]);

            startActivity(intent);
        }
    }
}
