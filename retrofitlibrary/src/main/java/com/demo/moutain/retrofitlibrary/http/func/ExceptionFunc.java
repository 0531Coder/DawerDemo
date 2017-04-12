package com.demo.moutain.retrofitlibrary.http.func;

import com.demo.moutain.retrofitlibrary.exception.FactoryException;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class ExceptionFunc implements Func1<Throwable, Observable> {
    @Override
    public Observable call(Throwable throwable) {
        return Observable.error(FactoryException.analysisExcetpion(throwable));
    }
}
