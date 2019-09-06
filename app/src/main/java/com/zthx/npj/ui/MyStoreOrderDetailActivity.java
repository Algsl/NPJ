package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AlsoLikeAdatper;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AlsoLikeResponseBean;
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
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
import butterknife.OnClick;

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
    @BindView(R.id.ac_myOrderDetail_rv_cai)
    RecyclerView acMyOrderDetailRvCai;
    @BindView(R.id.at_myOrderDetail_ll_orderSn)
    LinearLayout atMyOrderDetailLlOrderSn;
    @BindView(R.id.at_myOrderDetail_ll_payType)
    LinearLayout atMyOrderDetailLlPayType;
    @BindView(R.id.at_myOrderDetail_ll_createTime)
    LinearLayout atMyOrderDetailLlCreateTime;
    @BindView(R.id.at_myOrderDetail_ll_payTime)
    LinearLayout atMyOrderDetailLlPayTime;
    @BindView(R.id.at_myOrderDetail_ll_send)
    LinearLayout atMyOrderDetailLlSend;
    @BindView(R.id.at_myOrderDetail_ll_over)
    LinearLayout atMyOrderDetailLlOver;

    private String order_state = "";
    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private String order_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_detail);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "订单详情");

        order_state = getIntent().getStringExtra("order_state");


        getMyStoreOrderDetail();
        getAlsoLike();

    }

    private void getMyStoreOrderDetail() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
        order_id = getIntent().getStringExtra("order_id");
        SetSubscribe.myOrderDetail(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyOrderDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void getAlsoLike() {
        MainSubscribe.alsoLike("1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AlsoLikeResponseBean bean = GsonUtils.fromJson(result, AlsoLikeResponseBean.class);
                final ArrayList<AlsoLikeResponseBean.DataBean> data = bean.getData();
                GridLayoutManager layoutManager = new GridLayoutManager(MyStoreOrderDetailActivity.this, 2);
                acMyOrderDetailRvCai.setLayoutManager(layoutManager);
                AlsoLikeAdatper adatper = new AlsoLikeAdatper(MyStoreOrderDetailActivity.this, data);
                //设置添加或删除item时的动画，这里使用默认动画
                acMyOrderDetailRvCai.setItemAnimator(new DefaultItemAnimator());
                //设置适配器
                acMyOrderDetailRvCai.setItemAnimator(new DefaultItemAnimator());
                acMyOrderDetailRvCai.setAdapter(adatper);
                adatper.setOnItemClickListener(new AlsoLikeAdatper.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(MyStoreOrderDetailActivity.this, GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
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

    private void setMyOrderDetail(String result) {
        MyOrderDetailResponseBean bean = GsonUtils.fromJson(result, MyOrderDetailResponseBean.class);
        MyOrderDetailResponseBean.DataBean data = bean.getData();
        acMyOrderDetailTvUserName.setText(data.getConsignee());
        acMyOrderDetailTvCellPhone.setText(data.getMobile());
        acMyOrderDetailTvAddress.setText(data.getAddress());
        atMyOrderDetailTvStoreName.setText(data.getStore_name());

        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(atMyOrderDetailIvGoodsImg);
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(atMyOrderDetailIvGoodsImg1);
        atMyOrderDetailTvGoodsName.setText(data.getGoods_name());
        atMyOrderDetailTvGoodsName1.setText(data.getGoods_name());
        atMyOrderDetailTvGoodsPrice.setText("￥ " + data.getGoods_price());
        atMyOrderDetailTvGoodsPrice1.setText("￥ " + data.getGoods_price());
        atMyOrderDetailTvGoodsNum.setText("x " + data.getGoods_num());
        atMyOrderDetailTvGoodsNum1.setText("x " + data.getGoods_num());
        atMyOrderDetailTvIsFreeShipping.setText("￥ " + data.getShipping_fee());

        atMyOrderDetailTvOrderSn.setText(data.getOrder_sn());
        atMyOrderDetailTvCreateTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data.getOrder_time()*1000)));
        atMyOrderDetailTvPayTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data.getOrder_time()*1000)));

        switch (order_state) {
            case "1":
                acMyOrderDetailTvStatus.setText("待付款");
                acMyOrderDetailTvHint.setText("剩余24时00分自动关闭");
                acMyOrderDetailTvOption.setText("待付款");
                break;
            case "2":
                //头部
                acMyOrderDetailTvStatus.setText("等待卖家发货");
                acMyOrderDetailTvHint.setVisibility(View.GONE);//头部类型下方的文字提示

                //商品信息
                acMyOrderDetailTvOption.setText("退换");

                //订单
                atMyOrderDetailLlPayType.setVisibility(View.VISIBLE);//支付方式显示
                atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);//支付时间显示

                //底部
                acMyOrderDetailTvApplyRefund.setVisibility(View.VISIBLE);//申请退换显示
                acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                break;
            case "3":
                acMyOrderDetailTvStatus.setText("已发货");
                acMyOrderDetailTvHint.setText("剩余9天4时自动确认");
                acMyOrderDetailTvOption.setText("退换");
                atMyOrderDetailLlPayType.setVisibility(View.VISIBLE);
                atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                atMyOrderDetailLlSend.setVisibility(View.VISIBLE);

                acMyOrderDetailTvWuliu.setVisibility(View.VISIBLE);//查看物流
                acMyOrderDetailTvDelay.setVisibility(View.VISIBLE);//延长收货
                acMyOrderDetailTvConfirm.setVisibility(View.VISIBLE);//确认收货
                acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                break;
            case "4":
                acMyOrderDetailTvStatus.setText("交易成功");
                acMyOrderDetailTvHint.setText("期待再次为您服务");
                acMyOrderDetailTvOption.setText("申请售后");
                atMyOrderDetailLlPayType.setVisibility(View.VISIBLE);
                atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                atMyOrderDetailLlSend.setVisibility(View.VISIBLE);
                atMyOrderDetailLlOver.setVisibility(View.VISIBLE);

                acMyOrderDetailTvWuliu.setVisibility(View.VISIBLE);//查看物流
                acMyOrderDetailTvDelete.setVisibility(View.VISIBLE);//删除订单
                acMyOrderDetailTvComment.setVisibility(View.VISIBLE);//评价
                acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                break;
            case "6":
                acMyOrderDetailLlPaySend.setVisibility(View.GONE);
                acMyOrderDetailLlRefund.setVisibility(View.VISIBLE);
                acMyOrderDetailLlRefundNum.setVisibility(View.VISIBLE);
                acMyOrderDetailTvStatus.setText("已提交退款申请，等待卖家处理");
                acMyOrderDetailTvHint.setText("剩余2天4时自动确认");
                atMyOrderDetailLlPayType.setVisibility(View.VISIBLE);
                atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                atMyOrderDetailLlSend.setVisibility(View.VISIBLE);
                atMyOrderDetailLlOver.setVisibility(View.VISIBLE);

                acMyOrderDetailTvChat.setVisibility(View.VISIBLE);//联系卖家
                acMyOrderDetailTvCall.setVisibility(View.VISIBLE);//拨打电话
                acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                break;
        }
    }

    @OnClick({R.id.ac_myOrderDetail_tv_cancel, R.id.ac_myOrderDetail_tv_pay, R.id.ac_myOrderDetail_tv_applyRefund, R.id.ac_myOrderDetail_tv_wuliu, R.id.ac_myOrderDetail_tv_delay, R.id.ac_myOrderDetail_tv_confirm, R.id.ac_myOrderDetail_tv_delete, R.id.ac_myOrderDetail_tv_comment, R.id.ac_myOrderDetail_tv_chat, R.id.ac_myOrderDetail_tv_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_myOrderDetail_tv_cancel:
                SetSubscribe.cancelOrder(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.ac_myOrderDetail_tv_pay:
                Intent intent = new Intent(MyStoreOrderDetailActivity.this, ConfirmMyOrderActivity.class);
                intent.putExtra("order_id", order_id);
                startActivity(intent);
                break;
            case R.id.ac_myOrderDetail_tv_applyRefund:
                Intent intent3 = new Intent(MyStoreOrderDetailActivity.this, ApplyRefundActivity.class);
                intent3.putExtra("order_id", order_id);
                startActivity(intent3);
                break;
            case R.id.ac_myOrderDetail_tv_wuliu:
                Intent intent1 = new Intent(MyStoreOrderDetailActivity.this, KuaiDiDetailActivity.class);
                intent1.putExtra("order_id", order_id);
                startActivity(intent1);
                break;
            case R.id.ac_myOrderDetail_tv_delay:
                break;
            case R.id.ac_myOrderDetail_tv_confirm:
                SetSubscribe.receiveConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.ac_myOrderDetail_tv_delete:
                SetSubscribe.delOrder(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        showToast(errorMsg);
                    }
                }));
                break;
            case R.id.ac_myOrderDetail_tv_comment:
                Intent intent2 = new Intent(MyStoreOrderDetailActivity.this, CommentActivity.class);
                intent2.putExtra("order_id", order_id);
                startActivity(intent2);
                break;
            case R.id.ac_myOrderDetail_tv_chat:
                break;
            case R.id.ac_myOrderDetail_tv_call:

                break;
        }
    }
}
