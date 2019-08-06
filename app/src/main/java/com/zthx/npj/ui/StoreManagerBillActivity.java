package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreBillMessageAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.StoreManagerBillResponseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreManagerBillActivity extends ActivityBase {

    @BindView(R.id.at_store_manager_bill_rv)
    RecyclerView atStoreManagerBillRv;
    @BindView(R.id.at_store_manager_bill_tv_money)
    TextView atStoreManagerBillTvMoney;
    @BindView(R.id.at_store_manager_bill_tv_log)
    TextView atStoreManagerBillTvLog;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manager_bill);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"账单");


        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atStoreManagerBillRv.setLayoutManager(manager);
        StoreManagerBillResponseBean bean=new StoreManagerBillResponseBean();
        StoreBillMessageAdapter mAdapter = new StoreBillMessageAdapter(this, bean.getData());
        atStoreManagerBillRv.setAdapter(mAdapter);
    }
}
