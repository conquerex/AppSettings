package com.example.jongkook.rxandroid_basic09_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public final static String BASE_URL = "http://api.openweathermap.org";
    public final static String API_KEY = "b693b5a8a7fa98d6d2fc9e758e7cb9a4";
    TextView result;
    EditText city;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView)findViewById(R.id.textView2);
        button = (Button)findViewById(R.id.button);
        city = (EditText)findViewById(R.id.editText);

        button.setOnClickListener(e -> getWeather());
    }


    public void getWeather(){
        String cityName = city.getText().toString();

        // 1. retrofit 클라이언트 설정
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        // 2. Rest API 서비스 생성
        IWeather service = client.create(IWeather.class);

        // 3. 데이터 옵저버블 생성
        Observable<Data> weatherData = service.getData(cityName, API_KEY);

        // 4. subscribeOn -
        //   가. 데이터를 가져오는 대상 Observerble : new Thread 로 새로운 Thread에서 작업한다
        //   나. 화면에 세팅하는 Observer : mainThread에서 작업한다.
        weatherData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            String temp = "";
                            temp += "ID : " + data.getId();
                            temp += "\nName : " + data.getName();
                            temp += "\nBase : " + data.getBase();
                            result.setText(temp);
                        }
                );
    }


}
