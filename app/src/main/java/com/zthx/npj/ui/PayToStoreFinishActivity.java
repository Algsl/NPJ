package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.OfflineStoreCommentBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayToStoreFinishActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_payStore_iv_storeImg)
    ImageView acPayStoreIvStoreImg;
    @BindView(R.id.ac_payStore_tv_storeName)
    TextView acPayStoreTvStoreName;
    @BindView(R.id.ac_payStore_tv_payMoney)
    TextView acPayStoreTvPayMoney;
    @BindView(R.id.ac_payStore_rb_rb1)
    RatingBar acPayStoreRbRb1;
    @BindView(R.id.ac_payStore_rb_rb2)
    RatingBar acPayStoreRbRb2;
    @BindView(R.id.ac_payStore_rb_rb3)
    RatingBar acPayStoreRbRb3;
    @BindView(R.id.ac_payStore_et_desc)
    EditText acPayStoreEtDesc;
    @BindView(R.id.ac_payStore_tv_number)
    TextView acPayStoreTvNumber;
    @BindView(R.id.ac_payStore_tv_confirm)
    TextView acPayStoreTvConfirm;

    private String goods_start="5";
    private String service_start="5";
    private String logistics_start="5";

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    private String store_id;
    private String store_img;
    private String store_name;
    private String payMoney;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_store_finish);
        ButterKnife.bind(this);

        store_id=getIntent().getStringExtra("key0");
        store_img=getIntent().getStringExtra("key1");
        store_name=getIntent().getStringExtra("key2");
        payMoney=getIntent().getStringExtra("key3");

        Glide.with(this).load(Uri.parse(store_img)).into(acPayStoreIvStoreImg);
        acPayStoreTvStoreName.setText(store_name);
        acPayStoreTvPayMoney.setText(payMoney+"元");

        acPayStoreRbRb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                goods_start = String.valueOf(v);
            }
        });
        acPayStoreRbRb2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                service_start = String.valueOf(v);
            }
        });
        acPayStoreRbRb3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                logistics_start = String.valueOf(v);
            }
        });


    }

    @OnClick({R.id.ac_payStore_et_desc, R.id.ac_payStore_tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_payStore_et_desc:
                acPayStoreEtDesc.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(!acPayStoreEtDesc.getText().toString().trim().equals("")){
                            acPayStoreTvNumber.setText(acPayStoreEtDesc.getText().toString().trim().length()+"/200");
                        }else{
                            acPayStoreTvNumber.setText("0/200");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                break;
            case R.id.ac_payStore_tv_confirm:
                if(acPayStoreEtDesc.getText().toString().trim().equals("")){
                    showToast("请填写您的评价");
                }else{
                    OfflineStoreCommentBean bean=new OfflineStoreCommentBean();
                    bean.setUser_id(user_id);
                    bean.setToken(token);
                    bean.setStore_id(store_id);
                    bean.setContent(acPayStoreEtDesc.getText().toString().trim());
                    bean.setGoods_star(goods_start);
                    bean.setService_star(service_start);
                    bean.setLogistics_str(logistics_start);
                    MainSubscribe.offlineStoreComment(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("店铺评价成功");
                            openActivity(LocationStoreActivity.class);
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                }
                break;
        }
    }
}
