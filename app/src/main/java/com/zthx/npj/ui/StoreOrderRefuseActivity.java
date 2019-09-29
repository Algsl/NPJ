package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreOrderRefuseActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_myOrderRefund_tv_userName)
    TextView acMyOrderRefundTvUserName;
    @BindView(R.id.ac_myOrderRefund_tv_cellPhone)
    TextView acMyOrderRefundTvCellPhone;
    @BindView(R.id.ac_myOrderRefund_tv_address)
    TextView acMyOrderRefundTvAddress;
    @BindView(R.id.at_myOrderRefund_ll_address)
    LinearLayout atMyOrderRefundLlAddress;
    @BindView(R.id.at_myOrderRefund_tv_storeName)
    TextView atMyOrderRefundTvStoreName;
    @BindView(R.id.at_myOrderRefund_iv_goodsImg)
    RoundedImageView atMyOrderRefundIvGoodsImg;
    @BindView(R.id.at_myOrderRefund_tv_goodsName)
    TextView atMyOrderRefundTvGoodsName;
    @BindView(R.id.ac_orderDetail_tv_size)
    TextView acOrderDetailTvSize;
    @BindView(R.id.at_myOrderRefund_tv_goodsPrice)
    TextView atMyOrderRefundTvGoodsPrice;
    @BindView(R.id.at_myOrderRefund_tv_goodsNum)
    TextView atMyOrderRefundTvGoodsNum;
    @BindView(R.id.ac_myOrderRefund_tv_option)
    TextView acMyOrderRefundTvOption;
    @BindView(R.id.ac_myOrderRefund_ll)
    LinearLayout acMyOrderRefundLl;
    @BindView(R.id.ac_myOrderRefund_tv_allPrice)
    TextView acMyOrderRefundTvAllPrice;
    @BindView(R.id.at_myOrderRefund_tv_isFreeShipping)
    TextView atMyOrderRefundTvIsFreeShipping;
    @BindView(R.id.ac_myOrderRefund_tv_charge)
    TextView acMyOrderRefundTvCharge;
    @BindView(R.id.ac_myOrderRefund_tv_needPay)
    TextView acMyOrderRefundTvNeedPay;
    @BindView(R.id.at_myOrderRefund_tv_orderSn)
    TextView atMyOrderRefundTvOrderSn;
    @BindView(R.id.at_myOrderRefund_ll_orderSn)
    LinearLayout atMyOrderRefundLlOrderSn;
    @BindView(R.id.at_myOrderRefund_tv_payType)
    TextView atMyOrderRefundTvPayType;
    @BindView(R.id.at_myOrderRefund_ll_payType)
    LinearLayout atMyOrderRefundLlPayType;
    @BindView(R.id.at_myOrderRefund_tv_createTime)
    TextView atMyOrderRefundTvCreateTime;
    @BindView(R.id.at_myOrderRefund_ll_createTime)
    LinearLayout atMyOrderRefundLlCreateTime;
    @BindView(R.id.at_myOrderRefund_tv_payTime)
    TextView atMyOrderRefundTvPayTime;
    @BindView(R.id.at_myOrderRefund_ll_payTime)
    LinearLayout atMyOrderRefundLlPayTime;
    @BindView(R.id.ac_myOrderRefund_rv_cai)
    RecyclerView acMyOrderRefundRvCai;
    @BindView(R.id.seeMore)
    TextView seeMore;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_myOrderRefund_tv_applyRefund)
    TextView acMyOrderRefundTvApplyRefund;
    @BindView(R.id.ac_myOrderRefund_tv_wuliu)
    TextView acMyOrderRefundTvWuliu;
    @BindView(R.id.ac_myOrderRefund_tv_confirm)
    TextView acMyOrderRefundTvConfirm;
    @BindView(R.id.ac_myOrderRefund_tv_delete)
    TextView acMyOrderRefundTvDelete;
    @BindView(R.id.ac_myOrderRefund_tv_chat)
    TextView acMyOrderRefundTvChat;
    @BindView(R.id.ac_myOrderRefund_tv_call)
    TextView acMyOrderRefundTvCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_refund);
        ButterKnife.bind(this);
    }
}
