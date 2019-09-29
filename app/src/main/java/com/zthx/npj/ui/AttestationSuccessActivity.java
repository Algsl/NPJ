package com.zthx.npj.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttestationSuccessActivity extends ActivityBase {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.ac_attestation_iv_fail)
    ImageView acAttestationIvFail;
    @BindView(R.id.ac_attestation_tv_status)
    TextView acAttestationTvStatus;
    @BindView(R.id.ac_attestation_tv_hint)
    TextView acAttestationTvHint;
    @BindView(R.id.ac_attestation_btn_reUp)
    Button acAttestationBtnReUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attestation_success);
        ButterKnife.bind(this);

        back(titleBack);
        final String reason=getIntent().getStringExtra("key0");
        final String attestationId = getIntent().getStringExtra("key1");//认证类别
        String result = getIntent().getStringExtra("key2");//认证结果
        final String cert_id=getIntent().getStringExtra("key3");//认证id


        if (result.equals("0")) {//待审核
            acAttestationTvStatus.setText("认证信息审核中");
            acAttestationBtnReUp.setText("返回认证");
            acAttestationBtnReUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            acAttestationTvHint.setVisibility(View.GONE);
        } else if (result.equals("1")) {//已通过
            acAttestationTvStatus.setText("认证成功");
            acAttestationBtnReUp.setText("返回认证");
            acAttestationBtnReUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            acAttestationTvHint.setVisibility(View.GONE);
        } else if (result.equals("2")) {//未通过
            acAttestationTvStatus.setText("未通过");
            acAttestationBtnReUp.setText("重新上传");
            acAttestationBtnReUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (attestationId){
                        case "1":
                            openActivity(RealNameAuthentication2Activity.class,cert_id);
                            break;
                        case "2":
                            openActivity(EnterpriseCertification2Activity.class,cert_id);
                            break;
                        case "3":
                            openActivity(PurchaserCertification2Activity.class,cert_id);
                            break;
                        case "4":
                            openActivity(RealNameAuthentication2Activity.class,cert_id);
                            break;
                        case "5":
                            openActivity(ZiZhiInfoActivity.class,cert_id);
                            break;
                    }
                }
            });
            acAttestationTvHint.setVisibility(View.VISIBLE);
            acAttestationTvHint.setText(reason);
        }


        switch (attestationId) {
            case "1":
                changeTitle(acTitle, "实人认证");
                if(result.equals("0")){
                    acAttestationIvFail.setImageResource(R.drawable.shirenrenzheng_bg);//待审核
                }else if(result.equals("1")){
                    acAttestationIvFail.setImageResource(R.drawable.shirenrenzheng_c_bg);//认证成功
                }else{
                    acAttestationIvFail.setImageResource(R.drawable.shirenrenzheng_iv);//认证失败
                }
                break;
            case "2":
                changeTitle(acTitle, "企业认证");
                if(result.equals("0")){
                    acAttestationIvFail.setImageResource(R.drawable.qiyerenzheng_bg);
                }else if(result.equals("1")){
                    acAttestationIvFail.setImageResource(R.drawable.qiyerenzheng_c_bg);
                }else{
                    acAttestationIvFail.setImageResource(R.drawable.qiyerenzheng_f_bg);
                }
                break;
            case "3":
                changeTitle(acTitle, "采购认证");
                if(result.equals("0")){
                    acAttestationIvFail.setImageResource(R.drawable.caigourenzheng_bg);
                }else if(result.equals("1")){
                    acAttestationIvFail.setImageResource(R.drawable.caigourenzheng_c_bg);
                }else{
                    acAttestationIvFail.setImageResource(R.drawable.caigourenzheng_f_bg);
                }
                break;
            case "4":
                changeTitle(acTitle, "诚信认证");
                if(result.equals("1")){
                    acAttestationIvFail.setImageResource(R.drawable.chengxinrenzheng_c_bg);
                }
                break;
            case "5":
                changeTitle(acTitle, "资质认证");
                if(result.equals("0")){
                    acAttestationIvFail.setImageResource(R.drawable.zizhirenzheng_bg);
                }else if(result.equals("1")){
                    acAttestationIvFail.setImageResource(R.drawable.zizhirenzheng_c_bg);
                }else{
                    acAttestationIvFail.setImageResource(R.drawable.zizhirenzheng_f_bg);
                }
                break;
        }
    }

}
