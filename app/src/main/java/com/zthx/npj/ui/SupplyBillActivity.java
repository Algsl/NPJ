package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.zthx.npj.net.been.ConfirmSupplyResponseBean;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.SupplyBuy2Bean;
import com.zthx.npj.net.been.SupplyBuy2ResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplyBillActivity extends ActivityBase {

    @BindView(R.id.at_supply_bill_ll_choice_address)
    RelativeLayout atSupplyBillLlChoiceAddress;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_supply_bill_iv1)
    ImageView atSupplyBillIv1;
    @BindView(R.id.at_supply_bill_head_pic)
    MyCircleView atSupplyBillHeadPic;
    @BindView(R.id.at_supply_bill_tv_name)
    TextView atSupplyBillTvName;
    @BindView(R.id.at_supply_bill_iv_goods_pic)
    ImageView atSupplyBillIvGoodsPic;
    @BindView(R.id.at_supply_bill_tv_title)
    TextView atSupplyBillTvTitle;
    @BindView(R.id.at_supply_bill_tv_danjia)
    TextView atSupplyBillTvDanjia;
    @BindView(R.id.at_supply_bill_tv_unit)
    TextView atSupplyBillTvUnit;
    @BindView(R.id.at_supply_bill_tv_zongjia)
    TextView atSupplyBillTvZongjia;
    @BindView(R.id.at_supply_bill_btn_buy)
    Button atSupplyBillBtnBuy;
    @BindView(R.id.at_supply_bill_ll_bottom)
    LinearLayout atSupplyBillLlBottom;
    @BindView(R.id.at_supply_bill_tv_price)
    TextView atSupplyBillTvPrice;
    @BindView(R.id.at_supply_bill_tv_gongji_goods)
    TextView atSupplyBillTvGongjiGoods;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_supply_bill_tv_address)
    TextView atSupplyBillTvAddress;
    @BindView(R.id.ac_supplyBill_iv_choose1)
    ImageView acSupplyBillIvChoose1;
    @BindView(R.id.ac_supplyBill_iv_choose2)
    ImageView acSupplyBillIvChoose2;
    @BindView(R.id.ac_supplyBill_iv_choose3)
    ImageView acSupplyBillIvChoose3;
    @BindView(R.id.yunfei)
    EditText yunfei;
    @BindView(R.id.at_supply_bill_rl_jian)
    RelativeLayout atSupplyBillRlJian;
    @BindView(R.id.at_supply_bill_tv_buy_num)
    TextView atSupplyBillTvBuyNum;
    @BindView(R.id.at_supply_bill_rl_add)
    RelativeLayout atSupplyBillRlAdd;

    private long number;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String goodsId;
    private String pay_code = "2";
    private String address_id = "10";
    private String shipping_fee = "";
    private String remark = "sdf";
    private SupplyBuy2ResponseBean.DataBean data1;
    private ConfirmSupplyResponseBean.DataBean data;

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

    private String goodsImg;
    private String goodsName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_bill);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx76500efa65d19915");


        back(titleBack);
        changeTitle(acTitle, "商品确认订单");

        goodsId = getIntent().getStringExtra("key0");

        getData(goodsId);

    }

    private void getData(String id) {
        DiscoverSubscribe.confirmSupply(SharePerferenceUtils.getUserId(this), id, SharePerferenceUtils.getToken(this),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        data = GsonUtils.fromJson(result, ConfirmSupplyResponseBean.class).getData();
                        Glide.with(SupplyBillActivity.this).load(data.getHead_img()).into(atSupplyBillHeadPic);
                        atSupplyBillTvAddress.setText(data.getAddress());
                        atSupplyBillTvName.setText(data.getNick_name());
                        Glide.with(SupplyBillActivity.this).load(data.getGoods_img()).into(atSupplyBillIvGoodsPic);
                        goodsImg=data.getGoods_img();
                        atSupplyBillTvTitle.setText(data.getTitle());
                        atSupplyBillTvDanjia.setText("¥" + data.getPrice() + "/");
                        atSupplyBillTvUnit.setText(data.getGoods_unit());
                        number=Long.parseLong(data.getBuy_num());
                        atSupplyBillTvBuyNum.setText(data.getBuy_num()+"");
                        //atSupplyBillTvBuyNum.setHint(data.getBuy_num() + "" + data.getGoods_unit() + "起批");
                        address_id = data.getAddress_id() + "";
                        atSupplyBillTvGongjiGoods.setText("共计" + atSupplyBillTvBuyNum.getText().toString().trim() + data.getGoods_unit() + "商品   小计：");
                        atSupplyBillTvPrice.setText("¥" + new DecimalFormat("0.00").format(number * Double.parseDouble(data.getPrice())));
                        atSupplyBillTvZongjia.setText("¥" + new DecimalFormat("0.00").format(number * Double.parseDouble(data.getPrice())));
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        //showToast(errorMsg);
                    }
                }));
    }

    @OnClick({R.id.at_supply_bill_ll_choice_address, R.id.at_supply_bill_btn_buy, R.id.ac_supplyBill_iv_choose1,
            R.id.ac_supplyBill_iv_choose2, R.id.ac_supplyBill_iv_choose3, R.id.yunfei,
            R.id.at_supply_bill_rl_jian, R.id.at_supply_bill_rl_add})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.at_supply_bill_ll_choice_address:
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.at_supply_bill_btn_buy:
                if (shipping_fee.equals("")) {
                    showToast("请填写运费");
                } else {
                    buySupply();
                }
                break;
            case R.id.ac_supplyBill_iv_choose3:
                pay_code = "3";
                acSupplyBillIvChoose3.setImageResource(R.drawable.confirm_select);
                acSupplyBillIvChoose2.setImageResource(R.drawable.confirm_unselect);
                acSupplyBillIvChoose1.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_supplyBill_iv_choose2:
                pay_code = "2";
                acSupplyBillIvChoose3.setImageResource(R.drawable.confirm_unselect);
                acSupplyBillIvChoose2.setImageResource(R.drawable.confirm_select);
                acSupplyBillIvChoose1.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_supplyBill_iv_choose1:
                pay_code = "1";
                acSupplyBillIvChoose3.setImageResource(R.drawable.confirm_unselect);
                acSupplyBillIvChoose2.setImageResource(R.drawable.confirm_unselect);
                acSupplyBillIvChoose1.setImageResource(R.drawable.confirm_select);
                break;
            case R.id.yunfei:
                yunfei.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String money = yunfei.getText().toString();
                        if (!money.equals("")) {
                            shipping_fee = Double.parseDouble(money) + "";
                            String hjMoney = number * Double.parseDouble(data.getPrice()) + Double.parseDouble(money) + "";
                            atSupplyBillTvPrice.setText("¥" + hjMoney);
                            atSupplyBillTvZongjia.setText("¥" + hjMoney);
                        }else{
                            String hjMoney = number * Double.parseDouble(data.getPrice())+"";
                            atSupplyBillTvPrice.setText("¥" + hjMoney);
                            atSupplyBillTvZongjia.setText("¥" + hjMoney);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                break;
            /*case R.id.at_supply_bill_tv_buy_num:
                atSupplyBillTvBuyNum.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!atSupplyBillTvBuyNum.getText().toString().trim().equals("")) {
                            atSupplyBillTvGongjiGoods.setText("共计" + atSupplyBillTvBuyNum.getText().toString().trim() + data.getGoods_unit()+"商品   小计：");
                            allPrice = Double.parseDouble(atSupplyBillTvBuyNum.getText().toString()) * Double.parseDouble(data.getPrice());
                            atSupplyBillTvPrice.setText("¥" + allPrice);
                            atSupplyBillTvZongjia.setText("¥" + allPrice);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                break;*/
            case R.id.at_supply_bill_rl_jian:
                if(data.getBuy_num().equals(atSupplyBillTvBuyNum.getText().toString().trim())){
                    showToast("采购数量不能低于起批量");
                }else{
                    number--;
                    atSupplyBillTvBuyNum.setText(number+"");
                    atSupplyBillTvGongjiGoods.setText("共计" + atSupplyBillTvBuyNum.getText().toString().trim() + data.getGoods_unit() + "商品   小计：");
                    atSupplyBillTvPrice.setText("¥" +new DecimalFormat("0.00").format(number * Double.parseDouble(data.getPrice())+(shipping_fee.equals("")?0:Double.parseDouble(shipping_fee))));
                    atSupplyBillTvZongjia.setText("¥" + new DecimalFormat("0.00").format(number * Double.parseDouble(data.getPrice())+(shipping_fee.equals("")?0:Double.parseDouble(shipping_fee))));
                }
                break;
            case R.id.at_supply_bill_rl_add:
                number++;
                atSupplyBillTvBuyNum.setText(number+"");
                atSupplyBillTvGongjiGoods.setText("共计" + atSupplyBillTvBuyNum.getText().toString().trim() + data.getGoods_unit() + "商品   小计：");
                atSupplyBillTvPrice.setText("¥" + new DecimalFormat("0.00").format(number * Double.parseDouble(data.getPrice())+(shipping_fee.equals("")?0:Double.parseDouble(shipping_fee))));
                atSupplyBillTvZongjia.setText("¥" + new DecimalFormat("0.00").format(number * Double.parseDouble(data.getPrice())+(shipping_fee.equals("")?0:Double.parseDouble(shipping_fee))));
                break;
        }
    }

    public void buySupply() {
        SupplyBuy2Bean bean = new SupplyBuy2Bean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goodsId);
        bean.setGoods_num(atSupplyBillTvBuyNum.getText().toString().trim());
        bean.setPay_code(pay_code);
        bean.setAddress_id(address_id);
        bean.setShipping_fee(shipping_fee);
        bean.setRemark(remark);
        DiscoverSubscribe.supplyBuy2(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SupplyBuy2ResponseBean bean = GsonUtils.fromJson(result, SupplyBuy2ResponseBean.class);
                data1 = bean.getData();
                switch (pay_code) {
                    case "1":
                        alipay();
                        break;
                    case "2":
                        wxpay();
                        break;
                    case "3":
                        yuepay();
                        break;
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
                        openActivity(OrderFinishActivity.class, goodsImg, goodsName, Double.parseDouble(number * Double.parseDouble(data.getPrice()) + "") + Double.parseDouble(shipping_fee) + "", "6");
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


    private void yuepay() {
        finish();
    }

    private void wxpay() {
        DiscoverSubscribe.supplyPay("weixin", data1.getOrder_sn(), data1.getPay_money(), "4", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        DiscoverSubscribe.supplyPay("alipay", data1.getOrder_sn(), data1.getPay_money(), "4", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                PayTask alipay = new PayTask(SupplyBillActivity.this);
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
                        Toast.makeText(SupplyBillActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SupplyBillActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            address_id = data.getStringExtra("address_id");
            atSupplyBillTvAddress.setText(data.getStringExtra("address"));
        }
    }


}
