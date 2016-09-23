package com.jongkook.android.button360sender;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Service","----------intent="+intent.getExtras());
        for(int i=1;i<=10;i++) {

            Log.i("Service","----------angle="+i*36);
            int angle = i * 36;

            try {
                Intent result = new Intent();
                result.putExtra("key1", angle);
                PendingIntent reply = intent.getParcelableExtra(MainActivity.PENDING_RESULT);
                reply.send(this, MainActivity.RESULT_CODE, result);

                Thread.sleep(1000);
            }catch(Exception e){}
        }
    }
}
