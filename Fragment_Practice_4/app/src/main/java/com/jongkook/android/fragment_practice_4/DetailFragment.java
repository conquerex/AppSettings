package com.jongkook.android.fragment_practice_4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle args = getArguments();

        TextView titleText = (TextView)view.findViewById(R.id.title);
        TextView detailText = (TextView)view.findViewById(R.id.detail);

        titleText.setText(args.getString("title"));
        detailText.setText(args.getString("detail"));

        return view;
    }
}
