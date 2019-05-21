package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.StoreGoodsBillAdapter;
import com.zthx.npj.adapter.SupplyManagerListAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupplyManagerActivity extends AppCompatActivity {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.at_supply_manager_rv_supply_list1)
    RecyclerView atSupplyManagerRvSupplyList1;
    @BindView(R.id.at_supply_manager_rv_supply_list2)
    RecyclerView atSupplyManagerRvSupplyList2;
    @BindView(R.id.at_supply_manager_ll_supply_list)
    LinearLayout atSupplyManagerLlSupplyList;
    @BindView(R.id.at_supply_manager_rv_supply_bill)
    RecyclerView atSupplyManagerRvSupplyBill;
    @BindView(R.id.at_supply_manager_ll_supply_bill)
    LinearLayout atSupplyManagerLlSupplyBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_manager);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        atSupplyManagerRvSupplyList1.setLayoutManager(manager);
        atSupplyManagerRvSupplyList2.setLayoutManager(manager);
        atSupplyManagerRvSupplyBill.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        SupplyManagerListAdapter mAdapter1 = new SupplyManagerListAdapter(this,list);
        StoreGoodsBillAdapter mAdapter2 = new StoreGoodsBillAdapter(this,list);
        atSupplyManagerRvSupplyList1.setAdapter(mAdapter1);
        atSupplyManagerRvSupplyList2.setAdapter(mAdapter1);
        atSupplyManagerRvSupplyBill.setAdapter(mAdapter2);
    }
}
