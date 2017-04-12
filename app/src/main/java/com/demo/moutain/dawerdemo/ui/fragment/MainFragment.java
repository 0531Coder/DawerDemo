package com.demo.moutain.dawerdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.moutain.dawerdemo.R;
import com.demo.moutain.dawerdemo.adapter.MainPageAdapter;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class MainFragment extends RxFragment {

    private Context mContext;
    private FirFragment firFragment;
    private SecFragment secFragment;
    private ThirFragment thirFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
        if(savedInstanceState!=null){
            FragmentManager manager = getChildFragmentManager();
            firFragment = (FirFragment) manager.getFragment(savedInstanceState,"first");
            secFragment = (SecFragment) manager.getFragment(savedInstanceState,"second");
            thirFragment = (ThirFragment) manager.getFragment(savedInstanceState,"third");
        }else{
            firFragment = new FirFragment();
            secFragment = new SecFragment();
            thirFragment = new ThirFragment();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(3);
        MainPageAdapter adapter = new MainPageAdapter(
                getChildFragmentManager(),
                getActivity(),
                firFragment,
                secFragment,
                thirFragment);

        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getChildFragmentManager();
        manager.putFragment(outState, "first", firFragment);
        manager.putFragment(outState, "second", secFragment);
        manager.putFragment(outState, "third", thirFragment);
    }
}
