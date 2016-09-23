package com.jongkook.android.broadcastrecieve01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    static final String BROADCAST_ACTION = "com.jongkook.android.MESSAGE";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(BROADCAST_ACTION)){

            // Activity 화면을 띄어준다
            Intent i = new Intent(context, MapsActivity.class);
            i.addFlags(
                    i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TOP
            );
            context.startActivity(i);
        }
    }
}
