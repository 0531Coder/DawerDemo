package com.demo.moutain.retrofitlibrary.download;

import com.demo.moutain.retrofitlibrary.listener.HttpDownOnNextListener;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

@Entity
public class DownInfo {
    @Id
    private long id;
    /**
     * 存储位置
     */
    private String savePath;
    /**
     * 文件总长度
     */
    private long countLength;
    /**
     * 下载长度
     */
    private long readLength;
    /**
     * 下载唯一的HttpService
     */
    @Transient
    private HttpDownLoadApi downLoadApi;
    /**
     * 回调监听
     */
    @Transient
    private HttpDownOnNextListener nextListner;
    /**
     * 超时设置
     */
    private int connectionTime = 6;
    /**
     * state状态数据库保存
     */
    private int stateInt;
    /**
     * url
     */
    private String url;

    public DownInfo(String url, HttpDownOnNextListener listener) {
        setUrl(url);
        setNextListner(listener);
    }

    public DownInfo(String url) {
        setUrl(url);
    }

    @Generated(hash = 1106272748)
    public DownInfo(long id, String savePath, long countLength, long readLength,
            int connectionTime, int stateInt, String url) {
        this.id = id;
        this.savePath = savePath;
        this.countLength = countLength;
        this.readLength = readLength;
        this.connectionTime = connectionTime;
        this.stateInt = stateInt;
        this.url = url;
    }

    @Generated(hash = 928324469)
    public DownInfo() {
    }


    public DownState getState() {
        switch (getStateInt()) {
            case 0:
                return DownState.START;
            case 1:
                return DownState.DOWN;
            case 2:
                return DownState.PAUSE;
            case 3:
                return DownState.STOP;
            case 4:
                return DownState.ERROR;
            case 5:
            default:
                return DownState.FINISH;
        }
    }
    public void setState(DownState state) {
        setStateInt(state.getState());
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public long getCountLength() {
        return countLength;
    }

    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }

    public long getReadLength() {
        return readLength;
    }

    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }

    public HttpDownOnNextListener getNextListner() {
        return nextListner;
    }

    public void setNextListner(HttpDownOnNextListener nextListner) {
        this.nextListner = nextListner;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public int getStateInt() {
        return stateInt;
    }

    public void setStateInt(int stateInt) {
        this.stateInt = stateInt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpDownLoadApi getDownLoadApi() {
        return downLoadApi;
    }

    public void setDownLoadApi(HttpDownLoadApi downLoadApi) {
        this.downLoadApi = downLoadApi;
    }
}