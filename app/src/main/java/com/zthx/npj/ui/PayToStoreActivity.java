package com.zthx.npj.ui;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.StoreDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayToStoreActivity extends ActivityBase {

    @BindView(R.id.at_pay_to_store_iv_pic)
    ImageView atPayToStoreIvPic;
    @BindView(R.id.ac_payToStore_tv_storeName)
    TextView acPayToStoreTvStoreName;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_payToStore_et_inputMoney)
    EditText acPayToStoreEtInputMoney;
    @BindView(R.id.ac_payToStore_tv_payMoney)
    TextView acPayToStoreTvPayMoney;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.ac_payToStore_btn_toVIP)
    Button acPayToStoreBtnToVIP;
    @BindView(R.id.ac_payToStore_btn_toPay)
    Button acPayToStoreBtnToPay;

    private String user_level = SharePerferenceUtils.getUserLevel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_to_store);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "付款");

        if (!user_level.equals("0")) {
            acPayToStoreBtnToVIP.setVisibility(View.GONE);
        }

        String store_id = getIntent().getStringExtra("key0");
        getStoreDetail(store_id);
    }

    private void getStoreDetail(String store_id) {
        MainSubscribe.getStoreDetail(store_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setStoreDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setStoreDetail(String result) {
        StoreDetailResponseBean bean = GsonUtils.fromJson(result, StoreDetailResponseBean.class);
        //Glide.with(this).load(Uri.parse(bean.getData().getStore_img().get(0))).into(atPayToStoreIvPic);
        Glide.with(this).load(Uri.parse(bean.getData().getStore_img().get(0))).into(atPayToStoreIvPic);
        acPayToStoreTvStoreName.setText(bean.getData().getStore_name());
    }

    @OnClick({R.id.ac_payToStore_et_inputMoney,R.id.ac_payToStore_btn_toVIP,R.id.ac_payToStore_btn_toPay})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ac_payToStore_et_inputMoney:
               /* acPayToStoreEtInputMoney.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_SEND) {
                            String money = acPayToStoreEtInputMoney.getText().toString();
                            if (user_level.equals("0")) {
                                acPayToStoreTvPayMoney.setText(money);
                            } else {
                                Double realMoney = Double.parseDouble(money);
                                acPayToStoreTvPayMoney.setText(realMoney*0.9+"");
                            }
                        }
                        return false;
                    }
                });*/
               acPayToStoreEtInputMoney.addTextChangedListener(new TextWatcher() {
                   @Override
                   public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                   }

                   @Override
                   public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                       String money = acPayToStoreEtInputMoney.getText().toString();
                       if(user_level.equals("0")){
                           acPayToStoreTvPayMoney.setText("￥"+money);
                       }else{
                           if(money.equals("")){
                               acPayToStoreTvPayMoney.setText("￥0.00");
                           }else{
                               Double realMoney = Double.parseDouble(money);
                               acPayToStoreTvPayMoney.setText("￥"+realMoney*0.9+"");
                           }
                       }

                   }

                   @Override
                   public void afterTextChanged(Editable editable) {

                   }
               });
                break;
            case R.id.ac_payToStore_btn_toVIP:
                openActivity(MembershipPackageActivity.class);
                break;
            case R.id.ac_payToStore_btn_toPay:
                showBottomDialog();
                break;
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
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}
