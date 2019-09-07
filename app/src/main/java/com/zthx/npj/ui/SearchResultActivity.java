package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.SearchResultAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.SearchResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchResultActivity extends ActivityBase {
    @BindView(R.id.at_home_search_iv_back)
    ImageView atHomeSearchIvBack;
    @BindView(R.id.at_home_search_et_search)
    EditText atHomeSearchEtSearch;
    @BindView(R.id.at_home_search_tv_search)
    TextView atHomeSearchTvSearch;
    @BindView(R.id.at_home_search_rv_result)
    RecyclerView atHomeSearchRvResult;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String searchTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_search);
        ButterKnife.bind(this);

        back(atHomeSearchIvBack);
        searchTitle = getIntent().getStringExtra("key0");
        atHomeSearchEtSearch.setHint(searchTitle);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                setSearchResult(searchTitle);
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        setSearchResult(searchTitle);
    }

    @OnClick({R.id.at_home_search_et_search, R.id.at_home_search_tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_home_search_et_search:
                atHomeSearchEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_SEARCH) {
                            setSearchResult(atHomeSearchEtSearch.getText().toString().trim());
                        }
                        return true;
                    }
                });
                break;
            case R.id.at_home_search_tv_search:
                setSearchResult(atHomeSearchEtSearch.getText().toString().trim());
                break;
        }
    }

    private void setSearchResult(String str) {
        MainSubscribe.getSearchResult(user_id, str, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LinearLayoutManager manager = new LinearLayoutManager(SearchResultActivity.this, LinearLayoutManager.VERTICAL, false);
                atHomeSearchRvResult.setLayoutManager(manager);
                SearchResponseBean searchResponseBean = GsonUtils.fromJson(result, SearchResponseBean.class);
                final ArrayList<SearchResponseBean.DataBean> data = searchResponseBean.getData();
                if (data.size() <= 0) {
                    atHomeSearchRvResult.setVisibility(View.GONE);
                } else {
                    atHomeSearchRvResult.setVisibility(View.VISIBLE);
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchResultActivity.this);
                atHomeSearchRvResult.setLayoutManager(layoutManager);
                SearchResultAdapter adapter = new SearchResultAdapter(SearchResultActivity.this, data);
                atHomeSearchRvResult.setItemAnimator(new DefaultItemAnimator());
                atHomeSearchRvResult.setAdapter(adapter);
                adapter.setOnItemClickListener(new SearchResultAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(SearchResultActivity.this, GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }
}
