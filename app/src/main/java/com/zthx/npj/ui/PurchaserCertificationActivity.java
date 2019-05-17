package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurchaserCertificationActivity extends AppCompatActivity {

    @BindView(R.id.at_purchaser_certification_btn_attestation)
    Button atPurchaserCertificationBtnAttestation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaser_certification);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.at_purchaser_certification_btn_attestation)
    public void onViewClicked() {
        startActivity(new Intent(this,PurchaserCertification2Activity.class));
    }
}
