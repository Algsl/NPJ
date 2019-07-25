package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.net.been.BuyBean;
import com.zthx.npj.net.been.ConfirmOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmMyOrderActivity extends ActivityBase {
    @BindView(R.id.at_confirm_order_rl_title)
    RelativeLayout atConfirmOrderRlTitle;
    @BindView(R.id.at_confirm_myorder_tv_address)
    TextView atConfirmMyorderTvAddress;
    @BindView(R.id.at_confirm_myorder_tv_storeName)
    TextView atConfirmMyorderTvStoreName;
    @BindView(R.id.at_confirm_myorder_iv_goodsImg)
    ImageView atConfirmMyorderIvGoodsImg;
    @BindView(R.id.at_confirm_myorder_tv_goodsName)
    TextView atConfirmMyorderTvGoodsName;
    @BindView(R.id.at_confirm_myorder_tv_goodsPrice)
    TextView atConfirmMyorderTvGoodsPrice;
    @BindView(R.id.at_confirm_order_tv_goods_num)
    TextView atConfirmOrderTvGoodsNum;
    @BindView(R.id.at_confirm_myorder_tv_ship)
    TextView atConfirmMyorderTvShip;
    @BindView(R.id.at_confirm_myorder_tv_orderPrice)
    TextView atConfirmMyorderTvOrderPrice;
    @BindView(R.id.ac_confirm_myorder_payType1)
    ImageView acConfirmMyorderPayType1;
    @BindView(R.id.ac_confirm_myorder_payType2)
    ImageView acConfirmMyorderPayType2;
    @BindView(R.id.ac_confirm_myorder_payType3)
    ImageView acConfirmMyorderPayType3;
    @BindView(R.id.at_confirm_myorder_tv_orderPrice1)
    TextView atConfirmMyorderTvOrderPrice1;
    @BindView(R.id.ac_confirmOrder_btn_pay)
    Button acConfirmOrderBtnPay;

    String user_id = SharePerferenceUtils.getUserId(this);
    String token = SharePerferenceUtils.getToken(this);
    String pay_code = "1";
    ConfirmOrderResponseBean.DataBean data;
    public static IWXAPI api;
    private static final int SDK_PAY_FLAG = 1001;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_confirm_order_iv_go)
    ImageView atConfirmOrderIvGo;
    @BindView(R.id.at_confirm_order_iv_my_hongbao)
    ImageView atConfirmOrderIvMyHongbao;
    @BindView(R.id.at_confirm_order_rl_hongbao)
    RelativeLayout atConfirmOrderRlHongbao;
    @BindView(R.id.at_confirm_order_iv_my_col)
    ImageView atConfirmOrderIvMyCol;
    @BindView(R.id.at_confirm_order_iv_my_wechat)
    ImageView atConfirmOrderIvMyWechat;
    @BindView(R.id.at_confirm_order_iv_alipay)
    ImageView atConfirmOrderIvAlipay;
    @BindView(R.id.at_confirm_order_tv_jin)
    TextView atConfirmOrderTvJin;
    private String RSA_PRIVATE = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAujZ/wdGmsb2jaOGx5+8IpSh/V73TMAGXuw6HrAOG5QgtDLXF6IbBaDRo0nqr61jxrJgUYgLR5FWs8JNzaKP86fiiabhSGFzYaY1Jc8sGc7if0VKBQukP98LqAvDK06ft48ZDddmLRD89rdN7L3auNn1Wz6TUv3P8WLEAfiVvqchDFvRHE/EYXcMyJVDc+63gJs9oxDjQrXpQ3jsavEPdUCQGWeqeLc89oZiqw/3t+GhvWLfvzOSUb4GrXWeAkvVI+eut7erFsLu1rKjjg0a30pkCKg6/mVA52PjSvnsY8Rdl2iEp5RNZAckSIJHegPCPwNfszlW42gztjf9DPlhv5wIDAQAB";
    public static final String APPID = "2019071065831181";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_myorder);
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(ConfirmMyOrderActivity.this, null);
        api.registerApp("wx76500efa65d19915");

        back(titleBack);
        titleBack.setImageResource(R.drawable.goods_detial_back);
        changeTitle(acTitle,"确认订单");
        changeRightImg(acTitleIv,R.drawable.goods_detail_home,null,null);

        getMyConfirmOrder();
    }

    private void getMyConfirmOrder() {
        String order_id = getIntent().getStringExtra("order_id");
        SetSubscribe.confirmOrder(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyConfirmOrder(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyConfirmOrder(String result) {
        ConfirmOrderResponseBean bean = GsonUtils.fromJson(result, ConfirmOrderResponseBean.class);
        data = bean.getData();
        atConfirmMyorderTvAddress.setText(data.getAddress());
        atConfirmMyorderTvStoreName.setText(data.getStore_name());
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(atConfirmMyorderIvGoodsImg);
        atConfirmMyorderTvGoodsName.setText(data.getGoods_name());
        atConfirmMyorderTvGoodsPrice.setText("￥ " + data.getGoods_price());
        atConfirmOrderTvGoodsNum.setText("x " + data.getGoods_num());
        atConfirmMyorderTvOrderPrice.setText(data.getOrder_price());
        atConfirmMyorderTvOrderPrice1.setText(data.getOrder_price());
    }

    @OnClick({R.id.ac_confirm_myorder_payType1, R.id.ac_confirm_myorder_payType2, R.id.ac_confirm_myorder_payType3, R.id.ac_confirmOrder_btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_confirm_myorder_payType1:
                acConfirmMyorderPayType1.setImageResource(R.drawable.confirm_select);
                acConfirmMyorderPayType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmMyorderPayType3.setImageResource(R.drawable.confirm_unselect);
                pay_code = "3";
                break;
            case R.id.ac_confirm_myorder_payType2:
                acConfirmMyorderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmMyorderPayType2.setImageResource(R.drawable.confirm_select);
                acConfirmMyorderPayType3.setImageResource(R.drawable.confirm_unselect);
                pay_code = "2";
                break;
            case R.id.ac_confirm_myorder_payType3:
                acConfirmMyorderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmMyorderPayType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmMyorderPayType3.setImageResource(R.drawable.confirm_select);
                pay_code = "1";
                break;
            case R.id.ac_confirmOrder_btn_pay:
                switch (pay_code) {
                    case "1"://支付宝支付
                        alipay();
                        break;
                    case "2"://微信支付
                        wxpay();
                        break;
                    case "3"://余额支付
                        yuepay();
                        break;
                }
                BuyBean bean = new BuyBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(getIntent().getStringExtra("order_id"));
                bean.setAddress_id(data.getAddress_id() + "");
                bean.setPay_code(pay_code);
                SetSubscribe.buy(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }

    private void yuepay() {

    }

    private void wxpay() {
        PayReq req = new PayReq();
        req.appId = "wx76500efa65d19915";//你的微信appid
        req.partnerId = "1512847301";//商户号
        req.prepayId = "wx0818255793822758eb1d33a80072025131";//预支付交易会话ID
        req.nonceStr = "o32sb3Y27jFiLyqHe07e7n5mvSa3ZfIL";//随机字符串
        req.timeStamp = "1557311094";//时间戳
        req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
        req.sign = "279B44E2D21B4B54F80FE62B3917F27A";//签名
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        ConfirmMyOrderActivity.api.sendReq(req);
    }

    private void alipay() {
        boolean rsa = false;
        //构造支付订单参数列表
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
        //构造支付订单参数信息
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        Log.e("测试", "alipay: " + orderParam);
        //对支付参数信息进行签名
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
        //订单信息
        final String orderInfo = orderParam + "&" + sign;
        //异步处理
        Toast.makeText(this, "支付宝支付", Toast.LENGTH_LONG).show();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                //新建任务
                PayTask alipay = new PayTask(ConfirmMyOrderActivity.this);
                //获取支付结果
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(ConfirmMyOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConfirmMyOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
