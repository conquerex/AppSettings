package com.example.jongkook.remote_rest_practice;

import android.os.AsyncTask;

/**
 * Created by jongkook on 2016. 10. 25..
 */

public class OpenWeatherAPITask extends AsyncTask<Integer, Void, Weather> {
    @Override
    public Weather doInBackground(Integer... params) {
        OpenWeatherAPIClient client = new OpenWeatherAPIClient();

        int lat = params[0];
        int lon = params[1];
        // API 호출
        Weather w = client.getWeather(lat,lon);

        //System.out.println("Weather : "+w.getTemprature());

        // 작업 후 리
        return w;
    }
}