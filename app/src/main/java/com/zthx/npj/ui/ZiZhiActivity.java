package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.IsPersonCertResponseBean;
import com.zthx.npj.net.been.ZiZhiBean;
import com.zthx.npj.net.been.ZiZhiResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

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

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private boolean flag=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zizhi);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "资质认证");

        getZiZhi();
    }

    private void getZiZhi() {
        CertSubscribe.zizhi(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ZiZhiResponseBean bean=GsonUtils.fromJson(result,ZiZhiResponseBean.class);
                ZiZhiResponseBean.DataBean data=bean.getData();
                if(data.getStatus()==1){
                    flag=false;
                }else{
                    flag=true;
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.at_trust_bottom, R.id.at_zizhi_btn_attestation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class,"农品街平台认证协议","http://game.npj-vip.com/agreement/certificate.html");
                break;
            case R.id.at_zizhi_btn_attestation:
                isPersonCert();
                /*CertSubscribe.zizhi4(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        openActivity(ZiZhiInfoActivity.class);
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        CommonDialog dialog=new CommonDialog(ZiZhiActivity.this, R.style.dialog, "请先完成企业认证\n" +
                                "再进行资质认证", new CommonDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if(confirm){
                                    openActivity(MyAttestationActivity.class);
                                }
                            }
                        });
                        dialog.setPositiveButton("去企业认证");
                        dialog.show();
                    }
                }));*/
                break;
        }
    }

    private void isPersonCert() {
        CertSubscribe.isPersonCertDone(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                IsPersonCertResponseBean isPersonCertResponseBean = GsonUtils.fromJson(result, IsPersonCertResponseBean.class);
                IsPersonCertResponseBean.DataBean data = isPersonCertResponseBean.getData();
                Intent intent = new Intent(ZiZhiActivity.this, ZiZhiInfoActivity.class);
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
                "再进行资质认证\n", new CommonDialog.OnCloseListener() {
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
