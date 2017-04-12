package com.demo.moutain.retrofitlibrary.listener;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

public interface HttpOnNextListener<T> {

    /**
     * 成功后的ober返回，扩展链接式调用
     *
     * @param result:请求到的字符串 method:请求的方法，根据method来判断是哪个请求的回调
     */
    void onNext(String result, String method);

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     *
     * @param e
     */
    void onError(Throwable e);


}
