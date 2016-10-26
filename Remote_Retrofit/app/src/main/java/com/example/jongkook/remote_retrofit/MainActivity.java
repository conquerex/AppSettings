package com.example.jongkook.remote_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
*   Open API key : 4950685047636f6e3931536e4a594e
*   사용 api : http://openapi.seoul.go.kr:8088/(인증키)/json/SeoulRoadNameInfo/1/5/
*   key 적용한 api : http://openapi.seoul.go.kr:8088/sample/json/SeoulRoadNameInfo/1/5/
*
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String key = "sample";
        String serviceName = "SeoulRoadNameInfo";
        int begin = 1;
        int end = 5;

        String url = "http://openapi.seoul.go.kr:8088/"+ key
                + "/json/" + serviceName + "/" + begin + "/" + end + "/";

        Log.i("url"," ------ "+url);

        // 1. Retrofit client 생성
        Retrofit client = new Retrofit.Builder().baseUrl("http://openapi.seoul.go.kr:8088") //
                .addConverterFactory(GsonConverterFactory.create()) // json 컨버팅 ....
                .build();
        // 2. Rerofit client 에서 사용할 interface 지정
        ISeoulOpenData service = client.create(ISeoulOpenData.class);

        // 3. interface(서비스)를 통해서 data를 호출한다
        Call<RemoteData> remoteData = service.getData(key,serviceName,begin,end);

        // 4. 비동기 데이터를 받기위한 리스너 세팅
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if(response.isSuccessful()){
                    RemoteData data = response.body();
                    for(RemoteData.Row row : data.getSeoulRoadNameInfo().getRow()){
                        Log.i("Remote Data Result","roadNM="+row.getROAD_NM());
                    }
                }else{
                    Log.e("RemoteData",response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

interface ISeoulOpenData{
    @GET("/{key}/json/{serviceName}/{begin}/{end}/")
    Call<RemoteData> getData(@Path("key") String key,
                 @Path("serviceName") String serviceName,
                 @Path("begin") int begin,
                 @Path("end") int end);
}


