package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.ClassifyDetailAdapter;
import com.zthx.npj.net.been.GoodsListResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassfiyDetailActivity extends ActivityBase {

    @BindView(R.id.at_classfiy_detail_rv)
    RecyclerView atClassfiyDetailRv;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_classify_tv_noGoodsHint)
    TextView acClassifyTvNoGoodsHint;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.seeMore)
    TextView seeMore;

    private int page = 0;
    private ClassifyDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiy_detail);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, getIntent().getStringExtra("key1"));

        getGoodsList();

        //通过findViewById拿到RecyclerView实例
        //设置RecyclerView管理器


        //设置添加或删除item时的动画，这里使用默认动画

        //设置适配器
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 0;
                if (adapter != null) {
                    adapter.clearData();
                }
                seeMore.setText("查看更多");
                getGoodsList();
                refreshLayout.setLoadmoreFinished(false);
                refreshlayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                getGoodsList();
                refreshlayout.finishLoadmore();
            }
        });
    }

    private void getGoodsList() {
        MainSubscribe.goodsList(getIntent().getStringExtra("key0"), page + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setGoodsList(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setGoodsList(String result) {
        Log.e("测试", "setGoodsList: " + result);
        GoodsListResponseBean bean = GsonUtils.fromJson(result, GoodsListResponseBean.class);
        final ArrayList<GoodsListResponseBean.DataBean> data = bean.getData();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        atClassfiyDetailRv.setLayoutManager(layoutManager);

        if (data.size() == 0 && page == 0) {
            atClassfiyDetailRv.setVisibility(View.GONE);
            acClassifyTvNoGoodsHint.setVisibility(View.VISIBLE);
        } else {
            atClassfiyDetailRv.setVisibility(View.VISIBLE);
            acClassifyTvNoGoodsHint.setVisibility(View.GONE);
        }

        if (adapter == null) {
            adapter = new ClassifyDetailAdapter(this, data);
        } else {
            if (data != null && data.size()!=0) {
                if(data.size()<10){
                    seeMore.setText("没有更多了");
                    refreshLayout.setLoadmoreFinished(true);
                }
                adapter.addData(data);
            }
        }

        atClassfiyDetailRv.setItemAnimator(new DefaultItemAnimator());
        atClassfiyDetailRv.setAdapter(adapter);
        //atClassfiyDetailRv.smoothScrollToPosition(goodsData.size()-10);
        adapter.setOnItemClickListener(new ClassifyDetailAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position, ArrayList<GoodsListResponseBean.DataBean> mList) {
                Intent intent = new Intent(ClassfiyDetailActivity.this, GoodsDetailActivity.class);
                intent.putExtra("goods_id", mList.get(position).getId() + "");
                startActivity(intent);
            }
        });
    }

}
