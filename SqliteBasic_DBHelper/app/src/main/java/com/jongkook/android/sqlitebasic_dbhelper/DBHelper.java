package com.jongkook.android.sqlitebasic_dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jongkook on 2016. 10. 12..
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION = 3;

    @Override
    // 설치 될 때, 한번만 실행됨
    public void onCreate(SQLiteDatabase db) {
        // assets에 있는 파일을 복사해서 디스크로 옮긴다

        String scheme = "create table bbs4(no integer primary key autoincrement not null " +
                ", title text not null)";
        db.execSQL(scheme);

        for(int i=0;i<100;i++){
            String query = "insert into bbs4(title) values('>>>>>>>>>> title "+ i +"')";
            db.execSQL(query);
        }
    }

    @Override
    // DB_VERSION이 높아질 때, 한번만
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 변경사항이 있으면 디스크에 있는 db에 덮어쓰기 한다

        String query = "delete from bbs4";
        db.execSQL(query);
        for(int i=0;i<100;i++){
            query = "insert into bbs4(title) values(' everything "+ i +"')";
            db.execSQL(query);
        }
    }

    private static DBHelper dbHelper = null;
    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLiteDatabase openDatabase(Context context){
        if(dbHelper == null)
            dbHelper = new DBHelper(context);
        return dbHelper.getWritableDatabase();
    }
}
