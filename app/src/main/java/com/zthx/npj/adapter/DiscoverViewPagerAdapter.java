package com.zthx.npj.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.zthx.npj.ui.AgricultureVideoMainActivity;

import java.util.List;

public class DiscoverViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private List<String> mList;
    private List<Fragment> mFragment;

    public DiscoverViewPagerAdapter(FragmentManager fragmentManager, Context context, List<String> list, List<Fragment> fragment) {
        super(fragmentManager);
        mList = list;
        mContext = context;
        mFragment = fragment;
    }

    public DiscoverViewPagerAdapter(FragmentManager childFragmentManager, Activity activity, List<String> list, List<Fragment> list2) {
        super(childFragmentManager);
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
