package com.jongkook.android.sqlite_memopad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jongkook.android.sqlite_memopad.com.jongkook.android.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION = 1;

    // 1-2. 각 쿼리가 수행될 함수를 정의한다
    // C-insert R-select1개 R-select전체 U-update D-delete 쿼리를 만들어준다
    public void dbInsert(Memo memo){
        // db를 연결해주고
        SQLiteDatabase db = getWritableDatabase();

        //쿼리를 생성
        String query = " insert into memo(contents, ndate)" +
                " values('"+memo.contents+"','"+memo.ndate+"')";

    }

    public Memo dbSelectOne(int no){ // select 는 테이블의 키를 기준으로 값을 받는다
        Memo memo = new Memo(); // 리턴타입에 맞게 객체를 생성해주고

        // todo 처리

        return memo; // 위에서 정의된 리턴변수를 넘겨준다
    }

    public ArrayList<Memo> dbSelectAll(){
        ArrayList<Memo> datas = new ArrayList<>();

        // todo 처리 후

        return datas;
    }

    public void dbUpdate(Memo memo){

    }

    public void dbDelete(Memo memo){

    }



    // 1. SQLiteOpenHelper를 상속받은 dbhelper 생성
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1-1. DB version1 : 최초 등록
        String scheme_01 = "create table memo(no integer primary key autoincrement not null" +
                ", contents text not null" +
                ", ndate integer not null)";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 1-4. 현재 시간을 가져오는 함수를 만든다.
    public long getTimeStamp(){
        return System.currentTimeMillis();
    }

}
