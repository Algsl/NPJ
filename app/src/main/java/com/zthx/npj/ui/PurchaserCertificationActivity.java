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

public class PurchaserCertificationActivity extends ActivityBase {

    @BindView(R.id.at_purchaser_certification_btn_attestation)
    Button atPurchaserCertificationBtnAttestation;
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
        setContentView(R.layout.activity_purchaser_certification);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle, "采购认证");
    }

    @OnClick({R.id.at_purchaser_certification_btn_attestation,R.id.at_trust_bottom})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class,"农品街平台认证协议","http://game.npj-vip.com/agreement/certificate.html");
                break;
            case R.id.at_purchaser_certification_btn_attestation:
                CertSubscribe.isPersonCertDone(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        IsPersonCertResponseBean isPersonCertResponseBean = GsonUtils.fromJson(result, IsPersonCertResponseBean.class);
                        IsPersonCertResponseBean.DataBean data = isPersonCertResponseBean.getData();
                        Intent intent = new Intent(PurchaserCertificationActivity.this, PurchaserCertification2Activity.class);
                        intent.putExtra(Const.PERSON_CERT_NAME, data.getName());
                        intent.putExtra(Const.PERSON_CERT_PHONE, data.getMobile());
                        startActivity(intent);
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showDialog();
                    }
                }));
                break;
        }
    }

    private void showDialog() {
        CommonDialog commonDialog = new CommonDialog(this, R.style.dialog, "请先完成实人认证\n" +
                "再进行采购认证\n", new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if(confirm){
                    openActivity(MyAttestationActivity.class);
                }
            }
        });
        commonDialog.setNegativeButton("取消");
        commonDialog.setPositiveButton("去实人认证");
        commonDialog.show();
    }
}
