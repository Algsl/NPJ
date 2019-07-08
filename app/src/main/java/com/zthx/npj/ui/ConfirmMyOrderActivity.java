package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.net.been.BuyBean;
import com.zthx.npj.net.been.ConfirmOrderResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmMyOrderActivity extends AppCompatActivity {
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

    String user_id= SharePerferenceUtils.getUserId(this);
    String token=SharePerferenceUtils.getToken(this);
    String pay_code="1";
    ConfirmOrderResponseBean.DataBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_myorder);
        ButterKnife.bind(this);
        getMyConfirmOrder();
    }

    private void getMyConfirmOrder() {
        String order_id=getIntent().getStringExtra("order_id");
        SetSubscribe.confirmOrder(user_id,token,order_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        ConfirmOrderResponseBean bean= GsonUtils.fromJson(result,ConfirmOrderResponseBean.class);
        data=bean.getData();
        atConfirmMyorderTvAddress.setText(data.getAddress());
        atConfirmMyorderTvStoreName.setText(data.getStore_name());
        Glide.with(this).load(Uri.parse(data.getGoods_img())).into(atConfirmMyorderIvGoodsImg);
        atConfirmMyorderTvGoodsName.setText(data.getGoods_name());
        atConfirmMyorderTvGoodsPrice.setText("￥ "+data.getGoods_price());
        atConfirmOrderTvGoodsNum.setText("x "+data.getGoods_num());
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
                pay_code="3";
                break;
            case R.id.ac_confirm_myorder_payType2:
                acConfirmMyorderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmMyorderPayType2.setImageResource(R.drawable.confirm_select);
                acConfirmMyorderPayType3.setImageResource(R.drawable.confirm_unselect);
                pay_code="2";
                break;
            case R.id.ac_confirm_myorder_payType3:
                acConfirmMyorderPayType1.setImageResource(R.drawable.confirm_unselect);
                acConfirmMyorderPayType2.setImageResource(R.drawable.confirm_unselect);
                acConfirmMyorderPayType3.setImageResource(R.drawable.confirm_select);
                pay_code="1";
                break;
            case R.id.ac_confirmOrder_btn_pay:
                BuyBean bean=new BuyBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setOrder_id(getIntent().getStringExtra("order_id"));
                bean.setAddress_id(data.getAddress_id()+"");
                bean.setPay_code(pay_code);
                SetSubscribe.buy(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        IWXAPI api = WXAPIFactory.createWXAPI(getBaseContext(), null);
                        api.registerApp("wx0f4f8d4b6b85a921");
                        PayReq req = new PayReq();
                        req.appId           = "wx0f4f8d4b6b85a921";//你的微信appid
                        req.partnerId       = "1512847301";//商户号
                        req.prepayId        = "wx221459195887969f1343b9602040846145";//预支付交易会话ID
                        req.nonceStr        = "58uefEqK98rpSPob0sBzDcWvnOJtjvOr";//随机字符串
                        req.timeStamp       = "1555916317";//时间戳
                        req.packageValue    = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
                        req.sign            = "5F382CC6A49849E565266D0B67140FD4";//签名
//              req.extData         = "app data"; // optional
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }
}
