package com.zthx.npj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
    @BindView(R.id.at_membership_package_head_pic)
    MyCircleView atMembershipPackageHeadPic;
    @BindView(R.id.at_membership_package_tv_name)
    TextView atMembershipPackageTvName;
    @BindView(R.id.at_membership_package_tv_tuijian)
    TextView atMembershipPackageTvTuijian;
    @BindView(R.id.at_membership_package_tv_share)
    TextView atMembershipPackageTvShare;
    /*@BindView(R.id.at_membership_package_tv_logo)
    TextView atMembershipPackageTvLogo;*/
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
        changeTitle(acTitle, "会员礼包");

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
                showToast(errorMsg);
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
                showPublishPopwindow();
                break;
        }
    }

    public void showPublishPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_vip_hint, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setWidth((int) getResources().getDimension(R.dimen.dp_320));
        window.setHeight((int) getResources().getDimension(R.dimen.dp_400));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        TextView tv=contentView.findViewById(R.id.pw_vipHint_tv_generateHB);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(HaiBaoActivity.class);
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
