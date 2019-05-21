package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySupplyActivity extends AppCompatActivity {

    @BindView(R.id.at_my_supply_ll_my_bill)
    LinearLayout atMySupplyLlMyBill;
    @BindView(R.id.at_my_supply_ll_supply_manager)
    LinearLayout atMySupplyLlSupplyManager;
    @BindView(R.id.at_my_supply_ll_want_buy_manager)
    LinearLayout atMySupplyLlWantBuyManager;
    @BindView(R.id.at_my_supply_ll_publish_supply)
    LinearLayout atMySupplyLlPublishSupply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_supply);
        ButterKnife.bind(this);
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
