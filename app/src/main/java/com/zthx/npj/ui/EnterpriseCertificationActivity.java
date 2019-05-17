package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.view.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterpriseCertificationActivity extends AppCompatActivity {

    @BindView(R.id.at_enterprise_certification_btn_attestation)
    Button atEnterpriseCertificationBtnAttestation;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_certification);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.at_enterprise_certification_btn_attestation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                break;
            case R.id.at_enterprise_certification_btn_attestation:
                showDialog();
                startActivity(new Intent(this, EnterpriseCertification2Activity.class));
                break;
        }
    }

    private void showDialog() {
        CommonDialog commonDialog = new CommonDialog(this, R.style.dialog, "请先完成实人认证\n" +
                "在进行企业认证\n", new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {

            }
        });
        commonDialog.setNegativeButton("取消");
        commonDialog.setPositiveButton("去实人认证");
        commonDialog.show();
    }
}
