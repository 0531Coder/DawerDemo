package com.demo.moutain.retrofitlibrary.exception;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

public class HttpRunTimeException extends RuntimeException {
    /*未知错误*/
    public static final int UNKOWN_ERROR = 0x1002;
    /*本地无缓存错误*/
    public static final int NO_CHACHE_ERROR = 0x1003;
    /*缓存过时错误*/
    public static final int CHACHE_TIMEOUT_ERROR = 0x1004;


    public HttpRunTimeException(int resultCode) {
        super(getApiExceptionMessage(resultCode));
    }

    public HttpRunTimeException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 转换错误数据
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        switch (code) {
            case UNKOWN_ERROR:
                return "错误：网络错误";
            case NO_CHACHE_ERROR:
                return "错误：无缓存数据";
            case CHACHE_TIMEOUT_ERROR:
                return "错误：缓存数据过期";
            default:
                return "错误：未知错误";
        }
    }
}

