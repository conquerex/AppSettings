package com.jongkook.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class DataUtil {
    private final static String TAG = "DataUtil";
    public static final String DB_NAME = "sqlite.db";

    public static void insert(Context context, BbsData data){
        SQLiteDatabase db = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "insert into bbs3(name, title, contents) " +
                    "values('"+data.name+"','"+data.title+"','"+data.contents+"')";
            // 3. 쿼리를 실행한다
            db.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(db!=null) db.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void update(Context context, BbsData data){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "update bbs3 set " +
                    "name =     '"+data.name+"'," +
                    "title =    '"+data.title+"'," +
                    "contents = '"+data.contents+"'," +
                    "ndate = CURRENT_TIMESTAMP " +
                    "where no ="+data.no;
            Log.i("QQQ",">>>>>>>> "+query);
            // 3. 쿼리를 실행한다
            db.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(db!=null) db.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static BbsData select(Context context,int bbsno){
        BbsData data = new BbsData();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "select * from bbs3 where no = "+bbsno;
            // 3. 쿼리를 실행한다
            cursor = db.rawQuery(query,null);
            // 4. 반복문을 통해 값을 datas에 담아둔다
            if(cursor.moveToNext()){
                // 5. 레코드셋을 하나를 Bbsdata단위로 생성한 후
                //    ArrayList에 담아준다.
                int idx = cursor.getColumnIndex("no");
                data.no = cursor.getInt(idx);
                idx = cursor.getColumnIndex("title");
                data.title = cursor.getString(idx);
                idx = cursor.getColumnIndex("name");
                data.name = cursor.getString(idx);
                idx = cursor.getColumnIndex("contents");
                data.contents = cursor.getString(idx);
                idx = cursor.getColumnIndex("ndate");
                data.ndate = cursor.getString(idx);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(db!=null) db.close();
                if(cursor!=null) cursor.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return data;
            }

            public static ArrayList<BbsData> selectAll(Context context){
                ArrayList<BbsData> datas = new ArrayList<>();
                SQLiteDatabase db = null;
                Cursor cursor = null;
                try{
                    // 1. db를 연결한다
                    db = openDatabase(context, DB_NAME);
                    // 2. 쿼리를 만든다
                    String query = "select no, title from bbs3";
                    // 3. 쿼리를 실행한다
                    cursor = db.rawQuery(query,null);
                    // 4. 반복문을 통해 값을 datas에 담아둔다
                    while (cursor.moveToNext()){
                        // 5. 레코드셋을 하나씩 돌면서 Bbsdata단위로 생성한 후
                //    ArrayList에 담아준다.
                BbsData data = new BbsData();
                int idx = cursor.getColumnIndex("no");
                data.no = cursor.getInt(idx);
                idx = cursor.getColumnIndex("title");
                data.title = cursor.getString(idx);
                datas.add(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(db!=null) db.close();
                if(cursor!=null) cursor.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return datas;
    }

    public static void delete(int bbsno){

    }


    // 데이터베이스를 정의
    public static SQLiteDatabase openDatabase(Context context, String dbFileName){
        return SQLiteDatabase.openDatabase(getFullpath(context, dbFileName), null, 0);
        // 0 : 쓰기가능 / 1 : 읽기만
    }

//    public void init(){
//        assetToDisk("sqlite.db");
//
//        result = (TextView)findViewById(R.id.textView);
//        openDatabase = (Button)findViewById(R.id.button);
//        btnInsert = (Button)findViewById(R.id.button2);
//        btnSelect = (Button)findViewById(R.id.button3);
//        btnUpdate = (Button)findViewById(R.id.button4);
//        btnDelete = (Button)findViewById(R.id.button5);
//    }

    // 파일이름을 입력하면 내장 디렉토리에 있는 파일의 전체경로를 리턴
    public static String getFullpath(Context context, String fileName){
        return context.getFilesDir().getAbsolutePath() + File.separator + fileName;
    }

    // assets에 있는 파일을 쓰기가능한 disk 디렉토리로 복사한다
    // 안드로이드 internal disk는 cache, files등 쓰기가능한 폴더가 정해져 있다
    public static void assetToDisk(Context context, String fileName){
        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try{
            // 외부에서 작성된 sqlite db파일 사용하기
            // 1. assets에 담아둔 파일을 internal 혹은 external 공간으로 복사한다
            AssetManager manager = context.getAssets();

            is = manager.open(fileName); // assets에 파일이 없으면 exception이 발생하여 아래 로직이 실행되지 않는다
            bis = new BufferedInputStream(is);

            // 2. 저장할 위치에 파일을 생성한다
            String internalPath = context.getFilesDir().getAbsolutePath();
            Log.i(TAG,"internalPath >>>>>>> " + internalPath);
            // String targetFile = internalPath + File.separator + fileName;
            String targetFile = getFullpath(context, fileName);
            Log.i(TAG,"targetFile >>>>>>>>> " + targetFile);


            File file = new File(targetFile);
            if(!file.exists()){
                file.createNewFile();
            }

            // 3. outputStream을 생성해서 파일 내용을 쓴다
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            // 읽어올 데이터를 담아줄 변수
            int read = -1;
            // 한번에 읽을 버퍼의 크기를 지정
            byte buffer[] = new byte[1024];
            // 더 이상 읽어올 데이터가 없을대까지 buffer 단위로 읽어서 쓴다
            while((read = bis.read(buffer, 0 ,1024)) != -1){
                bos.write(buffer, 0, read);
            }
            // 남은 데이터 찌꺼기를 제거
            bos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bos != null) bos.close();
                if(fos != null) fos.close();
                if(bis != null) bis.close();
                if(is != null) is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
