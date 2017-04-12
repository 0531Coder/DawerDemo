package com.demo.moutain.retrofitlibrary.download.DownLoadListener;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

public interface DownloadProgressListener {
    void update(long read,long count,boolean done);
}
