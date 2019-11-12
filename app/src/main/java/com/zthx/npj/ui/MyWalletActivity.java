package com.zthx.npj.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends ActivityBase {

    @BindView(R.id.at_my_wallet_tv_money)
    TextView atMyWalletTvMoney;
    @BindView(R.id.at_myWallet_tv_mx)
    TextView atMyWalletTvMx;
    @BindView(R.id.ac_myallet_rl_bankCard)
    RelativeLayout acMyalletRlBankCard;
    @BindView(R.id.at_my_wallet_btn_tiqu)
    Button atMyWalletBtnTiqu;
    @BindView(R.id.ac_myWallet_rl_inManage)
    RelativeLayout acMyWalletRlInManage;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);

        //balance=getIntent().getStringExtra("balance");
        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的钱包");
        changeRightText(titleThemeTvRight, "充值", RechargeActivity.class, null);

        if (Double.parseDouble(SharePerferenceUtils.getUserLevel(this)) > 0) {
            acMyWalletRlInManage.setVisibility(View.VISIBLE);
        } else {
            acMyWalletRlInManage.setVisibility(View.GONE);
        }

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getUserInfo();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    @OnClick({R.id.at_myWallet_tv_mx, R.id.ac_myallet_rl_bankCard, R.id.at_my_wallet_btn_tiqu, R.id.ac_myWallet_rl_inManage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_myWallet_tv_mx:
                openActivity(UserMoneyActivity.class);
                break;
            case R.id.ac_myallet_rl_bankCard:
                openActivity(BankCardActivity.class);
                break;
            case R.id.at_my_wallet_btn_tiqu:
                openActivity(WithDrawActivity.class, balance);
                break;
            case R.id.ac_myWallet_rl_inManage:
                openActivity(SpokesmanRightsActivity.class);
                break;
        }
    }

    private void getUserInfo() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
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
        UserResponseBean userResponseBean = GsonUtils.fromJson(result, UserResponseBean.class);
        UserResponseBean.DataBean data = userResponseBean.getData();
        balance = data.getBalance();
        atMyWalletTvMoney.setText(balance);
    }
}
