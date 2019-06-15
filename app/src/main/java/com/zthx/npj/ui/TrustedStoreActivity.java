package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.net.been.ChengXinCertResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrustedStoreActivity extends AppCompatActivity {

    @BindView(R.id.at_trust_store_btn_attestation)
    Button atTrustStoreBtnAttestation;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_trust_store_tv_baozhengjin)
    TextView atTrustStoreTvBaozhengjin;
    @BindView(R.id.at_trust_store_ll_baozhengjin)
    LinearLayout atTrustStoreLlBaozhengjin;
    @BindView(R.id.at_trust_bottom)
    LinearLayout atTrustBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_store);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {

        CertSubscribe.isChengXinAlreadyCert(SharePerferenceUtils.getUserId(this), BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                ChengXinCertResponseBean chengXinCertResponseBean = GsonUtils.fromJson(result, ChengXinCertResponseBean.class);
                int status = chengXinCertResponseBean.getData().getStatus();
                if (status == 1) {
                    atTrustStoreLlBaozhengjin.setVisibility(View.VISIBLE);
                    atTrustStoreTvBaozhengjin.setText("您的当前保证金为"+chengXinCertResponseBean.getData().getStatus()+"元");
                } else {
                    atTrustStoreLlBaozhengjin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        },this));
    }

    @OnClick(R.id.at_trust_store_btn_attestation)
    public void onViewClicked() {

        CertSubscribe.isChengXinAlready2Cert(SharePerferenceUtils.getUserId(this), BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                startActivity(new Intent(TrustedStoreActivity.this, TrustedStore2Activity.class));
            }

            @Override
            public void onFault(String errorMsg) {

                Toast.makeText(TrustedStoreActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
            }
        },this));
    }
}
