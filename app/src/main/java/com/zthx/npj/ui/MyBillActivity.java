package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.MyBillListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBillActivity extends AppCompatActivity {

    @BindView(R.id.at_want_buy_manager_vp)
    ViewPager atWantBuyManagerVp;
    @BindView(R.id.at_want_buy_manager_tab)
    TabLayout atWantBuyManagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bill);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("待付款");
        list.add("待发货");
        list.add("待收货");
        list.add("待评价");
        list.add("售后/退款");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new MyBillListFragment());
        list2.add(new MyBillListFragment());
        list2.add(new MyBillListFragment());
        list2.add(new MyBillListFragment());
        list2.add(new MyBillListFragment());
        list2.add(new MyBillListFragment());
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atWantBuyManagerVp.setAdapter(mAdapter);
        atWantBuyManagerTab.setTabMode(TabLayout.MODE_FIXED);
        atWantBuyManagerTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        atWantBuyManagerTab.setupWithViewPager(atWantBuyManagerVp);
    }
}
