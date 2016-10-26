package com.example.jongkook.remote_retrofitwithokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* *   Open API key : 4950685047636f6e3931536e4a594e
* */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callHttp();
    }

    public void callHttp(){
        String key = "4950685047636f6e3931536e4a594e";
        String serviceName = "SeoulRoadNameInfo";
        int begin = 1;
        int end = 5;

        Call<RemoteData> remoteData = RestAdapter.getInstance()
                .getData(key,serviceName,begin,end);

        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if(response.isSuccessful()){
                    RemoteData data = response.body();
                    List<RemoteData.Row> rows = data.getSeoulRoadNameInfo().getRow();
                    for(RemoteData.Row row : rows){
                        Log.w("[ROW]","ROAD NM ------ " + row.getROAD_NM());
                        Log.w("[ROW]","ROAD TYPE ---- " + row.getROAD_TYPE());
                    }
                }else{
                    Log.e("Error",response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
