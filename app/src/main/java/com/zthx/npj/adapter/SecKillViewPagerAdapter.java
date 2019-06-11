package com.zthx.npj.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zthx.npj.R;

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

    public View getTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_sec_kill, null);
        TextView tv = v.findViewById(R.id.item_sec_kill_tv_date);
        tv.setText(mList.get(position));
        return v;
    }
        @Override
        public int getCount () {
            return mFragment.size();
        }

        @Override
        public Fragment getItem ( int i){
            return mFragment.get(i);
        }

        @Override
        public CharSequence getPageTitle ( int position){
            return mList.get(position);
        }


    }
