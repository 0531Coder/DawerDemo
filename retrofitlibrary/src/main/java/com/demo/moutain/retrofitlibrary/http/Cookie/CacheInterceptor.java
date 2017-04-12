package com.demo.moutain.retrofitlibrary.http.Cookie;

import com.demo.moutain.retrofitlibrary.RxRetrofitApp;
import com.demo.moutain.retrofitlibrary.util.HttpUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

public class CacheInterceptor implements Interceptor {
    /**
     * 通过拦截请求体和响应体来更改有网或者没网环境下的cookie的缓存生效时间。
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //如果断网的情况下必须强制读取缓存，否则退出应用或者等一分钟就不会读取缓存了。
        if (HttpUtil.isNetAvailable(RxRetrofitApp.getApplication())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        Response responseLatest;
        if (HttpUtil.isNetAvailable(RxRetrofitApp.getApplication())) {
//            有网情况下缓存时间为一分钟
            int maxage = 60;
            responseLatest = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age" + maxage)
                    .build();
        }else{
            //没网的情况下缓存6小时（因为上面判断没有网的情况下会强制读取缓存，即使缓存失效。也会响应，但是如果没有缓存的话会返回504）。
            int maxStale = 60*60*6;
            responseLatest = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control","public, only-if-cached, max-stale="+maxStale)
                    .build();
        }
        return responseLatest;
    }
}
