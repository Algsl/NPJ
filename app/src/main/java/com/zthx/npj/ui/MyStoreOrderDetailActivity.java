package com.zthx.npj.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.ImageCircleConner;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyStoreOrderDetailActivity extends ActivityBase {
    @BindView(R.id.at_myOrderDetail_ll_address)
    LinearLayout atMyOrderDetailLlAddress;
    @BindView(R.id.at_myOrderDetail_tv_storeName)
    TextView atMyOrderDetailTvStoreName;
    @BindView(R.id.at_myOrderDetail_iv_goodsImg)
    ImageView atMyOrderDetailIvGoodsImg;
    @BindView(R.id.at_myOrderDetail_tv_goodsName)
    TextView atMyOrderDetailTvGoodsName;
    @BindView(R.id.at_myOrderDetail_tv_goodsPrice)
    TextView atMyOrderDetailTvGoodsPrice;
    @BindView(R.id.at_myOrderDetail_tv_goodsNum)
    TextView atMyOrderDetailTvGoodsNum;
    @BindView(R.id.at_myOrderDetail_tv_isFreeShipping)
    TextView atMyOrderDetailTvIsFreeShipping;
    //@BindView(R.id.at_myOrderDetail_tv_orderPrice)
    //TextView atMyOrderDetailTvOrderPrice;
    @BindView(R.id.at_myOrderDetail_tv_orderSn)
    TextView atMyOrderDetailTvOrderSn;
    @BindView(R.id.at_myOrderDetail_tv_payType)
    TextView atMyOrderDetailTvPayType;
    @BindView(R.id.at_myOrderDetail_tv_createTime)
    TextView atMyOrderDetailTvCreateTime;
    @BindView(R.id.at_myOrderDetail_tv_payTime)
    TextView atMyOrderDetailTvPayTime;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_myOrderDetail_tv_status)
    TextView acMyOrderDetailTvStatus;
    @BindView(R.id.ac_myOrderDetail_tv_hint)
    TextView acMyOrderDetailTvHint;
    @BindView(R.id.ac_myOrderDetail_tv_userName)
    TextView acMyOrderDetailTvUserName;
    @BindView(R.id.ac_myOrderDetail_tv_cellPhone)
    TextView acMyOrderDetailTvCellPhone;
    @BindView(R.id.ac_myOrderDetail_tv_address)
    TextView acMyOrderDetailTvAddress;
    @BindView(R.id.ac_myOrderDetail_tv_option)
    TextView acMyOrderDetailTvOption;
    @BindView(R.id.ac_myOrderDetail_tv_allPrice)
    TextView acMyOrderDetailTvAllPrice;
    @BindView(R.id.ac_myOrderDetail_tv_charge)
    TextView acMyOrderDetailTvCharge;
    @BindView(R.id.ac_myOrderDetail_tv_needPay)
    TextView acMyOrderDetailTvNeedPay;
    @BindView(R.id.ac_myOrderDetail_iv_pwMsg)
    ImageView acMyOrderDetailIvPwMsg;
    @BindView(R.id.at_myOrderDetail_iv_goodsImg1)
    ImageView atMyOrderDetailIvGoodsImg1;
    @BindView(R.id.at_myOrderDetail_tv_goodsName1)
    TextView atMyOrderDetailTvGoodsName1;
    @BindView(R.id.at_myOrderDetail_tv_goodsPrice1)
    TextView atMyOrderDetailTvGoodsPrice1;
    @BindView(R.id.at_myOrderDetail_tv_goodsNum1)
    TextView atMyOrderDetailTvGoodsNum1;
    @BindView(R.id.ac_myOrderDetail_iv_refundType)
    TextView acMyOrderDetailIvRefundType;
    @BindView(R.id.ac_myOrderDetail_iv_refund)
    TextView acMyOrderDetailIvRefund;
    @BindView(R.id.at_myOrderDetail_tv_send)
    TextView atMyOrderDetailTvSend;
    @BindView(R.id.at_myOrderDetail_tv_over)
    TextView atMyOrderDetailTvOver;
    @BindView(R.id.at_myOrderDetail_tv_refundNum)
    TextView atMyOrderDetailTvRefundNum;
    @BindView(R.id.ac_myOrderDetail_ll_refund)
    LinearLayout acMyOrderDetailLlRefund;
    @BindView(R.id.ac_myOrderDetail_tv_cancel)
    TextView acMyOrderDetailTvCancel;
    @BindView(R.id.ac_myOrderDetail_tv_pay)
    TextView acMyOrderDetailTvPay;
    @BindView(R.id.ac_myOrderDetail_tv_applyRefund)
    TextView acMyOrderDetailTvApplyRefund;
    @BindView(R.id.ac_myOrderDetail_tv_wuliu)
    TextView acMyOrderDetailTvWuliu;
    @BindView(R.id.ac_myOrderDetail_tv_delay)
    TextView acMyOrderDetailTvDelay;
    @BindView(R.id.ac_myOrderDetail_tv_confirm)
    TextView acMyOrderDetailTvConfirm;
    @BindView(R.id.ac_myOrderDetail_tv_delete)
    TextView acMyOrderDetailTvDelete;
    @BindView(R.id.ac_myOrderDetail_tv_comment)
    TextView acMyOrderDetailTvComment;
    @BindView(R.id.ac_myOrderDetail_tv_chat)
    TextView acMyOrderDetailTvChat;
    @BindView(R.id.ac_myOrderDetail_tv_call)
    TextView acMyOrderDetailTvCall;
    @BindView(R.id.ac_myOrderDetail_ll_paySend)
    LinearLayout acMyOrderDetailLlPaySend;
    @BindView(R.id.ac_myOrderDetail_ll_refundNum)
    LinearLayout acMyOrderDetailLlRefundNum;

    private String order_state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_detail);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "订单详情");

        order_state = getIntent().getStringExtra("order_state");


        getMyStoreOrderDetail();
    }

    private void getMyStoreOrderDetail() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
        String order_id = getIntent().getStringExtra("order_id");
        SetSubscribe.myOrderDetail(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyOrderDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyOrderDetail(String result) {
        MyOrderDetailResponseBean bean = GsonUtils.fromJson(result, MyOrderDetailResponseBean.class);
        MyOrderDetailResponseBean.DataBean data = bean.getData();
        acMyOrderDetailTvUserName.setText(data.getConsignee());
        acMyOrderDetailTvCellPhone.setText(data.getMobile());
        acMyOrderDetailTvAddress.setText(data.getAddress());
        atMyOrderDetailTvStoreName.setText(data.getStore_name());

        Glide.with(this).load(Uri.parse(data.getGoods_img())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                atMyOrderDetailIvGoodsImg.setImageBitmap(ImageCircleConner.toRoundCorner(resource, 16));
            }
        });
        Glide.with(this).load(Uri.parse(data.getGoods_img())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                atMyOrderDetailIvGoodsImg1.setImageBitmap(ImageCircleConner.toRoundCorner(resource, 16));
            }
        });
        atMyOrderDetailTvGoodsName.setText(data.getGoods_name());
        atMyOrderDetailTvGoodsName1.setText(data.getGoods_name());
        atMyOrderDetailTvGoodsPrice.setText(data.getGoods_price());
        atMyOrderDetailTvGoodsPrice1.setText(data.getGoods_price());
        atMyOrderDetailTvGoodsNum.setText("x " + data.getGoods_num());
        atMyOrderDetailTvGoodsNum1.setText("x " + data.getGoods_num());
        atMyOrderDetailTvIsFreeShipping.setText(data.getShipping_fee());
        //atMyOrderDetailTvOrderPrice.setText(data.getOrder_price());

        atMyOrderDetailTvOrderSn.setText(data.getOrder_sn());
        atMyOrderDetailTvCreateTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data.getOrder_time())));
        atMyOrderDetailTvPayTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data.getOrder_time())));

        switch (order_state) {
            case "1":
                acMyOrderDetailTvStatus.setText("待付款");
                acMyOrderDetailTvHint.setText("剩余24时00分自动关闭");
                break;
            case "2":
                acMyOrderDetailTvStatus.setText("等待卖家发货");
                acMyOrderDetailTvHint.setVisibility(View.GONE);
                break;
            case "3":
                acMyOrderDetailTvStatus.setText("已发货");
                acMyOrderDetailTvHint.setText("剩余9天4时自动确认");
                break;
            case "4":
                acMyOrderDetailTvStatus.setText("交易成功");
                acMyOrderDetailTvHint.setText("期待再次为您服务");
                break;
            case "6":
                acMyOrderDetailLlPaySend.setVisibility(View.GONE);
                acMyOrderDetailLlRefund.setVisibility(View.VISIBLE);
                acMyOrderDetailLlRefundNum.setVisibility(View.VISIBLE);
                acMyOrderDetailTvStatus.setText("已提交退款申请，等待卖家处理");
                acMyOrderDetailTvHint.setText("剩余2天4时自动确认");

                break;
        }
    }

}
