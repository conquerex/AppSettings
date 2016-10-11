package com.jongkook.android.sqlitebasic_bbs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    EditFragment ef; // 쓰기 프래그먼트
    ListFragment lf; // 목록 프래그먼트

    ViewPager pager;
    private static ArrayList<BbsData> datas = new ArrayList<>();


    // 앱초기화 > onCreate에서 호출
    private void init(){
        // DB 파일이 internal files 디렉토리에 있는지 여부 검사
        File file = new File(DataUtil.getFullpath(this,DataUtil.DB_NAME));
        // 파일에 없을때만 db 파일 생성
        if(!file.exists())
            DataUtil.assetToDisk(this,DataUtil.DB_NAME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        setContentView(R.layout.activity_main);

        lf = new ListFragment();
        ef = new EditFragment();
        pager = (ViewPager)findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

    }


    // 파일이름을 입력하면 내장 디렉토리에 있는 파일의 전체경로를 리턴
    public String getFullpath(String fileName){
        return getFilesDir().getAbsolutePath() + File.separator + fileName;
    }

    /**************
     * 기능별 함수
    **************/

    /**************
     * Adapter
     **************/

    class CustomAdapter extends FragmentStatePagerAdapter{
        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            // 페이저의 첫번째 화면 : 목록 프래그먼트
            //        두번째 화면 : 상세 프래그먼트
            switch (position){
                case 0: fragment=lf; break;
                case 1: fragment=ef; break;
//                case ACTION_WRITE:
//                    // edit 프래그먼트로 이동
//                    pager.setCurrentItem(1); break;
//                case ACTION_SAVE:
//                    //
//                    pager.setCurrentItem(0); break;
//                case ACTION_CANCEL:
//                    pager.setCurrentItem(0); break;
//                case ACTION_MODIFY:
//                    pager.setCurrentItem(1); break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public final static int ACTION_GOLIST = 0;
    public final static int ACTION_GOEDIT = 1;
    public final static int ACTION_GOLIST_WITH_REFRESH = 4;

    @Override
    public void action(int flag) {
        Toast.makeText(this, "프래그먼트에서 클릭 됩니다", Toast.LENGTH_SHORT).show();
        switch (flag){
            case ACTION_GOLIST:
                pager.setCurrentItem(0);; break;
            case ACTION_GOEDIT:
                pager.setCurrentItem(1);; break;
            case ACTION_GOLIST_WITH_REFRESH:
                // TODO 목록 갱신추가
                // 리스트에 새로운 데이터를 추가하고
                lf.setList();
                // 페이저를 리스트로 이동
                lf.adapter.notifyDataSetChanged();
                pager.setCurrentItem(0);; break;
        }
    }

    @Override
    public void actionEdit(int bbsno) {
        ef.setData(bbsno);
        // action(ACTION_GOEDIT);
    }
}
