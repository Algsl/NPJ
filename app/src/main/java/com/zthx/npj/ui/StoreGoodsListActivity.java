package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.OrderListFragment;
import com.zthx.npj.ui.fragment.StoreGoodsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreGoodsListActivity extends AppCompatActivity {

    @BindView(R.id.at_store_goods_list_tab)
    TabLayout atStoreGoodsListTab;
    @BindView(R.id.at_store_goods_list_vp)
    ViewPager atStoreGoodsListVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goods_list);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        list.add("出售中");
        list.add("已下架");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new StoreGoodsListFragment().newIntent("1"));
        list2.add(new StoreGoodsListFragment().newIntent("2"));
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atStoreGoodsListVp.setAdapter(mAdapter);
        atStoreGoodsListTab.setTabMode(TabLayout.MODE_FIXED);
        atStoreGoodsListTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        atStoreGoodsListTab.setupWithViewPager(atStoreGoodsListVp);
    }
}
