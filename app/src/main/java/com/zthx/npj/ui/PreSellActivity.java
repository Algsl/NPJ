package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.PreSellAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BannerResponseBean;
import com.zthx.npj.net.been.PreSellDetailResponseBean;
import com.zthx.npj.net.been.PreSellResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.GlideImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreSellActivity extends ActivityBase {

    @BindView(R.id.at_pre_sell_rv)
    RecyclerView atPreSellRv;
    @BindView(R.id.at_pre_sell_tv_ing)
    TextView atPreSellTvIng;
    @BindView(R.id.at_pre_sell_tv_ed)
    TextView atPreSellTvEd;
    @BindView(R.id.at_pre_sell_rv2)
    RecyclerView atPreSellRv2;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private boolean isIng = true;
    private String type="0";

    private String user_id=SharePerferenceUtils.getUserId(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sell);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "新品众筹");
        getPreSellList("0");
        initBanner();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getPreSellList(type);
                initBanner();
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
    }

    private void getPreSellList(final String type) {
        PreSellSubscribe.getPreSell(type, new OnSuccessAndFaultSub(
                new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        PreSellResponseBean preSellResponseBean = GsonUtils.fromJson(result, PreSellResponseBean.class);
                        final ArrayList<PreSellResponseBean.DataBean> data = preSellResponseBean.getData();
                        LinearLayoutManager ll = new LinearLayoutManager(PreSellActivity.this, LinearLayoutManager.VERTICAL, false);
                        if ("0".equals(type)) {
                            atPreSellRv2.setVisibility(View.GONE);
                            if(preSellResponseBean==null){
                                atPreSellRv.setVisibility(View.GONE);
                            }else{
                                atPreSellRv.setVisibility(View.VISIBLE);
                            }
                            setView(data, ll, atPreSellRv);
                        } else {
                            atPreSellRv2.setVisibility(View.VISIBLE);
                            atPreSellRv.setVisibility(View.GONE);
                            if(preSellResponseBean==null){
                                atPreSellRv2.setVisibility(View.GONE);
                            }else{
                                atPreSellRv2.setVisibility(View.VISIBLE);
                            }
                            setView(data, ll, atPreSellRv2);
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }, this));
    }

    private void setView(final ArrayList<PreSellResponseBean.DataBean> data, LinearLayoutManager ll, RecyclerView rv) {
        rv.setLayoutManager(ll);
        PreSellAdapter adapter = new PreSellAdapter(PreSellActivity.this, data,type);
        adapter.setOnItemClickListener(new PreSellAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(PreSellActivity.this, GoodsDetailActivity.class);
                intent.setAction(Const.PRESELL);
                intent.putExtra("pre_type",type);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);
            }

            @Override
            public void onPreClick(int position) {
                PreSellSubscribe.getPreSellDetail(data.get(position).getId()+"",user_id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        PreSellDetailResponseBean preSellDetailResponseBean = GsonUtils.fromJson(result, PreSellDetailResponseBean.class);
                        PreSellDetailResponseBean.DataBean data = preSellDetailResponseBean.getData();
                        Intent intent = new Intent(PreSellActivity.this, ConfirmOrderActivity.class);
                        intent.putExtra(Const.ATTRIBUTE_ID, data.getAttribute_value().get(0).getId()+"");
                        intent.setAction(Const.PRESELL);
                        intent.putExtra(Const.GOODS_ID, data.getId()+"");
                        startActivity(intent);
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    @OnClick({R.id.at_pre_sell_tv_ing, R.id.at_pre_sell_tv_ed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_pre_sell_tv_ing:
                if (!isIng) {
                    atPreSellTvIng.setTextColor(getResources().getColor(android.R.color.white));
                    atPreSellTvIng.setBackgroundColor(getResources().getColor(R.color.app_theme));
                    atPreSellTvEd.setTextColor(getResources().getColor(R.color.text3));
                    atPreSellTvEd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isIng = true;
                    type="0";
                    getPreSellList("0");
                }
                break;
            case R.id.at_pre_sell_tv_ed:
                if (isIng) {
                    atPreSellTvIng.setTextColor(getResources().getColor(R.color.text3));
                    atPreSellTvIng.setBackgroundColor(getResources().getColor(android.R.color.white));
                    atPreSellTvEd.setTextColor(getResources().getColor(android.R.color.white));
                    atPreSellTvEd.setBackgroundColor(getResources().getColor(R.color.app_theme));
                    isIng = false;
                    type="1";
                    getPreSellList("1");
                }
                break;
        }
    }

    private void initBanner() {
        MainSubscribe.getMainBanner("2", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BannerResponseBean bean = GsonUtils.fromJson(result, BannerResponseBean.class);
                ArrayList<BannerResponseBean.DataBean> data = bean.getData();
                ArrayList<Uri> list = new ArrayList<>();
                ArrayList<String> list2 = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    list.add(Uri.parse(data.get(i).getImg()));
                    list2.add(data.get(i).getTitle());
                }
                //设置banner样式
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(list);
                //设置banner动画效果
                banner.setBannerAnimation(Transformer.DepthPage);
                //设置自动轮播，默认为true
                banner.isAutoPlay(true);
                //设置标题集合（当banner样式有显示title时）
                banner.setBannerTitles(list2);
                //设置轮播时间
                banner.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.RIGHT);
                //设置banner点击事件
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Log.e("huang", "position = " + position);
                    }
                });
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
