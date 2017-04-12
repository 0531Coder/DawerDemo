package com.demo.moutain.dawerdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.demo.moutain.dawerdemo.R;
import com.demo.moutain.dawerdemo.adapter.DoubanMomentAdapter;
import com.demo.moutain.dawerdemo.entity.api.FirFragmentApi;
import com.demo.moutain.dawerdemo.entity.result.FirListEntity;
import com.demo.moutain.dawerdemo.ui.base.BaseActivity;
import com.demo.moutain.retrofitlibrary.http.HttpManager;
import com.demo.moutain.retrofitlibrary.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class FirFragment extends RxFragment implements HttpOnNextListener {

    private FirFragmentApi getFirData;
    private List<FirListEntity.PostsBean> firlist;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private DoubanMomentAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadDate() {
        getFirData = new FirFragmentApi();
        getFirData.setDate("2016-08-01");
        HttpManager httpManager = new HttpManager(this, (BaseActivity) getActivity());
        httpManager.doHttpDeal(getFirData);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        loadDate();
        return view;
    }

    private void initView(View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        
    }

    @Override
    public void onNext(String result, String method) {
        if (method.equals(getFirData.getMothed())) {
            FirListEntity firListEntity = JSON.parseObject(result,new TypeReference<FirListEntity>(){});
            if(firListEntity!=null){
                firlist = firListEntity.getPosts();
            }
            adapter = new DoubanMomentAdapter(getActivity(),firlist);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
