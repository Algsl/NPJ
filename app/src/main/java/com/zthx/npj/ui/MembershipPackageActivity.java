package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.fragment.BuyGiftFragment;
import com.zthx.npj.ui.fragment.SpokesmanFragment;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MembershipPackageActivity extends ActivityBase {

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
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_membership_iv_level)
    ImageView acMembershipIvLevel;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_package);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "礼包店");

        getUserInfo();

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

    private void getUserInfo() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setUserInfo(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setUserInfo(String result) {
        UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
        Glide.with(this).load(Uri.parse(bean.getData().getHead_img())).into(atMembershipPackageHeadPic);
        atMembershipPackageTvName.setText(bean.getData().getNick_name());
        switch (bean.getData().getLevel()){
            case 0:acMembershipIvLevel.setImageResource(R.drawable.level0);break;
            case 1:acMembershipIvLevel.setImageResource(R.drawable.level1);break;
            case 2:acMembershipIvLevel.setImageResource(R.drawable.level2);break;
            case 3:acMembershipIvLevel.setImageResource(R.drawable.level3);break;
            case 4:acMembershipIvLevel.setImageResource(R.drawable.level4);break;
            case 5:acMembershipIvLevel.setImageResource(R.drawable.level5);break;
            case 6:acMembershipIvLevel.setImageResource(R.drawable.level6);break;
            case 7:acMembershipIvLevel.setImageResource(R.drawable.level7);break;
            case 8:acMembershipIvLevel.setImageResource(R.drawable.level8);break;
            case 9:acMembershipIvLevel.setImageResource(R.drawable.level9);break;
            case 10:acMembershipIvLevel.setImageResource(R.drawable.level10);break;
        }
    }

    @OnClick({R.id.at_membership_package_tv_tuijian, R.id.at_membership_package_tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_membership_package_tv_tuijian:
                openActivity(ReferrerActivity.class);
                break;
            case R.id.at_membership_package_tv_share:
                break;
        }
    }
}
