package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.BankCardAdapter;
import com.zthx.npj.net.been.BankCardResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankCardActivity extends AppCompatActivity {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_bankCard_rlv_allCard)
    RecyclerView acBankCardRlvAllCard;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        ButterKnife.bind(this);

        acTitle.setText("我的银行卡");
        atLocationStoreTvRuzhu.setText("添加");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyBankCard();
    }

    private void getMyBankCard() {
        SetSubscribe.bankCard(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyBankCard(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyBankCard(String result) {
        BankCardResponseBean bean = GsonUtils.fromJson(result, BankCardResponseBean.class);
        ArrayList<BankCardResponseBean.DataBean> data = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acBankCardRlvAllCard.setLayoutManager(layoutManager);
        BankCardAdapter adapter = new BankCardAdapter(this, data);
        acBankCardRlvAllCard.setAdapter(adapter);
    }

    @OnClick(R.id.at_location_store_tv_ruzhu)
    public void onViewClicked() {
        startActivity(new Intent(this,AddBankCardActivity.class));
    }
}
