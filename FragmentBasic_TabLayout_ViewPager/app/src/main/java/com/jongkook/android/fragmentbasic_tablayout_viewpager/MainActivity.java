package com.jongkook.android.fragmentbasic_tablayout_viewpager;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

/*
    1. Gradle에 design 라이브러리 추가
    2. main.xml에 tab
 */

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    static final int FRAGMENT_COUNT = 4;

    HomeFragment home;
    MapFragment map;
    EtcFragment etc;
    BlankFragment settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 01. 각 프래그먼트 선언
        home     = new HomeFragment();
        map      = new MapFragment();
        etc      = new EtcFragment();
        // settings = new SettingsFragment();
        settings = BlankFragment.newInstance("","");

        TabLayout tab = (TabLayout)findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Home"));
        tab.addTab(tab.newTab().setText("Map"));
        tab.addTab(tab.newTab().setText("Etc"));
        tab.addTab(tab.newTab().setText("Settings"));

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        // 05. 페이저가 변경되었을 때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        // 06. 탭에 페이져를 연결해주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

//        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        if(fragment instanceof BlankFragment){
            Toast.makeText(this,"Blank -------",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No Blank -----",Toast.LENGTH_SHORT).show();
        }

    }

    // 02. adapter 생성
    class PagerAdapter extends FragmentStatePagerAdapter{
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 04. 각 페이지에 fragment 적용
            Fragment fragment = null;
            switch (position){
                case 0: fragment = home; break;
                case 1: fragment = map; break;
                case 2: fragment = etc; break;
                case 3: fragment = settings; break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // 03. 카운트 임의 수 입력
            // 페이지 수 결정
            return FRAGMENT_COUNT;
        }
    }


}

