package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.net.been.MyCertResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAttestationActivity extends ActivityBase {

    @BindView(R.id.at_my_attestation_ll_people)
    LinearLayout atMyAttestationLlPeople;
    @BindView(R.id.at_my_attestation_ll_company)
    LinearLayout atMyAttestationLlCompany;
    @BindView(R.id.at_my_attestation_ll_buy)
    LinearLayout atMyAttestationLlBuy;
    @BindView(R.id.at_my_attestation_ll_trust)
    LinearLayout atMyAttestationLlTrust;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attestation);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"我的认证");
        getCertData();
    }

    private void getCertData() {
        CertSubscribe.getMyCert(SharePerferenceUtils.getUserId(this), BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                MyCertResponseBean myCertResponseBean = GsonUtils.fromJson(result, MyCertResponseBean.class);
                if (myCertResponseBean.getName_cert() == 1) {
                    atMyAttestationLlPeople.setBackgroundResource(R.drawable.shirenrenzheng_c_bg);
                }
                if (myCertResponseBean.getCompany_cert() == 1) {
                    atMyAttestationLlCompany.setBackgroundResource(R.drawable.qiyerenzheng_c_bg);
                }
                if (myCertResponseBean.getIntegrity_cert() == 1) {
                    atMyAttestationLlTrust.setBackgroundResource(R.drawable.chengxinrenzheng_c_bg);
                }
                if (myCertResponseBean.getStock_cert() == 1) {
                    atMyAttestationLlBuy.setBackgroundResource(R.drawable.caigourenzheng_c_bg);
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.at_my_attestation_ll_people, R.id.at_my_attestation_ll_company, R.id.at_my_attestation_ll_buy, R.id.at_my_attestation_ll_trust})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_attestation_ll_people:
                startActivity(new Intent(this, RealNameAuthenticationActivity.class));
                break;
            case R.id.at_my_attestation_ll_company:
                startActivity(new Intent(this, EnterpriseCertificationActivity.class));
                break;
            case R.id.at_my_attestation_ll_buy:
                startActivity(new Intent(this, PurchaserCertificationActivity.class));
                break;
            case R.id.at_my_attestation_ll_trust:
                startActivity(new Intent(this, TrustedStoreActivity.class));
                break;
        }
    }
}
