package com.example.jongkook.rxandroid_basic09_retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**  http://api.openweathermap.org/data/2.5/weather?q=seoul&APPID=b693b5a8a7fa98d6d2fc9e758e7cb9a4
 * {"coord":{"lon":126.98,"lat":37.57},"weather":[{"id":721,"main":"Haze","description":"haze","icon":"50d"}],"base":"stations","main":{"temp":288.3,"pressure":1013,"humidity":58,"temp_min":286.15,"temp_max":289.15},"visibility":6000,"wind":{"speed":0.5,"deg":100},"clouds":{"all":75},"dt":1478244000,"sys":{"type":1,"id":8519,"message":0.0111,"country":"KR","sunrise":1478210426,"sunset":1478248220},"id":1835848,"name":"Seoul","cod":200}
 *
 *
 * key : b693b5a8a7fa98d6d2fc9e758e7cb9a4
 */


public interface IWeather {
    @GET("/data/2.5/weather")
    Observable<Data> getData(
            @Query("q") String cityName,
            @Query("APPID") String API_KEY
    );
}
