package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CartStoreAdapter;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.net.been.AddressInfoResponseBean;
import com.zthx.npj.net.been.CartOrderOneBean;
import com.zthx.npj.net.been.CartOrderResponseBean;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.YsBuyOneResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.baidu.mapapi.BMapManager.getContext;

public class ShopingCartConfirmActivity extends ActivityBase {
    @BindView(R.id.ac_confirm_cartOrder_rv)
    RecyclerView acConfirmCartOrderRv;
    @BindView(R.id.at_confirm_order_iv_my_hongbao)
    ImageView atConfirmOrderIvMyHongbao;
    @BindView(R.id.at_confirm_order_rl_hongbao)
    RelativeLayout atConfirmOrderRlHongbao;
    @BindView(R.id.at_confirm_order_iv_my_col)
    ImageView atConfirmOrderIvMyCol;
    @BindView(R.id.ac_confirmCartOrder_payType1)
    ImageView acConfirmCartOrderPayType1;
    @BindView(R.id.at_confirm_order_iv_my_wechat)
    ImageView atConfirmOrderIvMyWechat;
    @BindView(R.id.ac_confirmCartOrder_payType2)
    ImageView acConfirmCartOrderPayType2;
    @BindView(R.id.at_confirm_order_iv_alipay)
    ImageView atConfirmOrderIvAlipay;
    @BindView(R.id.ac_confirmCartOrder_payType3)
    ImageView acConfirmCartOrderPayType3;
    @BindView(R.id.at_confirm_order_tv_jin)
    TextView atConfirmOrderTvJin;
    @BindView(R.id.at_confirmCartOrder_tv_orderPrice1)
    TextView atConfirmCartOrderTvOrderPrice1;
    @BindView(R.id.ac_confirmOrder_btn_pay)
    Button acConfirmOrderBtnPay;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_cartConfirm_tv_userInfo)
    TextView acCartConfirmTvUserInfo;
    @BindView(R.id.ac_cartConfirm_tv_address)
    TextView acCartConfirmTvAddress;
    @BindView(R.id.changeAddress)
    LinearLayout changeAddress;

    private YsBuyOneResponseBean.DataBean data1;

    private CartOrderResponseBean.DataBean data = new CartOrderResponseBean().getData();
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String cart_id = "";
    private String address_id = "";
    private String pay_code = "2";
    private HashMap<String, String> type = new HashMap<>();
    private HashMap<String, String> remark = new HashMap<>();
    private HashMap<String, String> ziti_id = new HashMap<>();
    private String RSA_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCx1Lq1TU+c8jDT\n" +
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
    public static IWXAPI api;
    private static final int SDK_PAY_FLAG = 1001;

    private static final String TAG = "测试";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cartorder);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx76500efa65d19915");

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "确认订单");

        String result = getIntent().getStringExtra("info");
        cart_id = getIntent().getStringExtra("cart_id");
        CartOrderResponseBean bean = GsonUtils.fromJson(result, CartOrderResponseBean.class);
        data = bean.getData();
        address_id = data.getAdd_id() + "";
        atConfirmCartOrderTvOrderPrice1.setText(SharePerferenceUtils.getTotlePrice());
        getAddress();
        setCartOrder();


    }

    private void setCartOrder() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acConfirmCartOrderRv.setLayoutManager(layoutManager);
        CartStoreAdapter adapter = new CartStoreAdapter(this, data.getList(), this);
        acConfirmCartOrderRv.setItemAnimator(new DefaultItemAnimator());
        acConfirmCartOrderRv.setAdapter(adapter);

        adapter.setOnItemClickListener(new CartStoreAdapter.ItemClickListener() {
            @Override
            public void onZitiClick(int position) {

            }

            @Override
            public void onPeisongClick(int position) {

            }

        });
    }

    //获取收货地址
    private void getAddress() {
        SetSubscribe.getAddressInfo(user_id, token, address_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AddressInfoResponseBean bean = GsonUtils.fromJson(result, AddressInfoResponseBean.class);
                AddressInfoResponseBean.DataBean data = bean.getData();
                acCartConfirmTvUserInfo.setText(data.getConsignee() + " " + data.getMobile());
                acCartConfirmTvAddress.setText(data.getAddress() + data.getHouse_number());
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    @OnClick({R.id.ac_confirmCartOrder_payType1, R.id.ac_confirmCartOrder_payType2, R.id.ac_confirmCartOrder_payType3, R.id.ac_confirmOrder_btn_pay,R.id.changeAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_confirmCartOrder_payType1:
                pay_code = "3";
                acConfirmCartOrderPayType1.setImageResource(R.drawable.confirm_select);
                acConfirmCartOrderPayType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType3.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_confirmCartOrder_payType2:
                pay_code = "2";
                acConfirmCartOrderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType2.setImageResource(R.drawable.confirm_select);
                acConfirmCartOrderPayType3.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_confirmCartOrder_payType3:
                pay_code = "1";
                acConfirmCartOrderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmCartOrderPayType3.setImageResource(R.drawable.confirm_select);
                break;
            case R.id.ac_confirmOrder_btn_pay:
                for (int i = 0; i < data.getList().size(); i++) {
                    String store_id = data.getList().get(i).get(0).getStore_id() + "";
                    String result = data.getList().get(i).get(0).getRemark() + "";
                    String ztId = data.getList().get(i).get(0).getZiti_id();
                    String goods_type = "1";
                    boolean flag = data.getList().get(i).get(0).isZiti();
                    if (!flag) {
                        goods_type = "1";
                        ztId = "";
                    } else {
                        goods_type = "2";
                    }
                    type.put(store_id, goods_type);
                    remark.put(store_id, result);
                    ziti_id.put(store_id, ztId);
                }

                CartOrderOneBean bean = new CartOrderOneBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setCart_id(cart_id);
                bean.setAddress_id(address_id);
                bean.setPay_code(pay_code);
                bean.setType(type);
                bean.setRemark(remark);
                bean.setZiti_id(ziti_id);
                SetSubscribe.cartOrderOne(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        YsBuyOneResponseBean bean = GsonUtils.fromJson(result, YsBuyOneResponseBean.class);
                        data1 = bean.getData();
                        switch (pay_code) {
                            case "1":
                                alipay();
                                break;
                            case "2":
                                wxpay();
                                break;
                            case "3":

                                break;
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(errorMsg);
                            int code = obj.getInt("code");
                            if(code==2){
                                openActivity(OrderFinishActivity.class,"http://game.npj-vip.com/apk/logo.png","购物车商品",SharePerferenceUtils.getTotlePrice(),"1");
                            }else{

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (obj == null) {
                            showToast("余额不足");
                        }
                    }
                }));
                break;
            case R.id.changeAddress:
                Intent intent=new Intent(this,AddressListActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }


    private void wxpay() {
        GiftSubscribe.pay("weixin", data1.getOrder_sn(), data1.getPay_money(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setWXResult(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setWXResult(String result) {
        PayResponse1Bean bean = GsonUtils.fromJson(result, PayResponse1Bean.class);
        PayResponse1Bean.DataBean data = bean.getData();
        PayReq req = new PayReq();
        req.appId = data.getAppid();//你的微信appid
        req.partnerId = data.getPartnerid();//商户号
        req.prepayId = data.getPrepayid();//预支付交易会话ID
        req.nonceStr = data.getNoncestr();//随机字符串
        req.timeStamp = data.getTimestamp();//时间戳
        req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
        req.sign = data.getSign();//签名
        //req.extData			= "app data";
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    private void alipay() {
        GiftSubscribe.pay("alipay", data1.getOrder_sn(), data1.getPay_money(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setPayResult(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setPayResult(String result) {
        PayResponseBean bean = GsonUtils.fromJson(result, PayResponseBean.class);
        PayResponseBean.DataBean data = bean.getData();
        alipay(data.getAlipay());
    }

    public void alipay(String str) {
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
                PayTask alipay = new PayTask(ShopingCartConfirmActivity.this);
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
                        Intent intent=new Intent(ShopingCartConfirmActivity.this,WXPayFinishActivity.class);
                        intent.putExtra("title","支付宝支付");
                        intent.putExtra("content","支付宝支付成功");
                        intent.putExtra("type","1");
                        startActivity(intent);
                        //Toast.makeText(ShopingCartConfirmActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ShopingCartConfirmActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            switch (resultCode){
                case 1:
                    address_id=data.getStringExtra("address_id");
                    acCartConfirmTvUserInfo.setText(data.getStringExtra("name")+" "+data.getStringExtra("mobile"));
                    acCartConfirmTvAddress.setText(data.getStringExtra("address"));
                    break;
                case 0:
                    String result = getIntent().getStringExtra("info");
                    cart_id = getIntent().getStringExtra("cart_id");
                    CartOrderResponseBean bean = GsonUtils.fromJson(result, CartOrderResponseBean.class);
                    CartOrderResponseBean.DataBean data1 = bean.getData();
                    address_id = data1.getAdd_id() + "";
                    atConfirmCartOrderTvOrderPrice1.setText(SharePerferenceUtils.getTotlePrice());
                    getAddress();
                    setCartOrder();
                    break;
            }
        }
    }
}
