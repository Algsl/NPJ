package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyOrderDetailResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyStoreOrderDetailActivity extends ActivityBase {
    @BindView(R.id.at_myOrderDetail_rl_title)
    RelativeLayout atMyOrderDetailRlTitle;
    @BindView(R.id.at_myOrderDetail_ll_address)
    LinearLayout atMyOrderDetailLlAddress;
    @BindView(R.id.item_choice_address_tv_name)
    TextView itemChoiceAddressTvName;
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
    @BindView(R.id.at_myOrderDetail_rl_address)
    RelativeLayout atMyOrderDetailRlAddress;
    @BindView(R.id.item_choice_address_tv_phone)
    TextView itemChoiceAddressTvPhone;
    @BindView(R.id.item_choice_address_tv_address)
    TextView itemChoiceAddressTvAddress;
    @BindView(R.id.at_myOrderDetail_tv_orderSn)
    TextView atMyOrderDetailTvOrderSn;
    @BindView(R.id.at_myOrderDetail_tv_payType)
    TextView atMyOrderDetailTvPayType;
    @BindView(R.id.at_myOrderDetail_tv_createTime)
    TextView atMyOrderDetailTvCreateTime;
    @BindView(R.id.at_myOrderDetail_tv_payTime)
    TextView atMyOrderDetailTvPayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_detail);
        ButterKnife.bind(this);


        getMyStoreOrderDetail();
    }

    private void getMyStoreOrderDetail() {
        String user_id = SharePerferenceUtils.getUserId(this);
        String token = SharePerferenceUtils.getToken(this);
        String order_id = getIntent().getStringExtra("order_id");
        SetSubscribe.myOrderDetail(user_id, token, order_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
        MyOrderDetailResponseBean bean = GsonUtils.fromJson(result, MyOrderDetailResponseBean.class);
        MyOrderDetailResponseBean.DataBean data = bean.getData();
        if (data.getAddress().equals(null)) {

        } else {
            atMyOrderDetailLlAddress.setVisibility(View.GONE);
            atMyOrderDetailRlAddress.setVisibility(View.VISIBLE);
            itemChoiceAddressTvName.setText(data.getConsignee());
            itemChoiceAddressTvPhone.setText(data.getMobile());
            itemChoiceAddressTvAddress.setText(data.getHouse_number());
            atMyOrderDetailTvStoreName.setText("京东旗舰店");
            Glide.with(this).load(Uri.parse(data.getGoods_img())).into(atMyOrderDetailIvGoodsImg);
            atMyOrderDetailTvGoodsName.setText(data.getGoods_name());
            atMyOrderDetailTvGoodsPrice.setText(data.getGoods_price());
            atMyOrderDetailTvGoodsNum.setText("x " + data.getGoods_num());
            atMyOrderDetailTvIsFreeShipping.setText(data.getShipping_fee());
            atMyOrderDetailTvOrderPrice.setText(data.getOrder_price());
            atMyOrderDetailTvOrderSn.setText(data.getOrder_sn());
            atMyOrderDetailTvCreateTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data.getOrder_time())));
            atMyOrderDetailTvPayTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data.getOrder_time())));
        }
    }

}
