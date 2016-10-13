package com.jongkook.android.sqlite_memopad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jongkook.android.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION = 2;
    SQLiteDatabase db;
    Cursor cursor;

    // 1-2. 각 쿼리가 수행될 함수를 정의한다
    // C-insert R-select1개 R-select전체 U-update D-delete 쿼리를 만들어준다
    public void dbInsert(Memo memo){
        // db를 연결해주고
        db = getWritableDatabase();

        // 2. insert 쿼리를 생성
        String query = " insert into memo(contents, ndate, image)" +
                " values('"+memo.contents+"','"+getTimeStamp()+"','"+memo.image+"')";
        db.execSQL(query);
        db.close();
    }

    public Memo dbSelectOne(int no){ // select 는 테이블의 키를 기준으로 값을 받는다
        Memo memo = new Memo(); // 리턴타입에 맞게 객체를 생성해주고

        // todo 처리

        return memo; // 위에서 정의된 리턴변수를 넘겨준다
    }

    public ArrayList<Memo> dbSelectAll(){
        // 3. Select All 쿼리를 생성
        ArrayList<Memo> datas = new ArrayList<>();

        db = getReadableDatabase();
        String query = "select no, contents, ndate, image from memo ";
        cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Memo data = new Memo();
            int idx = cursor.getColumnIndex("no");
            data.no = cursor.getInt(idx);
            idx = cursor.getColumnIndex("contents");
            data.contents = cursor.getString(idx);
            idx = cursor.getColumnIndex("ndate");
            data.ndate = cursor.getLong(idx);
            idx = cursor.getColumnIndex("image");
            data.image = cursor.getString(idx);
            datas.add(data);
        }
        cursor.close();
        db.close();
        return datas;
    }

    public void dbUpdate(Memo memo){

    }

    public void dbDelete(Memo memo){

    }



    // 1. SQLiteOpenHelper를 상속받은 dbhelper 생성
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1-1. DB version1 : 최초 등록
//        String scheme_01 = "create table memo(no integer primary key autoincrement not null" +
//                ", contents text not null" +
//                ", ndate integer not null)";
//        // 실행해서 table을 생성한다
//        db.execSQL(scheme_01);

        String scheme_02 = "create table memo(no integer primary key autoincrement not null" +
                ", contents text not null" +
                ", image text" +
                ", ndate integer not null)";
        db.execSQL(scheme_02);

        for(Memo memo:backupDatas){
            String query = " insert into memo(contents, ndate)" +
                    " values('"+memo.contents+"','"+memo.ndate+"')";
            db.execSQL(query);
        }
    }

    ArrayList<Memo> backupDatas = new ArrayList<>();

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Cursor cursor;
        Memo data;

        // 이전 데이터베이스 버전에 따라 처리방식이 달라진다
        if(oldVersion == 1){
            // 컬럼이 3개
            String query = "select * from memo order by no ";
            cursor = db.rawQuery(query,null);
            while(cursor.moveToNext()){
                data = new Memo();
                data.no = cursor.getInt(0);
                data.contents = cursor.getString(1);
                data.ndate = cursor.getLong(2);
                backupDatas.add(data);
            }
        }else if(oldVersion == 2){
            // 컬럼이 4개
        }

        // db version 2 - upgrade 20161013 14:13
        String scheme_01_drop = "drop table memo";
        db.execSQL(scheme_01_drop);

        onCreate(db);

    }

    // 1-4. 현재 시간을 가져오는 함수를 만든다.
    public long getTimeStamp(){
        return System.currentTimeMillis();
    }

}
