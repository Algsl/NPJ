package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zthx.npj.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_my_store_push_goods, R.id.at_my_store_goods_bill, R.id.at_my_store_goods_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_store_push_goods:
                startActivity(new Intent(this, PublishGoodsActivity.class));
                break;
            case R.id.at_my_store_goods_bill:
                startActivity(new Intent(this,StoreGoodsBillActivity.class));
                break;
            case R.id.at_my_store_goods_list:
                startActivity(new Intent(this, StoreGoodsListActivity.class));
                break;
        }
    }
}
