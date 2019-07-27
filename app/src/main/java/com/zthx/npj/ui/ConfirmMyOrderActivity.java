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
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
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
    String pay_code = "2";
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
    private String RSA_PRIVATE ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCx1Lq1TU+c8jDT\n" +
            "NEU5up1siPOXKJBU0ypde7oPfm9gyy2ajgcw6v3KF2ryjot5AKlBED6qdQPRa5Sk\n" +
            "jIf8ZE1W+x8CVOvEC2m1lCglpm5zbAw2EGXdE4NNH6D0tcxIHza94RFkVilx1rjc\n" +
            "5hQ1OnwnLCDWN2UbOBl6jyom+iqUWSnFu7pEm5J8ZlNyr654LmDvCQXoPio28VSk\n" +
            "uedjQLdM+OkQZdidUYMaKsWmc6Xy6qmRqrgfUjvArjCZb0MKaNMq7bm7K9tr9dmJ\n" +
            "cj4X1WSm7txR81FkDDREEYiwFBoocm/G3wqUDIho0vT++kMlz0tnoPEx4q339eJL\n" +
            "t9pkdQvFAgMBAAECggEAUzoMZ+3W5M00rKQ6Adqk8rblykjhw9FQcpAFdFroJZTx\n" +
            "svPlya8xN/PdyceM3wTAMgM4UO6S6uA+oQRkYGtRBvRgfubfsNDmmGTOpVBPQRXA\n" +
            "YU0rX1xShzXWTrEG+nohVJyRVzQ8EVs9CaVkr8S/dlXgyGEEoMiQpBt8zuEmLGcG\n" +
            "MTK/OyIbHQKtjfRoXfL8xfW2bifFhg4fGlmgbO2DfiljpRwEY3mU9cSLH3oYWr8y\n" +
            "z3rSWlFCtV5v8syHNCZ+2+DTfcAMw1kPk/g16u/KPHA7duvnWLwGDm6Ktv14CMM2\n" +
            "mq9SpeFl3uicBKIcdK2I5k0lCJY/8aik/f1d0BNpwQKBgQDlz0keZqzY4itvNOpe\n" +
            "McJzzNyok6+rl42mDBJwb7QD+9X9ZtFhsYbdSgXWLbjILNpFKcZK9PGMuMXj8Cx7\n" +
            "C26W6zH/zQWsypLT940owzjNy0eckFH8BFm4UQHinc7GILbuUtAJNBBCdKKIdep6\n" +
            "/4zQl35v62wAa+ijy2Lox1fOzwKBgQDGGPXghnOMEm+vntbjay81cEZNReHLSzo9\n" +
            "rr/QGktC6SIaroYYpQgBsgScX/srDdi5wAy9pgHkywCxeZ2jzOset2O6NZCHAcFp\n" +
            "Wb4BrG9Gf0nWVP/DG0uqvEVBmgPEa6lZaIZH13jFFVH8P+Vwn26zza46lW0gD2Kn\n" +
            "z4ClplGBKwKBgHskvjuqLUjyuO+YXVYoN9ixmDRFH0dFqMOniGHzmXThB+QHqn89\n" +
            "D9WYitQgH/oz/qo9HmKgKqeLg48G7e7pS1NXqK04Aah7zH4FEwEay1+LZE5DD4uK\n" +
            "EUGxNt9mTJzifuPqQEwOOABEW6vf88wBEEXeSARVFMSNDlZm8BNobmcFAoGAMT4V\n" +
            "KLnjUSdoEezXF/MV6h+9qgm8Bg/uK1UcIzvWB4zySFWnycqEQf+he8m0ItCvVgUy\n" +
            "ZZY1lE0OIA/OKuCOdbU6mhgklBrQnEKNo9bcVlbf4OKCLVrEpW1lfdguJY5pq2r7\n" +
            "LjKWt88D8UNk4mkPWKzBKZjpZnXMnVBMd2Dvk78CgYB8DC/wQzY/0ibckmXnqE9e\n" +
            "hjBuRaG3964je78O5JaCEIVXUNX6nn5TMTK+uWrQNyqgXs92kw98Xi4ZSuER3zXk\n" +
            "Vmc1SOW4LjF98RAFdVMct8fP2u9xZ2zHV/SZ/ot0D1Bmz+P0dQL38+kSJ4w1N1rz\n" +
            "HCGInP32FNZD8bmcY+gFXw==";
    public static final String APPID = "2019062565701049";
    private String order_sn="";
    private String order_price="";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_myorder);
        ButterKnife.bind(this);

        api= WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
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
        order_price=data.getOrder_price();
        getMyOrderDetail(data.getOrder_id());
    }

    private void getMyOrderDetail(long order_id) {
        SetSubscribe.myOrderDetail(user_id,token,order_id+"",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        MyOrderDetailResponseBean bean=GsonUtils.fromJson(result,MyOrderDetailResponseBean.class);
        MyOrderDetailResponseBean.DataBean data=bean.getData();
        order_sn=data.getOrder_sn();
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
        GiftSubscribe.pay("weixin",order_sn,order_price,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setWXResult(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setWXResult(String result) {
        PayResponse1Bean bean=GsonUtils.fromJson(result,PayResponse1Bean.class);
        PayResponse1Bean.DataBean data=bean.getData();
        PayReq req = new PayReq();
        req.appId = data.getAppid();//你的微信appid
        req.partnerId = data.getPartnerid();//商户号
        req.prepayId = data.getPrepayid();//预支付交易会话ID
        req.nonceStr = data.getNoncestr();//随机字符串
        req.timeStamp = data.getTimestamp();//时间戳
        req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
        req.sign =data.getSign();//签名
        //req.extData			= "app data";
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
        Log.e("测试", "setWXResult: "+data.getAppid()+" "+data.getPartnerid()+" "+data.getPrepayid()
        +" "+data.getNoncestr()+" "+data.getTimestamp()+" "+data.getSign());
    }

    private void alipay() {
        GiftSubscribe.pay("alipay",order_sn,order_price,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setPayResult(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));

    }

    private void setPayResult(String result) {
        PayResponseBean bean=GsonUtils.fromJson(result,PayResponseBean.class);
        PayResponseBean.DataBean data=bean.getData();
        alipay(data.getAlipay());
    }

    public void alipay(String str){
        Log.e("测试", "alipay: "+str);
        boolean rsa = false;
        //构造支付订单参数列表
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
        //构造支付订单参数信息
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        //对支付参数信息进行签名
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
        //订单信息
        final String orderInfo = str;
        //异步处理
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
