package com.zthx.npj.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
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
                Log.e("测试", "onFault: "+errorMsg);
            }
        }));
    }

    @OnClick({R.id.at_trust_bottom, R.id.at_zizhi_btn_attestation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class);
                break;
            case R.id.at_zizhi_btn_attestation:
                CertSubscribe.zizhi4(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                }));
                break;
        }
    }
}
