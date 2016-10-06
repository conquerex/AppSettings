package com.jongkook.android.fragmentbasic01;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fragment1 : goOne();
                        break;
                    case R.id.fragment2 : goTwo();
                        break;
                }

            }
        });

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
    }

    public void goTwo(){
        // FragmentManager manager = getFragmentManager();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // transaction.add(R.id.fragment, new FragmentTwo());
        transaction.replace(R.id.fragment, new FragmentTwo());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goOne(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // transaction.add(R.id.fragment, new FragmentOne());
        transaction.replace(R.id.fragment, new FragmentOne());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
