package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.ConfirmPreSellResponseBean;
import com.zthx.npj.net.been.ConfirmSupplyResponseBean;
import com.zthx.npj.net.been.GIftConfirmResponseBean;
import com.zthx.npj.net.been.YsBuyOneBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends AppCompatActivity {

    @BindView(R.id.at_confirm_order_rl_ziti)
    RelativeLayout atConfirmOrderRlZiti;
    @BindView(R.id.at_confirm_order_rl_title)
    RelativeLayout atConfirmOrderRlTitle;
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
    @BindView(R.id.at_confirm_order_tv_jin)
    TextView atConfirmOrderTvJin;
    @BindView(R.id.at_confirm_order_rl_hongbao)
    RelativeLayout atConfirmOrderRlHongbao;
    @BindView(R.id.ac_confirmOrder_btn_pay)
    Button acConfirmOrderBtnPay;
    @BindView(R.id.ac_confirmOrder_et_remark)
    EditText acConfirmOrderEtRemark;
    private String attId;
    private String goodsId;

    ConfirmPreSellResponseBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        if (Const.GIFT.equals(getIntent().getAction())) {
            goodsId = getIntent().getStringExtra(Const.GOODS_ID);
            atConfirmOrderRlHongbao.setVisibility(View.VISIBLE);
            getGiftConfirmData(goodsId);
        } else {
            attId = getIntent().getStringExtra(Const.ATTRIBUTE_ID);
            goodsId = getIntent().getStringExtra(Const.GOODS_ID);
            getData();
        }

    }

    private void getGiftConfirmData(String goodsId) {

        GiftSubscribe.getGiftConfirm(SharePerferenceUtils.getUserId(this), BaseConstant.TOKEN, goodsId, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                GIftConfirmResponseBean gIftConfirmResponseBean = GsonUtils.fromJson(result, GIftConfirmResponseBean.class);
                atConfirmOrderTvTitle.setText(gIftConfirmResponseBean.getTitle());
                Glide.with(ConfirmOrderActivity.this).load(gIftConfirmResponseBean.getImg()).into(atConfirmOrderIvPic);
                atConfirmOrderTvGoodsPrice.setText(gIftConfirmResponseBean.getPrice());
                if (gIftConfirmResponseBean.getStatus() == 0) {
                    atConfirmOrderRlHongbao.setVisibility(View.GONE);
                }
                atConfirmOrderTvStoreName.setText(gIftConfirmResponseBean.getStore_name());
                atConfirmOrderTvAddress.setText(gIftConfirmResponseBean.getAddress_id() + "");
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    private void getData() {
        ConfirmPreSellBean bean = new ConfirmPreSellBean();
        bean.setToken(BaseConstant.TOKEN);
        bean.setAtt_id(attId);
        bean.setPre_id(goodsId);
        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        PreSellSubscribe.getConfirmPreSell(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ConfirmPreSellResponseBean confirmPreSellResponseBean = GsonUtils.fromJson(result, ConfirmPreSellResponseBean.class);
                data=confirmPreSellResponseBean.getData();
                atConfirmOrderTvAddress.setText(data.getAddress());
                atConfirmOrderTvStoreName.setText(data.getStore_name());
                Glide.with(ConfirmOrderActivity.this).load(data.getGoods_img()).into(atConfirmOrderIvPic);
                atConfirmOrderTvTitle.setText(data.getGoods_name());
                atConfirmOrderTvSize.setText("规格： " +data.getAttributes().getId() );
                atConfirmOrderTvGoodsPrice.setText("¥" + data.getGoods_price());
                atConfirmOrderTvGoodsNum.setText("x" + data.getAttributes().getPre_number());
                atConfirmOrderTvPrice.setText("¥" + data.getAttributes().getPre_price());

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    @OnClick({R.id.at_confirm_order_rl_ziti, R.id.ac_confirmOrder_btn_pay})
    public void onViewClicked(View v) {
        showPopupWindow();
        switch (v.getId()) {
            case R.id.at_confirm_order_rl_ziti:
                break;
            case R.id.ac_confirmOrder_btn_pay:
                YsBuyOneBean bean = new YsBuyOneBean();
                bean.setUser_id(SharePerferenceUtils.getUserId(this));
                bean.setToken(SharePerferenceUtils.getToken(this));
                bean.setAtt_id(attId);
                bean.setPre_id(goodsId);
                bean.setAddress_id(data.getAddress_id()+"");
                bean.setPay_code("1");
                bean.setRemark(acConfirmOrderEtRemark.getText().toString().trim());
                PreSellSubscribe.ysBuyOne(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }

    private void showPopupWindow() {

    }

}
