package com.zthx.npj.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class SecKillViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private List<String> mList;
    private List<Fragment> mFragment;

    public SecKillViewPagerAdapter(FragmentManager fragmentManager, android.content.Context context, List<String> list, List<Fragment> fragment) {
        super(fragmentManager);
        mList = list;
        mContext = context;
        mFragment = fragment;
    }
    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }


}
