package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
import com.zthx.npj.adapter.GoodsImgDetailAdapter;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.GiftDetailResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
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
    /*@BindView(R.id.at_gift_detail_tv_des)
    TextView atGiftDetailTvDes;
    @BindView(R.id.at_gift_detail_tv_detail)
    TextView atGiftDetailTvDetail;
    @BindView(R.id.at_gift_detail_tv_needknow)
    TextView atGiftDetailTvNeedknow;*/
    @BindView(R.id.at_gift_detail_btn_buy)
    Button atGiftDetailBtnBuy;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_gift_rv_imgs)
    RecyclerView acGiftRvImgs;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    /*@BindView(R.id.ac_gift_ll_know)
    LinearLayout acGiftLlKnow;*/

    private String mId;
    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    private String level = SharePerferenceUtils.getUserLevel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        ButterKnife.bind(this);
        mId = getIntent().getStringExtra(Const.GOODS_ID);


        getData(mId);

        back(titleBack);
        changeTitle(acTitle, "商品详情");

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData(mId);
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
    }


    private void getData(String id) {
        GiftSubscribe.getGiftDetail(SharePerferenceUtils.getUserId(this), SharePerferenceUtils.getToken(this), id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GiftDetailResponseBean giftDetailResponseBean = GsonUtils.fromJson(result, GiftDetailResponseBean.class);
                GiftDetailResponseBean.DataBean data = giftDetailResponseBean.getData();
                atGiftDetailTvPrice.setText("¥" + data.getPrice());
                atGiftDetailTvTitle.setText(data.getTitle());
                //atGiftDetailTvDes.setText(data.getDescription());
                initBanner(data.getImggroup());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GiftActivity.this);
                acGiftRvImgs.setLayoutManager(layoutManager);
                GoodsImgDetailAdapter adapter = new GoodsImgDetailAdapter(GiftActivity.this, data.getImggroup());
                acGiftRvImgs.setItemAnimator(new DefaultItemAnimator());
                acGiftRvImgs.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    @OnClick({R.id.at_gift_detail_btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.at_gift_detail_tv_detail:
                atGiftDetailTvNeedknow.setBackgroundColor(getResources().getColor(R.color.white));
                atGiftDetailTvNeedknow.setTextColor(getResources().getColor(R.color.text3));
                atGiftDetailTvDetail.setBackgroundColor(getResources().getColor(R.color.app_theme));
                atGiftDetailTvDetail.setTextColor(getResources().getColor(R.color.white));
                acGiftRvImgs.setVisibility(View.VISIBLE);
                acGiftLlKnow.setVisibility(View.GONE);
                break;
            case R.id.at_gift_detail_tv_needknow:
                atGiftDetailTvDetail.setBackgroundColor(getResources().getColor(R.color.white));
                atGiftDetailTvDetail.setTextColor(getResources().getColor(R.color.text3));
                atGiftDetailTvNeedknow.setBackgroundColor(getResources().getColor(R.color.app_theme));
                atGiftDetailTvNeedknow.setTextColor(getResources().getColor(R.color.white));
                acGiftRvImgs.setVisibility(View.GONE);
                acGiftLlKnow.setVisibility(View.VISIBLE);
                break;*/
            case R.id.at_gift_detail_btn_buy:
                if (level.equals("0")) {
                    GiftSubscribe.referrer(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            Intent intent = new Intent(GiftActivity.this, ConfirmOrderActivity.class);
                            intent.putExtra(Const.GOODS_ID, mId);
                            intent.setAction(Const.GIFT);
                            startActivity(intent);
                        }

                        @Override
                        public void onFault(String errorMsg) {
                            CommonDialog dialog=new CommonDialog(GiftActivity.this, R.style.dialog, "您还没有绑定推荐人，请先去绑定推荐人",true, new CommonDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if(confirm){
                                        openActivity(InputInvitationCodeActivity.class);
                                    }else{
                                        dialog.dismiss();
                                    }
                                }
                            });
                            dialog.setPositiveButton("绑定推荐人");
                            dialog.setNegativeButton("暂不绑定");
                            dialog.show();
                        }
                    }));
                } else {
                    Toast toast = Toast.makeText(this, "您已经是代言人了，赶快去邀请好友加入农品街吧！", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
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
