package com.jongkook.android.personaladdress;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
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
    public static ArrayList<AddressData> datas = null;


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
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initData();
                }
                break;
        }
    }

    public void initData(){

        // datas = getMember();
        datas = getPhoneNumber();

        // 수직 수평 스크롤이 가능
        RecyclerView view = (RecyclerView)findViewById(R.id.recyclerView);
        AddressAdapter adapter = new AddressAdapter(datas,
                R.layout.activity_recycler_item, this);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

//        for(int i = 0 ; i < 100 ; i++) {
//            AddressData data = new AddressData();
//            if (i % 2 == 1) {
//                data.name = i + "Son jongkook";
//                data.phoneNum = "01011112222";
//            } else {
//                data.name = i + "Hong kil";
//                data.phoneNum = "01033334444";
//            }
//            datas.add(data);
//        }
    }

    public ArrayList<AddressData> getPhoneNumber(){
        ArrayList<AddressData> datas = new ArrayList<>();
        String projections[] = {
                ContactsContract.Contacts.DISPLAY_NAME, // 이름
                ContactsContract.Contacts._ID           // 아이디
        };

        Cursor c = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projections, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " desc"
        );

        if(c != null){
            while(c.moveToNext()){
                AddressData data = new AddressData();

                // phoneCusor 추가 시작

                int idIndex = c.getColumnIndex(ContactsContract.Contacts._ID);
                String contactId = c.getString(idIndex);
                Cursor phoneCursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone._ID
                                + " = " +contactId, // 폰의 아이디 = 주소록의 아이디
                        null, null);

                if(phoneCursor.moveToFirst()){
                    String tel = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    data.phoneNum = tel;
                }

                phoneCursor.close();

                // phoneCusor 추가 끝

                data.name = c.getString(c.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                ));
//                data.phoneNum = c.getString(c.getColumnIndex(
//                        ContactsContract.CommonDataKinds.Phone.NUMBER
//                ));
                datas.add(data);
            }
        }
        return datas;
    }

    public  ArrayList<AddressData> getMember(){
        ArrayList<AddressData> datas = new ArrayList<>();

        String projections[] = {
                ContactsContract.Contacts.DISPLAY_NAME, // 이름
                ContactsContract.Contacts._ID           // 아이디
        };

        //getContentResolver().query(주소 검색해 올 컬럼명들, 조건절, 조건절에 매핑되는 값, 정렬);
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                // ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " desc");
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
                AddressData data = new AddressData();

                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactId = cursor.getString(idIndex);

                Cursor phoneCursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone._ID
                                + " = " +contactId, // 폰의 아이디 = 주소록의 아이디
                        null, null);

                if(phoneCursor.moveToFirst()){
                    String tel = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    data.phoneNum = tel;
                }

                phoneCursor.close();

                data.name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                ));

                datas.add(data);
            }
        }

        return datas;
    }
}
