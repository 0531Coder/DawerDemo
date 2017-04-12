package com.demo.moutain.retrofitlibrary.download;

import com.demo.moutain.retrofitlibrary.download.DownLoadListener.DownloadInterceptor;
import com.demo.moutain.retrofitlibrary.exception.HttpRunTimeException;
import com.demo.moutain.retrofitlibrary.exception.RetryWhenNetworkException;
import com.demo.moutain.retrofitlibrary.subscribers.ProgressDownSubscriber;
import com.demo.moutain.retrofitlibrary.util.DbDownUtil;
import com.demo.moutain.retrofitlibrary.util.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.demo.moutain.retrofitlibrary.util.HttpUtil.writeCache;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

public class HttpDownLoadManager {
    /*记录下载数据*/
    private Set<DownInfo> downInfos;
    /*回调sub队列*/
    private HashMap<String,ProgressDownSubscriber> subMap;
    /*单利对象*/
    private volatile static HttpDownLoadManager INSTANCE;
    /*数据库类*/
    private DbDownUtil db;

    private HttpDownLoadManager(){
        downInfos=new HashSet<>();
        subMap=new HashMap<>();
        db= DbDownUtil.getInstance();
    }

    /**
     * 获取单例
     * @return
     */
    public static HttpDownLoadManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpDownLoadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDownLoadManager();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 开始下载
     */
    public void startDown(final DownInfo info){
        /*正在下载不处理*/
        if(info==null||subMap.get(info.getUrl())!=null){
            subMap.get(info.getUrl()).setDownInfo(info);
            return;
        }
        /*添加回调处理类*/
        ProgressDownSubscriber subscriber=new ProgressDownSubscriber(info);
        /*记录回调sub*/
        subMap.put(info.getUrl(),subscriber);
        /*获取service，多次请求公用一个sercie*/
        HttpDownLoadApi httpService;
        if(downInfos.contains(info)){
            httpService=info.getDownLoadApi();
        }else{
            DownloadInterceptor interceptor = new DownloadInterceptor(subscriber);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //手动创建一个OkHttpClient并设置超时时间
            builder.connectTimeout(info.getConnectionTime(), TimeUnit.SECONDS);
            builder.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(HttpUtil.getBaseUrl(info.getUrl()))
                    .build();
            httpService= retrofit.create(HttpDownLoadApi.class);
            info.setDownLoadApi(httpService);
            downInfos.add(info);
        }
        /*得到rx对象-上一次下載的位置開始下載*/
        httpService.download("bytes=" + info.getReadLength() + "-",info.getUrl())
                /*指定线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                   /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException())
                /*读取下载写入文件*/
                .map(new Func1<ResponseBody, DownInfo>() {
                    @Override
                    public DownInfo call(ResponseBody responseBody) {
                        try {
                            writeCache(responseBody,new File(info.getSavePath()),info);
                        } catch (IOException e) {
                            /*失败抛出异常*/
                            throw new HttpRunTimeException(e.getMessage());
                        }
                        return info;
                    }
                })
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*数据回调*/
                .subscribe(subscriber);

    }


    /**
     * 停止下载
     */
    public void stopDown(DownInfo info){
        if(info==null)return;
        info.setState(DownState.STOP);
        info.getNextListner().onStop();
        if(subMap.containsKey(info.getUrl())) {
            ProgressDownSubscriber subscriber=subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }
        /*保存数据库信息和本地文件*/
        db.save(info);
    }


    /**
     * 暂停下载
     * @param info
     */
    public void pause(DownInfo info){
        if(info==null)return;
        info.setState(DownState.PAUSE);
        info.getNextListner().onPuase();
        if(subMap.containsKey(info.getUrl())){
            ProgressDownSubscriber subscriber=subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }
        /*这里需要讲info信息写入到数据中，可自由扩展，用自己项目的数据库*/
        db.update(info);
    }

    /**
     * 停止全部下载
     */
    public void stopAllDown(){
        for (DownInfo downInfo : downInfos) {
            stopDown(downInfo);
        }
        subMap.clear();
        downInfos.clear();
    }

    /**
     * 暂停全部下载
     */
    public void pauseAll(){
        for (DownInfo downInfo : downInfos) {
            pause(downInfo);
        }
        subMap.clear();
        downInfos.clear();
    }


    /**
     * 返回全部正在下载的数据
     * @return
     */
    public Set<DownInfo> getDownInfos() {
        return downInfos;
    }

    /**
     * 移除下载数据
     * @param info
     */
    public void remove(DownInfo info){
        subMap.remove(info.getUrl());
        downInfos.remove(info);
    }

}
