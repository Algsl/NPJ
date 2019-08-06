package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.IsPersonCertResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterpriseCertificationActivity extends ActivityBase {

    @BindView(R.id.at_enterprise_certification_btn_attestation)
    Button atEnterpriseCertificationBtnAttestation;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_certification);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"企业认证");
    }

    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.at_enterprise_certification_btn_attestation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                break;
            case R.id.at_enterprise_certification_btn_attestation:
                isPersonCert();
                break;
        }
    }

    private void isPersonCert() {

        CertSubscribe.isPersonCertDone(SharePerferenceUtils.getUserId(this), BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                IsPersonCertResponseBean isPersonCertResponseBean = GsonUtils.fromJson(result, IsPersonCertResponseBean.class);
                IsPersonCertResponseBean.DataBean data = isPersonCertResponseBean.getData();
                Intent intent = new Intent(EnterpriseCertificationActivity.this, EnterpriseCertification2Activity.class);
                intent.putExtra(Const.PERSON_CERT_NAME, data.getName());
                intent.putExtra(Const.PERSON_CERT_PHONE, data.getMobile());
                startActivity(intent);
            }

            @Override
            public void onFault(String errorMsg) {
                showDialog();
            }
        }, this));
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
