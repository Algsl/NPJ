package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.TuiListAdapter;
import com.zthx.npj.net.been.ChengXinCertResponseBean;
import com.zthx.npj.net.been.TuiListResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.io.IOException;
import java.util.ArrayList;

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
    @BindView(R.id.ac_trustedStore_rv)
    RecyclerView acTrustedStoreRv;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String money;
    private TuiListAdapter adapter;

    private static final String TAG = "测试";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_store);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "诚信商家");
        getTuiList();
        getData();
    }

    private void getTuiList() {
        SetSubscribe.tuiList(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) throws IOException {
                setTuiList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setTuiList(String result) {
        TuiListResponseBean bean = GsonUtils.fromJson(result, TuiListResponseBean.class);
        ArrayList<TuiListResponseBean.DataBean> data = bean.getData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        acTrustedStoreRv.setLayoutManager(layoutManager);
        adapter=new TuiListAdapter(this,data);
        acTrustedStoreRv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                    money = chengXinCertResponseBean.getData().getBail();
                    atTrustStoreTvBaozhengjin.setText("您的当前保证金为" + money + "元");
                } else {
                    atTrustStoreLlBaozhengjin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.at_trust_store_btn_attestation, R.id.at_trust_bottom, R.id.ac_trustedStore_tv_apply})
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
                }));
                break;
            case R.id.at_trust_bottom:
                openActivity(ConsultActivity.class, "农品街平台认证协议", "http://game.npj-vip.com/agreement/certificate.html");
                break;
            case R.id.ac_trustedStore_tv_apply:
                showPublishPopwindow();
                /*CertSubscribe.margin(user_id,token,money,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("申请成功");
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));*/
                break;
        }
    }

    public void showPublishPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_trust_back, null);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        //window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(contentView, Gravity.CENTER, 0, 0);

        final EditText et = contentView.findViewById(R.id.pw_trustBack_et_back);
        TextView tv = contentView.findViewById(R.id.pw_trustBack_tv_confirm);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String money1=et.getText().toString().trim();
                if((adapter.getAllMoney()+Double.parseDouble(money1))<=Double.parseDouble(money)){
                    if(Long.parseLong(money1)%1000==0){
                        CertSubscribe.margin(user_id, token, money1, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                showToast("申请成功");
                            }

                            @Override
                            public void onFault(String errorMsg) {

                            }
                        }));
                        window.dismiss();
                        backgroundAlpha(1f);
                    }else{
                        showToast("请以1000的整数倍填写退还金额");
                    }
                }else{
                    showToast("退款金额不得超过认证总金额");
                }

            }
        });

        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                window.dismiss();
                backgroundAlpha(1f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
