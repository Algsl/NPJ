package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AlsoLikeAdatper;
import com.zthx.npj.adapter.GradViewAdapter;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AlsoLikeResponseBean;
import com.zthx.npj.net.been.KuaiDiResponseBean;
import com.zthx.npj.net.been.MySupplyOrderDetailResponseBean;
import com.zthx.npj.net.been.MySupplyOrderFahuoBean;
import com.zthx.npj.net.been.MySupplyOrderRefund3ResponseBean;
import com.zthx.npj.net.been.MySupplyOrderRefuseRefundBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.activity.ChatActivity;
import com.zthx.npj.tencent.util.Constants;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.TimeTextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySupplyOrderDetailActivity extends ActivityBase {
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
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.ac_myOrderDetail_tv_status)
    TextView acMyOrderDetailTvStatus;
    /*@BindView(R.id.ac_myOrderDetail_tv_hint)
    TextView acMyOrderDetailTvHint;*/
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
    @BindView(R.id.ac_myOrderDetail_ll_paySend)
    LinearLayout acMyOrderDetailLlPaySend;
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
    @BindView(R.id.ac_myOrderDetail_ll_refund)
    LinearLayout acMyOrderDetailLlRefund;
    @BindView(R.id.at_myOrderDetail_tv_send)
    TextView atMyOrderDetailTvSend;
    @BindView(R.id.at_myOrderDetail_tv_over)
    TextView atMyOrderDetailTvOver;
    @BindView(R.id.at_myOrderDetail_tv_refundNum)
    TextView atMyOrderDetailTvRefundNum;
    @BindView(R.id.ac_myOrderDetail_ll_refundNum)
    LinearLayout acMyOrderDetailLlRefundNum;
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
    @BindView(R.id.ac_myOrderDetail_ll)
    LinearLayout acMyOrderDetailLl;
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
    @BindView(R.id.ac_myOrderDetail_rv_cai)
    RecyclerView acMyOrderDetailRvCai;
    @BindView(R.id.ac_orderDetail_tv_size)
    TextView acOrderDetailTvSize;
    @BindView(R.id.seeMore)
    TextView seeMore;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_myOrderDetail_tv_hint1)
    TextView acMyOrderDetailTvHint1;
    @BindView(R.id.ac_myOrderDetail_tv_hint2)
    TimeTextView acMyOrderDetailTvHint2;
    @BindView(R.id.ac_myOrderDetail_tv_hint3)
    TextView acMyOrderDetailTvHint3;
    @BindView(R.id.ac_myOrderDetail_ll_refundHint)
    LinearLayout acMyOrderDetailLlRefundHint;

    private String order_id = "";
    private String order_state = "";
    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    private int position = 0;

    private int page = 1;
    private AlsoLikeAdatper adatper;
    private String type;

    String storeGoodsExExpressId = "1";
    String[] item;
    private String goods_name;

    private String nick_name = "";
    private String mobile;

    private long pay_time;//支付时间
    private long refund_time;//退款时间
    private long confirm_time;//确认收货时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_detail);
        ButterKnife.bind(this);

        order_state = getIntent().getStringExtra("order_state");
        order_id = getIntent().getStringExtra("order_id");
        type = getIntent().getStringExtra("type");


        acOrderDetailTvSize.setVisibility(View.INVISIBLE);


        back(titleThemeBack);
        titleThemeBack.setImageResource(R.drawable.goods_detial_back);
        changeTitle(titleThemeTitle, "订单详情");
        changeRightImg(titleThemeImgRight, R.drawable.goods_detail_home, null, null);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if (adatper != null) {
                    adatper.clearData();
                }
                seeMore.setText("查看更多");
                refreshLayout.setLoadmoreFinished(false);
                getMyStoreOrderDetail();
                getAlsoLike();

                refreshlayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                getAlsoLike();
                refreshlayout.finishLoadmore();
            }
        });

        getKuaiDiList();
        getAlsoLike();
        getMyStoreOrderDetail();

    }

    private void getAlsoLike() {
        MainSubscribe.alsoLike(page + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AlsoLikeResponseBean bean = GsonUtils.fromJson(result, AlsoLikeResponseBean.class);
                final ArrayList<AlsoLikeResponseBean.DataBean> data = bean.getData();
                GridLayoutManager layoutManager = new GridLayoutManager(MySupplyOrderDetailActivity.this, 2);
                acMyOrderDetailRvCai.setLayoutManager(layoutManager);
                if (adatper == null) {
                    adatper = new AlsoLikeAdatper(MySupplyOrderDetailActivity.this, data);
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
                acMyOrderDetailRvCai.setItemAnimator(new DefaultItemAnimator());
                //设置适配器
                acMyOrderDetailRvCai.setItemAnimator(new DefaultItemAnimator());
                acMyOrderDetailRvCai.setAdapter(adatper);
                adatper.setOnItemClickListener(new AlsoLikeAdatper.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<AlsoLikeResponseBean.DataBean> mList) {
                        Intent intent = new Intent(MySupplyOrderDetailActivity.this, GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, mList.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void getMyStoreOrderDetail() {
        SetSubscribe.mySupplyOrderDetail(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        MySupplyOrderDetailResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderDetailResponseBean.class);
        final MySupplyOrderDetailResponseBean.DataBean data = bean.getData();

        acMyOrderDetailTvUserName.setText(data.getConsignee());
        acMyOrderDetailTvCellPhone.setText(data.getMobile());
        acMyOrderDetailTvAddress.setText(data.getAddress());
        atMyOrderDetailIvGoodsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySupplyOrderDetailActivity.this, SupplyProductsActivity.class);
                intent.setAction(Const.SUPPLY_DETAIL);
                intent.putExtra("goods_id", data.getGoods_id());
                startActivity(intent);
            }
        });
        atMyOrderDetailIvGoodsImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySupplyOrderDetailActivity.this, SupplyProductsActivity.class);
                intent.setAction(Const.SUPPLY_DETAIL);
                intent.putExtra("goods_id", data.getGoods_id());
                startActivity(intent);
            }
        });



        goods_name = data.getGoods_name();
        if (data.getNick_name().substring(0,2).equals("用户")) {
            atMyOrderDetailTvStoreName.setText("农品街用户");
        } else {
            atMyOrderDetailTvStoreName.setText(data.getNick_name());
        }

        atMyOrderDetailTvStoreName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(UserMsgActivity.class,data.getSeller_id());
            }
        });

        pay_time = data.getOrder_time() + 24 * 60 * 60 - System.currentTimeMillis() / 1000;
        confirm_time = data.getOrder_time() + 14 * 24 * 60 * 60 - System.currentTimeMillis() / 1000;
        refund_time = data.getUpdate_time() + 3 * 24 * 60 * 60 - System.currentTimeMillis() / 1000;


        mobile = data.getMobile();


        Glide.with(this).load(Uri.parse("http://app.npj-vip.com" + data.getGoods_img().get(0))).into(atMyOrderDetailIvGoodsImg);
        Glide.with(this).load(Uri.parse("http://app.npj-vip.com" + data.getGoods_img().get(0))).into(atMyOrderDetailIvGoodsImg1);
        atMyOrderDetailTvGoodsName.setText(data.getGoods_name());
        atMyOrderDetailTvGoodsName1.setText(data.getGoods_name());
        atMyOrderDetailTvGoodsPrice.setText("￥ " + data.getGoods_price());
        atMyOrderDetailTvGoodsPrice1.setText("￥ " + data.getGoods_price());
        atMyOrderDetailTvGoodsNum.setText("x " + data.getOrder_num());
        atMyOrderDetailTvGoodsNum1.setText("x " + data.getOrder_num());
        atMyOrderDetailTvIsFreeShipping.setText("￥ " + data.getShipping_fee());

        //acMyOrderDetailTvAllPrice.setText("￥" + data.getGoods_price());
        acMyOrderDetailTvAllPrice.setText("￥" + new DecimalFormat("#0.00").format(Double.parseDouble(data.getOrder_price()) - Double.parseDouble(data.getShipping_fee())));
        atMyOrderDetailTvIsFreeShipping.setText("￥" + data.getShipping_fee());
        acMyOrderDetailTvNeedPay.setText("￥" + data.getOrder_price());

        atMyOrderDetailTvOrderSn.setText(data.getOrder_sn());
        atMyOrderDetailTvCreateTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getOrder_time() * 1000)));
        atMyOrderDetailTvPayTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getOrder_time() * 1000)));
        if(data.getFahuo_time()!=0){
            atMyOrderDetailTvSend.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getFahuo_time() * 1000)));
        }
        acMyOrderDetailIvRefund.setText(data.getRefund_price());

        if (type.equals("buss")) {
            acMyOrderDetailLl.setVisibility(View.GONE);
            switch (order_state) {
                case "1":
                    acMyOrderDetailTvStatus.setText("待付款");
                    //acMyOrderDetailTvHint.setText("剩余24时00分自动关闭");
                    if (pay_time >= 0) {
                        setTime(acMyOrderDetailTvHint2, pay_time);
                    }
                    acMyOrderDetailTvHint3.setText("交易自动关闭");
                    acMyOrderDetailTvOption.setText("待付款");
                    break;
                case "2":
                    //头部
                    acMyOrderDetailTvStatus.setText("等待发货");
                    //acMyOrderDetailTvHint.setVisibility(View.GONE);//头部类型下方的文字提示
                    acMyOrderDetailTvHint1.setVisibility(View.GONE);
                    acMyOrderDetailTvHint2.setVisibility(View.GONE);
                    acMyOrderDetailTvHint3.setVisibility(View.GONE);

                    //商品信息
                    acMyOrderDetailTvOption.setText("退换");
                    acMyOrderDetailTvPay.setText("发货");

                    //订单
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);//支付方式显示
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);//支付时间显示

                    //底部
                    acMyOrderDetailTvApplyRefund.setVisibility(View.GONE);//申请退换显示
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    acMyOrderDetailTvPay.setVisibility(View.VISIBLE);//付款按钮隐藏
                    break;
                case "3":
                    acMyOrderDetailTvStatus.setText("已发货");
                    //acMyOrderDetailTvHint.setText("剩余9天4时自动确认");
                    if (confirm_time >= 0) {
                        setTime(acMyOrderDetailTvHint2, confirm_time);
                    }
                    acMyOrderDetailTvHint3.setText("自动确认收货");
                    acMyOrderDetailTvOption.setText("退换");
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlSend.setVisibility(View.VISIBLE);

                    acMyOrderDetailTvWuliu.setVisibility(View.GONE);//查看物流
                    acMyOrderDetailTvDelay.setVisibility(View.GONE);//延长收货
                    acMyOrderDetailTvConfirm.setVisibility(View.GONE);//确认收货
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "4":
                    acMyOrderDetailTvStatus.setText("交易成功");
                    acMyOrderDetailTvHint1.setText("期待再次为您服务");
                    acMyOrderDetailTvHint2.setVisibility(View.GONE);
                    acMyOrderDetailTvHint3.setVisibility(View.GONE);
                    acMyOrderDetailTvOption.setText("申请售后");
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlSend.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlOver.setVisibility(View.GONE);

                    acMyOrderDetailTvWuliu.setVisibility(View.GONE);//查看物流
                    acMyOrderDetailTvDelete.setVisibility(View.GONE);//删除订单
                    acMyOrderDetailTvComment.setVisibility(View.GONE);//评价
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "5":
                    acMyOrderDetailTvWuliu.setVisibility(View.GONE);//查看物流
                    acMyOrderDetailTvDelete.setVisibility(View.GONE);//删除订单
                    acMyOrderDetailTvComment.setVisibility(View.GONE);//评价
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "6":
                    acMyOrderDetailLlPaySend.setVisibility(View.GONE);
                    acMyOrderDetailLlRefund.setVisibility(View.VISIBLE);
                    acMyOrderDetailLlRefundNum.setVisibility(View.GONE);
                    acMyOrderDetailLlRefundHint.setVisibility(View.GONE);
                    acMyOrderDetailTvStatus.setText("已提交退款申请，等待处理");
                    //acMyOrderDetailTvHint.setText("剩余2天4时自动确认");
                    if (refund_time >= 0) {
                        setTime(acMyOrderDetailTvHint2, refund_time);
                    }
                    acMyOrderDetailTvHint3.setText("自动退款");
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlSend.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlOver.setVisibility(View.GONE);

                    acMyOrderDetailTvPay.setText("退款");

                    acMyOrderDetailTvChat.setVisibility(View.GONE);//联系卖家
                    acMyOrderDetailTvCall.setVisibility(View.GONE);//拨打电话
                    acMyOrderDetailTvPay.setVisibility(View.VISIBLE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "7":
                    acMyOrderDetailTvStatus.setText("已退款");
                    //acMyOrderDetailTvHint.setVisibility(View.GONE);//头部类型下方的文字提示
                    acMyOrderDetailTvHint1.setVisibility(View.GONE);
                    acMyOrderDetailTvHint2.setVisibility(View.GONE);
                    acMyOrderDetailTvHint3.setVisibility(View.GONE);


                    acMyOrderDetailTvChat.setVisibility(View.GONE);//联系卖家
                    acMyOrderDetailTvCall.setVisibility(View.GONE);//拨打电话
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "8":
                    acMyOrderDetailTvStatus.setText("已取消退款");
                    //acMyOrderDetailTvHint.setVisibility(View.GONE);//头部类型下方的文字提示
                    acMyOrderDetailTvHint1.setVisibility(View.GONE);
                    acMyOrderDetailTvHint2.setVisibility(View.GONE);
                    acMyOrderDetailTvHint3.setVisibility(View.GONE);

                    acMyOrderDetailTvChat.setVisibility(View.GONE);//联系卖家
                    acMyOrderDetailTvCall.setVisibility(View.GONE);//拨打电话
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
            }
        } else {
            switch (order_state) {
                case "1":
                    acMyOrderDetailTvStatus.setText("待付款");
                    if (pay_time >= 0) {
                        setTime(acMyOrderDetailTvHint2, pay_time);
                    }
                    acMyOrderDetailTvHint3.setText("交易自动关闭");
                    acMyOrderDetailTvOption.setText("待付款");
                    break;
                case "2":
                    //头部
                    acMyOrderDetailTvStatus.setText("等待卖家发货");
                    acMyOrderDetailTvHint1.setVisibility(View.GONE);
                    acMyOrderDetailTvHint2.setVisibility(View.GONE);
                    acMyOrderDetailTvHint3.setVisibility(View.GONE);

                    //商品信息
                    acMyOrderDetailTvOption.setText("退换");

                    //订单
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);//支付方式显示
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);//支付时间显示

                    //底部
                    acMyOrderDetailTvApplyRefund.setVisibility(View.VISIBLE);//申请退换显示
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "3":
                    acMyOrderDetailTvStatus.setText("已发货");
                    //acMyOrderDetailTvHint.setText("剩余9天4时自动确认");
                    if (confirm_time >= 0) {
                        setTime(acMyOrderDetailTvHint2, confirm_time);
                    }
                    acMyOrderDetailTvHint3.setText("自动确认收货");
                    acMyOrderDetailTvOption.setText("退换");
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlSend.setVisibility(View.VISIBLE);

                    acMyOrderDetailTvWuliu.setVisibility(View.VISIBLE);//查看物流
                    acMyOrderDetailTvDelay.setVisibility(View.GONE);//延长收货
                    acMyOrderDetailTvConfirm.setVisibility(View.VISIBLE);//确认收货
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "4":
                    acMyOrderDetailTvStatus.setText("交易成功");
                    acMyOrderDetailTvHint1.setText("期待再次为您服务");
                    acMyOrderDetailTvHint2.setVisibility(View.GONE);
                    acMyOrderDetailTvHint3.setVisibility(View.GONE);
                    acMyOrderDetailTvOption.setText("申请售后");
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlSend.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlOver.setVisibility(View.GONE);

                    acMyOrderDetailTvWuliu.setVisibility(View.GONE);//查看物流
                    acMyOrderDetailTvDelete.setVisibility(View.GONE);//删除订单
                    acMyOrderDetailTvComment.setVisibility(View.VISIBLE);//评价
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "5":
                    acMyOrderDetailTvApplyRefund.setVisibility(View.VISIBLE);//申请退换显示
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
                case "6":
                    acMyOrderDetailLlPaySend.setVisibility(View.GONE);
                    acMyOrderDetailLlRefund.setVisibility(View.VISIBLE);
                    acMyOrderDetailLlRefundNum.setVisibility(View.GONE);
                    acMyOrderDetailLlRefundHint.setVisibility(View.VISIBLE);
                    acMyOrderDetailTvStatus.setText("已提交退款申请，等待卖家处理");
                    //acMyOrderDetailTvHint.setText("剩余2天4时自动确认");
                    if (refund_time >= 0) {
                        setTime(acMyOrderDetailTvHint2, refund_time);
                    }
                    acMyOrderDetailTvHint3.setText("自动退款");
                    atMyOrderDetailLlPayType.setVisibility(View.GONE);
                    atMyOrderDetailLlPayTime.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlSend.setVisibility(View.VISIBLE);
                    atMyOrderDetailLlOver.setVisibility(View.GONE);

                    acMyOrderDetailTvChat.setVisibility(View.VISIBLE);//联系卖家
                    acMyOrderDetailTvCall.setVisibility(View.VISIBLE);//拨打电话
                    acMyOrderDetailTvPay.setVisibility(View.GONE);//付款按钮隐藏
                    acMyOrderDetailTvCancel.setVisibility(View.GONE);//取消按钮隐藏
                    break;
            }
        }
    }

    @OnClick({R.id.ac_myOrderDetail_tv_cancel, R.id.ac_myOrderDetail_tv_pay, R.id.ac_myOrderDetail_tv_applyRefund,
            R.id.ac_myOrderDetail_tv_wuliu, R.id.ac_myOrderDetail_tv_delay, R.id.ac_myOrderDetail_tv_confirm,
            R.id.ac_myOrderDetail_tv_delete, R.id.ac_myOrderDetail_tv_comment, R.id.ac_myOrderDetail_tv_chat,
            R.id.ac_myOrderDetail_tv_call, R.id.ac_myOrderDetail_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_myOrderDetail_tv_cancel://取消订单
                SetSubscribe.mySupplyOrderCancel(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
            case R.id.ac_myOrderDetail_tv_pay://支付订单
                if (type.equals("buss")) {
                    if (order_state.equals("2")) {
                        showPublishPopwindow(order_id);
                    } else if (order_state.equals(6)) {
                        SetSubscribe.mySupplyOrderRefund3(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                MySupplyOrderRefund3ResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderRefund3ResponseBean.class);
                                MySupplyOrderRefund3ResponseBean.DataBean data = bean.getData();
                                showRefundPopwindow(order_id, data, goods_name);
                            }

                            @Override
                            public void onFault(String errorMsg) {

                            }
                        }));
                    }
                } else {
                    Intent intent = new Intent(MySupplyOrderDetailActivity.this, ConfirmMySupplyOrderActivity.class);
                    intent.putExtra("order_id", order_id);
                    startActivity(intent);
                }
                break;
            case R.id.ac_myOrderDetail_tv_applyRefund://申请退款
                Intent intent3 = new Intent(MySupplyOrderDetailActivity.this, MySupplyOrderRefundActivity.class);
                intent3.putExtra("order_id", order_id);
                startActivity(intent3);
                break;
            case R.id.ac_myOrderDetail_tv_wuliu://查看物流
                Intent intent1 = new Intent(MySupplyOrderDetailActivity.this, KuaiDiDetailActivity.class);
                intent1.putExtra("order_id", order_id);
                intent1.putExtra("type", "supply");
                startActivity(intent1);
                break;
            case R.id.ac_myOrderDetail_tv_delay:
                break;
            case R.id.ac_myOrderDetail_tv_confirm:
                //showPublishPopwindow();
                SetSubscribe.mySupplyGoodsConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("确认收货成功");
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
            case R.id.ac_myOrderDetail_tv_delete:
                SetSubscribe.mySupplyOrderDel(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
            case R.id.ac_myOrderDetail_tv_comment:
                Intent intent2 = new Intent(MySupplyOrderDetailActivity.this, MySupplyOrderCommentActivity.class);
                intent2.putExtra("order_id", order_id);
                startActivity(intent2);
                break;
            case R.id.ac_myOrderDetail_tv_chat:
                ChatInfo chatInfo = new ChatInfo();
                chatInfo.setType(TIMConversationType.C2C);
                chatInfo.setId(mobile);
                chatInfo.setChatName(nick_name);
                Intent intent = new Intent(BaseApp.getApp(), ChatActivity.class);
                intent.putExtra(Constants.CHAT_INFO, chatInfo);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApp.getApp().startActivity(intent);
                break;
            case R.id.ac_myOrderDetail_tv_call:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile)));
                break;
            case R.id.ac_myOrderDetail_ll:
                if (order_state.equals("2") || order_state.equals("3")) {
                    Intent intentll = new Intent(MySupplyOrderDetailActivity.this, MySupplyOrderRefundActivity.class);
                    intentll.putExtra("order_id", order_id);
                    startActivity(intentll);
                }
                break;
        }
    }

    public void showPublishPopwindow() {
        position = 0;
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_mima, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT,
                true);
        // 设置PopupWindow的背景
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        GridView gv = contentView.findViewById(R.id.pw_mima_gv);
        final String[] strs = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "确定", "0", "删除"};
        GradViewAdapter adapter = new GradViewAdapter(this, strs);
        gv.setAdapter(adapter);

        TextView tv1 = contentView.findViewById(R.id.tv1);
        TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv3 = contentView.findViewById(R.id.tv3);
        TextView tv4 = contentView.findViewById(R.id.tv4);
        TextView tv5 = contentView.findViewById(R.id.tv5);
        TextView tv6 = contentView.findViewById(R.id.tv6);
        final TextView[] tvs = new TextView[]{tv1, tv2, tv3, tv4, tv5, tv6};

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < 11 && i != 9) {
                    if (position < 6) {
                        tvs[position].setText(strs[i]);
                        position += 1;
                    }
                } else if (i == 11) {
                    if (position > 0) {
                        position--;
                        tvs[position].setText("");
                    }
                }
                //空按钮
                if (i == 9) {
                    String password = "";
                    for (int j = 0; j < 6; j++) {
                        password += tvs[j].getText().toString().trim();
                    }
                    if (password.equals("123456")) {
                        SetSubscribe.mySupplyGoodsConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                showToast("确认收货成功");
                                backgroundAlpha(1f);
                                window.dismiss();
                            }

                            @Override
                            public void onFault(String errorMsg) {

                            }
                        }));
                    } else {
                        showToast("密码不正确");
                        position = 0;
                        for (int j = 0; j < 6; j++) {
                            tvs[j].setText("");
                        }
                    }
                }

            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private void getKuaiDiList() {
        SetSubscribe.getKuaiDiList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setKuaiDiList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setKuaiDiList(String result) {
        KuaiDiResponseBean bean = GsonUtils.fromJson(result, KuaiDiResponseBean.class);
        ArrayList<KuaiDiResponseBean.DataBean> data = bean.getData();
        item = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            item[i] = data.get(i).getExpress_name();
        }
    }


    public void showPublishPopwindow(final String order_id) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT,
                true);
        // 设置PopupWindow的背景
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        final EditText storeGoodsEtExpressNumber = contentView.findViewById(R.id.pw_storeGoods_et_expressNumber);
        Spinner spinner = contentView.findViewById(R.id.pw_storeGoods_s_expressName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                storeGoodsExExpressId = (i + 1) + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button commit = contentView.findViewById(R.id.pw_storeGoods_btn_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String express_number = storeGoodsEtExpressNumber.getText().toString().trim();
                MySupplyOrderFahuoBean bean = new MySupplyOrderFahuoBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(order_id);
                bean.setExpress_id(storeGoodsExExpressId);
                bean.setExpress_number(express_number);
                SetSubscribe.mySupplyOrderFahuo(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("发货成功");
                        finish();
                        backgroundAlpha(1f);
                        window.dismiss();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }


    public void showRefundPopwindow(final String order_id, MySupplyOrderRefund3ResponseBean.DataBean data, String goods_name) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill_refund, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_500));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_320));
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        TextView goodsName = contentView.findViewById(R.id.pw_storeGoods_tv_goodsName);
        TextView goodsPrice = contentView.findViewById(R.id.pw_storeGoods_tv_goodsPrice);
        TextView goodsState = contentView.findViewById(R.id.pw_storeGoods_tv_goodsState);
        TextView refundReason = contentView.findViewById(R.id.pw_storeGoods_tv_refundReason);
        TextView refundDesc = contentView.findViewById(R.id.pw_storeGoods_tv_refundDesc);
        ImageView goodsImg = contentView.findViewById(R.id.pw_storeGoods_iv_goodsImg);
        goodsName.setText(goods_name);
        goodsPrice.setText("￥" + data.getRefund_price());
        switch ((int) data.getRefund_state()) {
            case 0:
                goodsState.setText("已收货");
                break;
            case 1:
                goodsState.setText("未收货");
                break;
        }
        refundReason.setText(data.getRefund_reason());
        refundDesc.setText(data.getRefund_desc() == null ? "无" : data.getRefund_desc());
        Glide.with(this).load(Uri.parse("http://app.npj-vip.com" + data.getRefund_img())).into(goodsImg);
        TextView refund = contentView.findViewById(R.id.pw_storeGoods_btn_refund);
        TextView refuse = contentView.findViewById(R.id.pw_storeGoods_btn_refuse);
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSubscribe.mySupplyOrderRefund2(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("退款成功");
                        backgroundAlpha(1f);
                        window.dismiss();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                backgroundAlpha(1f);
                showRefusePopwindow(order_id);
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    //拒绝退款弹窗
    public void showRefusePopwindow(final String order_id) {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_goods_bill_refuse, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_500));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_320));
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        final EditText etReason = contentView.findViewById(R.id.pw_storeGoodsBill_et_reason);
        TextView confirm = contentView.findViewById(R.id.pw_storeGoodsBill_tv_refuse);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySupplyOrderRefuseRefundBean bean = new MySupplyOrderRefuseRefundBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(order_id);
                bean.setJujue_yuanyin(etReason.getText().toString().trim());
                SetSubscribe.mySupplyOrderRefuseRefund(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("测试", "onSuccess: " + result);
                        showToast("拒绝退款成功");
                        backgroundAlpha(1f);
                        window.dismiss();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });

        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    public void setTime(TimeTextView ttv, long time) {
        long second = time % 60;//计算秒
        long min = time / 60 % 60;
        long hour = time / 3600 % 24;
        long day = time / 3600 / 24;
        ttv.setTimes(new long[]{hour, min, second, day});
        if (!ttv.isRun()) {
            ttv.run();
        }
    }
}
