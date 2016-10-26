package com.example.jongkook.remote_retrofitwithokhttp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jongkook on 2016. 10. 26..
 */

public interface ISeoulOpenData {
    @GET("/{key}/json/{serviceName}/{begin}/{end}/")
    Call<RemoteData> getData(@Path("key") String key,
                             @Path("serviceName") String serviceName,
                             @Path("begin") int begin,
                             @Path("end") int end);
}
