package com.jongkook.android.medialibrary;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE = 0;
    public static ArrayList<RecyclerData> datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            initData();
        else checkPermissions();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions(){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ){
            // 쓰기 권한이 없으면 로직 처리
            String permissionArray[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissionArray, REQUEST_CODE);

        }else{
            // 쓰기 권한이 있으면 파일 생성
            initData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initData();
                }
                break;
        }
    }

    public void initData(){
        datas = getMusicInfo();

//        for(int i = 0 ; i < 100 ; i++) {
//            RecyclerData data = new RecyclerData();
//            if (i % 2 == 1) {
//                data.title = i + " Good night";
//                data.singer = "Jiwoo";
//            } else {
//                data.title = i + " Fall";
//                data.singer = "Jiwoo";
//            }
//            datas.add(data);
//        }

        RecyclerView view = (RecyclerView)findViewById(R.id.recyclerCardView);

        RecyclerCardAdapter adapter = new RecyclerCardAdapter(datas,
                R.layout.activity_recycler_card_item, this);

        view.setAdapter(adapter);

        // 수직 수평 스크롤이 가능
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        // 그리드 타입
        // RecyclerView.LayoutManager manager = new GridLayoutManager(this,2);
        // StaggerdGrid 타입
        // RecyclerView.LayoutManager manager
        //        = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        view.setLayoutManager(manager);
    }

    public  ArrayList<RecyclerData> getMusicInfo(){
        ArrayList<RecyclerData> datas = new ArrayList<>();

        String projections[] = {
                MediaStore.Audio.Media._ID,     // 노래 아이디
                MediaStore.Audio.Media.ALBUM_ID,// 앨범 아이디
                MediaStore.Audio.Media.TITLE,   // 제목
                MediaStore.Audio.Media.ARTIST   // 가수
        };

        //getContentResolver().query(주소 검색해 올 컬럼명들, 조건절, 조건절에 매핑되는 값, 정렬);
        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                // ContactsContract.Contacts.CONTENT_URI,
                projections, null, null, null);
        /*
        * - uri           : content://스키마 형태로 정해져 있는 곳의 데이터를 가져온다.
        * - projection    : 가져올 컬럼 이름들의 배열. null을 입력하면 모든 값을 가져온다
        * - selection     : 조건절(where)에 해당하는 내용
        * - selectionArgs : 조건절이 preparedstatement 형태일때 ?에 매핑되는 값의 배열
        * - sort          : 정렬 조건
        * */

        if(cursor != null){
            // 데이터에 가수 이름을 입력
            // 1. 가수 이름 컬럼의 순서(index)를 가져온다
            // 2. 해당 index를 가진 칼럼의 실제값을 가져온다
            while (cursor.moveToNext()){
                RecyclerData data = new RecyclerData();
                data.singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                data.title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                data.albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                data.musicId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));

                datas.add(data);
            }
        }

        return datas;
    }
}
