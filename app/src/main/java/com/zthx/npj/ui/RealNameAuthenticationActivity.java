package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameAuthenticationActivity extends AppCompatActivity {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_real_name_authentication_btn_attestation)
    Button atRealNameAuthenticationBtnAttestation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.at_real_name_authentication_btn_attestation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                break;
            case R.id.at_real_name_authentication_btn_attestation:
                startActivity(new Intent(this,RealNameAuthentication2Activity.class));
                break;
        }
    }
}
