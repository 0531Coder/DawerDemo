package com.demo.moutain.retrofitlibrary.http.Cookie;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by MOUTAIN on 2017/4/11.
 */
@Entity
public class CookieResult {

    @Id
    private Long id;
    /*url*/
    private String url;
    /*返回结果*/
    private String result;
    /*时间*/
    private long time;

    public CookieResult(String url, String resulte, long time) {
        this.url = url;
        this.result = resulte;
        this.time = time;
    }


    @Generated(hash = 430401114)
    public CookieResult(Long id, String url, String result, long time) {
        this.id = id;
        this.url = url;
        this.result = result;
        this.time = time;
    }


    @Generated(hash = 43459054)
    public CookieResult() {
    }


    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getResult() {
        return this.result;
    }
    public void setResulte(String resulte) {
        this.result = resulte;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }


    public void setResult(String result) {
        this.result = result;
    }


}
