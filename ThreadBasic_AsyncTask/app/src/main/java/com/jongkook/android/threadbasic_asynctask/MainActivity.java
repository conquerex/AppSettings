package com.jongkook.android.threadbasic_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progress;
    TextView percent;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar)findViewById(R.id.progressBar);
        // progress.setMax(100);
        percent = (TextView)findViewById(R.id.textView);
        btnDownload = (Button)findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AsyncTask 동작함.....
                new DownloadTask().execute(100);
            }
        });
    }
                                //        1,    2,    3
    class DownloadTask extends AsyncTask<Integer, Integer, String>{
                                // 1. doInBackground의 parameter 타입
                                // 2. onProgressUpdate의 parameter 타입
                                // 3. onPostExecute의 parameter 타입

        @Override // 제일 먼저 수행되는 함수. 생성자같은 역할
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];
            Log.i("max","max value :::::: "+max);
            try{
                for(int i =0 ; i <= max; i++ ){
                    publishProgress(i);
                    Thread.sleep(10);
                }
            }catch (Exception e){ e.printStackTrace();}

            return "Finish >>>";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
            progress.setProgress(values[0]);
            percent.setText(values[0]+ "%");
        }
        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(aVoid);
            Log.i("Result","msg >>>>>> "+ result);
        }
    }
}
