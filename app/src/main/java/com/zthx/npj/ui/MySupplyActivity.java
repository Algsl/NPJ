package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyStoreResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySupplyActivity extends ActivityBase {

    @BindView(R.id.at_my_supply_ll_my_bill)
    LinearLayout atMySupplyLlMyBill;
    @BindView(R.id.at_my_supply_ll_supply_manager)
    LinearLayout atMySupplyLlSupplyManager;
    @BindView(R.id.at_my_supply_ll_want_buy_manager)
    LinearLayout atMySupplyLlWantBuyManager;
    @BindView(R.id.at_my_supply_ll_publish_supply)
    LinearLayout atMySupplyLlPublishSupply;
    @BindView(R.id.ac_mySupply_mcv_headImg)
    MyCircleView acMySupplyMcvHeadImg;
    @BindView(R.id.ac_mySupply_tv_nickName)
    TextView acMySupplyTvNickName;
    @BindView(R.id.ac_mySupply_tv_reputation)
    TextView acMySupplyTvReputation;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_supply);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"我的供求");

        getMyStore();
    }

    private void getMyStore() {
        SetSubscribe.myStore(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyStore(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyStore(String result) {
        MyStoreResponseBean bean = GsonUtils.fromJson(result, MyStoreResponseBean.class);
        MyStoreResponseBean.DataBean data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getStore_img())).into(acMySupplyMcvHeadImg);
        acMySupplyTvNickName.setText(data.getStore_name());
        acMySupplyTvReputation.setText("信誉分：" + data.getReputation());
    }

    @OnClick({R.id.at_my_supply_ll_my_bill, R.id.at_my_supply_ll_supply_manager, R.id.at_my_supply_ll_want_buy_manager, R.id.at_my_supply_ll_publish_supply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_supply_ll_my_bill:
                startActivity(new Intent(this, MyBillActivity.class));
                break;
            case R.id.at_my_supply_ll_supply_manager:
                startActivity(new Intent(this, SupplyManagerActivity.class));
                break;
            case R.id.at_my_supply_ll_want_buy_manager:
                startActivity(new Intent(this, WantBuyManagerActivity.class));
                break;
            case R.id.at_my_supply_ll_publish_supply:
                startActivity(new Intent(this, ReleaseSupplyActivity.class));
                break;
        }
    }
}
