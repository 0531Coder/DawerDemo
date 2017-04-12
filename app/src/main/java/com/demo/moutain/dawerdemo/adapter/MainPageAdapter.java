package com.demo.moutain.dawerdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demo.moutain.dawerdemo.R;
import com.demo.moutain.dawerdemo.ui.fragment.FirFragment;
import com.demo.moutain.dawerdemo.ui.fragment.SecFragment;
import com.demo.moutain.dawerdemo.ui.fragment.ThirFragment;

/**
 * Created by MOUTAIN on 2017/4/12.
 */

public class MainPageAdapter extends FragmentPagerAdapter {
    private ThirFragment thirFragment;
    private SecFragment secFragment;
    private FirFragment firFragment;
    private Context Context;
    private final String[] titles;

    public MainPageAdapter(FragmentManager fm, Context context,
                           FirFragment zhihuDailyFragment,
                           SecFragment guokrFragment,
                           ThirFragment doubanMomentFragment) {
        super(fm);
        this.Context = context;
        this.firFragment = zhihuDailyFragment;
        this.secFragment = guokrFragment;
        this.thirFragment = doubanMomentFragment;
        titles = new String[]{
                context.getString(R.string.fir_fragment_title)
                , context.getString(R.string.sec_fragment_title)
                , context.getString(R.string.thir_fragment_title)
        };
    }

    @Override
    public Fragment getItem(int position) {
        if(position==1){
            return firFragment;
        }else if(position==2){
            return secFragment;
        }
        return thirFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
