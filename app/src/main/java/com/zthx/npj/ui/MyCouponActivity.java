package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCouponActivity extends AppCompatActivity {

    @BindView(R.id.at_my_coupon_tab)
    TabLayout atMyCouponTab;
    @BindView(R.id.at_my_coupon_vp)
    ViewPager atMyCouponVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        list.add("未使用");
        list.add("已使用");
        list.add("已失效");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atMyCouponVp.setAdapter(mAdapter);
        atMyCouponTab.setTabMode(TabLayout.MODE_FIXED);
        atMyCouponTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        atMyCouponTab.setupWithViewPager(atMyCouponVp);
    }
}
