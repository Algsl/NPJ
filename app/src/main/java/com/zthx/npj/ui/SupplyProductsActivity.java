package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.LocalStoreAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.BaoJiaBean;
import com.zthx.npj.net.been.NeedDetailResponseBean;
import com.zthx.npj.net.been.SupplyDetailResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.GlideImageLoader;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplyProductsActivity extends ActivityBase {

    @BindView(R.id.at_supply_products_btn_buy_now)
    Button atSupplyProductsBtnBuyNow;
    @BindView(R.id.at_supply_products_banner)
    Banner atSupplyProductsBanner;
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
    @BindView(R.id.at_supply_products_tv_level)
    TextView atSupplyProductsTvLevel;
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

    private String type;
    private String goodsId;
    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_products);
        ButterKnife.bind(this);
        goodsId = getIntent().getStringExtra("goods_id");
        type = getIntent().getAction();
        Log.e("测试", "onCreate: "+goodsId );
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
    }

    private void getNeedData(String id) {

        DiscoverSubscribe.needDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                NeedDetailResponseBean.DataBean data = GsonUtils.fromJson(result, NeedDetailResponseBean.class).getData();

                atNeedProductsTvCaigouNum.setVisibility(View.VISIBLE);
                atSupplyProductsLlNeedBaojia.setVisibility(View.VISIBLE);
                atSupplyProductsLlSupplyGuanggao.setVisibility(View.GONE);
                atSupplyProductsRlNeedGuanggao.setVisibility(View.VISIBLE);

                initBanner(data.getImg());
                atSupplyProductsTvPrice.setText(data.getAmount());
                atSupplyProductsTvUnit.setText(data.getUnit());
                atSupplyProductsTvTitle.setText(data.getTitle());
                atSupplyProductsTvLookNum.setText(data.getHits() + "人看过");
                atSupplyProductTvBaojia.setText(data.getUser_count() + "人已报价");
                Glide.with(SupplyProductsActivity.this).load(data.getHead_img()).into(atSupplyProductsIvHeadPic);
                atSupplyProductsTvName.setText(data.getNick_name());
                atSupplyProductsTvLevel.setText(data.getLevel() + "");
                atSupplyProductsTvXinyufen.setText("信誉分" + data.getReputation());
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    private void getSupplyData(String id) {
        DiscoverSubscribe.supplyDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                SupplyDetailResponseBean supplyDetailResponseBean = GsonUtils.fromJson(result, SupplyDetailResponseBean.class);
                initBanner(supplyDetailResponseBean.getData().getGoods_img());
                atSupplyProductsTvPrice.setText(supplyDetailResponseBean.getData().getPrice());
                atSupplyProductsTvUnit.setText(supplyDetailResponseBean.getData().getGoods_unit());
                atSupplyProductsTvTitle.setText(supplyDetailResponseBean.getData().getTitle());
                atSupplyProductsTvLookNum.setText(supplyDetailResponseBean.getData().getHits() + "人看过");
                atSupplyProductsTvSellNum.setText("已售 " + supplyDetailResponseBean.getData().getSold());
                Glide.with(SupplyProductsActivity.this).load(supplyDetailResponseBean.getData().getHead_img()).into(atSupplyProductsIvHeadPic);
                atSupplyProductsTvName.setText(supplyDetailResponseBean.getData().getNick_name());
                //目前level没有给出具体分级名称
                atSupplyProductsTvLevel.setText(supplyDetailResponseBean.getData().getLevel() + "");
                atSupplyProductsTvXinyufen.setText("信誉分： " + supplyDetailResponseBean.getData().getReputation());
                //还剩一个recycleView的图片单元要补


            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }


    private void showPopupWindow(View view) {
        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(SupplyProductsActivity.this).inflate(R.layout.popupwindow_supply_producets, null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, 100, 100, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 初始化轮播图
     */
    private void initBanner(ArrayList<String> bannerList) {

        ArrayList<Uri> list = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            list.add(Uri.parse(bannerList.get(i)));
        }


        //设置banner样式
        atSupplyProductsBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        atSupplyProductsBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        atSupplyProductsBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        atSupplyProductsBanner.setImages(list);
        //设置banner动画效果
        atSupplyProductsBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        atSupplyProductsBanner.isAutoPlay(true);
        //设置标题集合（当banner样式有显示title时）
//        atSupplyProductsBanner.setBannerTitles(list2);
        //设置轮播时间
        atSupplyProductsBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        atSupplyProductsBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置banner点击事件
        atSupplyProductsBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.e("huang", "position = " + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        atSupplyProductsBanner.start();
    }

    @OnClick({R.id.at_supply_products_btn_buy_now, R.id.at_supply_products_ll_call, R.id.at_supply_products_ll_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_supply_products_ll_call:
                break;
            case R.id.at_supply_products_ll_chat:
                break;

            case R.id.at_supply_products_btn_buy_now:
                if (type.equals(Const.SUPPLY_DETAIL)) {
                    openActivity(SupplyBillActivity.class,goodsId+"");
                } else {
                    showPublishPopwindow();
                }
                break;
        }
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
        EditText baojiaContent=contentView.findViewById(R.id.pw_baojia_et_content);
        EditText baojiaRemark=contentView.findViewById(R.id.pw_baojia_et_remark);
        Button commit=contentView.findViewById(R.id.pw_baojia_btn_commit);
        final String content=baojiaContent.getText().toString().trim();
        final String remark=baojiaRemark.getText().toString().trim();
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaoJiaBean bean=new BaoJiaBean();
                bean.setUser_id(user_id);
                bean.setToken(token);
                bean.setContent(content);
                bean.setRemark(remark);
                bean.setList_id(goodsId);
                DiscoverSubscribe.baojia(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        backgroundAlpha(1f);
                        window.dismiss();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
