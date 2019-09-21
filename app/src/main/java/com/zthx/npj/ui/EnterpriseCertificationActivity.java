package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @BindView(R.id.at_trust_bottom)
    LinearLayout atTrustBottom;

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_certification);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle, "企业认证");
    }

    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.at_enterprise_certification_btn_attestation,R.id.at_trust_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:
                break;
            case R.id.at_enterprise_certification_btn_attestation:
                isPersonCert();
                break;
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class);
                break;
        }
    }

    private void isPersonCert() {
        CertSubscribe.isPersonCertDone(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                showToast(errorMsg);
            }
        }, this));
    }

    private void showDialog() {
        CommonDialog commonDialog = new CommonDialog(this, R.style.dialog, "请先完成实人认证\n" +
                "再进行企业认证\n", new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if(confirm){
                    openActivity(RealNameAuthenticationActivity.class);
                }
            }
        });
        commonDialog.setNegativeButton("取消");
        commonDialog.setPositiveButton("去实人认证");
        commonDialog.show();
    }
}
