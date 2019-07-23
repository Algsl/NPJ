package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.StoreDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.view.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreDetailActivity extends ActivityBase {

    @BindView(R.id.at_store_detail_rv)
    RecyclerView atStoreDetailRv;
    @BindView(R.id.at_store_detail_btn_pay)
    Button atStoreDetailBtnPay;
    @BindView(R.id.at_store_detail_name)
    TextView atStoreDetailName;
    @BindView(R.id.at_store_detail_iv_star1)
    ImageView atStoreDetailIvStar1;
    @BindView(R.id.at_store_detail_iv_star2)
    ImageView atStoreDetailIvStar2;
    @BindView(R.id.at_store_detail_iv_star3)
    ImageView atStoreDetailIvStar3;
    @BindView(R.id.at_store_detail_iv_star4)
    ImageView atStoreDetailIvStar4;
    @BindView(R.id.at_store_detail_iv_star5)
    ImageView atStoreDetailIvStar5;
    @BindView(R.id.at_store_detail_ll_star)
    LinearLayout atStoreDetailLlStar;
    @BindView(R.id.at_store_detail_tv_popularity)
    TextView atStoreDetailTvPopularity;
    @BindView(R.id.at_store_detail_tv_open_time)
    TextView atStoreDetailTvOpenTime;
    @BindView(R.id.at_store_detail_tv_consumption)
    TextView atStoreDetailTvConsumption;
    @BindView(R.id.at_store_detail_tv_address)
    TextView atStoreDetailTvAddress;
    @BindView(R.id.at_store_detail_tv_address2)
    TextView atStoreDetailTvAddress2;
    @BindView(R.id.at_store_detail_iv_call)
    ImageView atStoreDetailIvCall;
    @BindView(R.id.item_location_store_ll_dikou)
    LinearLayout itemLocationStoreLlDikou;
    @BindView(R.id.item_location_store_ll_dikou2)
    LinearLayout itemLocationStoreLlDikou2;
    @BindView(R.id.at_store_detail_tv_offer)
    TextView atStoreDetailTvOffer;
    @BindView(R.id.at_store_detail_tv_relief)
    TextView atStoreDetailTvRelief;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.banner_discover_service)
    Banner bannerDiscoverService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,"店铺详情");

        getStoreDetail(getIntent().getStringExtra(Const.STORE_ID));
        getStoreComment(getIntent().getStringExtra(Const.STORE_ID));
    }

    private void getStoreComment(String id) {

        MainSubscribe.getStoreComment(id, "5", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setComment(String result) {
        String test="{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 50,\n" +
                "            \"user_id\": 25,\n" +
                "            \"goods_id\": 1,\n" +
                "            \"store_id\": 23,\n" +
                "            \"content\": \"商品质量非常好，期待下次合作\",\n" +
                "            \"img\": [\n" +
                "                \"http://www.test666.com/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg\",\n" +
                "                \"http://www.test666.com/public/upload/20190420/defa05252410178d8f8a9b1bb6f1d274.jpg\"\n" +
                "            ],\n" +
                "            \"status\": 0,\n" +
                "            \"create_time\": 1556095903,\n" +
                "            \"type\": 1,\n" +
                "            \"goods_star\": 5,\n" +
                "            \"logistics_star\": 5,\n" +
                "            \"service_star\": 5,\n" +
                "            \"nick_name\": \"用户15853102073\",\n" +
                "            \"head_img\": \"http://app.npj-vip.com/public/upload/20190711/80d23137c98abfb897db59eb918b892e.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"msg\": \"加载成功\"\n" +
                "}";
        CommentResponseBean bean = GsonUtils.fromJson(test, CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data = bean.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        atStoreDetailRv.setLayoutManager(layoutManager);
        CommentAdapter adapter = new CommentAdapter(this, data);
        atStoreDetailRv.setAdapter(adapter);
    }

    private void getStoreDetail(String id) {

        MainSubscribe.getStoreDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setData(result);

            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick(R.id.at_store_detail_btn_pay)
    public void onViewClicked() {
        openActivity(PayToStoreActivity.class,getIntent().getStringExtra(Const.STORE_ID));
    }

    public void setData(String result) {

        StoreDetailResponseBean storeDetailResponseBean = GsonUtils.fromJson(result, StoreDetailResponseBean.class);
        StoreDetailResponseBean.DataBean data = storeDetailResponseBean.getData();
        initBanner(data.getStore_img());
        atStoreDetailName.setText(data.getStore_name());
        switch (data.getPopularity()) {
            case 1:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 2:
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 3:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar3.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 4:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar3.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar4.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 5:
                atStoreDetailIvStar1.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar2.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar3.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar4.setBackgroundResource(R.drawable.item_location_store_star);
                atStoreDetailIvStar5.setBackgroundResource(R.drawable.item_location_store_star);
        }
        atStoreDetailTvPopularity.setText(data.getPopularity() + "分");
        atStoreDetailTvOpenTime.setText(data.getBusiness_hours() + "营业");
        atStoreDetailTvConsumption.setText("人均消费¥ " + data.getConsumption());
        atStoreDetailTvOffer.setText("葫芦币折扣" + data.getOffer() + "%现金");
        atStoreDetailTvRelief.setText("新会员在该商家成为代言人首单结算减免" + data.getRelief() + "元现金");
        atStoreDetailTvAddress.setText(data.getAddress2());
        atStoreDetailTvAddress2.setText(data.getAddress());
    }

    private void initBanner(ArrayList<String> list) {
        //设置banner样式
        bannerDiscoverService.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerDiscoverService.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        bannerDiscoverService.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerDiscoverService.setImages(list);
        //设置banner动画效果
        bannerDiscoverService.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        bannerDiscoverService.isAutoPlay(true);
        //设置轮播时间
        bannerDiscoverService.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bannerDiscoverService.setIndicatorGravity(BannerConfig.RIGHT);
        //设置banner点击事件
        bannerDiscoverService.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.e("huang", "position = " + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        bannerDiscoverService.start();
    }
}
