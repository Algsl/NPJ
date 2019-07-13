package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends AppCompatActivity {

    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);

        acTitle.setText("我的钱包");
        atLocationStoreTvRuzhu.setText("充值");
    }

    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.at_myWallet_tv_mx, R.id.ac_myallet_rl_bankCard, R.id.at_my_wallet_btn_tiqu,R.id.ac_myWallet_rl_inManage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                startActivity(new Intent(this, RechargeActivity.class));
                break;
            case R.id.at_myWallet_tv_mx:
                startActivity(new Intent(this,UserMoneyActivity.class));
                break;
            case R.id.ac_myallet_rl_bankCard:
                startActivity(new Intent(this, BankCardActivity.class));
                break;
            case R.id.at_my_wallet_btn_tiqu:
                startActivity(new Intent(this, WithDrawActivity.class));
                break;
            case R.id.ac_myWallet_rl_inManage:
                startActivity(new Intent(this,SpokesmanRightsActivity.class));
                break;
        }
    }
}
