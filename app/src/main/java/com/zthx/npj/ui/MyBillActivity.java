package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.MyBillListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBillActivity extends ActivityBase {

    @BindView(R.id.at_want_buy_manager_vp)
    ViewPager atWantBuyManagerVp;
    @BindView(R.id.at_want_buy_manager_tab)
    TabLayout atWantBuyManagerTab;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bill);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"我的订单");

        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("待付款");
        list.add("待发货");
        list.add("待收货");
        list.add("待评价");
        list.add("售后/退款");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new MyBillListFragment().newInstence("100"));
        list2.add(new MyBillListFragment().newInstence("1"));
        list2.add(new MyBillListFragment().newInstence("2"));
        list2.add(new MyBillListFragment().newInstence("3"));
        list2.add(new MyBillListFragment().newInstence("4"));
        list2.add(new MyBillListFragment().newInstence("6"));

        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atWantBuyManagerVp.setAdapter(mAdapter);
        atWantBuyManagerTab.setupWithViewPager(atWantBuyManagerVp);
    }
}
