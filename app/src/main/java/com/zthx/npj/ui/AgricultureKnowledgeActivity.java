package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AKAdapter;
import com.zthx.npj.adapter.NewsListAdapter;
import com.zthx.npj.adapter.OtherSearchAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AkListResponseBean;
import com.zthx.npj.net.been.NewsListResponseBean;
import com.zthx.npj.net.been.OtherSearchResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.activity.MessageCenterActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgricultureKnowledgeActivity extends ActivityBase {

    @BindView(R.id.at_ak_rv)
    RecyclerView atAkRv;
    @BindView(R.id.at_ak_iv_search)
    EditText atAkIvSearch;
    @BindView(R.id.at_ak_iv_back)
    ImageView atAkIvBack;
    @BindView(R.id.at_ak_iv_1)
    ImageView atAkIv1;
    @BindView(R.id.at_ak_tv_1)
    TextView atAkTv1;
    @BindView(R.id.at_ak_ll_1)
    LinearLayout atAkLl1;
    @BindView(R.id.at_ak_iv_2)
    ImageView atAkIv2;
    @BindView(R.id.at_ak_tv_2)
    TextView atAkTv2;
    @BindView(R.id.at_ak_ll_2)
    LinearLayout atAkLl2;
    @BindView(R.id.at_ak_iv_3)
    ImageView atAkIv3;
    @BindView(R.id.at_ak_tv_3)
    TextView atAkTv3;
    @BindView(R.id.at_ak_ll_3)
    LinearLayout atAkLl3;
    @BindView(R.id.at_ak_iv_4)
    ImageView atAkIv4;
    @BindView(R.id.at_ak_tv_4)
    TextView atAkTv4;
    @BindView(R.id.at_ak_ll_4)
    LinearLayout atAkLl4;
    @BindView(R.id.ac_agriculture_tv_searchResult)
    TextView acAgricultureTvSearchResult;
    @BindView(R.id.ac_agriculture_ll1)
    LinearLayout acAgricultureLl1;
    @BindView(R.id.ac_agriculture_ll2)
    LinearLayout acAgricultureLl2;
    @BindView(R.id.ac_agriculture_knowledge_iv_message)
    ImageView acAgricultureKnowledgeIvMessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private AKAdapter mAdapter;

    private String type="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_knowledge);
        ButterKnife.bind(this);

        back(atAkIvBack);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if(type.equals("1")){
                    getData(type);
                }else{
                    getNewsList(type);
                }
                refreshLayout.finishRefresh();
                showToast("刷新完成");
            }
        });

        getData("1");
    }

    private void getNewsList(final String s) {
        DiscoverSubscribe.newsList(s, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                NewsListResponseBean bean = GsonUtils.fromJson(result, NewsListResponseBean.class);
                final ArrayList<NewsListResponseBean.DataBean> data = bean.getData();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AgricultureKnowledgeActivity.this);
                atAkRv.setLayoutManager(layoutManager);
                NewsListAdapter adapter = new NewsListAdapter(AgricultureKnowledgeActivity.this, data);
                adapter.notifyDataSetChanged();
                atAkRv.setItemAnimator(new DefaultItemAnimator());
                atAkRv.setAdapter(adapter);
                adapter.setOnItemClickListener(new NewsListAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        openActivity(NewsDetailActivity.class, data.get(position).getId() + "");
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }


    @OnClick({R.id.at_ak_ll_1, R.id.at_ak_ll_2, R.id.at_ak_ll_3, R.id.at_ak_ll_4, R.id.at_ak_iv_search,
            R.id.ac_agriculture_knowledge_iv_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_ak_ll_1:
                type="1";
                getData("1");
                changeBackground("1");
                break;
            case R.id.at_ak_ll_2:
                type="3";
                getNewsList("3");
                changeBackground("2");
                break;
            case R.id.at_ak_ll_3:
                type="4";
                getNewsList("4");
                changeBackground("3");
                break;
            case R.id.at_ak_ll_4:
                type="5";
                getNewsList("5");
                changeBackground("4");
                break;
            case R.id.at_ak_iv_search:
                atAkIvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_SEARCH) {
                            acAgricultureLl1.setVisibility(View.VISIBLE);
                            acAgricultureLl2.setVisibility(View.GONE);
                            getSearchResult(atAkIvSearch.getText().toString().trim());
                        }
                        return true;
                    }
                });
                break;
            case R.id.ac_agriculture_knowledge_iv_message:
                openActivity(MessageCenterActivity.class);
                break;
        }
    }

    private void getSearchResult(String trim) {
        DiscoverSubscribe.otherSearch(trim, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                /*AkListResponseBean bean = GsonUtils.fromJson(result, AkListResponseBean.class);
                final ArrayList<AkListResponseBean.DataBean> data = bean.getData();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AgricultureKnowledgeActivity.this);
                atAkRv.setLayoutManager(layoutManager);
                AKAdapter adapter = new AKAdapter(AgricultureKnowledgeActivity.this, data);
                atAkRv.setItemAnimator(new DefaultItemAnimator());
                atAkRv.setAdapter(adapter);
                if (data.size() > 0) {
                    acAgricultureTvSearchResult.setText("共搜索到" + data.size() + "个相关记录");
                } else {
                    acAgricultureTvSearchResult.setText("共搜索到0个相关记录");
                }

                adapter.setOnItemClickListener(new AKAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(AgricultureKnowledgeActivity.this, AgricultureVideoMainActivity.class);
                        intent.putExtra(Const.VIDEO_ID, data.get(position).getId() + "");
                        startActivity(intent);
                    }
                });*/
                OtherSearchResponseBean bean = GsonUtils.fromJson(result, OtherSearchResponseBean.class);
                final ArrayList<OtherSearchResponseBean.DataBean> data = bean.getData();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AgricultureKnowledgeActivity.this);
                atAkRv.setLayoutManager(layoutManager);
                OtherSearchAdapter adapter = new OtherSearchAdapter(AgricultureKnowledgeActivity.this, data);
                atAkRv.setItemAnimator(new DefaultItemAnimator());
                atAkRv.setAdapter(adapter);
                if (data.size() > 0) {
                    acAgricultureTvSearchResult.setText("共搜索到" + data.size() + "个相关记录");
                } else {
                    acAgricultureTvSearchResult.setText("共搜索到0个相关记录");
                }

                adapter.setOnItemClickListener(new OtherSearchAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        openActivity(NewsDetailActivity.class, data.get(position).getId() + "");
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getData(String id) {
        DiscoverSubscribe.getKnowledgeList(SharePerferenceUtils.getUserId(this), id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AkListResponseBean akListResponseBean = GsonUtils.fromJson(result, AkListResponseBean.class);
                final ArrayList<AkListResponseBean.DataBean> data = akListResponseBean.getData();
                if (mAdapter != null) {
                    mAdapter.setNewData(data);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(AgricultureKnowledgeActivity.this, LinearLayoutManager.VERTICAL, false);
                    atAkRv.setLayoutManager(manager);
                    mAdapter = new AKAdapter(AgricultureKnowledgeActivity.this, data);
                    mAdapter.setOnItemClickListener(new AKAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(AgricultureKnowledgeActivity.this, AgricultureVideoMainActivity.class);
                            intent.putExtra(Const.VIDEO_ID, data.get(position).getId() + "");
                            startActivity(intent);
                        }
                    });
                }
                atAkRv.setItemAnimator(new DefaultItemAnimator());
                atAkRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    private void changeBackground(String id) {
        atAkIv1.setImageResource(R.drawable.agriculture_profiser_gray);
        atAkIv2.setImageResource(R.drawable.yinanzazeng_gray);
        atAkIv3.setImageResource(R.drawable.new_teachkonowledge_gray);
        atAkIv4.setImageResource(R.drawable.everthing_knowledge);
        atAkTv1.setTextColor(getResources().getColor(R.color.text3));
        atAkTv2.setTextColor(getResources().getColor(R.color.text3));
        atAkTv3.setTextColor(getResources().getColor(R.color.text3));
        atAkTv4.setTextColor(getResources().getColor(R.color.text3));
        if ("1".equals(id)) {
            atAkTv1.setTextColor(getResources().getColor(R.color.app_theme));
            atAkIv1.setImageResource(R.drawable.agriculture_profiser_theme);
        } else if ("2".equals(id)) {
            atAkTv2.setTextColor(getResources().getColor(R.color.app_theme));
            atAkIv2.setImageResource(R.drawable.yinanzazheng_theme);
        } else if ("3".equals(id)) {
            atAkTv3.setTextColor(getResources().getColor(R.color.app_theme));
            atAkIv3.setImageResource(R.drawable.new_teachkonowledge_theme);
        } else {
            atAkTv4.setTextColor(getResources().getColor(R.color.app_theme));
            atAkIv4.setImageResource(R.drawable.everthing_knowledge_theme);
        }
    }

    @OnClick()
    public void onViewClicked() {

    }
}
