package com.zthx.npj.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.adapter.GoodsImgDetailAdapter;
import com.zthx.npj.banner.Banner;
import com.zthx.npj.banner.BannerConfig;
import com.zthx.npj.banner.loader.LocalImageLoader;
import com.zthx.npj.banner.loader.LocalVideoLoader;
import com.zthx.npj.banner.loader.ViewItemBean;
import com.zthx.npj.banner.transformer.DefaultTransformer;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BaoJiaBean;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.NeedDetailResponseBean;
import com.zthx.npj.net.been.SupplyDetailResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.ac_supply_tv_company)
    TextView acSupplyTvCompany;
    @BindView(R.id.ac_supply_tv_realName)
    TextView acSupplyTvRealName;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private String type;
    private String goodsId;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private SupplyDetailResponseBean.DataBean supplyData;
    private NeedDetailResponseBean.DataBean needData;
    private GoodsImgDetailAdapter adapter;
    private String sn_user_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_products);
        ButterKnife.bind(this);

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
                if(type.equals(Const.SUPPLY_DETAIL)){
                    getSupplyData(goodsId);
                }else{
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
                showLevel(needData.getLevel());
                atNeedProductsTvCaigouNum.setVisibility(View.VISIBLE);
                atSupplyProductsLlNeedBaojia.setVisibility(View.VISIBLE);
                atSupplyProductsLlSupplyGuanggao.setVisibility(View.GONE);
                atSupplyProductsRlNeedGuanggao.setVisibility(View.VISIBLE);

                initBanner1(needData.getImg());
                atSupplyProductsTvPrice.setText(needData.getAmount());
                atSupplyProductsTvUnit.setText(needData.getUnit());
                atSupplyProductsTvTitle.setText(needData.getTitle());

                if (needData.getCertification() != null) {
                    String[] strs = needData.getCertification().split(",");
                    for (String str : strs) {
                        if (str.equals("1")) {
                            acSupplyTvRealName.setVisibility(View.VISIBLE);
                        } else if (str.equals("2")) {
                            acSupplyTvCompany.setVisibility(View.VISIBLE);
                        }
                    }
                }

                if (needData.getHits() != null) {
                    atSupplyProductsTvLookNum.setText(needData.getHits() + "人看过");
                } else {
                    atSupplyProductsTvLookNum.setText("0人看过");
                }
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

                Glide.with(SupplyProductsActivity.this).load(needData.getHead_img()).into(atSupplyProductsIvHeadPic);
                atSupplyProductsTvName.setText(needData.getNick_name());
                atSupplyProductsTvXinyufen.setText("信誉分" + needData.getReputation());
                //SupplyProductsAdapter adapter = new SupplyProductsAdapter(SupplyProductsActivity.this, data.getContent());
                GoodsImgDetailAdapter adapter = new GoodsImgDetailAdapter(SupplyProductsActivity.this, needData.getContent());
                atSupplyProductsRvPic.setItemAnimator(new DefaultItemAnimator());
                atSupplyProductsRvPic.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
            }
        }, this));
    }

    //供应详情
    private void getSupplyData(String id) {
        DiscoverSubscribe.supplyDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SupplyDetailResponseBean supplyDetailResponseBean = GsonUtils.fromJson(result, SupplyDetailResponseBean.class);
                supplyData = supplyDetailResponseBean.getData();
                initBanner1(supplyData.getGoods_img());

                showLevel(supplyData.getLevel());
                atSupplyProductsTvPrice.setText("￥" + supplyData.getPrice());
                atSupplyProductsTvUnit.setText("元/" + supplyData.getGoods_unit());
                atSupplyProductsTvTitle.setText(supplyData.getTitle());
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
                if (supplyData.getCertification() != null) {
                    String[] strs = supplyData.getCertification().split(",");
                    for (String str : strs) {
                        if (str.equals("1")) {
                            acSupplyTvRealName.setVisibility(View.VISIBLE);
                        } else if (str.equals("2")) {
                            acSupplyTvCompany.setVisibility(View.VISIBLE);
                        }
                    }
                }
                /*atSupplyProductsTvLookNum.setText(data.getHits() + "人看过");
                atSupplyProductsTvSellNum.setText("已售 " + data.getSold());
                atSupplyProductsTvXinyufen.setText("信誉分： " + data.getReputation());*/

                Glide.with(SupplyProductsActivity.this).load(supplyData.getHead_img()).into(atSupplyProductsIvHeadPic);
                atSupplyProductsTvName.setText(supplyData.getNick_name());
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
        }, this));
    }

    /**
     * 初始化轮播图
     */
    /*private void initBanner(ArrayList<String> bannerList) {

        ArrayList<Uri> list = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            list.add(Uri.parse(bannerList.get(i)));
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
//        atSupplyProductsBanner.setBannerTitles(list2);
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
    }*/
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
            R.id.ac_supply_iv_home, R.id.ac_supplyProducts_seeInfo, R.id.ac_supply_tv_detail, R.id.ac_supply_tv_common})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_supply_products_ll_call:
                break;
            case R.id.at_supply_products_ll_chat:
                break;

            case R.id.at_supply_products_btn_buy_now:
                if (type.equals(Const.SUPPLY_DETAIL)) {
                    openActivity(SupplyBillActivity.class, goodsId + "");
                } else {
                    showPublishPopwindow();
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
        }
    }

    private void getComment() {
        MainSubscribe.getStoreComment(goodsId, "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CommentResponseBean bean = GsonUtils.fromJson(result, CommentResponseBean.class);
                CommentAdapter adapter = new CommentAdapter(SupplyProductsActivity.this, bean.getData());
                atSupplyProductsRvPic.setItemAnimator(new DefaultItemAnimator());
                atSupplyProductsRvPic.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {
                showToast(errorMsg);
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
                        showToast(errorMsg);
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

    public void showLevel(int level) {
        switch (level) {
            case 0:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level0);
                break;
            case 1:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level1);
                break;
            case 2:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level2);
                break;
            case 3:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level3);
                break;
            case 4:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level4);
                break;
            case 5:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level5);
                break;
            case 6:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level6);
                break;
            case 7:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level7);
                break;
            case 8:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level8);
                break;
            case 9:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level9);
                break;
            case 10:
                atSupplyProductsTvLevel.setImageResource(R.drawable.level10);
                break;
        }
    }

}
