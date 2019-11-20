package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.AlsoLikeAdatper;
import com.zthx.npj.adapter.CollectionAdapter;
import com.zthx.npj.adapter.CollectionStoreAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AlsoLikeResponseBean;
import com.zthx.npj.net.been.CollectionResponseBean;
import com.zthx.npj.net.been.CollectionStoreResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectActivity extends ActivityBase {

    @BindView(R.id.at_my_collect_goods_rv)
    RecyclerView atMyCollectGoodsRv;
    @BindView(R.id.at_my_collect_store_rv)
    RecyclerView atMyCollectStoreRv;
    @BindView(R.id.at_my_collect_like_rv)
    RecyclerView atMyCollectLikeRv;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_myCollect_tv_goods)
    TextView acMyCollectTvGoods;
    @BindView(R.id.ac_myStore_tv_stores)
    TextView acMyStoreTvStores;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_myCollect_tv_preGoods)
    TextView acMyCollectTvPreGoods;
    @BindView(R.id.at_my_collect_presell_rv)
    RecyclerView atMyCollectPresellRv;
    @BindView(R.id.seeMore)
    TextView seeMore;

    private boolean flag = true;
    private String type = "1";
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    private int page = 1;
    private AlsoLikeAdatper adatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "收藏");

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if (adatper != null) {
                    adatper.clearData();
                }
                seeMore.setText("查看更多");
                refreshLayout.setLoadmoreFinished(false);
                getCollection();
                getAlsoLike();
                refreshlayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                getAlsoLike();
                refreshlayout.finishLoadmore();
            }
        });

        getCollection();
        getAlsoLike();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCollection();
    }

    private void getAlsoLike() {
        MainSubscribe.alsoLike(page+"", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AlsoLikeResponseBean bean = GsonUtils.fromJson(result, AlsoLikeResponseBean.class);
                final ArrayList<AlsoLikeResponseBean.DataBean> data = bean.getData();
                GridLayoutManager layoutManager = new GridLayoutManager(MyCollectActivity.this, 2);
                atMyCollectLikeRv.setLayoutManager(layoutManager);

                if (adatper == null) {
                    adatper = new AlsoLikeAdatper(MyCollectActivity.this, data);
                } else {
                    if (data != null && data.size() != 0) {
                        if (data.size() < 10) {
                            seeMore.setText("没有更多了");
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        adatper.addData(data);
                    }
                }
                //设置添加或删除item时的动画，这里使用默认动画
                atMyCollectLikeRv.setItemAnimator(new DefaultItemAnimator());
                //设置适配器
                atMyCollectLikeRv.setItemAnimator(new DefaultItemAnimator());
                atMyCollectLikeRv.setAdapter(adatper);
                adatper.setOnItemClickListener(new AlsoLikeAdatper.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<AlsoLikeResponseBean.DataBean> mList) {
                        Intent intent = new Intent(MyCollectActivity.this, GoodsDetailActivity.class);
                        intent.putExtra(Const.GOODS_ID, mList.get(position).getId() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void getCollection() {
        SetSubscribe.collectionList(user_id, token, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setResult(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setResult(String result) {
        if (type.equals("1")) {
            atMyCollectGoodsRv.setVisibility(View.VISIBLE);
            atMyCollectStoreRv.setVisibility(View.GONE);
            atMyCollectPresellRv.setVisibility(View.GONE);
            CollectionResponseBean bean = GsonUtils.fromJson(result, CollectionResponseBean.class);
            final ArrayList<CollectionResponseBean.DataBean> data = bean.getData();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            atMyCollectGoodsRv.setLayoutManager(layoutManager);
            CollectionAdapter adapter = new CollectionAdapter(this, data, "1");
            atMyCollectGoodsRv.setItemAnimator(new DefaultItemAnimator());
            atMyCollectGoodsRv.setAdapter(adapter);
            adapter.setOnItemClickListener(new CollectionAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(MyCollectActivity.this, GoodsDetailActivity.class);
                    intent.putExtra(Const.GOODS_ID, data.get(position).getList_id() + "");
                    startActivity(intent);
                }

                @Override
                public void onItemAddCart(int position) {

                }

                @Override
                public void onItemDelete(int position) {
                    SetSubscribe.delCollection(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            getCollection();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
                }
            });
        } else if (type.equals("2")) {
            CollectionStoreResponseBean bean = GsonUtils.fromJson(result, CollectionStoreResponseBean.class);
            final ArrayList<CollectionStoreResponseBean.DataBean> data = bean.getData();
            atMyCollectGoodsRv.setVisibility(View.GONE);
            atMyCollectStoreRv.setVisibility(View.VISIBLE);
            atMyCollectPresellRv.setVisibility(View.GONE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            atMyCollectStoreRv.setLayoutManager(layoutManager);
            CollectionStoreAdapter adapter = new CollectionStoreAdapter(this, data);
            atMyCollectStoreRv.setItemAnimator(new DefaultItemAnimator());
            atMyCollectStoreRv.setAdapter(adapter);
            adapter.setOnItemClickListener(new CollectionStoreAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    openActivity(StoreActivity.class, data.get(position).getList_id() + "");
                }

                @Override
                public void onDeleteClick(int position) {
                    SetSubscribe.delCollection(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            getCollection();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
                }
            });
        } else {
            atMyCollectGoodsRv.setVisibility(View.GONE);
            atMyCollectStoreRv.setVisibility(View.GONE);
            atMyCollectPresellRv.setVisibility(View.VISIBLE);
            CollectionResponseBean bean = GsonUtils.fromJson(result, CollectionResponseBean.class);
            final ArrayList<CollectionResponseBean.DataBean> data = bean.getData();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            atMyCollectPresellRv.setLayoutManager(layoutManager);
            CollectionAdapter adapter = new CollectionAdapter(this, data, "3");
            atMyCollectPresellRv.setItemAnimator(new DefaultItemAnimator());
            atMyCollectPresellRv.setAdapter(adapter);
            adapter.setOnItemClickListener(new CollectionAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(MyCollectActivity.this, GoodsDetailActivity.class);
                    intent.setAction(Const.PRESELL);
                    intent.putExtra("pre_type","0");
                    intent.putExtra(Const.GOODS_ID, data.get(position).getList_id() + "");
                    startActivity(intent);
                }

                @Override
                public void onItemAddCart(int position) {

                }

                @Override
                public void onItemDelete(int position) {
                    SetSubscribe.delCollection(user_id, token, data.get(position).getId() + "", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            getCollection();
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            //showToast(errorMsg);
                        }
                    }));
                }
            });
        }
    }

    @OnClick({R.id.ac_myCollect_tv_goods, R.id.ac_myStore_tv_stores, R.id.ac_myCollect_tv_preGoods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_myCollect_tv_goods:
                acMyCollectTvGoods.setTextColor(getResources().getColor(android.R.color.white));
                acMyCollectTvGoods.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acMyStoreTvStores.setTextColor(getResources().getColor(R.color.text3));
                acMyStoreTvStores.setBackgroundColor(getResources().getColor(android.R.color.white));
                acMyCollectTvPreGoods.setTextColor(getResources().getColor(R.color.text3));
                acMyCollectTvPreGoods.setBackgroundColor(getResources().getColor(android.R.color.white));
                type = "1";
                getCollection();
                break;
            case R.id.ac_myStore_tv_stores:
                acMyStoreTvStores.setTextColor(getResources().getColor(android.R.color.white));
                acMyStoreTvStores.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acMyCollectTvGoods.setTextColor(getResources().getColor(R.color.text3));
                acMyCollectTvGoods.setBackgroundColor(getResources().getColor(android.R.color.white));
                acMyCollectTvPreGoods.setTextColor(getResources().getColor(R.color.text3));
                acMyCollectTvPreGoods.setBackgroundColor(getResources().getColor(android.R.color.white));
                type = "2";
                getCollection();
                break;
            case R.id.ac_myCollect_tv_preGoods:
                acMyCollectTvPreGoods.setTextColor(getResources().getColor(R.color.white));
                acMyCollectTvPreGoods.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acMyStoreTvStores.setTextColor(getResources().getColor(R.color.text3));
                acMyStoreTvStores.setBackgroundColor(getResources().getColor(android.R.color.white));
                acMyCollectTvGoods.setTextColor(getResources().getColor(R.color.text3));
                acMyCollectTvGoods.setBackgroundColor(getResources().getColor(android.R.color.white));
                type = "3";
                getCollection();
                break;
        }
    }

}
