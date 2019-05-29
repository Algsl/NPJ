package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreQuotationAdapter;
import com.zthx.npj.adapter.WantBuyManagerListAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WantBuyManagerActivity extends AppCompatActivity {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.at_want_buy_manager_rv_supply_list1)
    RecyclerView atWantBuyManagerRvSupplyList1;
    @BindView(R.id.at_swant_buy_manager_rv_supply_list2)
    RecyclerView atSwantBuyManagerRvSupplyList2;
    @BindView(R.id.at_want_buy_manager_ll_supply_list)
    LinearLayout atWantBuyManagerLlSupplyList;
    @BindView(R.id.at_want_buy_manager_rv_supply_bill)
    RecyclerView atWantBuyManagerRvSupplyBill;
    @BindView(R.id.at_want_buy_manager_ll_supply_bill)
    LinearLayout atWantBuyManagerLlSupplyBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_buy_manager);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        atWantBuyManagerRvSupplyList1.setLayoutManager(manager);
        atSwantBuyManagerRvSupplyList2.setLayoutManager(manager);
        atWantBuyManagerRvSupplyBill.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        WantBuyManagerListAdapter mAdapter1 = new WantBuyManagerListAdapter(this,list);
        StoreQuotationAdapter mAdapter2 = new StoreQuotationAdapter(this,list);
        mAdapter2.setOnItemClickListener(new StoreQuotationAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(WantBuyManagerActivity.this, StoreQuotationListActivity.class));
            }
        });
        atWantBuyManagerRvSupplyList1.setAdapter(mAdapter1);
        atSwantBuyManagerRvSupplyList2.setAdapter(mAdapter1);
        atWantBuyManagerRvSupplyBill.setAdapter(mAdapter2);
    }
}
