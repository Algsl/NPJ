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

public class MyAttestationActivity extends AppCompatActivity {

    @BindView(R.id.at_my_attestation_ll_people)
    LinearLayout atMyAttestationLlPeople;
    @BindView(R.id.at_my_attestation_ll_company)
    LinearLayout atMyAttestationLlCompany;
    @BindView(R.id.at_my_attestation_ll_buy)
    LinearLayout atMyAttestationLlBuy;
    @BindView(R.id.at_my_attestation_ll_trust)
    LinearLayout atMyAttestationLlTrust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attestation);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_my_attestation_ll_people, R.id.at_my_attestation_ll_company, R.id.at_my_attestation_ll_buy, R.id.at_my_attestation_ll_trust})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_attestation_ll_people:
                startActivity(new Intent(this, RealNameAuthenticationActivity.class));
                break;
            case R.id.at_my_attestation_ll_company:
                startActivity(new Intent(this, EnterpriseCertificationActivity.class));
                break;
            case R.id.at_my_attestation_ll_buy:
                startActivity(new Intent(this,PurchaserCertificationActivity.class));
                break;
            case R.id.at_my_attestation_ll_trust:
                startActivity(new Intent(this, TrustedStoreActivity.class));
                break;
        }
    }
}
