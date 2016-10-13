package com.jongkook.android.fragment_practice_3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CustomListFragment extends ListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    CustomAdapter adapter;

    public CustomListFragment() {
        // Required empty public constructor
    }

    public static CustomListFragment newInstance(String param1, String param2) {
        CustomListFragment fragment = new CustomListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new CustomAdapter();
        setListAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("AAA","11111");
        // 두 번째 아이템 추가.
        adapter.addItem("Circle", "Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        adapter.addItem("Ind", "Assignment Ind Black 36dp") ;

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void addItem(String title, String desc) {
        adapter.addItem(title, desc) ;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ListViewItem item = (ListViewItem) l.getItemAtPosition(position);

        String title = item.getTitle();
        String contents = item.getContents();

    }

    //    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
