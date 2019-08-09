package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @BindView(R.id.at_trust_bottom)
    LinearLayout atTrustBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaser_certification);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle, "采购认证");
    }

    @OnClick({R.id.at_purchaser_certification_btn_attestation,R.id.at_trust_bottom})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class);
                break;
            case R.id.at_purchaser_certification_btn_attestation:
                startActivity(new Intent(this, PurchaserCertification2Activity.class));
                break;
        }
    }
}
