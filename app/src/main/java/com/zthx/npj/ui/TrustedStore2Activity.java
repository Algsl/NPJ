package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.UploadChengXinCertBean;
import com.zthx.npj.net.been.UploadChengxinCertResponseBean;
import com.zthx.npj.net.netsubscribe.CertSubscribe;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrustedStore2Activity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @BindView(R.id.at_trust_store2_tv_2000)
    TextView atTrustStore2Tv2000;
    @BindView(R.id.at_trust_store2_tv2_2000)
    TextView atTrustStore2Tv22000;
    @BindView(R.id.at_trust_store2_rl_2000)
    RelativeLayout atTrustStore2Rl2000;
    @BindView(R.id.at_trust_store2_tv_3000)
    TextView atTrustStore2Tv3000;
    @BindView(R.id.at_trust_store2_tv2_3000)
    TextView atTrustStore2Tv23000;
    @BindView(R.id.at_trust_store2_rl_3000)
    RelativeLayout atTrustStore2Rl3000;
    @BindView(R.id.at_trust_store2_tv_4000)
    TextView atTrustStore2Tv4000;
    @BindView(R.id.at_trust_store2_tv2_4000)
    TextView atTrustStore2Tv24000;
    @BindView(R.id.at_trust_store2_rl_4000)
    RelativeLayout atTrustStore2Rl4000;
    @BindView(R.id.at_trust_store2_rb_read)
    RadioButton atTrustStore2RbRead;
    @BindView(R.id.at_trust_store2_btn_join)
    Button atTrustStore2BtnJoin;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    private int check=0;
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
    private String payType="";
    private UploadChengxinCertResponseBean.DataBean data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_store2);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"诚信商家");

        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx76500efa65d19915");
    }

    @OnClick({R.id.at_trust_store2_rl_2000, R.id.at_trust_store2_rl_3000, R.id.at_trust_store2_rl_4000, R.id.at_trust_store2_rb_read, R.id.at_trust_store2_btn_join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_trust_store2_rl_2000:
                clickPrice(2);
                check = 2;
                break;
            case R.id.at_trust_store2_rl_3000:
                clickPrice(3);
                check = 3;
                break;
            case R.id.at_trust_store2_rl_4000:
                clickPrice(5);
                check = 5;
                break;
            case R.id.at_trust_store2_rb_read:
                atTrustStore2RbRead.setChecked(true);
                break;
            case R.id.at_trust_store2_btn_join:
                if(!atTrustStore2RbRead.isChecked()){
                    showToast("请阅读并同意卖家保障服务协议");
                }else if(check==0){
                    showToast("请选择保证金类别");
                }else{
                    showBottomDialog();
                }
                break;
        }
    }

    private void clickPrice(int i) {
        atTrustStore2Rl2000.setBackgroundResource(android.R.color.white);
        atTrustStore2Rl3000.setBackgroundResource(android.R.color.white);
        atTrustStore2Rl4000.setBackgroundResource(android.R.color.white);
        atTrustStore2Tv2000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv3000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv4000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv22000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv23000.setTextColor(getResources().getColor(R.color.app_theme));
        atTrustStore2Tv24000.setTextColor(getResources().getColor(R.color.app_theme));
        if (i == 2) {
            atTrustStore2Rl2000.setBackgroundResource(R.color.app_theme);
            atTrustStore2Tv2000.setTextColor(getResources().getColor(android.R.color.white));
            atTrustStore2Tv22000.setTextColor(getResources().getColor(android.R.color.white));
        } else if (i == 3) {
            atTrustStore2Rl3000.setBackgroundResource(R.color.app_theme);
            atTrustStore2Tv3000.setTextColor(getResources().getColor(android.R.color.white));
            atTrustStore2Tv23000.setTextColor(getResources().getColor(android.R.color.white));
        } else if (i == 5) {
            atTrustStore2Rl4000.setBackgroundResource(R.color.app_theme);
            atTrustStore2Tv4000.setTextColor(getResources().getColor(android.R.color.white));
            atTrustStore2Tv24000.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_pay_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_pay_ali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType="1";
                uploadData();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType="2";
                uploadData();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_pay_yue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payType="3";
                uploadData();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void uploadData() {
        UploadChengXinCertBean bean = new UploadChengXinCertBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(TrustedStore2Activity.this));
        bean.setToken(SharePerferenceUtils.getToken(TrustedStore2Activity.this));
        bean.setPrice(check + "000");
        bean.setPay_code(payType);
        CertSubscribe.uploadChengxinCert(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UploadChengxinCertResponseBean bean=GsonUtils.fromJson(result,UploadChengxinCertResponseBean.class);
                data1=bean.getData();
                if(payType.equals("1")){
                    alipay();
                }else if(payType.equals("2")){
                    wxpay();
                }else{
                    yue();
                }
            }

            @Override
            public void onFault(String errorMsg) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(errorMsg);
                    Log.e("测试", "onFault: " + obj);
                    int code = obj.getInt("code");
                    if (code == 2) {
                        openActivity(OrderFinishActivity.class,"http://game.npj-vip.com/apk/logo.png","诚信认证",check+"000","7");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("测试", "onFault: " + (obj == null));
                if (obj == null) {
                    showToast("余额不足");
                }
            }
        }));
    }

    private void yue() {

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
                PayTask alipay = new PayTask(TrustedStore2Activity.this);
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
                        Intent intent=new Intent(TrustedStore2Activity.this,WXPayFinishActivity.class);
                        intent.putExtra("title","支付宝支付");
                        intent.putExtra("content","支付宝支付成功");
                        intent.putExtra("type","1");
                        startActivity(intent);
                        //Toast.makeText(TrustedStore2Activity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TrustedStore2Activity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
