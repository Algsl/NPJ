package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
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
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.adapter.GoodsImgDetailAdapter;
import com.zthx.npj.banner.Banner;
import com.zthx.npj.banner.BannerConfig;
import com.zthx.npj.banner.loader.LocalImageLoader;
import com.zthx.npj.banner.loader.LocalVideoLoader;
import com.zthx.npj.banner.loader.ViewItemBean;
import com.zthx.npj.banner.transformer.DefaultTransformer;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BaoJiaBean;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.NeedDetailResponseBean;
import com.zthx.npj.net.been.SupplyDetailResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
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
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zthx.npj.ui.UserMsgActivity.bmpToByteArray;

public class SupplyProductsActivity extends ActivityBase {

    @BindView(R.id.at_supply_products_btn_buy_now)
    Button atSupplyProductsBtnBuyNow;
    @BindView(R.id.at_need_products_tv_caigou_num)
    TextView atNeedProductsTvCaigouNum;
    @BindView(R.id.at_supply_products_tv_price)
    TextView atSupplyProductsTvPrice;
    @BindView(R.id.at_supply_products_tv_unit)
    TextView atSupplyProductsTvUnit;
    @BindView(R.id.at_supply_products_tv_title)
    TextView atSupplyProductsTvTitle;
    @BindView(R.id.at_supply_products_tv_look_num)
    TextView atSupplyProductsTvLookNum;
    @BindView(R.id.at_supply_products_tv_sell_num)
    TextView atSupplyProductsTvSellNum;
    @BindView(R.id.at_supply_products_ll_need_baojia)
    LinearLayout atSupplyProductsLlNeedBaojia;
    @BindView(R.id.at_supply_products_ll_supply_guanggao)
    LinearLayout atSupplyProductsLlSupplyGuanggao;
    @BindView(R.id.ll_xainshanggoumai)
    LinearLayout llXainshanggoumai;
    @BindView(R.id.renzhengshangjia)
    TextView renzhengshangjia;
    @BindView(R.id.at_supply_products_rl_need_guanggao)
    RelativeLayout atSupplyProductsRlNeedGuanggao;
    @BindView(R.id.at_supply_products_iv_head_pic)
    MyCircleView atSupplyProductsIvHeadPic;
    @BindView(R.id.at_supply_products_tv_name)
    TextView atSupplyProductsTvName;
    @BindView(R.id.at_supply_products_tv_diwei)
    TextView atSupplyProductsTvDiwei;
    @BindView(R.id.at_supply_products_tv_xinyufen)
    TextView atSupplyProductsTvXinyufen;
    @BindView(R.id.at_supply_products_rv_pic)
    RecyclerView atSupplyProductsRvPic;
    @BindView(R.id.at_supply_products_ll_call)
    LinearLayout atSupplyProductsLlCall;
    @BindView(R.id.at_supply_products_ll_chat)
    LinearLayout atSupplyProductsLlChat;
    @BindView(R.id.at_supply_product_ll_supply_detail)
    LinearLayout atSupplyProductLlSupplyDetail;
    @BindView(R.id.at_supply_product_ll_need_detail)
    LinearLayout atSupplyProductLlNeedDetail;
    @BindView(R.id.at_supply_product_tv_baojia)
    TextView atSupplyProductTvBaojia;
    @BindView(R.id.ac_supply_iv_back)
    ImageView acSupplyIvBack;
    @BindView(R.id.ac_supply_iv_home)
    ImageView acSupplyIvHome;
    @BindView(R.id.ac_supplyProducts_seeInfo)
    TextView acSupplyProductsSeeInfo;
    @BindView(R.id.at_supply_products_tv_level)
    ImageView atSupplyProductsTvLevel;
    @BindView(R.id.ac_supply_tv_detail)
    TextView acSupplyTvDetail;
    @BindView(R.id.ac_supply_tv_common)
    TextView acSupplyTvCommon;
    @BindView(R.id.ac_supply_tv_realName)
    TextView acSupplyTvRealName;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ac_supply_tv_enterPrice)
    TextView acSupplyTvEnterPrice;
    @BindView(R.id.ac_supply_tv_purchase)
    TextView acSupplyTvPurchase;
    @BindView(R.id.ac_supply_tv_trust)
    TextView acSupplyTvTrust;
    @BindView(R.id.ac_supply_tv_zizhi)
    TextView acSupplyTvZizhi;
    @BindView(R.id.ac_supply_ll)
    LinearLayout acSupplyLl;
    @BindView(R.id.at_supply_product_tv_address)
    TextView atSupplyProductTvAddress;
    @BindView(R.id.ac_supply_product_showLocation)
    RelativeLayout acSupplyProductShowLocation;
    @BindView(R.id.comment_hint)
    TextView commentHint;
    @BindView(R.id.ac_supply_ll_renzheng)
    LinearLayout acSupplyLlRenzheng;
    @BindView(R.id.ac_supply_ll_baozheng)
    LinearLayout acSupplyLlBaozheng;

    @BindView(R.id.ac_supply_products_iv_share)
    ImageView acSupplyProductsIvShare;
    @BindView(R.id.ac_supply_products_iv_innerGoodsImg)
    RoundedImageView acSupplyProductsIvInnerGoodsImg;
    @BindView(R.id.ac_supply_products_tv_innerGoodsTitle)
    TextView acSupplyProductsTvInnerGoodsTitle;
    @BindView(R.id.ac_supply_products_tv_innerGoodsPrice)
    TextView acSupplyProductsTvInnerGoodsPrice;
    @BindView(R.id.ac_supply_products_iv_qrcode)
    ImageView acSupplyProductsIvQrcode;
    @BindView(R.id.ac_supply_products_Rl_inncer)
    RelativeLayout acSupplyProductsRlInncer;
    @BindView(R.id.ac_supply_products_save)
    TextView acSupplyProductsSave;
    @BindView(R.id.ac_supply_products_ll_inner)
    LinearLayout acSupplyProductsLlInner;


    private String type;
    private String goodsId;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private SupplyDetailResponseBean.DataBean supplyData;
    private NeedDetailResponseBean.DataBean needData;
    private GoodsImgDetailAdapter adapter;
    private String sn_user_id = "";

    private String mobile;
    private String nick_name;
    private String lat;
    private String lng;

    private String imgStrMsg = "";
    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_products);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        api.registerApp("wx76500efa65d19915");

        back(acSupplyIvBack);

        goodsId = getIntent().getStringExtra("goods_id");
        type = getIntent().getAction();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SupplyProductsActivity.this);
        atSupplyProductsRvPic.setLayoutManager(layoutManager);
        if (type.equals(Const.SUPPLY_DETAIL)) {
            atSupplyProductLlSupplyDetail.setVisibility(View.VISIBLE);
            atSupplyProductLlNeedDetail.setVisibility(View.GONE);
            getSupplyData(goodsId);
        } else {
            atSupplyProductLlSupplyDetail.setVisibility(View.GONE);
            atSupplyProductLlNeedDetail.setVisibility(View.VISIBLE);
            getNeedData(goodsId);
            atSupplyProductsBtnBuyNow.setText("我要报价");
        }

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (type.equals(Const.SUPPLY_DETAIL)) {
                    getSupplyData(goodsId);
                } else {
                    getNeedData(goodsId);
                }
                refreshlayout.finishRefresh();
                showToast("刷新完成");
            }
        });
    }

    //求购详情
    private void getNeedData(String id) {
        DiscoverSubscribe.needDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                NeedDetailResponseBean bean = GsonUtils.fromJson(result, NeedDetailResponseBean.class);
                needData = bean.getData();
                MyCustomUtils.showLevelImg(needData.getLevel(), atSupplyProductsTvLevel);
                atNeedProductsTvCaigouNum.setVisibility(View.VISIBLE);
                atSupplyProductsLlNeedBaojia.setVisibility(View.VISIBLE);
                atSupplyProductsLlSupplyGuanggao.setVisibility(View.GONE);
                atSupplyProductsRlNeedGuanggao.setVisibility(View.VISIBLE);
                lat = needData.getLat();
                lng = needData.getLng();
                LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                getLocateinfo(latLng);

                mobile = needData.getMobile();
                nick_name = needData.getNick_name();

                initBanner1(needData.getImg());
                atSupplyProductsTvPrice.setText(needData.getAmount());
                atSupplyProductsTvUnit.setText(needData.getUnit());
                atSupplyProductsTvTitle.setText(needData.getTitle());

                if (needData.getCertification() == null) {
                    acSupplyLl.setVisibility(View.GONE);
                } else {
                    acSupplyLl.setVisibility(View.VISIBLE);
                    String[] strs = needData.getCertification().split(",");
                    for (String str : strs) {
                        if (str.equals("1")) {
                            acSupplyTvRealName.setVisibility(View.VISIBLE);
                        } else if (str.equals("2")) {
                            acSupplyTvEnterPrice.setVisibility(View.VISIBLE);
                        } else if (str.equals("3")) {
                            acSupplyTvPurchase.setVisibility(View.VISIBLE);
                        } else if (str.equals("4")) {
                            acSupplyTvTrust.setVisibility(View.VISIBLE);
                        } else if (str.equals("5")) {
                            acSupplyTvZizhi.setVisibility(View.VISIBLE);
                        }
                    }
                }
                atSupplyProductsTvLookNum.setText((needData.getHits() == null ? "0" : needData.getHits()) + "人看过");
                if (needData.getUser_count() >= 0) {
                    atSupplyProductTvBaojia.setText(needData.getUser_count() + "人已报价");
                } else {
                    atSupplyProductTvBaojia.setText("0人已报价");
                }
                if (needData.getReputation() >= 0) {
                    atSupplyProductsTvXinyufen.setText("信誉分： " + needData.getReputation());
                } else {
                    atSupplyProductsTvXinyufen.setText("信誉分： 0");
                }


                sn_user_id = needData.getUser_id();
                if (!needData.getHead_img().equals("")) {
                    Glide.with(SupplyProductsActivity.this).load(needData.getHead_img()).into(atSupplyProductsIvHeadPic);
                }
                if (needData.getNick_name().substring(0, 2).equals("用户")) {
                    atSupplyProductsTvName.setText("农品街新客");
                } else {
                    atSupplyProductsTvName.setText(needData.getNick_name());
                }

                atSupplyProductsTvXinyufen.setText("信誉分" + needData.getReputation());
                //SupplyProductsAdapter adapter = new SupplyProductsAdapter(SupplyProductsActivity.this, data.getContent());
                GoodsImgDetailAdapter adapter = new GoodsImgDetailAdapter(SupplyProductsActivity.this, needData.getContent());
                atSupplyProductsRvPic.setItemAnimator(new DefaultItemAnimator());
                atSupplyProductsRvPic.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    //供应详情
    private void getSupplyData(String id) {
        DiscoverSubscribe.supplyDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SupplyDetailResponseBean supplyDetailResponseBean = GsonUtils.fromJson(result, SupplyDetailResponseBean.class);
                supplyData = supplyDetailResponseBean.getData();
                initBanner1(supplyData.getGoods_img());

                MyCustomUtils.showLevelImg(supplyData.getLevel(), atSupplyProductsTvLevel);
                atSupplyProductsTvPrice.setText("￥" + supplyData.getPrice());
                atSupplyProductsTvUnit.setText("元/" + supplyData.getGoods_unit());
                atSupplyProductsTvTitle.setText(supplyData.getTitle());
                lat = supplyData.getLat();
                lng = supplyData.getLng();
                LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                getLocateinfo(latLng);

                mobile = supplyData.getMobile();
                nick_name = supplyData.getNick_name();

                if (supplyData.getHits() == null) {
                    atSupplyProductsTvLookNum.setText("0人看过");
                } else {
                    atSupplyProductsTvLookNum.setText(supplyData.getHits() + "人看过");
                }
                if (supplyData.getSold() == null) {
                    atSupplyProductsTvSellNum.setText("已售 0");
                } else {
                    atSupplyProductsTvSellNum.setText("已售 " + supplyData.getSold());
                }
                if (supplyData.getReputation() == null) {
                    atSupplyProductsTvXinyufen.setText("信誉分： 0");
                } else {
                    atSupplyProductsTvXinyufen.setText("信誉分： " + supplyData.getReputation());
                }
                sn_user_id = supplyData.getUser_id();
                if (!supplyData.getHead_img().equals("")) {
                    Glide.with(SupplyProductsActivity.this).load(supplyData.getHead_img()).into(atSupplyProductsIvHeadPic);
                }
                if (supplyData.getNick_name().substring(0, 2).equals("用户")) {
                    atSupplyProductsTvName.setText("农品街新客");
                } else {
                    atSupplyProductsTvName.setText(supplyData.getNick_name());
                }

                if (supplyData.getCertification() == null) {
                    acSupplyLl.setVisibility(View.GONE);
                } else {
                    acSupplyLl.setVisibility(View.VISIBLE);
                    String[] strs = supplyData.getCertification().split(",");
                    for (String str : strs) {
                        if (str.equals("1")) {
                            acSupplyTvRealName.setVisibility(View.VISIBLE);
                            acSupplyLlRenzheng.setVisibility(View.VISIBLE);
                            acSupplyLlBaozheng.setVisibility(View.GONE);
                        } else if (str.equals("2")) {
                            acSupplyTvEnterPrice.setVisibility(View.VISIBLE);
                        } else if (str.equals("3")) {
                            acSupplyTvPurchase.setVisibility(View.VISIBLE);
                        } else if (str.equals("4")) {
                            acSupplyTvTrust.setVisibility(View.VISIBLE);
                        } else if (str.equals("5")) {
                            acSupplyTvZizhi.setVisibility(View.VISIBLE);
                        }
                    }
                }
                //目前level没有给出具体分级名称
                //还剩一个recycleView的图片单元要补
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SupplyProductsActivity.this);
                atSupplyProductsRvPic.setLayoutManager(layoutManager);
                //SupplyProductsAdapter adapter = new SupplyProductsAdapter(SupplyProductsActivity.this, supplyData.getContent());
                GoodsImgDetailAdapter adapter = new GoodsImgDetailAdapter(SupplyProductsActivity.this, supplyData.getContent());
                atSupplyProductsRvPic.setItemAnimator(new DefaultItemAnimator());
                atSupplyProductsRvPic.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }));
    }

    private void initBanner1(ArrayList<String> bannerList) {
        ArrayList<ViewItemBean> list = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            list.add(new ViewItemBean(bannerList.get(i)));
        }
        banner.setViews(list)
                .setBannerAnimation(DefaultTransformer.class)
                .setImageLoader(new LocalImageLoader())
                .setVideoLoader(new LocalVideoLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .start();
    }

    @OnClick({R.id.at_supply_products_btn_buy_now, R.id.at_supply_products_ll_call, R.id.at_supply_products_ll_chat,
            R.id.ac_supply_iv_home, R.id.ac_supplyProducts_seeInfo, R.id.ac_supply_tv_detail, R.id.ac_supply_tv_common,
            R.id.ac_supply_product_showLocation, R.id.ac_supply_products_iv_share,R.id.ac_supply_products_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_supply_products_ll_call:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile)));
                break;
            case R.id.at_supply_products_ll_chat:
                ChatInfo chatInfo = new ChatInfo();
                chatInfo.setType(TIMConversationType.C2C);
                chatInfo.setId(mobile);
                chatInfo.setChatName(nick_name);
                Intent intent = new Intent(BaseApp.getApp(), ChatActivity.class);
                intent.putExtra(Constants.CHAT_INFO, chatInfo);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApp.getApp().startActivity(intent);
                break;
            case R.id.at_supply_products_btn_buy_now:
                if (user_id.equals("")) {
                    CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                startActivity(new Intent(SupplyProductsActivity.this, LoginActivity.class));
                            }
                        }
                    });
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("去登录");
                    dialog.show();
                } else {
                    if (type.equals(Const.SUPPLY_DETAIL)) {
                        openActivity(SupplyBillActivity.class, goodsId + "");
                    } else {
                        showPublishPopwindow();
                    }
                }
                break;
            case R.id.ac_supply_iv_home:
                openActivity(MainActivity.class);
                break;
            case R.id.ac_supplyProducts_seeInfo:
                openActivity(UserMsgActivity.class, sn_user_id);
                break;
            case R.id.ac_supply_tv_detail:
                acSupplyTvDetail.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acSupplyTvDetail.setTextColor(getResources().getColor(R.color.white));
                acSupplyTvCommon.setBackgroundColor(getResources().getColor(R.color.white));
                acSupplyTvCommon.setTextColor(getResources().getColor(R.color.text3));
                commentHint.setVisibility(View.GONE);
                if (type.equals(Const.SUPPLY_DETAIL)) {
                    adapter = new GoodsImgDetailAdapter(SupplyProductsActivity.this, supplyData.getContent());
                } else {
                    adapter = new GoodsImgDetailAdapter(SupplyProductsActivity.this, needData.getContent());
                }
                atSupplyProductsRvPic.setItemAnimator(new DefaultItemAnimator());
                atSupplyProductsRvPic.setAdapter(adapter);
                break;
            case R.id.ac_supply_tv_common:
                acSupplyTvDetail.setBackgroundColor(getResources().getColor(R.color.white));
                acSupplyTvDetail.setTextColor(getResources().getColor(R.color.text3));
                acSupplyTvCommon.setBackgroundColor(getResources().getColor(R.color.app_theme));
                acSupplyTvCommon.setTextColor(getResources().getColor(R.color.white));
                getComment();
                break;
            case R.id.ac_supply_product_showLocation:
                openActivity(ShowLocationActivity.class, lat, lng);
                break;
            case R.id.ac_supply_products_iv_share:
                acSupplyProductsLlInner.setVisibility(View.VISIBLE);
                String url = "";
                if (type.equals(Const.SUPPLY_DETAIL)) {
                    url = supplyData.getGoods_img().get(0);
                    imgStrMsg = "http://game.npj-vip.com/h5/jumpApp.html?type=gongying&id=" + goodsId + "&img=null&discount=0";
                    acSupplyProductsTvInnerGoodsTitle.setText(supplyData.getGoods_name());
                    acSupplyProductsTvInnerGoodsPrice.setText("￥" + supplyData.getPrice());
                } else {
                    url = needData.getImg().get(0);
                    imgStrMsg = "http://game.npj-vip.com/h5/jumpApp.html?type=qiugou&id=" + goodsId + "&img=null&discount=0";
                    acSupplyProductsTvInnerGoodsTitle.setText(needData.getTitle());
                    acSupplyProductsTvInnerGoodsPrice.setVisibility(View.GONE);
                }
                if (url.substring(url.length() - 4).equals(".mp4")) {
                    acSupplyProductsIvInnerGoodsImg.setImageBitmap(MyCustomUtils.getVideoThumbnail(url));
                } else {
                    Glide.with(this).load(Uri.parse(url)).into(acSupplyProductsIvInnerGoodsImg);
                }
                acSupplyProductsIvQrcode.setImageBitmap(QRCodeUtil.createQRCodeBitmap(imgStrMsg, (int) getResources().getDimension(R.dimen.dp_180)));
                break;
            case R.id.ac_supply_products_save:
                Bitmap bitmap = SimpleUtil.createViewBitmap(acSupplyProductsRlInncer);
                showSingleBottomDialog(bitmap);
                break;
        }
    }

    private void getComment() {
        MainSubscribe.getStoreComment(goodsId, "7", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CommentResponseBean bean = GsonUtils.fromJson(result, CommentResponseBean.class);
                if (bean.getData().size() == 0) {
                    commentHint.setVisibility(View.VISIBLE);
                } else {
                    commentHint.setVisibility(View.GONE);
                }
                CommentAdapter adapter = new CommentAdapter(SupplyProductsActivity.this, bean.getData());
                atSupplyProductsRvPic.setItemAnimator(new DefaultItemAnimator());
                atSupplyProductsRvPic.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
            }
        }));
    }

    public void showPublishPopwindow() {
        backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_baojia, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView);
        window.setHeight((int) getResources().getDimension(R.dimen.dp_350));
        window.setWidth((int) getResources().getDimension(R.dimen.dp_271));
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        //window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        final EditText baojiaContent = contentView.findViewById(R.id.pw_baojia_et_content);
        final EditText baojiaRemark = contentView.findViewById(R.id.pw_baojia_et_remark);
        Button commit = contentView.findViewById(R.id.pw_baojia_btn_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = baojiaContent.getText().toString().trim();
                String remark = baojiaRemark.getText().toString().trim();
                BaoJiaBean bean = new BaoJiaBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setContent(content);
                bean.setRemark(remark);
                bean.setList_id(goodsId);
                DiscoverSubscribe.baojia(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        backgroundAlpha(1f);
                        window.dismiss();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        //showToast(errorMsg);
                    }
                }));
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.pw_iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private void getLocateinfo(LatLng latLng) {
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                atSupplyProductTvAddress.setText(reverseGeoCodeResult.getAddress());
                //Log.e(TAG, "onGetReverseGeoCodeResult: "+ reverseGeoCodeResult.getAddress() + reverseGeoCodeResult.getSematicDescription());
            }
        });
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }
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
                acSupplyProductsLlInner.setVisibility(View.GONE);
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
                acSupplyProductsLlInner.setVisibility(View.GONE);
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
                acSupplyProductsLlInner.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }
}
