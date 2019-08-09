package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZiZhiActivity extends ActivityBase {
    @BindView(R.id.at_trust_bottom)
    LinearLayout atTrustBottom;
    @BindView(R.id.at_zizhi_btn_attestation)
    Button atZizhiBtnAttestation;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zizhi);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "资质认证");
    }

    @OnClick({R.id.at_trust_bottom, R.id.at_zizhi_btn_attestation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class);
                break;
            case R.id.at_zizhi_btn_attestation:
                openActivity(ZiZhiInfoActivity.class);
                break;
        }
    }
}
