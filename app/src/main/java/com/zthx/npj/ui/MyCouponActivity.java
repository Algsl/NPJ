package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.CouponListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCouponActivity extends ActivityBase {

    @BindView(R.id.at_my_coupon_tab)
    TabLayout atMyCouponTab;
    @BindView(R.id.at_my_coupon_vp)
    ViewPager atMyCouponVp;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"优惠券");

        List<String> list = new ArrayList<>();
        list.add("未使用");
        list.add("已使用");
        list.add("已失效");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new CouponListFragment().getNewOnce("1"));
        list2.add(new CouponListFragment().getNewOnce("2"));
        list2.add(new CouponListFragment().getNewOnce("3"));
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atMyCouponVp.setAdapter(mAdapter);
        atMyCouponTab.setTabMode(TabLayout.MODE_FIXED);
        atMyCouponTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        atMyCouponTab.setupWithViewPager(atMyCouponVp);
    }
}
