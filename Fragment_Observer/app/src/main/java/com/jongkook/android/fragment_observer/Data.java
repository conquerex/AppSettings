package com.jongkook.android.fragment_observer;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by jongkook on 2016. 10. 7..
 */
public class Data {
    ArrayList<Observer> observers = new ArrayList<>();
    ArrayList<MusicData> datas = new ArrayList<>();
    int position = -1;

    public void attach(Observer o){

    }

    class MusicData{

    }

    public int getCount(){

    }

    public interface Observer {
        public void update();
    }
}
