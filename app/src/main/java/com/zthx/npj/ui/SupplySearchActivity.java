package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverNeedAdapter;
import com.zthx.npj.adapter.DiscoverSupplyAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.NeedListResponseBean;
import com.zthx.npj.net.been.SupplyListResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplySearchActivity extends ActivityBase {
    @BindView(R.id.ac_supplySearch_iv_back)
    ImageView acSupplySearchIvBack;
    @BindView(R.id.ac_supplySearch_et_search)
    EditText acSupplySearchEtSearch;
    @BindView(R.id.ac_supplySearch_tv_supply)
    TextView acSupplySearchTvSupply;
    @BindView(R.id.ac_supply_tv_qiugou)
    TextView acSupplyTvQiugou;
    @BindView(R.id.ac_supplySearch_rv_supply)
    RecyclerView acSupplySearchRvSupply;
    @BindView(R.id.ac_supplySearch_rv_qiugou)
    RecyclerView acSupplySearchRvQiugou;
    @BindView(R.id.ac_supplySearch_tv_search)
    TextView acSupplySearchTvSearch;
    @BindView(R.id.ac_supplySearch_ll_show)
    LinearLayout acSupplySearchLlShow;

    private String searchType = "1";//默认设置为供应搜索
    private String searchKeyword = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_search);
        ButterKnife.bind(this);

        back(acSupplySearchIvBack);
    }

    @OnClick({R.id.ac_supplySearch_tv_search, R.id.ac_supplySearch_tv_supply, R.id.ac_supply_tv_qiugou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_supplySearch_tv_search:
                searchKeyword = acSupplySearchEtSearch.getText().toString().trim();
                if(searchKeyword.equals("")){
                    showToast("请输入您想要的产品");
                }else{
                    acSupplySearchLlShow.setVisibility(View.VISIBLE);
                    getSearchResult(searchType);
                }
                break;
            case R.id.ac_supplySearch_tv_supply:
                acSupplySearchTvSupply.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acSupplySearchTvSupply.setTextColor(getResources().getColor(R.color.white));
                acSupplyTvQiugou.setBackgroundColor(getResources().getColor(R.color.white));
                acSupplyTvQiugou.setTextColor(getResources().getColor(R.color.text3));

                acSupplySearchRvSupply.setVisibility(View.VISIBLE);
                acSupplySearchRvQiugou.setVisibility(View.GONE);
                searchType = "1";
                getSupplySearch();
                break;
            case R.id.ac_supply_tv_qiugou:
                acSupplyTvQiugou.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acSupplyTvQiugou.setTextColor(getResources().getColor(R.color.white));
                acSupplySearchTvSupply.setBackgroundColor(getResources().getColor(R.color.white));
                acSupplySearchTvSupply.setTextColor(getResources().getColor(R.color.text3));

                acSupplySearchRvQiugou.setVisibility(View.VISIBLE);
                acSupplySearchRvSupply.setVisibility(View.GONE);
                searchType = "2";
                getQiuGouSearch();
                break;
        }
    }

    private void getSearchResult(String type) {
        switch (type) {
            case "1":
                getSupplySearch();
                break;
            case "2":
                getQiuGouSearch();
                break;
        }
    }

    private void getSupplySearch() {
        DiscoverSubscribe.supplySearch(searchKeyword, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SupplyListResponseBean supplyListResponseBean = GsonUtils.fromJson(result, SupplyListResponseBean.class);
                if(supplyListResponseBean==null){
                    acSupplySearchRvSupply.setVisibility(View.GONE);
                }else{
                    acSupplySearchRvSupply.setVisibility(View.VISIBLE);
                }
                final ArrayList<SupplyListResponseBean.DataBean> data = supplyListResponseBean.getData();
                LinearLayoutManager manager = new LinearLayoutManager(SupplySearchActivity.this, LinearLayoutManager.VERTICAL, false);
                acSupplySearchRvSupply.setLayoutManager(manager);
                DiscoverSupplyAdapter mAdapter = new DiscoverSupplyAdapter(SupplySearchActivity.this, data,true);
                mAdapter.notifyDataSetChanged();
                mAdapter.setOnItemClickListener(new DiscoverSupplyAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<SupplyListResponseBean.DataBean> list) {
                        Intent intent = new Intent(SupplySearchActivity.this, SupplyProductsActivity.class);
                        intent.setAction(Const.SUPPLY_DETAIL);
                        intent.putExtra("goods_id", data.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                acSupplySearchRvSupply.setItemAnimator(new DefaultItemAnimator());
                acSupplySearchRvSupply.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getQiuGouSearch() {
        DiscoverSubscribe.qiugouSearch(searchKeyword, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                NeedListResponseBean bean = GsonUtils.fromJson(result, NeedListResponseBean.class);
                if(bean==null){
                    acSupplySearchRvQiugou.setVisibility(View.GONE);
                }else{
                    acSupplySearchRvQiugou.setVisibility(View.VISIBLE);
                }
                final ArrayList<NeedListResponseBean.DataBean> data=bean.getData();
                LinearLayoutManager manager = new LinearLayoutManager(SupplySearchActivity.this, LinearLayoutManager.VERTICAL, false);
                acSupplySearchRvQiugou.setLayoutManager(manager);
                DiscoverNeedAdapter mAdapter2 = new DiscoverNeedAdapter(SupplySearchActivity.this, data,true);
                mAdapter2.notifyDataSetChanged();
                mAdapter2.setOnItemClickListener(new DiscoverNeedAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<NeedListResponseBean.DataBean> list) {
                        Intent intent = new Intent(SupplySearchActivity.this, SupplyProductsActivity.class);
                        intent.setAction(Const.NEED_DETAIL);
                        intent.putExtra("goods_id", data.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
                acSupplySearchRvQiugou.setItemAnimator(new DefaultItemAnimator());
                acSupplySearchRvQiugou.setAdapter(mAdapter2);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
