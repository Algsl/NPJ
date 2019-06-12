package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.BuyGiftFragment;
import com.zthx.npj.ui.fragment.SpokesmanFragment;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MembershipPackageActivity extends AppCompatActivity {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_membership_package_head_pic)
    MyCircleView atMembershipPackageHeadPic;
    @BindView(R.id.at_membership_package_tv_name)
    TextView atMembershipPackageTvName;
    @BindView(R.id.at_membership_package_tv_tuijian)
    TextView atMembershipPackageTvTuijian;
    @BindView(R.id.at_membership_package_tv_share)
    TextView atMembershipPackageTvShare;
    @BindView(R.id.at_membership_package_tv_logo)
    TextView atMembershipPackageTvLogo;
    @BindView(R.id.at_membership_package_tb)
    TabLayout atMembershipPackageTb;
    @BindView(R.id.at_membership_package_viewpager)
    ViewPager atMembershipPackageVp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_package);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        list.add("购买礼包");
        list.add("代言人权益");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new BuyGiftFragment());
        list2.add(new SpokesmanFragment());
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atMembershipPackageVp.setAdapter(mAdapter);
        atMembershipPackageTb.setTabMode(TabLayout.MODE_FIXED);
        atMembershipPackageTb.setTabGravity(TabLayout.GRAVITY_CENTER);
        atMembershipPackageTb.setupWithViewPager(atMembershipPackageVp);
    }

    @OnClick({R.id.at_membership_package_tv_tuijian, R.id.at_membership_package_tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_membership_package_tv_tuijian:
                break;
            case R.id.at_membership_package_tv_share:
                break;
        }
    }
}
