package com.demo.moutain.retrofitlibrary.http.func;

import com.demo.moutain.retrofitlibrary.exception.HttpRunTimeException;

import rx.functions.Func1;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class ResultFunc implements Func1<Object,Object> {
    @Override
    public Object call(Object o) {
        if (o == null || "".equals(o.toString())) {
            throw new HttpRunTimeException("数据错误");
        }
        return o;
    }
}
