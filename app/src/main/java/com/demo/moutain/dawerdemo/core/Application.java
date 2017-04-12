package com.demo.moutain.dawerdemo.core;

import android.content.Context;

import com.demo.moutain.retrofitlibrary.RxRetrofitApp;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class Application extends android.app.Application {
    public static Context getApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationContext = getApplicationContext();
        RxRetrofitApp.init(this,true);
    }
}
