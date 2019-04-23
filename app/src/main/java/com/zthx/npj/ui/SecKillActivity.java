package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.SecKillViewPagerAdapter;
import com.zthx.npj.ui.fragment.SecKillFragment;

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

    TextView tv1;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_kill);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        List<Fragment> list2 = new ArrayList<>();
        initData(list, list2);
        initTable(list, list2);
        initListener();
    }

    private void initListener() {
        atSecKillMainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //定义方法，判断是否选中
                updateTabView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initTable(List<String> list, List<Fragment> list2) {
        SecKillViewPagerAdapter mAdapter = new SecKillViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atSecKillViewPager.setAdapter(mAdapter);
        atSecKillMainTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        atSecKillMainTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.app_theme));
        atSecKillMainTab.setupWithViewPager(atSecKillViewPager);
        for (int i = 0; i < atSecKillMainTab.getTabCount(); i++) {
            TabLayout.Tab tab = atSecKillMainTab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mAdapter.getTabView(i));
            }
        }
        atSecKillMainTab.getTabAt(0).getCustomView().setSelected(true);
    }

    private void initData(List<String> list, List<Fragment> list2) {
        for (int i = 0; i < 4; i++) {
            list.add("3月" + (i + 1) + "日");
            list2.add(new SecKillFragment());
        }
    }

    private void updateTabView(TabLayout.Tab tab, boolean b) {
        tv1 = tab.getCustomView().findViewById(R.id.item_sec_kill_tv_date);
        tv2 = tab.getCustomView().findViewById(R.id.item_sec_kill_tv_date_state);
        if(b) {
            //设置标签选中
            tv1.setSelected(true);
            tv2.setSelected(true);
            //选中后字体变大
            tv1.setTextColor(getResources().getColor(R.color.app_theme));
            tv2.setTextColor(getResources().getColor(R.color.app_theme));
        }else{
            //设置标签取消选中
            tv1.setSelected(false);
            tv2.setSelected(false);
            //恢复为默认字体大小
            tv1.setTextColor(getResources().getColor(R.color.text3));
            tv2.setTextColor(getResources().getColor(R.color.text9));
        }
    }

}
