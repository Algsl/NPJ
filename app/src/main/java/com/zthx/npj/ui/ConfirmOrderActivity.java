package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.adapter.LocalStoreAdapter;
import com.zthx.npj.aliapi.OrderInfoUtil2_0;
import com.zthx.npj.aliapi.PayResult;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BonusListResponseBean;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.ConfirmPreSellResponseBean;
import com.zthx.npj.net.been.GiftBuyOneBean;
import com.zthx.npj.net.been.GiftConfirmResponseBean;
import com.zthx.npj.net.been.GoodsBuyOneBean;
import com.zthx.npj.net.been.GoodsOrderBean;
import com.zthx.npj.net.been.GoodsOrderResponseBean;
import com.zthx.npj.net.been.PayResponse1Bean;
import com.zthx.npj.net.been.PayResponseBean;
import com.zthx.npj.net.been.SeckillOrderBean;
import com.zthx.npj.net.been.SeckillOrderResponseBean;
import com.zthx.npj.net.been.SelfLiftingResponseBean;
import com.zthx.npj.net.been.SpikeOrderBuyOneBean;
import com.zthx.npj.net.been.YsBuyOneBean;
import com.zthx.npj.net.been.YsBuyOneResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends ActivityBase {

    @BindView(R.id.at_confirm_order_rl_ziti)
    RelativeLayout atConfirmOrderRlZiti;
    @BindView(R.id.at_confirm_order_tv_address)
    TextView atConfirmOrderTvAddress;
    @BindView(R.id.at_confirm_order_tv_store_name)
    TextView atConfirmOrderTvStoreName;
    @BindView(R.id.at_confirm_order_iv_pic)
    ImageView atConfirmOrderIvPic;
    @BindView(R.id.at_confirm_order_tv_title)
    TextView atConfirmOrderTvTitle;
    @BindView(R.id.at_confirm_order_tv_size)
    TextView atConfirmOrderTvSize;
    @BindView(R.id.at_confirm_order_tv_goods_price)
    TextView atConfirmOrderTvGoodsPrice;
    @BindView(R.id.at_confirm_order_tv_goods_num)
    TextView atConfirmOrderTvGoodsNum;
    @BindView(R.id.at_confirm_order_iv_go)
    ImageView atConfirmOrderIvGo;
    @BindView(R.id.at_confirm_order_tv_to_store)
    TextView atConfirmOrderTvToStore;
    @BindView(R.id.at_confirm_order_tv_price)
    TextView atConfirmOrderTvPrice;
    @BindView(R.id.iv_pop_lingdang)
    ImageView ivPopLingdang;
    @BindView(R.id.iv_pop_look)
    ImageView ivPopLook;
    @BindView(R.id.at_confirm_order_iv_my_hongbao)
    ImageView atConfirmOrderIvMyHongbao;
    @BindView(R.id.at_confirm_order_iv_my_col)
    ImageView atConfirmOrderIvMyCol;
    @BindView(R.id.at_confirm_order_iv_my_wechat)
    ImageView atConfirmOrderIvMyWechat;
    @BindView(R.id.at_confirm_order_iv_alipay)
    ImageView atConfirmOrderIvAlipay;
    @BindView(R.id.at_confirm_order_rl_hongbao)
    RelativeLayout atConfirmOrderRlHongbao;
    @BindView(R.id.ac_confirmOrder_btn_pay)
    Button acConfirmOrderBtnPay;
    @BindView(R.id.ac_confirmOrder_et_remark)
    EditText acConfirmOrderEtRemark;
    @BindView(R.id.ac_confirmOrder_iv_type3)
    ImageView acConfirmOrderIvType3;
    @BindView(R.id.ac_confirmOrder_iv_type2)
    ImageView acConfirmOrderIvType2;
    @BindView(R.id.ac_confirmOrder_iv_type1)
    ImageView acConfirmOrderIvType1;
    @BindView(R.id.ac_confirmOrder_btn_ziti)
    Button acConfirmOrderBtnZiti;
    @BindView(R.id.ac_confirmORder_btn_peisong)
    Button acConfirmORderBtnPeisong;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.at_confirm_order_rl_peisong)
    RelativeLayout atConfirmOrderRlPeisong;
    @BindView(R.id.ac_confirmOrder_ll_chooseAddress)
    LinearLayout acConfirmOrderLlChooseAddress;
    @BindView(R.id.ac_confirmOrder_rl_toDYR)
    RelativeLayout acConfirmOrderRlToDYR;
    @BindView(R.id.ac_confirmOrder_tv_hongbao)
    TextView acConfirmOrderTvHongbao;
    @BindView(R.id.ac_confirmOrder_tv_realPay)
    TextView acConfirmOrderTvRealPay;
    @BindView(R.id.ac_confirmOrder_rl_tihuo)
    RelativeLayout acConfirmOrderRlTihuo;
    @BindView(R.id.ac_confirmOrder_tv_goodsAllNum)
    TextView acConfirmOrderTvGoodsAllNum;
    @BindView(R.id.ac_confirmOrder_tv_lisheng)
    TextView acConfirmOrderTvLisheng;
    @BindView(R.id.at_confirmOrder_tv_dyrYH)
    TextView atConfirmOrderTvDyrYH;
    @BindView(R.id.at_confirm_order_rl_hasDYR)
    RelativeLayout atConfirmOrderRlHasDYR;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_confirmOrder_tv_balance)
    TextView acConfirmOrderTvBalance;
    @BindView(R.id.at_confirm_order_rl_chooseYHQ)
    RelativeLayout atConfirmOrderRlChooseYHQ;
    private String attId;
    private String goodsId;
    private String address_id = "0";
    private String allAddress = "";
    private String tihuoType = "1";

    private ConfirmPreSellResponseBean.DataBean data;
    private YsBuyOneResponseBean.DataBean data1;
    private GoodsOrderResponseBean.DataBean ptdata;
    private SeckillOrderResponseBean.DataBean seckillData;

    private String pay_code = "2";
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String level = SharePerferenceUtils.getUserLevel(this);
    private ArrayList<SelfLiftingResponseBean.DataBean> localData = new ArrayList<>();

    private String bonus_id="";
    private String hongbao="";

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
    private String confirmType = "1";
    private String goodsCount = "1";

    private double payMoney;

    private String goodsImg, goodsName, goodsPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx76500efa65d19915");

        back(titleBack);
        changeTitle(acTitle, "确认订单");
        changeRightImg(acTitleIv, R.drawable.goods_detail_home, null, null);

        //acConfirmOrderTvBalance.setText("钱包(当前余额" + SharePerferenceUtils.getBalance(this) + "元)");

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getLocalStore("2");
                if (Const.GIFT.equals(getIntent().getAction())) {//礼包店确认订单
                    confirmType = "1";
                    goodsId = getIntent().getStringExtra(Const.GOODS_ID);
                    atConfirmOrderRlHongbao.setVisibility(View.VISIBLE);
                    acConfirmOrderRlToDYR.setVisibility(View.GONE);
                    getGiftConfirmData(goodsId);
                } else if (Const.PRESELL.equals(getIntent().getAction())) {//新品众筹确认订单
                    confirmType = "2";
                    attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
                    goodsId = getIntent().getStringExtra(Const.GOODS_ID);
                    getData();
                } else if ("miaosha".equals(getIntent().getAction())) {
                    confirmType = "4";
                    attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
                    goodsId = getIntent().getStringExtra(Const.GOODS_ID);
                    goodsCount = getIntent().getStringExtra("count");
                    getSeckillData();
                } else {//普通商品确认订单
                    confirmType = "3";
                    atConfirmOrderRlChooseYHQ.setVisibility(View.VISIBLE);
                    acConfirmOrderRlTihuo.setVisibility(View.VISIBLE);
                    attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
                    goodsId = getIntent().getStringExtra(Const.GOODS_ID);
                    goodsCount = getIntent().getStringExtra("count");
                    getGoodsData();
                }
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        getLocalStore("2");
        if (Const.GIFT.equals(getIntent().getAction())) {//礼包店确认订单
            confirmType = "1";
            goodsId = getIntent().getStringExtra(Const.GOODS_ID);
            atConfirmOrderRlHongbao.setVisibility(View.VISIBLE);
            acConfirmOrderRlToDYR.setVisibility(View.GONE);
            getGiftConfirmData(goodsId);
        } else if (Const.PRESELL.equals(getIntent().getAction())) {//新品众筹确认订单
            confirmType = "2";
            attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
            goodsId = getIntent().getStringExtra(Const.GOODS_ID);
            getData();
        } else if ("miaosha".equals(getIntent().getAction())) {
            confirmType = "4";
            attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
            goodsId = getIntent().getStringExtra(Const.GOODS_ID);
            goodsCount = getIntent().getStringExtra("count");
            getSeckillData();
        } else {//普通商品确认订单
            confirmType = "3";
            acConfirmOrderRlTihuo.setVisibility(View.VISIBLE);
            atConfirmOrderRlChooseYHQ.setVisibility(View.VISIBLE);
            attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
            goodsId = getIntent().getStringExtra(Const.GOODS_ID);
            Log.e("测试", "onCreate: " + goodsCount);
            goodsCount = getIntent().getStringExtra("count");
            getGoodsData();
        }

    }

    //秒杀商品确认订单
    private void getSeckillData() {
        SeckillOrderBean bean = new SeckillOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goodsId);
        bean.setItem_id(attId);
        bean.setGoods_num(goodsCount);
        SetSubscribe.seckillOrder(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSeckillData(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setSeckillData(String result) {
        SeckillOrderResponseBean bean = GsonUtils.fromJson(result, SeckillOrderResponseBean.class);
        seckillData = bean.getData();

        address_id=seckillData.getAddress_id()+"";
        atConfirmOrderTvAddress.setText(seckillData.getAddress());
        atConfirmOrderTvStoreName.setText(seckillData.getStore_name());
        Glide.with(ConfirmOrderActivity.this).load(seckillData.getGoods_img()).into(atConfirmOrderIvPic);
        atConfirmOrderTvTitle.setText(seckillData.getGoods_name());
        atConfirmOrderTvGoodsPrice.setText("¥" + seckillData.getGoods_price());
        atConfirmOrderTvGoodsNum.setText("x" + goodsCount);

        acConfirmOrderTvGoodsAllNum.setText("共" + goodsCount + "件商品  总计：");
        payMoney = (Double.parseDouble(seckillData.getGoods_price())) * ((int) Double.parseDouble(goodsCount));
        /*if (level.equals("0")) {
            acConfirmOrderTvLisheng.setText("成为农品街代言人此单立省" + getIntent().getStringExtra("lisheng") + "元");
        } else {
            acConfirmOrderRlToDYR.setVisibility(View.GONE);
            atConfirmOrderRlHasDYR.setVisibility(View.VISIBLE);
            atConfirmOrderTvDyrYH.setText("-￥" + getIntent().getStringExtra("lisheng"));
        }*/
        atConfirmOrderTvPrice.setText("￥" + payMoney);
        acConfirmOrderTvRealPay.setText("￥" + payMoney);

        goodsImg = seckillData.getGoods_img();
        goodsName = seckillData.getGoods_name();
        goodsPrice = payMoney + "元";
    }

    private void getGoodsData() {
        GoodsOrderBean bean = new GoodsOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goodsId);
        bean.setItem_id(attId);
        bean.setGoods_num(goodsCount);
        SetSubscribe.goodsOrder(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setGoodsData(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setGoodsData(String result) {
        GoodsOrderResponseBean bean = GsonUtils.fromJson(result, GoodsOrderResponseBean.class);
        ptdata = bean.getData();
        atConfirmOrderTvAddress.setText(ptdata.getAddress());
        atConfirmOrderTvStoreName.setText(ptdata.getStore_name());
        address_id = ptdata.getAddress_id() + "";
        Glide.with(ConfirmOrderActivity.this).load(ptdata.getGoods_img()).into(atConfirmOrderIvPic);
        atConfirmOrderTvTitle.setText(ptdata.getGoods_name());
        //atConfirmOrderTvSize.setText("规格： "+ptdata.getAttributes().getKey_name());
        //atConfirmOrderTvGoodsPrice.setText("¥" + ptdata.getAttributes().getSpec_user_price());

        atConfirmOrderTvSize.setText("规格： "+ptdata.getKey_name()==null?"无":ptdata.getKey_name());
        atConfirmOrderTvGoodsPrice.setText("¥" + ptdata.getPrice1());
        atConfirmOrderTvGoodsNum.setText("x" + ptdata.getGoods_num());

        acConfirmOrderTvGoodsAllNum.setText("共" + ptdata.getGoods_num() + "件商品  总计：");
        double payMoney = (Double.parseDouble(ptdata.getPrice())) * ((int) Double.parseDouble(ptdata.getGoods_num()));
        if (level.equals("0")) {
            acConfirmOrderTvLisheng.setText("成为农品街代言人此单立省" + getIntent().getStringExtra("lisheng") + "元");
        } else {
            acConfirmOrderRlToDYR.setVisibility(View.GONE);
            atConfirmOrderRlHasDYR.setVisibility(View.VISIBLE);
            atConfirmOrderTvDyrYH.setText("-￥" + getIntent().getStringExtra("lisheng"));
        }

        atConfirmOrderTvPrice.setText("￥" + payMoney);
        acConfirmOrderTvRealPay.setText("￥" + payMoney);

        goodsImg = ptdata.getGoods_img();
        goodsName = ptdata.getGoods_name();
        goodsPrice = payMoney + "元";
    }

    private void getLocalStore(String type) {
        /*LocalStoreBean bean = new LocalStoreBean();
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        bean.setPage("1");
        bean.setType(type);
        MainSubscribe.getLocalStore(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LocalStoreResponseBean bean = GsonUtils.fromJson(result, LocalStoreResponseBean.class);
                localData = bean.getData();
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));*/
        SetSubscribe.selfLifting(user_id,token,SharePerferenceUtils.getLng(this),SharePerferenceUtils.getLat(this),new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SelfLiftingResponseBean bean=GsonUtils.fromJson(result,SelfLiftingResponseBean.class);
                localData=bean.getData();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    //礼包店确认订单
    private void getGiftConfirmData(String goodsId) {
        GiftSubscribe.getGiftConfirm(user_id, token, goodsId, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GiftConfirmResponseBean gIftConfirmResponseBean = GsonUtils.fromJson(result, GiftConfirmResponseBean.class);
                GiftConfirmResponseBean.DataBean data = gIftConfirmResponseBean.getData();
                atConfirmOrderTvTitle.setText(data.getTitle());
                Glide.with(ConfirmOrderActivity.this).load(data.getImg()).into(atConfirmOrderIvPic);
                atConfirmOrderTvGoodsPrice.setText(data.getPrice());
                if ((int) data.getStatus() == 0) {
                    atConfirmOrderRlHongbao.setVisibility(View.GONE);
                }
                atConfirmOrderTvStoreName.setText(data.getStore_name());
                atConfirmOrderTvAddress.setText(data.getAddress() + "");
                address_id = data.getAddress_id() + "";

                atConfirmOrderTvPrice.setText("￥" + data.getPrice());
                acConfirmOrderTvRealPay.setText("￥" + data.getPrice());

                payMoney=Double.parseDouble(data.getPrice());

                goodsImg = data.getImg();
                goodsName = data.getTitle();
                goodsPrice = data.getPrice() + "元";
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    //新品众筹确认订单
    private void getData() {
        ConfirmPreSellBean bean = new ConfirmPreSellBean();
        bean.setToken(token);
        bean.setAtt_id(attId);
        bean.setPre_id(goodsId);
        bean.setUser_id(user_id);
        PreSellSubscribe.getConfirmPreSell(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: " + result);
                ConfirmPreSellResponseBean confirmPreSellResponseBean = GsonUtils.fromJson(result, ConfirmPreSellResponseBean.class);
                data = confirmPreSellResponseBean.getData();
                address_id = data.getAddress_id() + "";
                Log.e("测试", "onSuccess: " + address_id);
                atConfirmOrderTvAddress.setText(data.getAddress());
                atConfirmOrderTvStoreName.setText(data.getStore_name());
                Glide.with(ConfirmOrderActivity.this).load(data.getGoods_img()).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        atConfirmOrderIvPic.setImageBitmap(resource);
                    }
                });
                atConfirmOrderTvTitle.setText(data.getGoods_name());
                atConfirmOrderTvSize.setText("规格： " + "x" + data.getAttributes().getPre_number() + " ￥" + data.getAttributes().getPre_price());
                atConfirmOrderTvGoodsPrice.setText("¥" + data.getGoods_price());
                atConfirmOrderTvGoodsNum.setText("x" + data.getAttributes().getPre_number());

                acConfirmOrderTvGoodsAllNum.setText("共" + data.getAttributes().getPre_number() + "件商品  总计：");
                int payMoney = (int) Double.parseDouble(data.getAttributes().getPre_price());
                double lisheng = 0.15 * payMoney;
                acConfirmOrderTvLisheng.setText("成为农品街代言人此单立省" + lisheng + "元");
                atConfirmOrderTvPrice.setText("¥" + payMoney);
                acConfirmOrderTvRealPay.setText("￥" + payMoney);

                goodsImg = data.getGoods_img();
                goodsName = data.getGoods_name();
                goodsPrice = payMoney + "元";
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    @OnClick({R.id.at_confirm_order_rl_ziti, R.id.ac_confirmOrder_btn_pay, R.id.ac_confirmOrder_iv_type3,
            R.id.ac_confirmOrder_iv_type2, R.id.ac_confirmOrder_iv_type1, R.id.ac_confirmOrder_btn_ziti,
            R.id.ac_confirmORder_btn_peisong, R.id.ac_confirmOrder_ll_chooseAddress,
            R.id.at_confirm_order_rl_hongbao, R.id.ac_confirmOrder_rl_toDYR,R.id.at_confirm_order_rl_chooseYHQ})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ac_confirmOrder_btn_pay:
                Log.e("测试", "onSuccess: " + address_id);
                if (address_id.equals("0")) {
                    showToast("请选择收货地址");
                } else {
                    //确认订单
                    showToast("订单生成中，请稍等");
                    if (confirmType.equals("1")) {
                        giftConfirm();
                    } else if (confirmType.equals("2")) {
                        preSellConfirm();
                    } else if (confirmType.equals("3")) {
                        goodsConfirm();
                    }else{
                        seckConfirm();
                    }
                }
                break;
            case R.id.ac_confirmOrder_ll_chooseAddress:
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.ac_confirmOrder_iv_type3:
                pay_code = "3";
                acConfirmOrderIvType3.setImageResource(R.drawable.confirm_select);
                acConfirmOrderIvType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmOrderIvType1.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_confirmOrder_iv_type2:
                pay_code = "2";
                acConfirmOrderIvType3.setImageResource(R.drawable.confirm_unselect);
                acConfirmOrderIvType2.setImageResource(R.drawable.confirm_select);
                acConfirmOrderIvType1.setImageResource(R.drawable.confirm_unselect);
                break;
            case R.id.ac_confirmOrder_iv_type1:
                pay_code = "1";
                acConfirmOrderIvType3.setImageResource(R.drawable.confirm_unselect);
                acConfirmOrderIvType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmOrderIvType1.setImageResource(R.drawable.confirm_select);
                break;
            case R.id.ac_confirmOrder_btn_ziti:
                acConfirmOrderBtnZiti.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acConfirmOrderBtnZiti.setTextColor(getResources().getColor(R.color.white));
                acConfirmORderBtnPeisong.setBackgroundColor(getResources().getColor(R.color.white));
                acConfirmORderBtnPeisong.setTextColor(getResources().getColor(R.color.text3));
                showPublishPopwindow();
                tihuoType = "2";
                atConfirmOrderRlZiti.setVisibility(View.VISIBLE);
                atConfirmOrderRlPeisong.setVisibility(View.GONE);
                break;
            case R.id.ac_confirmORder_btn_peisong:
                tihuoType = "1";
                acConfirmOrderBtnZiti.setBackgroundColor(getResources().getColor(R.color.white));
                acConfirmOrderBtnZiti.setTextColor(getResources().getColor(R.color.text3));
                acConfirmORderBtnPeisong.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acConfirmORderBtnPeisong.setTextColor(getResources().getColor(R.color.white));
                atConfirmOrderRlPeisong.setVisibility(View.VISIBLE);
                atConfirmOrderRlZiti.setVisibility(View.GONE);
                break;
            case R.id.at_confirm_order_rl_hongbao:
                showHongBaoPopwindow();
                break;
            case R.id.ac_confirmOrder_rl_toDYR:
                openActivity(MembershipPackageActivity.class);
                break;
            case R.id.at_confirm_order_rl_chooseYHQ:
                showYHQPopwindow();
                break;
        }
    }

    private void seckConfirm() {
        SpikeOrderBuyOneBean bean = new SpikeOrderBuyOneBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setAddress_id(address_id);
        bean.setPay_code(pay_code);
        bean.setGoods_id(goodsId);
        bean.setGoods_num(goodsCount);
        SetSubscribe.spikeOrderBuyOne(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("用户订单已生成,正在调起支付...");
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
                        yuepay();
                        break;
                }
            }

            @Override
            public void onFault(String errorMsg) {
                Log.e("测试", "onFault: "+errorMsg );
                JSONObject obj = null;
                try {
                    obj = new JSONObject(errorMsg);
                    Log.e("测试", "onFault: " + obj);
                    if (obj == null) {

                    } else {
                        int code = obj.getInt("code");
                        if (code == 2) {
                            openActivity(OrderFinishActivity.class, goodsImg, goodsName, goodsPrice,confirmType);
                        }
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

    //普通商品
    private void goodsConfirm() {
        GoodsBuyOneBean bean = new GoodsBuyOneBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setAddress_id(address_id);
        bean.setPay_code(pay_code);
        bean.setType(tihuoType);
        bean.setGoods_id(goodsId);
        bean.setGoods_num(goodsCount);
        bean.setItem_id(attId);
        SetSubscribe.goodsBuyOne(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("用户订单已生成,正在调起支付...");
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
                        yuepay();
                        break;
                }
            }

            @Override
            public void onFault(String errorMsg) {
                Log.e("测试", "onFault: "+errorMsg );
                JSONObject obj = null;
                try {
                    obj = new JSONObject(errorMsg);
                    Log.e("测试", "onFault: " + obj);
                    if (obj == null) {

                    } else {
                        int code = obj.getInt("code");
                        if (code == 2) {
                            openActivity(OrderFinishActivity.class, goodsImg, goodsName, goodsPrice,confirmType);
                        }
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

    private void giftConfirm() {
        GiftBuyOneBean bean = new GiftBuyOneBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setGift_id(goodsId);
        bean.setAddress_id(address_id);
        bean.setPay_code(pay_code);
        bean.setBonus_id(bonus_id);

        GiftSubscribe.giftBuyOne(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                YsBuyOneResponseBean bean = GsonUtils.fromJson(result, YsBuyOneResponseBean.class);
                data1 = bean.getData();
                showToast("用户订单已生成,正在调起支付...");
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
                    if (obj == null) {

                    } else {
                        int code = obj.getInt("code");
                        if (code == 2) {
                            openActivity(OrderFinishActivity.class, goodsImg, goodsName, goodsPrice,confirmType);
                            SharePerferenceUtils.setUserLevel(ConfirmOrderActivity.this, "1");
                        }
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

    private void preSellConfirm() {
        YsBuyOneBean bean = new YsBuyOneBean();
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        bean.setToken(SharePerferenceUtils.getToken(this));
        bean.setAtt_id(attId);
        bean.setPre_id(goodsId);
        bean.setAddress_id(address_id);
        bean.setPay_code(pay_code);
        bean.setRemark(acConfirmOrderEtRemark.getText().toString().trim());
        PreSellSubscribe.ysBuyOne(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                YsBuyOneResponseBean bean = GsonUtils.fromJson(result, YsBuyOneResponseBean.class);
                data1 = bean.getData();
                showToast("用户订单已生成,正在调起支付...");
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
                    if (obj == null) {

                    } else {
                        int code = obj.getInt("code");
                        if (code == 2) {
                            openActivity(OrderFinishActivity.class, goodsImg, goodsName, goodsPrice,confirmType);
                        }
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            switch (resultCode) {
                case 1:
                    address_id = data.getStringExtra("address_id");
                    atConfirmOrderTvAddress.setText(data.getStringExtra("address"));
                    Log.e("测试", "onActivityResult: " + address_id);
                    break;
                case 0:
                    if (Const.GIFT.equals(getIntent().getAction())) {
                        goodsId = getIntent().getStringExtra(Const.GOODS_ID);
                        atConfirmOrderRlHongbao.setVisibility(View.VISIBLE);
                        acConfirmOrderRlToDYR.setVisibility(View.GONE);
                        getGiftConfirmData(goodsId);
                    } else {
                        attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
                        goodsId = getIntent().getStringExtra(Const.GOODS_ID);
                        getData();
                    }
                    Log.e("测试", "onActivityResult: ");
                    break;
            }

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
                PayTask alipay = new PayTask(ConfirmOrderActivity.this);
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
                        SharePerferenceUtils.setUserLevel(ConfirmOrderActivity.this, "1");
                        //Toast.makeText(ConfirmOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ConfirmOrderActivity.this,WXPayFinishActivity.class);
                        intent.putExtra("title","支付宝支付");
                        intent.putExtra("content","支付宝支付成功");
                        intent.putExtra("type","1");
                        startActivity(intent);
                    } else {
                        Toast.makeText(ConfirmOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
            }
        }
    };

    public void showPublishPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_local_store, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        //window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        RecyclerView localStore = contentView.findViewById(R.id.pw_localStore_rv_storeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        localStore.setLayoutManager(layoutManager);
        LocalStoreAdapter localStoreAdapter = new LocalStoreAdapter(this, localData);
        localStore.setItemAnimator(new DefaultItemAnimator());
        localStore.setAdapter(localStoreAdapter);
        localStoreAdapter.setOnItemClickListener(new LocalStoreAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                atConfirmOrderTvToStore.setText(localData.get(position).getStore_name());
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

        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }

    public void showHongBaoPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_hongbao, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_180));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_350));
        // 设置PopupWindow的背景

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        //window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        final EditText input = contentView.findViewById(R.id.pw_hongbao_et_input);
        Button confirm = contentView.findViewById(R.id.pw_hongbao_btn_comfirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hongbaoMa = input.getText().toString().trim();
                GiftSubscribe.getBonusList(user_id,token,hongbaoMa,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("测试", "onSuccess: "+result );
                        BonusListResponseBean bean=GsonUtils.fromJson(result,BonusListResponseBean.class);
                        BonusListResponseBean.DataBean data=bean.getData();
                        if(data.getStatus()==1){
                            acConfirmOrderTvHongbao.setText("红包抵扣"+data.getList().getType_money()+"元");
                            atConfirmOrderRlHongbao.setClickable(false);
                            hongbao=data.getList().getType_money();
                            bonus_id=data.getList().getBonus_id()+"";
                            Log.e("测试", "onSuccess: "+payMoney+" "+Double.parseDouble(data.getList().getType_money())+" "+(payMoney-Double.parseDouble(data.getList().getType_money())));
                            payMoney-=Double.parseDouble(data.getList().getType_money());
                            acConfirmOrderTvRealPay.setText("￥" + new DecimalFormat("0.00").format(payMoney));
                            backgroundAlpha(1f);
                            window.dismiss();
                        }else if(data.getStatus()==2){
                            input.setText("");
                            showToast("该红包码已被使用！");
                        }else if(data.getStatus()==3){
                            input.setText("");
                            showToast("无效优惠码！");
                        }

                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }

    public void showYHQPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_store_coupon, null);
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

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
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
}
