package com.demo.moutain.retrofitlibrary.listener;

import rx.Observable;

/**
 * Created by MOUTAIN on 2017/4/10.
 */
public interface HttpOnNextSubListener {

    /**
     * ober成功回调
     * @param observable
     * @param method
     */
    void onNext(Observable observable, String method);
}
