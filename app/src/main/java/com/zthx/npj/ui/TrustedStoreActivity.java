package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zthx.npj.R;
import com.zthx.npj.ui.TrustedStore2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrustedStoreActivity extends AppCompatActivity {

    @BindView(R.id.at_trust_store_btn_attestation)
    Button atTrustStoreBtnAttestation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_store);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.at_trust_store_btn_attestation)
    public void onViewClicked() {
        startActivity(new Intent(this, TrustedStore2Activity.class));

    }
}
