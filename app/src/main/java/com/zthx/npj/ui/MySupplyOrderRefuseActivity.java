package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AlsoLikeAdatper;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AlsoLikeResponseBean;
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
import com.zthx.npj.net.been.MySupplyOrderDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySupplyOrderRefuseActivity extends ActivityBase {
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
    @BindView(R.id.ac_myOrderRefund_iv)
    ImageView acMyOrderRefundIv;
    @BindView(R.id.ac_myOrderRefund_tv_state)
    TextView acMyOrderRefundTvState;
    @BindView(R.id.ac_myOrderRefund_tv_time)
    TextView acMyOrderRefundTvTime;
    @BindView(R.id.ac_myOrderRefund_ll_reason)
    LinearLayout acMyOrderRefundLlReason;
    @BindView(R.id.ac_myOrderRefund_tv_reason)
    TextView acMyOrderRefundTvReason;

    private String order_id;
    private String order_state;

    private int page = 1;
    private AlsoLikeAdatper adatper;
    private MySupplyOrderDetailResponseBean.DataBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_refund);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "退款详情");

        order_id = getIntent().getStringExtra("order_id");
        order_state = getIntent().getStringExtra("order_state");

        getMySupplyOrderDetail();
        getAlsoLike();

    }

    private void getMySupplyOrderDetail() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
        SetSubscribe.mySupplyOrderDetail(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMySupplyOrderDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void setMySupplyOrderDetail(String result) {
        MySupplyOrderDetailResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderDetailResponseBean.class);
        data= bean.getData();
        acMyOrderRefundTvUserName.setText(data.getConsignee());
        acMyOrderRefundTvCellPhone.setText(data.getMobile());
        acMyOrderRefundTvAddress.setText(data.getAddress());
        //atMyOrderRefundTvStoreName.setText(data.getStore_name());

        acMyOrderRefundTvAllPrice.setText("￥"+data.getOrder_price());
        acMyOrderRefundTvCharge.setText("￥"+data.getOrder_price());

        Glide.with(this).load(Uri.parse("http://app.npj-vip.com"+data.getGoods_img().get(0))).into(atMyOrderRefundIvGoodsImg);
        atMyOrderRefundTvGoodsName.setText(data.getGoods_name());
        atMyOrderRefundTvGoodsPrice.setText("￥ " + data.getGoods_price());
        atMyOrderRefundTvGoodsNum.setText("x " + data.getOrder_num());
        atMyOrderRefundTvIsFreeShipping.setText("￥ " + data.getShipping_fee());

        atMyOrderRefundTvOrderSn.setText(data.getJujue_yuanyin());
        atMyOrderRefundTvPayType.setText("￥"+data.getOrder_price());
        atMyOrderRefundTvCreateTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data.getOrder_time() * 1000)));
        atMyOrderRefundTvPayTime.setText(data.getOrder_sn());

        switch (order_state) {
            case "6":
                acMyOrderRefundIv.setImageResource(R.drawable.refuse_refund);
                acMyOrderRefundTvState.setText("退款中");
                acMyOrderRefundTvOption.setText("退款中");
                atMyOrderRefundLlAddress.setVisibility(View.VISIBLE);
                acMyOrderRefundLlReason.setVisibility(View.GONE);
                acMyOrderRefundTvTime.setVisibility(View.VISIBLE);
                break;
            case "7":
                acMyOrderRefundIv.setImageResource(R.drawable.refund_success);
                acMyOrderRefundTvState.setText("退款成功");
                acMyOrderRefundTvOption.setText("退款成功");
                atMyOrderRefundLlAddress.setVisibility(View.VISIBLE);
                acMyOrderRefundLlReason.setVisibility(View.GONE);
                acMyOrderRefundTvTime.setVisibility(View.GONE);
                break;
            case "8":
                acMyOrderRefundIv.setImageResource(R.drawable.refund_fail);
                acMyOrderRefundTvState.setText("退款申请被拒绝，建议再次申请");
                acMyOrderRefundTvOption.setText("退款中");
                atMyOrderRefundLlAddress.setVisibility(View.GONE);
                acMyOrderRefundLlReason.setVisibility(View.VISIBLE);
                acMyOrderRefundTvReason.setText(data.getJujue_yuanyin());
                acMyOrderRefundTvTime.setVisibility(View.GONE);
                break;
        }
    }



    //猜你喜欢
    private void getAlsoLike() {
        MainSubscribe.alsoLike(page + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AlsoLikeResponseBean bean = GsonUtils.fromJson(result, AlsoLikeResponseBean.class);
                final ArrayList<AlsoLikeResponseBean.DataBean> data = bean.getData();
                GridLayoutManager layoutManager = new GridLayoutManager(MySupplyOrderRefuseActivity.this, 2);
                acMyOrderRefundRvCai.setLayoutManager(layoutManager);

                if (adatper == null) {
                    adatper = new AlsoLikeAdatper(MySupplyOrderRefuseActivity.this, data);
                } else {
                    if (data != null && data.size() != 0) {
                        if (data.size() < 10) {
                            seeMore.setText("没有更多了");
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        adatper.addData(data);
                    }
                }
                //设置添加或删除item时的动画，这里使用默认动画
                acMyOrderRefundRvCai.setItemAnimator(new DefaultItemAnimator());
                //设置适配器
                acMyOrderRefundRvCai.setItemAnimator(new DefaultItemAnimator());
                acMyOrderRefundRvCai.setAdapter(adatper);
                adatper.setOnItemClickListener(new AlsoLikeAdatper.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<AlsoLikeResponseBean.DataBean> mList) {
                        Intent intent = new Intent(MySupplyOrderRefuseActivity.this, GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, mList.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }
}
