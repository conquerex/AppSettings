package com.jongkook.android.fragment_practice_2;


import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;


public class MenuListFragment extends ListFragment {
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String str = (String)l.getItemAtPosition(position);
    }
}
