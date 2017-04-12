package com.demo.moutain.retrofitlibrary.download.DownLoadListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

/**
 * 自定义精度的下载的responsebody
 */
public class DownloadResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private DownloadProgressListener listener;
    private BufferedSource bufferedSource;

    public DownloadResponseBody(ResponseBody responseBody, DownloadProgressListener listener) {
        this.responseBody = responseBody;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if(bufferedSource==null){
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }
    private Source source(Source source){
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink,byteCount);
                //如果返回的buffer为-1，说明下载完毕，如果不为-1，则未下载完成。
                totalBytesRead += bytesRead!=-1?bytesRead:0;
                if(listener!=null){
                    listener.update(totalBytesRead,responseBody.contentLength(),bytesRead==-1);
                }
                return bytesRead;
            }
        };
    }
}
