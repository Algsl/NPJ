package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CompanyBean;
import com.zthx.npj.net.been.CompanyResponseBean;
import com.zthx.npj.net.been.IntegrityResponseBean;
import com.zthx.npj.net.been.MyCertResponseBean;
import com.zthx.npj.net.been.RealNameResponseBean;
import com.zthx.npj.net.been.StockResponseBean;
import com.zthx.npj.net.been.ZiZhiResponseBean;
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
    @BindView(R.id.at_my_attestation_ll_zizhi)
    LinearLayout atMyAttestationLlZizhi;

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attestation);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "我的认证");
        getCertData();
    }

    private void getCertData() {
        CertSubscribe.getMyCert(user_id,token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MyCertResponseBean myCertResponseBean = GsonUtils.fromJson(result, MyCertResponseBean.class);
                if (myCertResponseBean.getName_cert() == 1) {
                    atMyAttestationLlPeople.setBackgroundResource(R.drawable.shirenrenzheng_c_bg);
                }
                if (myCertResponseBean.getCompany_cert() == 1) {
                    atMyAttestationLlCompany.setBackgroundResource(R.drawable.qiyerenzheng_c_bg);
                }
                if (myCertResponseBean.getIntegrity_cert() == 0) {
                    atMyAttestationLlTrust.setBackgroundResource(R.drawable.chengxinrenzheng_c_bg);
                }
                if (myCertResponseBean.getStock_cert() == 2) {
                    atMyAttestationLlBuy.setBackgroundResource(R.drawable.caigourenzheng_c_bg);
                }
                if(myCertResponseBean.getZizhi_cert()==0){
                    atMyAttestationLlBuy.setBackgroundResource(R.drawable.caigourenzheng_c_bg);
                }
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    @OnClick({R.id.at_my_attestation_ll_people, R.id.at_my_attestation_ll_company, R.id.at_my_attestation_ll_buy, R.id.at_my_attestation_ll_trust,R.id.at_my_attestation_ll_zizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_attestation_ll_people:
                CertSubscribe.realName(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        RealNameResponseBean bean=GsonUtils.fromJson(result,RealNameResponseBean.class);
                        switch (bean.getData().getStatus()+""){
                            case "1":
                                openActivity(RealNameAuthenticationActivity.class);
                                break;
                            case "2":
                                openActivity(AttestationSuccessActivity.class,"1","0");
                                break;
                            case "3":
                                openActivity(AttestationSuccessActivity.class,"1","1");
                                break;
                            case "4":
                                openActivity(AttestationSuccessActivity.class,"1","2",bean.getData().getCert_id());
                                break;
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.at_my_attestation_ll_company:
                CertSubscribe.company(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        CompanyResponseBean bean=GsonUtils.fromJson(result,CompanyResponseBean.class);
                        switch (bean.getData().getStatus()+""){
                            case "1":
                                openActivity(EnterpriseCertificationActivity.class);
                                break;
                            case "2":
                                openActivity(AttestationSuccessActivity.class,"2","0");
                                break;
                            case "3":
                                openActivity(AttestationSuccessActivity.class,"2","1");
                                break;
                            case "4":
                                openActivity(AttestationSuccessActivity.class,"2","2",bean.getData().getCert_id()+"");
                                break;
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.at_my_attestation_ll_buy:
                CertSubscribe.stock(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        StockResponseBean bean=GsonUtils.fromJson(result,StockResponseBean.class);
                        /*switch (bean.getData().getStatus()+""){
                            case "1":
                                openActivity(EnterpriseCertificationActivity.class);
                                break;
                            case "2":
                                openActivity(AttestationWaitActivity.class,"2");
                                break;
                            case "3":
                                openActivity(AttestationSuccessActivity.class,"2");
                                break;
                            case "4":
                                openActivity(AttestationFailActivity.class,"2");
                                break;
                        }*/
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                startActivity(new Intent(this, PurchaserCertificationActivity.class));
                break;
            case R.id.at_my_attestation_ll_trust:
                CertSubscribe.integrity(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        IntegrityResponseBean bean=GsonUtils.fromJson(result,IntegrityResponseBean.class);
                        switch (bean.getData().getStatus()+""){
                            case "1":
                                openActivity(AttestationSuccessActivity.class,"4","0");
                                break;
                            case "2":
                                openActivity(TrustedStoreActivity.class);
                                break;
                            case "3":
                                openActivity(AttestationSuccessActivity.class,"4","1");
                                break;
                            case "4":
                                openActivity(AttestationSuccessActivity.class,"4","2");
                                break;
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.at_my_attestation_ll_zizhi:
                CertSubscribe.zizhi(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        ZiZhiResponseBean bean=GsonUtils.fromJson(result,ZiZhiResponseBean.class);
                        switch (bean.getData().getStatus()+""){
                            case "1":
                                openActivity(ZiZhiActivity.class);
                                break;
                            case "2":
                                openActivity(AttestationSuccessActivity.class,"5","0");
                                break;
                            case "3":
                                openActivity(AttestationSuccessActivity.class,"5","1");
                                break;
                            case "4":
                                openActivity(AttestationSuccessActivity.class,"5","2",bean.getData().getCert_id()+"");
                                break;
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
        }
    }

}
