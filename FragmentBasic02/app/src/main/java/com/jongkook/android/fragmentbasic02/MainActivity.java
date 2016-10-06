package com.jongkook.android.fragmentbasic02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        setOne();

    }

    public void setOne(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.fragment, new FragmentOne());
        transaction.commit();
    }

    public void goTwo(){
        // FragmentManager manager = getFragmentManager();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // 애니메이션 효과
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // transaction.add(R.id.fragment, new FragmentTwo());
        transaction.replace(R.id.fragment, new FragmentTwo());
        // transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goOne(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // 애니메이션 효과
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // transaction.add(R.id.fragment, new FragmentOne());
        transaction.replace(R.id.fragment, new FragmentOne());
        // transaction.addToBackStack(null);
        transaction.commit();
    }
}
