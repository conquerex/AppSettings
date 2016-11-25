package com.example.jongkook.myapplication2;

import android.app.Application;

/**
 * Created by jongkook on 2016. 11. 21..
 */

public class MyApp extends Application {
    public static boolean firstService = false;
    public static void setServiceStatus(boolean flag){
        firstService = flag;

    }

    public static boolean isFisrtService(){
        return firstService;
    }
}
