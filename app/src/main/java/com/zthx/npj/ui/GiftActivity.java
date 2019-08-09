package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.GiftDetailResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.GlideImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GiftActivity extends ActivityBase {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_gift_detail_banner)
    Banner atGiftDetailBanner;
    @BindView(R.id.at_gift_detail_tv_price)
    TextView atGiftDetailTvPrice;
    @BindView(R.id.at_gift_detail_tv_title)
    TextView atGiftDetailTvTitle;
    @BindView(R.id.at_gift_detail_tv_des)
    TextView atGiftDetailTvDes;
    @BindView(R.id.at_gift_detail_tv_detail)
    TextView atGiftDetailTvDetail;
    @BindView(R.id.at_gift_detail_tv_needknow)
    TextView atGiftDetailTvNeedknow;
    @BindView(R.id.at_gift_detail_btn_buy)
    Button atGiftDetailBtnBuy;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;

    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        ButterKnife.bind(this);
        mId = getIntent().getStringExtra(Const.GOODS_ID);
        getData(mId);

        back(titleBack);
        changeTitle(acTitle,"商品详情");
    }


    private void getData(String id) {
        GiftSubscribe.getGiftDetail(SharePerferenceUtils.getUserId(this), BaseConstant.TOKEN, id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GiftDetailResponseBean giftDetailResponseBean = GsonUtils.fromJson(result, GiftDetailResponseBean.class);
                GiftDetailResponseBean.DataBean data=giftDetailResponseBean.getData();
                atGiftDetailTvPrice.setText("¥" + data.getPrice());
                atGiftDetailTvTitle.setText(data.getTitle());
                atGiftDetailTvDes.setText(data.getDescription());
                initBanner(data.getImggroup());
            }

            @Override
            public void onFault(String errorMsg) {
                String test = "{\n" +
                        "    \"code\": 1,\n" +
                        "    \"data\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"title\": \"水多多面膜\",\n" +
                        "        \"description\": \"水多多专属礼包产品\",\n" +
                        "        \"imggroup\": [\n" +
                        "            \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\",\n" +
                        "            \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\",\n" +
                        "            \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\"\n" +
                        "        ],\n" +
                        "        \"price\": \"298.00\",\n" +
                        "        \"content\": null\n" +
                        "    },\n" +
                        "    \"msg\": \"加载成功\"\n" +
                        "}";
                GiftDetailResponseBean giftDetailResponseBean = GsonUtils.fromJson(test, GiftDetailResponseBean.class);
                GiftDetailResponseBean.DataBean data=giftDetailResponseBean.getData();
                atGiftDetailTvPrice.setText("¥" + data.getPrice());
                atGiftDetailTvTitle.setText(data.getTitle());
                atGiftDetailTvDes.setText(data.getDescription());
                initBanner(data.getImggroup());
            }
        }));
    }

    @OnClick({R.id.at_gift_detail_tv_detail, R.id.at_gift_detail_tv_needknow, R.id.at_gift_detail_btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_gift_detail_tv_detail:
                break;
            case R.id.at_gift_detail_tv_needknow:
                break;
            case R.id.at_gift_detail_btn_buy:
                Intent intent = new Intent(this, ConfirmOrderActivity.class);
                intent.putExtra(Const.GOODS_ID, mId);
                intent.setAction(Const.GIFT);
                startActivity(intent);
                break;
        }
    }

    /**
     * 初始化轮播图
     */
    private void initBanner(ArrayList<String> uri) {

        ArrayList<Uri> list = new ArrayList<>();
        for (int i = 0; i < uri.size(); i++) {
            list.add(Uri.parse(uri.get(i)));
        }


        //设置banner样式
        atGiftDetailBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        atGiftDetailBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        atGiftDetailBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        atGiftDetailBanner.setImages(list);
        //设置banner动画效果
        atGiftDetailBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        atGiftDetailBanner.isAutoPlay(true);
        //设置轮播时间
        atGiftDetailBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        atGiftDetailBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置banner点击事件
        atGiftDetailBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
        atGiftDetailBanner.start();
    }
}
