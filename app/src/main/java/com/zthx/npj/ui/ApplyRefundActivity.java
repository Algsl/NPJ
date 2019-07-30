package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.ApplyRefundBean;
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyRefundActivity extends ActivityBase {
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_order_applyRefund_iv_goodsImg)
    ImageView acOrderApplyRefundIvGoodsImg;
    @BindView(R.id.at_order_applyRefund_tv_goodsName)
    TextView atOrderApplyRefundTvGoodsName;
    @BindView(R.id.at_order_applyRefund_tv_goodsPrice)
    TextView atOrderApplyRefundTvGoodsPrice;
    @BindView(R.id.at_order_applyRefund_tv_goodsNum)
    TextView atOrderApplyRefundTvGoodsNum;
    @BindView(R.id.at_order_applyRefund_tv_state)
    TextView atOrderApplyRefundTvState;
    @BindView(R.id.at_order_applyRefund_tv_reason)
    TextView atOrderApplyRefundTvReason;
    @BindView(R.id.at_order_applyRefund_tv_orderPrice)
    TextView atOrderApplyRefundTvOrderPrice;
    @BindView(R.id.at_order_applyRefund_tv_ship)
    TextView atOrderApplyRefundTvShip;
    @BindView(R.id.ac_order_applyRefund_et_reason)
    EditText acOrderApplyRefundEtReason;
    @BindView(R.id.ac_order_applyRefund_iv_img)
    ImageView acOrderApplyRefundIvImg;
    @BindView(R.id.ac_order_applyRefund_btn_confirm)
    Button acOrderApplyRefundBtnConfirm;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    MyOrderDetailResponseBean.DataBean data;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.at_confirm_order_iv_go)
    ImageView atConfirmOrderIvGo;
    @BindView(R.id.ac_order_applyRefund_choose)
    ImageView acOrderApplyRefundChoose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_applyrefund);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"申请退款");
        getOrderDetail();
    }

    private void getOrderDetail() {
        String order_id = getIntent().getStringExtra("order_id");
        SetSubscribe.myOrderDetail(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setOrderDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setOrderDetail(String result) {
        MyOrderDetailResponseBean bean = GsonUtils.fromJson(result, MyOrderDetailResponseBean.class);
        data = bean.getData();
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(acOrderApplyRefundIvGoodsImg);
        atOrderApplyRefundTvGoodsName.setText(data.getGoods_name());
        atOrderApplyRefundTvGoodsPrice.setText("￥ " + data.getGoods_price());
        atOrderApplyRefundTvGoodsNum.setText("x " + data.getGoods_num());
        atOrderApplyRefundTvOrderPrice.setText("￥ " + data.getOrder_price());
        atOrderApplyRefundTvShip.setText("最多" + data.getOrder_price() + "元，含运费￥" + data.getShipping_fee());
    }

    @OnClick({R.id.at_order_applyRefund_tv_state, R.id.at_order_applyRefund_tv_reason, R.id.ac_order_applyRefund_iv_img, R.id.ac_order_applyRefund_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_order_applyRefund_tv_state:
                break;
            case R.id.at_order_applyRefund_tv_reason:
                break;
            case R.id.ac_order_applyRefund_iv_img:
                break;
            case R.id.ac_order_applyRefund_btn_confirm:
                ApplyRefundBean bean = new ApplyRefundBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(getIntent().getStringExtra("order_id"));
                bean.setRefund_state("0");
                bean.setRefund_reason("颜色不对");
                bean.setRefund_price(data.getOrder_price());
                bean.setRefund_desc(acOrderApplyRefundEtReason.getText().toString().trim());
                bean.setRefund_img("/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg");
                Log.e("测试", user_id + " " + token + " " + getIntent().getStringExtra("order_id"));
                SetSubscribe.applyRefund(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }
}
