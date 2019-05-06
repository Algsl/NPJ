package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplyBillActivity extends AppCompatActivity {

    @BindView(R.id.at_supply_bill_ll_choice_address)
    RelativeLayout atSupplyBillLlChoiceAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_bill);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.at_supply_bill_ll_choice_address)
    public void onViewClicked() {
        startActivity(new Intent(this, ChoiceAddressActivity.class));
    }
}
