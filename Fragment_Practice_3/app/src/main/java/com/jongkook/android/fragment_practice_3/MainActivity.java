package com.jongkook.android.fragment_practice_3;

import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CustomListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomListFragment customListFragment
                = (CustomListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        // customListFragment.addItem("New Box", "New Account Box Black 36dp") ;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
