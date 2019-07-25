package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurchaserCertificationActivity extends ActivityBase {

    @BindView(R.id.at_purchaser_certification_btn_attestation)
    Button atPurchaserCertificationBtnAttestation;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaser_certification);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"采购认证");
    }

    @OnClick(R.id.at_purchaser_certification_btn_attestation)
    public void onViewClicked() {
        startActivity(new Intent(this, PurchaserCertification2Activity.class));
    }
}
