package com.jongkook.android.button360sender;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 0.
    public static final String PENDING_RESULT = "pending_result";
    public static final String RESULT = "result";
    public static final int RESULT_CODE = "ok".hashCode();

    private static final int REQUEST_CODE = 0;

    public static final String ACTION = "com.kodonho.android.ACTION_SPINBAR";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Act","---------- onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

    }

    @Override
    // 대기 상태
    protected void onResume() {
        Log.i("Act","---------- onResume");
        super.onResume();

        Intent intent = getIntent();
        if(intent != null){
            Log.i("Act","---------- onResume -- if 1");
            Bundle bundle = intent.getExtras();
            if(bundle!=null && bundle.get("angle") != null) {
                // 필요없는 소스
                Log.i("Act","---------- onResume -- if 2");
                int angle = bundle.getInt("angle");
                spinBar(button, angle);
            }
        }
    }

    public void startService(View v){
        // 1. Start : PendingIntent 선언
        // 인텐트를 나중에 대신 전달하기 위해 사용
        Log.i("Act","---------- startService");
        // PendingIntent 생성
        // createPendingResult(int requestCode, Intent data, int flags)
        // Create a new PendingIntent object which you can hand
        // to others for them to use
        // to send result data back to your onActivityResult(int, int, Intent) callback.
        PendingIntent pending = createPendingResult(REQUEST_CODE, new Intent(), 0);
        // Intent 생성 - MyIntentService.class에 전달
        Intent intent = new Intent(this, MyIntentService.class);
        // pending을 PENDING_RESULT에 담는다
        intent.putExtra(PENDING_RESULT, pending);
        // 서비스 실행
        startService(intent);
        Log.i("Act","---------- startService end");
    }

    protected void onActivityResult(int req, int res, Intent data) {
        Log.i("Act","---------- onActivityResult");
        if (req == REQUEST_CODE && res == RESULT_CODE) {
            Log.i("Act","---------- onActivityResult -- if");
            int result = data.getExtras().getInt("key1");
            spinBar(button, result);
        }
        super.onActivityResult(req, res, data);
    }

    public void spinBar(View v, int angle){
        Log.i("Act","---------- spinBar");
        ObjectAnimator ani = ObjectAnimator.ofFloat(v,"rotation",angle);
        ani.setDuration(1000);
        ani.start();
    }
}
