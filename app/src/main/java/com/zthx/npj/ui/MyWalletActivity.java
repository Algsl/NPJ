package com.zthx.npj.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的钱包");
        changeRightText(titleThemeTvRight, "充值", RechargeActivity.class, null);

    }

    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.at_myWallet_tv_mx, R.id.ac_myallet_rl_bankCard, R.id.at_my_wallet_btn_tiqu, R.id.ac_myWallet_rl_inManage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_myWallet_tv_mx:
                openActivity(UserMoneyActivity.class);
                break;
            case R.id.ac_myallet_rl_bankCard:
                openActivity(BankCardActivity.class);
                break;
            case R.id.at_my_wallet_btn_tiqu:
                openActivity(WithDrawActivity.class);
                break;
            case R.id.ac_myWallet_rl_inManage:
                openActivity(SpokesmanRightsActivity.class);
                break;
        }
    }
}
