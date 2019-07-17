package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.VideoSearchAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SearchResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends ActivityBase {

    @BindView(R.id.at_home_search_rv)
    RecyclerView atHomeSearchRv;
    @BindView(R.id.at_home_search_iv_back)
    ImageView atHomeSearchIvBack;
    @BindView(R.id.at_home_search_et_search)
    EditText atHomeSearchEtSearch;
    @BindView(R.id.at_home_search_tv_search)
    TextView atHomeSearchTvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        back(atHomeSearchIvBack);
    }

    @OnClick(R.id.at_home_search_tv_search)
    public void onViewClicked() {

        MainSubscribe.getSearchResult(atHomeSearchEtSearch.getText().toString().trim(),new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LinearLayoutManager manager = new LinearLayoutManager(HomeSearchActivity.this, LinearLayoutManager.VERTICAL, false);
                atHomeSearchRv.setLayoutManager(manager);
                SearchResponseBean searchResponseBean = GsonUtils.fromJson(result, SearchResponseBean.class);
                ArrayList<SearchResponseBean.DataBean> data = searchResponseBean.getData();

//                VideoSearchAdapter mAdapter = new VideoSearchAdapter(this, data);
//                atHomeSearchRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));

    }
}
