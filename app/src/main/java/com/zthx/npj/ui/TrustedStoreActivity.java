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
import com.zthx.npj.net.been.ChengXinCertResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrustedStoreActivity extends ActivityBase {

    @BindView(R.id.at_trust_store_btn_attestation)
    Button atTrustStoreBtnAttestation;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @BindView(R.id.at_trust_store_tv_baozhengjin)
    TextView atTrustStoreTvBaozhengjin;
    @BindView(R.id.at_trust_store_ll_baozhengjin)
    LinearLayout atTrustStoreLlBaozhengjin;
    @BindView(R.id.at_trust_bottom)
    LinearLayout atTrustBottom;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.ac_trustedStore_tv_apply)
    TextView acTrustedStoreTvApply;

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private String money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_store);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "诚信商家");
        getData();
    }

    private void getData() {

        CertSubscribe.isChengXinAlreadyCert(SharePerferenceUtils.getUserId(this), SharePerferenceUtils.getToken(this), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                ChengXinCertResponseBean chengXinCertResponseBean = GsonUtils.fromJson(result, ChengXinCertResponseBean.class);
                int status = chengXinCertResponseBean.getData().getStatus();
                if (status == 1) {
                    atTrustStoreLlBaozhengjin.setVisibility(View.VISIBLE);
                    money=chengXinCertResponseBean.getData().getBail();
                    atTrustStoreTvBaozhengjin.setText("您的当前保证金为" + money+ "元");
                } else {
                    atTrustStoreLlBaozhengjin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    @OnClick({R.id.at_trust_store_btn_attestation, R.id.at_trust_bottom,R.id.ac_trustedStore_tv_apply})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.at_trust_store_btn_attestation:
                CertSubscribe.isChengXinAlready2Cert(SharePerferenceUtils.getUserId(this), SharePerferenceUtils.getToken(this), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        startActivity(new Intent(TrustedStoreActivity.this, TrustedStore2Activity.class));
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        CommonDialog dialog = new CommonDialog(TrustedStoreActivity.this, R.style.dialog, "请先完成企业认证\n" +
                                "再进行诚信认证", new CommonDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if (confirm) {
                                    openActivity(EnterpriseCertificationActivity.class);
                                }
                            }
                        });
                        dialog.setPositiveButton("去企业认证");
                        dialog.show();
                    }
                }, this));
                break;
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class);
                break;
            case R.id.ac_trustedStore_tv_apply:
                CertSubscribe.margin(user_id,token,money,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("退款成功");
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }
}
