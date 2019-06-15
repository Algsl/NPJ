package com.zthx.npj.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.net.been.UploadChengXinCertBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrustedStore2Activity extends AppCompatActivity {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_trust_store2_tv_2000)
    TextView atTrustStore2Tv2000;
    @BindView(R.id.at_trust_store2_tv2_2000)
    TextView atTrustStore2Tv22000;
    @BindView(R.id.at_trust_store2_rl_2000)
    RelativeLayout atTrustStore2Rl2000;
    @BindView(R.id.at_trust_store2_tv_3000)
    TextView atTrustStore2Tv3000;
    @BindView(R.id.at_trust_store2_tv2_3000)
    TextView atTrustStore2Tv23000;
    @BindView(R.id.at_trust_store2_rl_3000)
    RelativeLayout atTrustStore2Rl3000;
    @BindView(R.id.at_trust_store2_tv_4000)
    TextView atTrustStore2Tv4000;
    @BindView(R.id.at_trust_store2_tv2_4000)
    TextView atTrustStore2Tv24000;
    @BindView(R.id.at_trust_store2_rl_4000)
    RelativeLayout atTrustStore2Rl4000;
    @BindView(R.id.at_trust_store2_rb_read)
    RadioButton atTrustStore2RbRead;
    @BindView(R.id.at_trust_store2_btn_join)
    Button atTrustStore2BtnJoin;

    private int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_store2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_trust_store2_rl_2000, R.id.at_trust_store2_rl_3000, R.id.at_trust_store2_rl_4000, R.id.at_trust_store2_rb_read, R.id.at_trust_store2_btn_join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_trust_store2_rl_2000:
                clickPrice(2);
                check = 2;
                break;
            case R.id.at_trust_store2_rl_3000:
                clickPrice(3);
                check = 3;
                break;
            case R.id.at_trust_store2_rl_4000:
                clickPrice(4);
                check = 4;
                break;
            case R.id.at_trust_store2_rb_read:
                atTrustStore2RbRead.setChecked(true);
                break;
            case R.id.at_trust_store2_btn_join:
                showBottomDialog();
                break;
        }
    }

    private void clickPrice(int i) {
        atTrustStore2Rl2000.setBackgroundResource(android.R.color.white);
        atTrustStore2Rl3000.setBackgroundResource(android.R.color.white);
        atTrustStore2Rl4000.setBackgroundResource(android.R.color.white);
        atTrustStore2Tv2000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv3000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv4000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv22000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv23000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv24000.setTextColor(getResources().getColor(R.color.app_theme));
        if (i == 2) {
            atTrustStore2Rl2000.setBackgroundResource(R.color.app_theme);
            atTrustStore2Tv2000.setTextColor(getResources().getColor(android.R.color.white));
            atTrustStore2Tv22000.setTextColor(getResources().getColor(android.R.color.white));
        } else if (i == 3) {
            atTrustStore2Rl3000.setBackgroundResource(R.color.app_theme);
            atTrustStore2Tv3000.setTextColor(getResources().getColor(android.R.color.white));
            atTrustStore2Tv23000.setTextColor(getResources().getColor(android.R.color.white));
        } else if (i == 4) {
            atTrustStore2Rl4000.setBackgroundResource(R.color.app_theme);
            atTrustStore2Tv4000.setTextColor(getResources().getColor(android.R.color.white));
            atTrustStore2Tv24000.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_pay_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_pay_ali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData(1);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData(2);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void uploadData(int i) {
        UploadChengXinCertBean bean = new UploadChengXinCertBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(TrustedStore2Activity.this));
        bean.setToken(BaseConstant.TOKEN);
        bean.setPrice(check+ "000");
        bean.setPay_code(i + "");
        CertSubscribe.uploadChengxinCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
