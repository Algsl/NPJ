package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyStoreResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyStoreActivity extends AppCompatActivity {

    @BindView(R.id.at_my_store_push_goods)
    LinearLayout atMyStorePushGoods;
    @BindView(R.id.at_my_store_goods_bill)
    LinearLayout atMyStoreGoodsBill;
    @BindView(R.id.at_my_store_goods_list)
    LinearLayout atMyStoreGoodsList;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.ac_myStore_mcv_storeImg)
    MyCircleView acMyStoreMcvStoreImg;
    @BindView(R.id.ac_myStore_tv_storeName)
    TextView acMyStoreTvStoreName;
    @BindView(R.id.ac_myStore_tv_reputation)
    TextView acMyStoreTvReputation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store);
        ButterKnife.bind(this);
        getMyStore();
    }

    private void getMyStore() {
        String user_id= SharePerferenceUtils.getUserId(this);
        String token=SharePerferenceUtils.getToken(this);
        SetSubscribe.myStore(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setMyStore(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setMyStore(String result) {
        MyStoreResponseBean bean= GsonUtils.fromJson(result,MyStoreResponseBean.class);
        MyStoreResponseBean.DataBean data=bean.getData();
        Glide.with(this).load(Uri.parse(data.getStore_img())).into(acMyStoreMcvStoreImg);
        acMyStoreTvStoreName.setText(data.getStore_name());
        acMyStoreTvReputation.setText("信誉分："+data.getReputation());
    }

    @OnClick({R.id.at_my_store_push_goods, R.id.at_my_store_goods_bill, R.id.at_my_store_goods_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_store_push_goods:
                startActivity(new Intent(this, PublishGoodsActivity.class));
                break;
            case R.id.at_my_store_goods_bill:
                startActivity(new Intent(this, StoreGoodsBillActivity.class));
                break;
            case R.id.at_my_store_goods_list:
                startActivity(new Intent(this, StoreGoodsListActivity.class));
                break;
        }
    }
}
