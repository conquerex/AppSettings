package com.jongkook.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    TextView result;
    Button openDatabase;
    Button btnInsert;
    Button btnSelect;
    Button btnUpdate;
    Button btnDelete;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        openDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스를 연결하는 Api
                db = SQLiteDatabase.openDatabase(
                        getFullpath("sqlite.db"), null, 0);
                        // 0 : 쓰기가능 / 1 : 읽기만
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null){
                    // 쿼리를 실행해 준다
                    // Select문을 제외한 모든 쿼리에 사용
                    db.execSQL("insert into bbs3(title,name,contents)" +
                            "values('안드로이드','구글','contents test');");
                    db.execSQL("insert into bbs3(title,name,contents)" +
                            "values('스위프트','애플','apple test');");
                    // 쿼리를 실행 후 결과값을 커서로 리턴해준다
                    // 즉 Select문에 사용
                    // db.rawQuery("",null);
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null){
                    Cursor cursor = db.rawQuery("select * from bbs3",null);
                    while(cursor.moveToNext()){
                        int idx = cursor.getColumnIndex("no");
                        String id = cursor.getString(idx);
                        idx = cursor.getColumnIndex("title");
                        String title = cursor.getString(idx);
                        idx = cursor.getColumnIndex("name");
                        String name = cursor.getString(idx);
                        idx = cursor.getColumnIndex("contents");
                        String contents = cursor.getString(idx);
                        idx = cursor.getColumnIndex("ndate");
                        String ndate = cursor.getString(idx);

                        String temp = result.getText().toString();

                        result.setText(temp+"\n id : "+id+" // title : "+title
                                + " // name : "+name +" // contents : "+ contents
                                + " // ndate : "+ndate);

                    }
                }
            }
        });

    }

    public void init(){
        assetToDisk("sqlite.db");

        result = (TextView)findViewById(R.id.textView);
        openDatabase = (Button)findViewById(R.id.button);
        btnInsert = (Button)findViewById(R.id.button2);
        btnSelect = (Button)findViewById(R.id.button3);
        btnUpdate = (Button)findViewById(R.id.button4);
        btnDelete = (Button)findViewById(R.id.button5);
    }

    // 파일이름을 입력하면 내장 디렉토리에 있는 파일의 전체경로를 리턴
    public String getFullpath(String fileName){
        return getFilesDir().getAbsolutePath() + File.separator + fileName;
    }

    public void assetToDisk(String fileName){

        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try{
            // 외부에서 작성된 sqlite db파일 사용하기
            // 1. assets에 담아둔 파일을 internal 혹은 external 공간으로 복사한다
            AssetManager manager = getAssets();

            is = manager.open(fileName); // assets에 파일이 없으면 exception이 발생하여 아래 로직이 실행되지 않는다
            bis = new BufferedInputStream(is);

            // 2. 저장할 위치에 파일을 생성한다
            String internalPath = getFilesDir().getAbsolutePath();
            Log.i(TAG,"internalPath >>>>>>> " + internalPath);
            // String targetFile = internalPath + File.separator + fileName;
            String targetFile = getFullpath(fileName);
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
