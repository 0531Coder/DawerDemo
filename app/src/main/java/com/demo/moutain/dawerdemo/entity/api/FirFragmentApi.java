package com.demo.moutain.dawerdemo.entity.api;

import com.demo.moutain.dawerdemo.entity.service.HttpGetService;
import com.demo.moutain.retrofitlibrary.api.BaseApi;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class FirFragmentApi extends BaseApi {
    /**
     * 定义请求接口所需要的参数
     */

    private String date;

    /**
     * Constructor接收参数并设置
     */
    public FirFragmentApi(){
        //进行个性化设置，比方说分页加载不需要加载框就可以设置 setShowProgress(false)
        setCancel(true);
        setCache(true);
        //path缓存用的
        setMothed("api/stream/date/");
        setCookieNetWorkTime(100);
        setCookieNoNetWorkTime(24*60*60);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService service = retrofit.create(HttpGetService.class);
        return service.getFirList(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
