package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends ActivityBase {

    @BindView(R.id.at_my_order_vp)
    ViewPager atMyOrderVp;
    @BindView(R.id.at_my_order_tab)
    TabLayout atMyOrderTab;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "我的订单");
        acTitleIv.setImageResource(R.drawable.discover_service_search);

        acTitleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(SearchOrderActivity.class,"store");
            }
        });


        int currentItem = getIntent().getIntExtra("currentItem", 0);

        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("待付款");
        list.add("待发货");
        list.add("待收货");
        list.add("待评价");
        list.add("售后/退款");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new OrderListFragment().newIntent("100"));
        list2.add(new OrderListFragment().newIntent("1"));
        list2.add(new OrderListFragment().newIntent("2"));
        list2.add(new OrderListFragment().newIntent("3"));
        list2.add(new OrderListFragment().newIntent("4"));
        list2.add(new OrderListFragment().newIntent("6"));

        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atMyOrderVp.setAdapter(mAdapter);
        atMyOrderVp.setCurrentItem(currentItem);
        atMyOrderTab.setupWithViewPager(atMyOrderVp);

    }
}
