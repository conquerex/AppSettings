package com.jongkook.android.fragment_practice_4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class TitleFragment extends ListFragment {

    public interface OnTitleSelectedListener{
        public void onTitleSelected(int position);
    }

    OnTitleSelectedListener titleSelectedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            titleSelectedListener = (OnTitleSelectedListener)context;
        }catch (Exception e){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // super.onListItemClick(l, v, position, id);

    }

}
