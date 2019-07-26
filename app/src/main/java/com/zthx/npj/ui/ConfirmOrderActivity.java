package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.adapter.LocalStoreAdapter;
import com.zthx.npj.adapter.LocationStoreAdapter;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.ConfirmPreSellResponseBean;
import com.zthx.npj.net.been.GIftConfirmResponseBean;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.LocalStoreResponseBean;
import com.zthx.npj.net.been.YsBuyOneBean;
import com.zthx.npj.net.been.YsBuyOneResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends ActivityBase {

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
    @BindView(R.id.ac_confirmOrder_iv_back)
    ImageView acConfirmOrderIvBack;
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
    private String attId;
    private String goodsId;

    ConfirmPreSellResponseBean.DataBean data;
    private String pay_code = "1";
    private ArrayList<LocalStoreResponseBean.DataBean> localData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);

        back(acConfirmOrderIvBack);

        getLocalStore("2");
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

    private void getLocalStore(String type) {
        LocalStoreBean bean = new LocalStoreBean();
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        bean.setPage("1");
        bean.setType(type);
        MainSubscribe.getLocalStore(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: "+result );
                LocalStoreResponseBean bean=GsonUtils.fromJson(result,LocalStoreResponseBean.class);
                localData=bean.getData();
            }

            @Override
            public void onFault(String errorMsg) {
                Log.e("测试", "onSuccess: "+errorMsg);
            }
        }));
    }


    private void getGiftConfirmData(String goodsId) {

        GiftSubscribe.getGiftConfirm(SharePerferenceUtils.getUserId(this), SharePerferenceUtils.getToken(this), goodsId, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
                data = confirmPreSellResponseBean.getData();
                atConfirmOrderTvAddress.setText(data.getAddress());
                atConfirmOrderTvStoreName.setText(data.getStore_name());
                Glide.with(ConfirmOrderActivity.this).load(data.getGoods_img()).into(atConfirmOrderIvPic);
                atConfirmOrderTvTitle.setText(data.getGoods_name());
                atConfirmOrderTvSize.setText("规格： " + data.getAttributes().getId());
                atConfirmOrderTvGoodsPrice.setText("¥" + data.getGoods_price());
                atConfirmOrderTvGoodsNum.setText("x" + data.getAttributes().getPre_number());
                atConfirmOrderTvPrice.setText("¥" + data.getAttributes().getPre_price());

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    @OnClick({R.id.at_confirm_order_rl_ziti, R.id.ac_confirmOrder_btn_pay, R.id.ac_confirmOrder_iv_type3, R.id.ac_confirmOrder_iv_type2, R.id.ac_confirmOrder_iv_type1,R.id.ac_confirmOrder_btn_ziti, R.id.ac_confirmORder_btn_peisong})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ac_confirmOrder_btn_pay:
                YsBuyOneBean bean = new YsBuyOneBean();
                bean.setUser_id(SharePerferenceUtils.getUserId(this));
                bean.setToken(SharePerferenceUtils.getToken(this));
                bean.setAtt_id(attId);
                bean.setPre_id(goodsId);
                bean.setAddress_id(data.getAddress_id()+"");
                bean.setPay_code(pay_code);
                bean.setRemark(acConfirmOrderEtRemark.getText().toString().trim());
                PreSellSubscribe.ysBuyOne(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        YsBuyOneResponseBean bean = GsonUtils.fromJson(result, YsBuyOneResponseBean.class);
                        Log.e("测试", "onSuccess: " + bean.getData());
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
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
                break;
            case R.id.ac_confirmORder_btn_peisong:
                acConfirmOrderBtnZiti.setBackgroundColor(getResources().getColor(R.color.white));
                acConfirmOrderBtnZiti.setTextColor(getResources().getColor(R.color.text3));
                acConfirmORderBtnPeisong.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acConfirmORderBtnPeisong.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }

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
        RecyclerView localStore=contentView.findViewById(R.id.pw_localStore_rv_storeList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        localStore.setLayoutManager(layoutManager);
        Log.e("测试", "showPublishPopwindow: "+localData.get(0).getStore_name());
        LocalStoreAdapter localStoreAdapter=new LocalStoreAdapter(this,localData);
        localStore.setAdapter(localStoreAdapter);
        localStoreAdapter.setOnItemClickListener(new LocalStoreAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                atConfirmOrderTvToStore.setText(localData.get(position).getStore_name());
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
