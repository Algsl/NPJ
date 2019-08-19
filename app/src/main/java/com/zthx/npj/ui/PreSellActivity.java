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

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.PreSellAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BannerResponseBean;
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


    private boolean isIng = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sell);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "新品预售");
        getPreSellList("0");
        initBanner();
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
                            atPreSellRv.setVisibility(View.VISIBLE);
                            setView(data, ll, atPreSellRv);
                        } else {
                            atPreSellRv2.setVisibility(View.VISIBLE);
                            atPreSellRv.setVisibility(View.GONE);
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
        PreSellAdapter adapter = new PreSellAdapter(PreSellActivity.this, data);
        adapter.setOnItemClickListener(new PreSellAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(PreSellActivity.this, GoodsDetailActivity.class);
                intent.setAction(Const.PRESELL);
                intent.putExtra(Const.GOODS_ID, data.get(position).getId() + "");
                startActivity(intent);

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
                    getPreSellList("1");
                }
                break;
        }
    }

    private void initBanner() {
        MainSubscribe.getMainBanner("2",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
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
