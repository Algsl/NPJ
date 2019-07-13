package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.UserMoneyAdapter;
import com.zthx.npj.net.been.UserMoneyResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserMoneyActivity extends AppCompatActivity {

    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_myWallet_tv_chooseTime)
    TextView acMyWalletTvChooseTime;
    @BindView(R.id.ac_myWallet_tv_ioMoney)
    TextView acMyWalletTvIoMoney;
    @BindView(R.id.ac_myWallet_rv_mingxi)
    RecyclerView acMyWalletRvMingxi;


    String type = "";
    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String begin_time = "2015-1-1";
    String end_time = "2015-1-31";
    @BindView(R.id.ac_vipJL_tv_allType)
    TextView acVipJLTvAllType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet_income);
        ButterKnife.bind(this);


        acTitle.setText("钱包明细");
        getUserMoney();
    }

    private void getUserMoney() {
        SetSubscribe.userMoney(user_id, token, type, begin_time, end_time, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setUserMoney(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.ac_myWallet_tv_chooseTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_myWallet_tv_chooseTime:

                break;
        }
    }

    private void setUserMoney(String result) {
        UserMoneyResponseBean bean = GsonUtils.fromJson(result, UserMoneyResponseBean.class);
        UserMoneyResponseBean.DataBean data = bean.getData().get(0);
        acMyWalletTvIoMoney.setText("充值" + data.getRecharge() + "元，提现" + data.getWithdraw() + "元");
        ArrayList<UserMoneyResponseBean.DataBean.MingXi> mingXis = data.getMingxi();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acMyWalletRvMingxi.setLayoutManager(layoutManager);
        UserMoneyAdapter adapter = new UserMoneyAdapter(this, mingXis);
        acMyWalletRvMingxi.setAdapter(adapter);
    }

}
