package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MySupplyOrderConfirmResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.at_myOrderDetail_tv_orderPrice)
    TextView atMyOrderDetailTvOrderPrice;
    @BindView(R.id.at_myOrderDetail_tv_orderSn)
    TextView atMyOrderDetailTvOrderSn;
    @BindView(R.id.at_myOrderDetail_tv_payType)
    TextView atMyOrderDetailTvPayType;
    @BindView(R.id.at_myOrderDetail_tv_createTime)
    TextView atMyOrderDetailTvCreateTime;
    @BindView(R.id.at_myOrderDetail_tv_payTime)
    TextView atMyOrderDetailTvPayTime;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.at_myOrderDetail_tv_address)
    TextView atMyOrderDetailTvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_detail);
        ButterKnife.bind(this);

        back(titleBack);
        titleBack.setImageResource(R.drawable.goods_detial_back);
        changeTitle(acTitle, "订单详情");
        changeRightImg(acTitleIv, R.drawable.goods_detail_home, null, null);

        getMyStoreOrderDetail();
    }

    private void getMyStoreOrderDetail() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
        String order_id = getIntent().getStringExtra("order_id");
        SetSubscribe.mySupplyOrderConfirm(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        MySupplyOrderConfirmResponseBean bean = GsonUtils.fromJson(result, MySupplyOrderConfirmResponseBean.class);
        MySupplyOrderConfirmResponseBean.DataBean data = bean.getData();
        if (data.getAddress().equals(null)) {

        } else {
            /*atMyOrderDetailLlAddress.setVisibility(View.GONE);
            atMyOrderDetailRlAddress.setVisibility(View.VISIBLE);
            itemChoiceAddressTvName.setText(data.getConsignee());
            itemChoiceAddressTvPhone.setText(data.getMobile());
            itemChoiceAddressTvAddress.setText(data.getAddress());*/
            atMyOrderDetailTvAddress.setText(data.getAddress());
            atMyOrderDetailTvStoreName.setText("京东旗舰店");

            Glide.with(this).load(Uri.parse(data.getGoods_img())).into(atMyOrderDetailIvGoodsImg);
            atMyOrderDetailTvGoodsName.setText(data.getTitle());
            atMyOrderDetailTvGoodsPrice.setText("￥" + data.getGoods_price());
            atMyOrderDetailTvGoodsNum.setText("x " + data.getOrder_num());
            atMyOrderDetailTvIsFreeShipping.setText(data.getShipping_fee());
            atMyOrderDetailTvOrderPrice.setText(data.getOrder_price());
            atMyOrderDetailTvOrderSn.setText(data.getOrder_sn());
            atMyOrderDetailTvCreateTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            atMyOrderDetailTvPayTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        }
    }


}
