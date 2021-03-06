package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.adapter.GoodsImgDetailAdapter;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AddCartBean;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.GoodsDetailResponseBean;
import com.zthx.npj.net.been.PreSellDetailResponseBean;
import com.zthx.npj.net.been.SecKillGoodsDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netsubscribe.SecKillSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.activity.ChatActivity;
import com.zthx.npj.tencent.util.Constants;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.QRCodeUtil;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.utils.SimpleUtil;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.GlideImageLoader;
import com.zthx.npj.view.GoodSizePopupwindow;
import com.zthx.npj.view.SaleDetailProgressView;
import com.zthx.npj.view.TimeTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zthx.npj.ui.UserMsgActivity.bmpToByteArray;

public class GoodsDetailActivity extends ActivityBase {

    @BindView(R.id.at_goods_detail_btn_add_shopping_cart)
    Button atGoodsDetailBtnAddShoppingCart;
    @BindView(R.id.at_goods_detail_btn_buy_now)
    Button atGoodsDetailBtnBuyNow;
    @BindView(R.id.at_goods_detail_rl_sec_kill)
    RelativeLayout atGoodsDetailRlSecKill;
    @BindView(R.id.at_goods_detail_ttv)
    TimeTextView atGoodsDetailTtv;
    @BindView(R.id.at_goods_detail_spv)
    SaleDetailProgressView atGoodsDetailSpv;
    @BindView(R.id.iv_miaosha_pic)
    ImageView ivMiaoshaPic;
    @BindView(R.id.at_goods_detail_tv_log)
    TextView atGoodsDetailTvLog;
    @BindView(R.id.at_goods_detail_tv_goods_new_price)
    TextView atGoodsDetailTvGoodsNewPrice;
    @BindView(R.id.at_goods_detail_tv_goods_old_price)
    TextView atGoodsDetailTvGoodsOldPrice;
    @BindView(R.id.at_goods_detail_tv_goods_title)
    TextView atGoodsDetailTvGoodsTitle;
    @BindView(R.id.at_goods_detail_selled_num)
    TextView atGoodsDetailSelledNum;
    @BindView(R.id.at_goods_detail_hold_num)
    TextView atGoodsDetailHoldNum;
    @BindView(R.id.at_pre_sell_pb)
    ProgressBar atPreSellPb;
    @BindView(R.id.at_goods_detail_kind)
    TextView atGoodsDetailKind;
    @BindView(R.id.at_goods_detail_ll_goods)
    LinearLayout atGoodsDetailLlGoods;
    @BindView(R.id.at_goods_detail_tv_pre_sell_title)
    TextView atGoodsDetailTvPreSellTitle;
    @BindView(R.id.at_goods_detail_tv_pre_sell_price)
    TextView atGoodsDetailTvPreSellPrice;
    @BindView(R.id.at_goods_detail_tv_pre_sell_yuding)
    TextView atGoodsDetailTvPreSellYuding;
    @BindView(R.id.at_goods_detail_tv_pre_sell_dacheng)
    TextView atGoodsDetailTvPreSellDacheng;
    @BindView(R.id.at_goods_detail_ll_presell)
    LinearLayout atGoodsDetailLlPresell;
    @BindView(R.id.at_goods_detail_btn_pre_sell_know)
    Button atGoodsDetailBtnPreSellKnow;
    @BindView(R.id.at_goods_detail_rl_sec_kill_done)
    LinearLayout atGoodsDetailRlSecKillDone;
    @BindView(R.id.at_goods_detail_banner)
    Banner atGoodsDetailBanner;
    @BindView(R.id.ac_goodsDetail_ll_collect)
    LinearLayout acGoodsDetailLlCollect;
    @BindView(R.id.ac_goodsDetail_ll_store)
    LinearLayout acGoodsDetailLlStore;
    @BindView(R.id.ac_goodsDetail_iv_back)
    ImageView acGoodsDetailIvBack;
    @BindView(R.id.at_goods_detail_btn_pre_sell_comment)
    Button atGoodsDetailBtnPreSellComment;
    @BindView(R.id.ac_goodsDetail_rv_content)
    RecyclerView acGoodsDetailRvContent;
    @BindView(R.id.at_goods_detail_btn_pre_sell_detail)
    Button atGoodsDetailBtnPreSellDetail;
    @BindView(R.id.ac_goodsDetail_chooseSize)
    RelativeLayout acGoodsDetailChooseSize;
    @BindView(R.id.ac_goodsDetail_iv_share)
    ImageView acGoodsDetailIvShare;
    @BindView(R.id.ac_goodsDetail_iv_collect)
    ImageView acGoodsDetailIvCollect;
    @BindView(R.id.at_goodsDetail_rl_willBegin)
    RelativeLayout atGoodsDetailRlWillBegin;
    @BindView(R.id.ac_goodsDetail_ll_wuliu)
    LinearLayout acGoodsDetailLlWuliu;
    @BindView(R.id.ac_goodsDetail_ll_know)
    LinearLayout acGoodsDetailLlKnow;
    @BindView(R.id.ac_goodsDetail_iv_home)
    ImageView acGoodsDetailIvHome;

    @BindView(R.id.ac_goodsDetail_iv_innerGoodsImg)
    ImageView acGoodsDetailIvInnerGoodsImg;
    @BindView(R.id.ac_goodsDetail_tv_innerGoodsTitle)
    TextView acGoodsDetailTvInnerGoodsTitle;
    @BindView(R.id.ac_goodsDetail_iv_qrcode)
    ImageView acGoodsDetailIvQrcode;
    @BindView(R.id.ac_goodsDetail_tv_innerGoodsPrice)
    TextView acGoodsDetailTvInnerGoodsPrice;
    @BindView(R.id.ac_goodsDetail_ll_inncer)
    RelativeLayout acGoodsDetailLlInncer;
    @BindView(R.id.ac_goodsDetail_ll_bar)
    LinearLayout acGoodsDetailLlBar;
    @BindView(R.id.ac_goodsDetail_save)
    TextView acGoodsDetailSave;
    @BindView(R.id.ac_goodsDetail_ll_inner)
    LinearLayout acGoodsDetailLlInner;
    @BindView(R.id.ac_goodsDetail_rv_comment)
    RecyclerView acGoodsDetailRvComment;
    @BindView(R.id.ac_goodsDetail_ttv_beginTime)
    TimeTextView acGoodsDetailTtvBeginTime;
    @BindView(R.id.ac_goodsDetail_sv)
    NestedScrollView acGoodsDetailSv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_goodsDetail_iv_willEnd)
    ImageView acGoodsDetailIvWillEnd;
    @BindView(R.id.ac_goodsDetail_tv_willEnd)
    TextView acGoodsDetailTvWillEnd;
    @BindView(R.id.ac_goodsDetail_iv_sending)
    ImageView acGoodsDetailIvSending;
    @BindView(R.id.ac_goodsDetail_tv_sending)
    TextView acGoodsDetailTvSending;
    @BindView(R.id.ac_goodsDetail_tv_send)
    TextView acGoodsDetailTvSend;
    @BindView(R.id.at_goods_detail_tv_goods_title1)
    TextView atGoodsDetailTvGoodsTitle1;
    @BindView(R.id.ac_goodsdetail_ll_kefu)
    LinearLayout acGoodsdetailLlKefu;
    @BindView(R.id.ac_goodsDetail_tv_comment)
    TextView acGoodsDetailTvComment;
    @BindView(R.id.ac_goodsDetail_tv_prMoney)
    TextView acGoodsDetailTvPrMoney;


    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String level = SharePerferenceUtils.getUserLevel(this);
    private String goodsId;
    TextView tvCartNum;
    private String type = "1";
    private int count = 1;
    private String imgStrMsg = "";
    private String collectType = "1";
    private String attribute_id = "";

    private String nick_name;
    private String mobile;


    private PreSellDetailResponseBean.DataBean mPreData = new PreSellDetailResponseBean().getData();
    private GoodsDetailResponseBean.DataBean mGoodsData = new GoodsDetailResponseBean().getData();
    private SecKillGoodsDetailResponseBean.DataBean mSeckillData = new SecKillGoodsDetailResponseBean().getData();

    private IWXAPI api;
    private GoodSizePopupwindow sizePopWin = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);


        api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        api.registerApp("wx76500efa65d19915");

        goodsId = getIntent().getStringExtra(Const.GOODS_ID);

        back(acGoodsDetailIvBack);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acGoodsDetailRvContent.setLayoutManager(layoutManager);

        //抢购商品
        if ("miaosha".equals(getIntent().getAction())) {//限时抢购
            int status = getIntent().getIntExtra(Const.SECKILL_STATUS, 1);
            if (status == 1) {//抢购已结束
                atGoodsDetailRlWillBegin.setVisibility(View.GONE);//即将开始隐藏
                atGoodsDetailRlSecKill.setVisibility(View.GONE);//
                atGoodsDetailRlSecKillDone.setVisibility(View.VISIBLE);//结束隐藏
                acGoodsDetailLlBar.setVisibility(View.GONE);
                acGoodsDetailChooseSize.setClickable(false);
            } else if (status == 2) {//抢购进行中
                atGoodsDetailRlWillBegin.setVisibility(View.GONE);
                atGoodsDetailRlSecKill.setVisibility(View.VISIBLE);
                atGoodsDetailRlSecKillDone.setVisibility(View.GONE);
            } else {//抢购即将开始
                acGoodsDetailChooseSize.setClickable(false);//规格隐藏
                atGoodsDetailRlWillBegin.setVisibility(View.VISIBLE);
                atGoodsDetailRlSecKill.setVisibility(View.GONE);
                atGoodsDetailRlSecKillDone.setVisibility(View.GONE);
                acGoodsDetailLlBar.setVisibility(View.GONE);
            }
            type = "4";
            acGoodsDetailChooseSize.setVisibility(View.GONE);
            acGoodsDetailLlCollect.setVisibility(View.GONE);
            atGoodsDetailLlGoods.setVisibility(View.VISIBLE);
            atGoodsDetailLlPresell.setVisibility(View.GONE);
            acGoodsDetailIvShare.setVisibility(View.GONE);//隐藏分享按钮
            atGoodsDetailBtnAddShoppingCart.setVisibility(View.GONE);
            getSecKillDetail();
        } else if (Const.PRESELL.equals(getIntent().getAction())) {
            type = "3";
            collectType = "3";
            atGoodsDetailRlSecKill.setVisibility(View.GONE);
            atGoodsDetailLlGoods.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.VISIBLE);
            atGoodsDetailBtnAddShoppingCart.setVisibility(View.GONE);
            acGoodsDetailLlStore.setVisibility(View.GONE);
            acGoodsDetailLlWuliu.setVisibility(View.VISIBLE);
            atGoodsDetailBtnPreSellKnow.setVisibility(View.VISIBLE);
            acGoodsDetailIvShare.setVisibility(View.GONE);//隐藏分享按钮
            getPreSellDetail(goodsId);

            if (!getIntent().getStringExtra("pre_type").equals("0")) {
                acGoodsDetailLlBar.setVisibility(View.GONE);
                acGoodsDetailChooseSize.setClickable(false);
            }

            atGoodsDetailBtnPreSellKnow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    atGoodsDetailBtnPreSellDetail.setBackgroundColor(getResources().getColor(R.color.white));
                    atGoodsDetailBtnPreSellDetail.setTextColor(getResources().getColor(R.color.text3));
                    atGoodsDetailBtnPreSellComment.setBackgroundColor(getResources().getColor(R.color.white));
                    atGoodsDetailBtnPreSellComment.setTextColor(getResources().getColor(R.color.text3));
                    atGoodsDetailBtnPreSellKnow.setBackgroundColor(getResources().getColor(R.color.app_theme));
                    atGoodsDetailBtnPreSellKnow.setTextColor(getResources().getColor(R.color.white));
                    acGoodsDetailLlKnow.setVisibility(View.VISIBLE);
                    acGoodsDetailRvContent.setVisibility(View.GONE);
                    acGoodsDetailRvComment.setVisibility(View.GONE);

                    acGoodsDetailTvComment.setVisibility(View.GONE);
                }
            });
        } else {
            type = "1";
            atGoodsDetailRlSecKill.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.GONE);
            getGoodsDetail(goodsId);
        }

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
    }

    //商品详情图片展示
    private void setGoodsContent(ArrayList<String> lists) {
        GoodsImgDetailAdapter adapter = new GoodsImgDetailAdapter(this, lists);
        acGoodsDetailRvContent.setItemAnimator(new DefaultItemAnimator());
        acGoodsDetailRvContent.setAdapter(adapter);
    }

    //抢购商品详情
    private void getSecKillDetail() {
        SecKillSubscribe.getSecKillGoodsDetail(goodsId, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSecKillData(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setSecKillData(String result) {
        SecKillGoodsDetailResponseBean secKillGoodsDetailResponseBean = GsonUtils.fromJson(result, SecKillGoodsDetailResponseBean.class);
        mSeckillData = secKillGoodsDetailResponseBean.getData();
        initBanner(mSeckillData.getGroup_img());

        getGoodsContent();

        nick_name = mSeckillData.getNick_name();
        mobile = mSeckillData.getMobile();

        atGoodsDetailTvGoodsTitle1.setText(mSeckillData.getGoods_desc());
        atGoodsDetailTvGoodsTitle.setText(mSeckillData.getGoods_name());
        atGoodsDetailTvGoodsNewPrice.setText("¥" + mSeckillData.getGoods_price());
        atGoodsDetailTvGoodsOldPrice.setText("¥" + mSeckillData.getMarket_price());
        atGoodsDetailTvGoodsOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        atGoodsDetailSelledNum.setText("已售" + (mSeckillData.getSold() == null ? "0" : mSeckillData.getSold()) + "件");
        atGoodsDetailHoldNum.setText("库存" + mSeckillData.getGoods_num());


        if (mSeckillData.isCollect()) {
            acGoodsDetailIvCollect.setImageResource(R.drawable.collect_star);
        } else {
            acGoodsDetailIvCollect.setImageResource(R.drawable.uncollect_star);
        }

        long time;
        int status = getIntent().getIntExtra(Const.SECKILL_STATUS, 1);
        if (status == 2) {
            time = (mSeckillData.getEnd_time() * 1000 - System.currentTimeMillis()) / 1000;
            setTime(atGoodsDetailTtv, time);
        } else if (status == 3) {
            time = (mSeckillData.getBegin_time() * 1000 - System.currentTimeMillis()) / 1000;
            setTime(acGoodsDetailTtvBeginTime, time);
        }
        atGoodsDetailSpv.setTotalAndCurrentCount(Integer.parseInt(mSeckillData.getGoods_num()), Integer.parseInt(mSeckillData.getSold()));
    }

    public void setTime(TimeTextView ttv, long time) {
        long second = time % 60;//计算秒
        long min = time / 60 % 60;
        long hour = time / 3600 % 24;
        long day = time / 3600 / 24;
        ttv.setTimes(new long[]{hour, min, second, day});
        if (!ttv.isRun()) {
            ttv.run();
        }
    }

    //众筹商品详情
    private void getPreSellDetail(String id) {
        PreSellSubscribe.getPreSellDetail(id, user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setPreSellData(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setPreSellData(String result) {
        PreSellDetailResponseBean preSellDetailResponseBean = GsonUtils.fromJson(result, PreSellDetailResponseBean.class);
        PreSellDetailResponseBean.DataBean data = preSellDetailResponseBean.getData();
        mPreData = data;
        initBanner(mPreData.getGroup_img());
        getGoodsContent();

        nick_name = mPreData.getNick_name();
        mobile = mPreData.getMobile();

        if (mPreData.getIs_shoucang() == 1) {
            acGoodsDetailIvCollect.setImageResource(R.drawable.collect_star);
        }

        long time = data.getShipment() - System.currentTimeMillis() / 1000;
        long day = time / 3600 / 24;
        if (day <= 0) {
            acGoodsDetailIvWillEnd.setImageResource(R.drawable.circle_gray);
            acGoodsDetailTvWillEnd.setBackgroundResource(R.color.white);
            acGoodsDetailIvSending.setImageResource(R.drawable.circle_theme);
            acGoodsDetailTvSending.setBackgroundResource(R.drawable.stroke_theme_2);
            acGoodsDetailTvWillEnd.setText("  众筹结束");
        } else {
            acGoodsDetailTvWillEnd.setText("  众筹中（" + day + "天后结束）");
        }

        acGoodsDetailTvSend.setText("预计" + new SimpleDateFormat("MM.dd").format(new Date(data.getShipment() * 1000)) + "号开始发货");
        atGoodsDetailTvPreSellTitle.setText(data.getGoods_name());
        atGoodsDetailTvPreSellPrice.setText("¥" + data.getGoods_price());
        atGoodsDetailTvPreSellYuding.setText(data.getUser_num());

        acGoodsDetailTvPrMoney.setText(Long.parseLong(data.getSale_num())*Double.parseDouble(data.getGoods_price())+"");
        atGoodsDetailTvPreSellDacheng.setText(Double.parseDouble(data.getSale_num())/Double.parseDouble(data.getGoods_num())*100 + "%");
        //atPreSellPb.setProgress(Integer.parseInt(data.getProportion() + ""));
        atPreSellPb.setMax((int)Long.parseLong(data.getGoods_num()));
        atPreSellPb.setProgress((int)Long.parseLong(data.getSale_num()));
    }


    //普通商品详情
    private void getGoodsDetail(String id) {
        MainSubscribe.getGoodsDetail(id, user_id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setGoodsData(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setGoodsData(String result) {
        GoodsDetailResponseBean goodsDetailResponseBean = GsonUtils.fromJson(result, GoodsDetailResponseBean.class);
        mGoodsData = goodsDetailResponseBean.getData();
        initBanner(mGoodsData.getGoods_img());
        getGoodsContent();
        String level = SharePerferenceUtils.getUserLevel(this);
        atGoodsDetailTvGoodsNewPrice.setText("¥" + mGoodsData.getUser_price());
        atGoodsDetailTvGoodsOldPrice.setText("￥" + mGoodsData.getMarket_price());//市场价
        atGoodsDetailTvGoodsOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        atGoodsDetailTvGoodsTitle.setText(mGoodsData.getGoods_name());
        atGoodsDetailSelledNum.setText("已售" + mGoodsData.getSold() + "");
        atGoodsDetailHoldNum.setText("库存" + mGoodsData.getInventory() + "");
        atGoodsDetailTvGoodsTitle1.setText(mGoodsData.getGoods_desc());
        nick_name = mGoodsData.getNick_name();
        mobile = mGoodsData.getMobile();
        String str;
        if (mGoodsData.getIs_free_shipping() == 0) {
            str = "免运费";
        } else {
            str = mGoodsData.getIs_free_shipping() + "元";
        }
        if (mGoodsData.getCollection() == 1) {
            acGoodsDetailIvCollect.setImageResource(R.drawable.collect_star);
        }
    }

    @OnClick({R.id.at_goods_detail_btn_add_shopping_cart, R.id.at_goods_detail_btn_buy_now, R.id.ac_goodsDetail_ll_collect,
            R.id.ac_goodsDetail_ll_store, R.id.at_goods_detail_btn_pre_sell_know, R.id.at_goods_detail_btn_pre_sell_comment,
            R.id.at_goods_detail_btn_pre_sell_detail, R.id.ac_goodsDetail_chooseSize, R.id.ac_goodsDetail_iv_share,
            R.id.ac_goodsDetail_iv_home, R.id.ac_goodsDetail_save, R.id.ac_goodsdetail_ll_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_goods_detail_btn_add_shopping_cart://加入购物车
                if (user_id.equals("")) {
                    CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
                            }
                        }
                    });
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("去登录");
                    dialog.show();
                } else {
                    showPopupwindow(view);
                }
                break;
            case R.id.at_goods_detail_btn_buy_now://立即购买
                if (user_id.equals("")) {
                    CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
                            }
                        }
                    });
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("去登录");
                    dialog.show();
                } else {
                    showPopupwindow(view);
                }
                break;
            case R.id.ac_goodsDetail_ll_collect://收藏
                if (user_id.equals("")) {
                    CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
                            }
                        }
                    });
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("去登录");
                    dialog.show();
                } else {
                    goodsCollect();
                }
                break;
            case R.id.ac_goodsdetail_ll_kefu:
                //openActivity(ServicesListActivity.class);
                ChatInfo chatInfo = new ChatInfo();
                chatInfo.setType(TIMConversationType.C2C);
                chatInfo.setId(mobile);
                chatInfo.setChatName(nick_name);
                Intent intent = new Intent(BaseApp.getApp(), ChatActivity.class);
                intent.putExtra(Constants.CHAT_INFO, chatInfo);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApp.getApp().startActivity(intent);
                break;
            case R.id.at_goods_detail_btn_pre_sell_detail://商品详情
                acGoodsDetailLlKnow.setVisibility(View.GONE);
                acGoodsDetailRvContent.setVisibility(View.VISIBLE);
                acGoodsDetailRvComment.setVisibility(View.GONE);
                acGoodsDetailTvComment.setVisibility(View.GONE);
                atGoodsDetailBtnPreSellDetail.setBackgroundColor(getResources().getColor(R.color.app_theme));
                atGoodsDetailBtnPreSellDetail.setTextColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellComment.setBackgroundColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellComment.setTextColor(getResources().getColor(R.color.text3));
                atGoodsDetailBtnPreSellKnow.setBackgroundColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellKnow.setTextColor(getResources().getColor(R.color.text3));
                getGoodsContent();
                break;
            case R.id.at_goods_detail_btn_pre_sell_comment://评论
                acGoodsDetailLlKnow.setVisibility(View.GONE);
                acGoodsDetailRvContent.setVisibility(View.GONE);
                acGoodsDetailRvComment.setVisibility(View.VISIBLE);
                atGoodsDetailBtnPreSellDetail.setBackgroundColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellDetail.setTextColor(getResources().getColor(R.color.text3));
                atGoodsDetailBtnPreSellComment.setBackgroundColor(getResources().getColor(R.color.app_theme));
                atGoodsDetailBtnPreSellComment.setTextColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellKnow.setBackgroundColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellKnow.setTextColor(getResources().getColor(R.color.text3));
                getComments();
                break;
            case R.id.ac_goodsDetail_chooseSize://选择规格
                if (user_id.equals("")) {
                    CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
                            }
                        }
                    });
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("去登录");
                    dialog.show();
                } else {
                    showPopupwindow(view);
                }
                break;
            case R.id.ac_goodsDetail_iv_share://分享
                acGoodsDetailLlInner.setVisibility(View.VISIBLE);
                switch (type) {
                    case "4":
                        imgStrMsg = "http://game.npj-vip.com/h5/jumpApp.html?type=qianggou&id=" + goodsId+"&img="+mSeckillData.getGroup_img().get(0)+"&discount=0";
                        Glide.with(GoodsDetailActivity.this).load(Uri.parse(mSeckillData.getGroup_img().get(0))).into(acGoodsDetailIvInnerGoodsImg);
                        acGoodsDetailTvInnerGoodsTitle.setText(mSeckillData.getGoods_name());
                        acGoodsDetailTvInnerGoodsPrice.setText("￥" + mSeckillData.getGoods_price());
                        break;
                    case "3":
                        imgStrMsg = "http://game.npj-vip.com/h5/jumpApp.html?type=zhongchou&id=" + goodsId+"&img="+mPreData.getGroup_img().get(0)+"&discount=0";
                        Glide.with(GoodsDetailActivity.this).load(Uri.parse(mPreData.getGroup_img().get(0))).into(acGoodsDetailIvInnerGoodsImg);
                        acGoodsDetailTvInnerGoodsTitle.setText(mPreData.getGoods_name());
                        acGoodsDetailTvInnerGoodsPrice.setText("￥" + mPreData.getGoods_price());
                        break;
                    case "1":
                        imgStrMsg = "http://game.npj-vip.com/h5/jumpApp.html?type=putong&id=" + goodsId+"&img="+mGoodsData.getGoods_img().get(0)+"&discount=0";
                        Glide.with(GoodsDetailActivity.this).load(Uri.parse(mGoodsData.getGoods_img().get(0))).into(acGoodsDetailIvInnerGoodsImg);
                        acGoodsDetailTvInnerGoodsTitle.setText(mGoodsData.getGoods_name());
                        acGoodsDetailTvInnerGoodsPrice.setText("￥" + mGoodsData.getMember_price());
                        break;
                }
                acGoodsDetailIvQrcode.setImageBitmap(QRCodeUtil.createQRCodeBitmap(imgStrMsg, (int) getResources().getDimension(R.dimen.dp_180)));
                break;
            case R.id.ac_goodsDetail_ll_store://店铺
                if (user_id.equals("")) {
                    CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
                            }
                        }
                    });
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("去登录");
                    dialog.show();
                } else {
                    if (type.equals("1")) {
                        openActivity(StoreActivity.class, mGoodsData.getUser_id());
                    } else if (type.equals("4")) {
                        openActivity(StoreActivity.class, mSeckillData.getUser_id() + "");
                    }

                }
                break;
            case R.id.ac_goodsDetail_iv_home://首页
                openActivity(MainActivity.class);
                break;
            case R.id.ac_goodsDetail_save:
                Bitmap bitmap = SimpleUtil.createViewBitmap(acGoodsDetailLlInncer);
                showSingleBottomDialog(bitmap);
                break;
        }
    }

    private void getGoodsContent() {
        switch (type) {
            case "4":
                setGoodsContent(MyCustomUtils.getImgStr(mSeckillData.getContent()));
                break;
            case "3":
                setGoodsContent(MyCustomUtils.getImgStr(mPreData.getContent()));
                break;
            case "1":
                setGoodsContent(mGoodsData.getGoods_content());
                break;
        }
    }

    private void getComments() {
        MainSubscribe.getStoreComment(goodsId, type, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setComment(result);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    private void setComment(String result) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acGoodsDetailRvComment.setLayoutManager(layoutManager);
        CommentResponseBean bean = GsonUtils.fromJson(result, CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data = bean.getData();

        if (data == null || data.size() == 0) {
            acGoodsDetailRvComment.setVisibility(View.GONE);
            acGoodsDetailTvComment.setVisibility(View.VISIBLE);
        } else {
            acGoodsDetailRvComment.setVisibility(View.VISIBLE);
            acGoodsDetailTvComment.setVisibility(View.GONE);
        }

        CommentAdapter adapter = new CommentAdapter(this, data);
        acGoodsDetailRvComment.setItemAnimator(new DefaultItemAnimator());
        acGoodsDetailRvComment.setAdapter(adapter);
    }

    private void goodsCollect() {
        SetSubscribe.addCollection(user_id, token, goodsId, collectType, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                acGoodsDetailIvCollect.setImageResource(R.drawable.collect_star);
                Toast.makeText(GoodsDetailActivity.this, "商品收藏成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFault(String errorMsg) {
                showToast("已收藏过该商品");
            }
        }));
    }

    //响应弹出框点击事件
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_pop_goods_num_add:
                    count = Integer.valueOf((String) tvCartNum.getText());
                    if (count == 1) {
                        Toast.makeText(GoodsDetailActivity.this, "不能再减了哦", Toast.LENGTH_SHORT).show();
                    } else {
                        count--;
                        tvCartNum.setText((count) + "");
                    }
                    break;
                case R.id.item_pop_goods_num_jian://数量减
                    count = Integer.valueOf((String) tvCartNum.getText());
                    count++;
                    tvCartNum.setText(count + "");
                    break;
                case R.id.item_pop_goods_add_shopping_car://加入购物车
                    attribute_id = sizePopWin.getAttId();

                    AddCartBean bean = new AddCartBean();
                    bean.setUser_id(user_id);
                    bean.setToken(token);
                    bean.setGoods_id(goodsId);
                    bean.setGoods_num(count + "");
                    bean.setItem_id(attribute_id);

                    if (sizePopWin.getHasAttribute()) {
                        if (attribute_id.equals("")) {
                            showToast("请选择商品规格");
                        } else {
                            SetSubscribe.addCart(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                                @Override
                                public void onSuccess(String result) {
                                    Toast.makeText(GoodsDetailActivity.this, "加入购物车成功", Toast.LENGTH_LONG).show();
                                    sizePopWin.dismiss();
                                    backgroundAlpha(1f);
                                }

                                @Override
                                public void onFault(String errorMsg) {
                                    //showToast(errorMsg);
                                }
                            }));
                        }
                    } else {
                        SetSubscribe.addCart(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(GoodsDetailActivity.this, "加入购物车成功", Toast.LENGTH_LONG).show();
                                sizePopWin.dismiss();
                                backgroundAlpha(1f);
                            }

                            @Override
                            public void onFault(String errorMsg) {
                                //showToast(errorMsg);
                            }
                        }));
                    }
                    break;
                case R.id.item_pop_goods_buy://立即购买
                    attribute_id = sizePopWin.getAttId();
                    if (sizePopWin.getHasAttribute()) {//有商品规格
                        if (attribute_id.equals("")) {
                            showToast("请选择商品规格");
                        } else {
                            Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                            if ("miaosha".equals(getIntent().getAction())) {
                                intent.putExtra(Const.ATTRIBUTE_ID, attribute_id);
                                intent.putExtra("count", count + "");
                                intent.setAction("miaosha");
                            } else if (Const.PRESELL.equals(getIntent().getAction())) {
                                intent.putExtra(Const.ATTRIBUTE_ID, attribute_id);
                                intent.setAction(Const.PRESELL);
                            } else {
                                intent.putExtra(Const.ATTRIBUTE_ID, attribute_id);
                                intent.putExtra("count", count + "");
                                if (level.equals("0")) {
                                    intent.putExtra("price", mGoodsData.getUser_price());
                                } else {
                                    intent.putExtra("price", mGoodsData.getMember_price());
                                }
                                double lisheng = sizePopWin.getLiSheng() * count;
                                intent.putExtra("lisheng", String.format("%.2f", lisheng));
                                intent.setAction(Const.GOODS);
                            }
                            intent.putExtra(Const.GOODS_ID, goodsId);
                            startActivity(intent);
                            sizePopWin.dismiss();
                        }
                    } else {//没有商品规格
                        Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                        if ("miaosha".equals(getIntent().getAction())) {
                            intent.putExtra(Const.ATTRIBUTE_ID, attribute_id);
                            intent.putExtra("count", count + "");
                            intent.setAction("miaosha");
                        } else if (Const.PRESELL.equals(getIntent().getAction())) {
                            intent.putExtra(Const.ATTRIBUTE_ID, attribute_id);
                            intent.setAction(Const.PRESELL);
                        } else {
                            intent.putExtra(Const.ATTRIBUTE_ID, attribute_id);
                            intent.putExtra("count", count + "");
                            if (level.equals("0")) {
                                intent.putExtra("price", mGoodsData.getUser_price());
                            } else {
                                intent.putExtra("price", mGoodsData.getMember_price());
                            }
                            double lisheng = sizePopWin.getLiSheng() * count;
                            intent.putExtra("lisheng", String.format("%.2f", lisheng));
                            intent.setAction(Const.GOODS);
                        }
                        intent.putExtra(Const.GOODS_ID, goodsId);
                        startActivity(intent);
                        sizePopWin.dismiss();
                    }
                    break;
            }
        }
    };

    private void showPopupwindow(View view) {
        String type = "";
        if ("miaosha".equals(getIntent().getAction())) {
            type = "1";
            sizePopWin = new GoodSizePopupwindow(this, onClickListener, mSeckillData);
        } else if ("presell".equals(getIntent().getAction())) {
            type = "2";
            sizePopWin = new GoodSizePopupwindow(this, onClickListener, mPreData);
        } else {
            type = "3";
            sizePopWin = new GoodSizePopupwindow(this, onClickListener, mGoodsData);
        }
        View contentView = sizePopWin.getContentView();
//        addCartNumTv = ((TextView) contentView.findViewById(R.id.goodsRule_numTv));
        //设置Popupwindow显示位置（从底部弹出）
        sizePopWin.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //当弹出Popupwindow时，背景变半透明
        backgroundAlpha(0.4f);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        tvCartNum = contentView.findViewById(R.id.item_pop_goods_tv_num);
        final ImageView headImg = contentView.findViewById(R.id.pop_goods_size_iv_pic);
        TextView marketPrice = contentView.findViewById(R.id.pop_goods_size_tv_old_price);
        TextView memberPrice = contentView.findViewById(R.id.pop_goods_size_tv_price);
        TextView inventory = contentView.findViewById(R.id.pop_goods_size_tv_total_num);
        // TextView toVip = contentView.findViewById(R.id.pw_goodsSize_tv_toVIP);
        RelativeLayout rlToVip = contentView.findViewById(R.id.pw_goodsSize_rl_toVip);
        switch (type) {
            case "1":
                Glide.with(this).load(Uri.parse(mSeckillData.getGroup_img().get(0))).into(headImg);
                marketPrice.setText("￥" + mSeckillData.getGoods_price());
                rlToVip.setVisibility(View.INVISIBLE);
                break;
            case "2":
                Glide.with(this).load(Uri.parse(mPreData.getGroup_img().get(0))).into(headImg);
                rlToVip.setVisibility(View.INVISIBLE);
                break;
            case "3":
                Glide.with(this).load(Uri.parse(mGoodsData.getGoods_img().get(0))).into(headImg);
                /*marketPrice.setText("￥" + mGoodsData.getUser_price());
                memberPrice.setText("代言人价 " + mGoodsData.getMember_price());
                inventory.setText("库存：" + mGoodsData.getInventory());*/
                //double lisheng = Double.parseDouble(mGoodsData.getUser_price()) - Double.parseDouble(mGoodsData.getMember_price());
                //toVip.setText("成为农品街代言人此单立省" + String.format("%.2f", lisheng) + "元");
                break;
        }
        rlToVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level.equals("0")) {
                    openActivity(MembershipPackageActivity.class);
                } else {
                    showToast("您已经是代言人了，提交订单时自动减免");
                }
            }
        });
        sizePopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 初始化轮播图
     */
    private void initBanner(ArrayList<String> imgList) {
        ArrayList<Uri> list = new ArrayList<>();
        for (int i = 0; i < imgList.size(); i++) {
            list.add(Uri.parse(imgList.get(i)));
        }
        //设置banner样式
        atGoodsDetailBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        atGoodsDetailBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        atGoodsDetailBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        atGoodsDetailBanner.setImages(list);
        //设置banner动画效果
        atGoodsDetailBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        atGoodsDetailBanner.isAutoPlay(true);
        //设置轮播时间
        atGoodsDetailBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        atGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置banner点击事件
        atGoodsDetailBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.e("huang", "position = " + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        atGoodsDetailBanner.start();
    }


    //两个选项的Dialog
    private void showSingleBottomDialog(final Bitmap bmp) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        final View view = View.inflate(this, R.layout.dialog_share_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dialog_share_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acGoodsDetailLlInner.setVisibility(View.GONE);
                WXImageObject imgObj = new WXImageObject(bmp);
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imgObj;
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120, 120, true);
                bmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmp, true);  // 设置所图；
                msg.title = "标题";
                msg.description = "内容";
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "img" + String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
                //  req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
                api.sendReq(req);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_share_pyq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acGoodsDetailLlInner.setVisibility(View.GONE);
                WXImageObject imgObj = new WXImageObject(bmp);
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = imgObj;
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120, 120, true);
                bmp.recycle();
                msg.thumbData = bmpToByteArray(thumbBmp, true);  // 设置所图；
                msg.title = "标题";
                msg.description = "内容";
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "img" + String.valueOf(System.currentTimeMillis());
                req.message = msg;
                //req.scene = SendMessageToWX.Req.WXSceneSession;   //设置发送给朋友
                req.scene = SendMessageToWX.Req.WXSceneTimeline;    //设置发送到朋友圈
                api.sendReq(req);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acGoodsDetailLlInner.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }
}
