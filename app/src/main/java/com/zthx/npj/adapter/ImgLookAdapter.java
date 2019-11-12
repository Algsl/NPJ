package com.zthx.npj.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ImgLookAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mFragment;

    public ImgLookAdapter(FragmentManager fm,Context context, List<Fragment> fragments){
        super(fm);
        mContext=context;
        mFragment=fragments;
    }

    public ImgLookAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
