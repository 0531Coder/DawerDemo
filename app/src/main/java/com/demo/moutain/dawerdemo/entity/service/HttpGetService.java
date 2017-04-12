package com.demo.moutain.dawerdemo.entity.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by MOUTAIN on 2017/4/12.
 */

public interface HttpGetService {
    @GET("api/stream/date/{date}")
    Observable<String> getFirList(@Path("date") String date);

}
