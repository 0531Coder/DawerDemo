package com.demo.moutain.retrofitlibrary;

import android.app.Application;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

public class RxRetrofitApp extends Application {
    private static boolean isDebug = false;
    private static Application application;
    public static void init(Application application,boolean isDebug){
        setApplication(application);
        setIsDebug(isDebug);
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        RxRetrofitApp.isDebug = isDebug;
    }

    public static Application getApplication() {
        return application;
    }

    public static void setApplication(Application application) {
        RxRetrofitApp.application = application;
    }
}
