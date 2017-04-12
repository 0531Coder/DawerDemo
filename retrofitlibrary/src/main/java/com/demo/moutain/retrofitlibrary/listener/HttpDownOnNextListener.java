package com.demo.moutain.retrofitlibrary.listener;

/**
 * Created by MOUTAIN on 2017/4/11.
 */

public abstract class HttpDownOnNextListener<T> {
    /**
     * 成功后回调的方法
     */
    public abstract void onNext(T t);

    /**
     * 开始下载时的回调
     */
    public abstract void onStart();
    /**
     * 完成下载时的回调
     */
    public abstract void onComplete();
    /**
     * 下载进度
     */
    public abstract void updateProgress(long readLength,long countLength);
    /**
     * 失败或者下载错误的回调
     */
    public abstract void onError(Throwable e);

    /**
     *
     */
    public abstract void onPuase();

    /**
     * 停止下载
     */
    public abstract void onStop();
}
