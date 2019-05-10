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
import com.zthx.npj.ui.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends AppCompatActivity {

    @BindView(R.id.at_my_order_vp)
    ViewPager atMyOrderVp;
    @BindView(R.id.at_my_order_tab)
    TabLayout atMyOrderTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("待付款");
        list.add("待发货");
        list.add("待收货");
        list.add("待评价");
        list.add("售后/退款");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        list2.add(new OrderListFragment());
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atMyOrderVp.setAdapter(mAdapter);
        atMyOrderTab.setTabMode(TabLayout.MODE_FIXED);
        atMyOrderTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        atMyOrderTab.setupWithViewPager(atMyOrderVp);
    }
}
