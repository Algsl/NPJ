package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.DiscoverSupplyFragment;
import com.zthx.npj.ui.fragment.DiscverServiceFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 限时秒杀
 */
public class SecKillActivity extends AppCompatActivity {


    @BindView(R.id.at_sec_kill_main_tab)
    TabLayout atSecKillMainTab;
    @BindView(R.id.at_sec_kill_view_pager)
    ViewPager atSecKillViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_kill);
        ButterKnife.bind(this);

//        List<String> list = new ArrayList<>();
//        list.add("服务");
//        list.add("供求");
//        List<Fragment> list2 = new ArrayList<>();
//        list2.add(DiscverServiceFragment.getInstance());
//        list2.add(DiscoverSupplyFragment.getInstance());
//        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getChildFragmentManager(), getActivity(), list, list2);
//        viewPager.setAdapter(mAdapter);
//        fgDiscoverMainTab.setTabMode(TabLayout.MODE_FIXED);
//        fgDiscoverMainTab.setTabGravity(TabLayout.GRAVITY_CENTER);
//        fgDiscoverMainTab.setupWithViewPager(viewPager);
    }

}
