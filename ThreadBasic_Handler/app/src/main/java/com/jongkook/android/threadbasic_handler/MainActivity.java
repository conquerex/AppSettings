package com.jongkook.android.threadbasic_handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button btnStartHandler;
    Button btnCall;
    TextView textView;

    SubThread thread;
    // Thread subThread;
    LooperHandler handlerThread;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int temp = msg.arg1;
                    // textView.setText("Input from SubThread --------");
                    textView.setText("Result : "+ temp);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new SubThread(handler);

        textView = (TextView)findViewById(R.id.textView);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });

        btnCall = (Button)findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.printLog();
            }
        });

        // 프로그레스바에 쓰레드 적용을 위함
        handlerThread = new LooperHandler(this, "Looper Handler");
        // subThread = HandlerThread.
        handlerThread.start();
        btnStartHandler = (Button)findViewById(R.id.btnStartHandler);
        btnStartHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handlerThread.looperHandler.sendEmptyMessage(LooperHandler.SHOW_PROGRESS);
                handlerThread.start();
                run();
                // handlerThread.looperHandler.sendEmptyMessage(LooperHandler.HIDE_PROGRESS);
                handlerThread.hideProgress();
            }
        });


    }

    public void run(){
        try{
            int sum = 0;
            for(int i = 0 ;i<30;i++){
                sum += i;
                Thread.sleep(100);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class LooperHandler extends HandlerThread{
        public static final int SHOW_PROGRESS = 1;
        public static final int HIDE_PROGRESS = 2;
        Handler looperHandler;
        Context context;
        ProgressDialog progress;

        public LooperHandler(Context context, String name) {
            super(name);
            this.context = context;

        }

        @Override
        protected void onLooperPrepared() {
            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Progress Bar Title");
            progress.setMessage("Message");
            progress.setCancelable(false);

            progress.show();
            Log.i("showProgress","Show Progress msg -------");
            // super.onLooperPrepared();
//            looperHandler = new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    switch (msg.what){
//                        case SHOW_PROGRESS:
//                            showProgress();
//                            break;
//                        case HIDE_PROGRESS:
//                            hideProgress();
//                            break;
//                    }
//                }
//            };
        }

        private void showProgress(){
//            progress.show();
//            Log.i("showProgress","Show Progress msg -------");
        }

        public void hideProgress(){
            progress.dismiss();
            Log.i("showProgress","Hide Progress msg =======");
            quit();
        }
    }

    class SubThread extends Thread{
        Handler mainHandler;
        Handler subHandler;

        public SubThread(Handler mHandler) {
            mainHandler = mHandler;
        }

        int sum = 0;
        public void run(){
            for(int i =0;i<5000;i++){
                sum += i;
            }
            Message msg = new Message();
            msg.what = 1;
            msg.arg1 = sum;
            //textView.setText("Result : "+sum);
            // sHandler.sendEmptyMessage(1);
            mainHandler.sendMessage(msg);
        }

        public void printLog(){
            Log.i("SubThread","called from the main Thread ☃☃☃☃☃");
        }


//        public void run(){
//            Looper.prepare();
//            Log.i("SubThread","after  prepare() ☃☃☃☃☃");
//            subHandler = new Handler(){
//
//            };
//            Log.i("SubThread","before loop() ☃☃☃☃☃");
//            Looper.loop();
//            Log.i("SubThread","after  loop() ☃☃☃☃☃");
//        }
    }
}
