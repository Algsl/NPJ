package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.MyBillListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBillActivity extends ActivityBase {

    @BindView(R.id.at_want_buy_manager_vp)
    ViewPager atWantBuyManagerVp;
    @BindView(R.id.at_want_buy_manager_tab)
    TabLayout atWantBuyManagerTab;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bill);
        ButterKnife.bind(this);

        int currentItem = getIntent().getIntExtra("currentItem", 0);

        back(titleBack);
        changeTitle(acTitle, "我的订单");

        acTitleIv.setImageResource(R.drawable.discover_service_search);

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
        atWantBuyManagerVp.setCurrentItem(currentItem);
        atWantBuyManagerTab.setupWithViewPager(atWantBuyManagerVp);
    }

    @OnClick(R.id.ac_title_iv)
    public void onViewClicked() {
        openActivity(SearchOrderActivity.class,"supply");
    }
}
