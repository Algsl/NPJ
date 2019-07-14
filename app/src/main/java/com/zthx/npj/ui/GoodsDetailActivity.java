package com.zthx.npj.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.GoodsDetailResponseBean;
import com.zthx.npj.net.been.PreSellDetailResponseBean;
import com.zthx.npj.net.been.SecKillGoodsDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netsubscribe.SecKillSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.GlideImageLoader;
import com.zthx.npj.view.GoodSizePopupwindow;
import com.zthx.npj.view.SaleDetailProgressView;
import com.zthx.npj.view.TimeTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailActivity extends AppCompatActivity {

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
    @BindView(R.id.at_goods_detail_tv_goods_is_baoyou)
    TextView atGoodsDetailTvGoodsIsBaoyou;
    @BindView(R.id.at_goods_detail_ll_goods)
    LinearLayout atGoodsDetailLlGoods;
    @BindView(R.id.at_goods_detail_tv_pre_sell_title)
    TextView atGoodsDetailTvPreSellTitle;
    @BindView(R.id.at_goods_detail_tv_pre_sell_price)
    TextView atGoodsDetailTvPreSellPrice;
    @BindView(R.id.at_goods_detail_tv_pre_sell_yuding)
    TextView atGoodsDetailTvPreSellYuding;
    @BindView(R.id.at_goods_detail_tv_pre_sell_yushou)
    TextView atGoodsDetailTvPreSellYushou;
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

    private String user_id= SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private String goodsId;

    private PreSellDetailResponseBean.DataBean mPreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra(Const.GOODS_ID);
        goodsId = id;
        if ("miaosha".equals(getIntent().getAction())) {
            int status = getIntent().getIntExtra(Const.SECKILL_STATUS, 1);
            if (status == 0) {
                atGoodsDetailRlSecKill.setVisibility(View.GONE);
                atGoodsDetailRlSecKillDone.setVisibility(View.VISIBLE);
            } else if (status == 1) {
                atGoodsDetailRlSecKill.setVisibility(View.VISIBLE);
                atGoodsDetailRlSecKillDone.setVisibility(View.GONE);
            } else {
                atGoodsDetailRlSecKill.setVisibility(View.VISIBLE);
                atGoodsDetailRlSecKillDone.setVisibility(View.GONE);
            }
            atGoodsDetailLlGoods.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.GONE);

            getSecKillDetail(id);

        } else if (Const.PRESELL.equals(getIntent().getAction())) {
            atGoodsDetailRlSecKill.setVisibility(View.GONE);
            atGoodsDetailLlGoods.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.VISIBLE);
            getPreSellDetail(id);
        } else {
            getGoodsDetail(id);
        }

    }

    private void getSecKillDetail(String id) {

        SecKillSubscribe.getSecKillGoodsDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setSecKillData(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    private void setSecKillData(String result) {
        SecKillGoodsDetailResponseBean secKillGoodsDetailResponseBean = GsonUtils.fromJson(result, SecKillGoodsDetailResponseBean.class);
        SecKillGoodsDetailResponseBean.DataBean data = secKillGoodsDetailResponseBean.getData();
        atGoodsDetailTvGoodsTitle.setText(data.getGoods_name());
        atGoodsDetailTvGoodsNewPrice.setText("¥" + data.getGoods_price());
        atGoodsDetailTvGoodsOldPrice.setText("¥" + data.getMarket_price());
        atGoodsDetailTvGoodsOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        atGoodsDetailSelledNum.setText("已售" + data.getSold());
        atGoodsDetailHoldNum.setText("库存" + data.getGoods_num());
        long time = data.getEnd_time() - data.getBegin_time();
        long hour = time / (60 * 60 * 1000);
        long min = ((time / (60 * 1000)) - hour * 60);
        long second = ((time / 1000) - hour * 60 - min * 60);

        atGoodsDetailTtv.setTimes(new long[]{hour, min, second});
        if (!atGoodsDetailTtv.isRun()) {
            atGoodsDetailTtv.run();
        }
        atGoodsDetailSpv.setTotalAndCurrentCount(Integer.parseInt(data.getGoods_num()), Integer.parseInt(data.getSale_num()));
        initBanner(data.getGroup_img());
    }

    private void getPreSellDetail(String id) {
        PreSellSubscribe.getPreSellDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {

                setPreSellData(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    private void setPreSellData(String result) {
        PreSellDetailResponseBean preSellDetailResponseBean = GsonUtils.fromJson(result, PreSellDetailResponseBean.class);
        PreSellDetailResponseBean.DataBean data = preSellDetailResponseBean.getData();
        mPreData = data;
        atGoodsDetailTvPreSellTitle.setText(data.getGoods_name());
        atGoodsDetailTvPreSellPrice.setText("¥" + data.getGoods_price());
        atGoodsDetailTvPreSellYuding.setText(data.getUser_num());
        atGoodsDetailTvPreSellYushou.setText(data.getSale_price());
        atGoodsDetailTvPreSellDacheng.setText(data.getProportion() + "%");
        atPreSellPb.setProgress(Integer.parseInt(data.getProportion() + ""));
        initBanner(data.getGroup_img());
    }

    private void getGoodsDetail(String id) {
        MainSubscribe.getGoodsDetail(id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setData(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }

    private void setData(String result) {

        GoodsDetailResponseBean goodsDetailResponseBean = GsonUtils.fromJson(result, GoodsDetailResponseBean.class);
        GoodsDetailResponseBean.DataBean data = goodsDetailResponseBean.getData();
        atGoodsDetailTvGoodsNewPrice.setText("¥" + data.getMember_price());
        atGoodsDetailTvGoodsOldPrice.setText(data.getMarket_price());
        atGoodsDetailTvGoodsTitle.setText(data.getGoods_name());
        atGoodsDetailSelledNum.setText(data.getSold() + "");
        atGoodsDetailHoldNum.setText(data.getInventory() + "");
        String str;
        if (data.getYunfei() != 0) {
            str = "免运费";
        } else {
            str = data.getYunfei() + "";
        }
        atGoodsDetailTvGoodsIsBaoyou.setText(str);
        initBanner(data.getGoods_img());

    }

    @OnClick({R.id.at_goods_detail_btn_add_shopping_cart, R.id.at_goods_detail_btn_buy_now, R.id.ac_goodsDetail_ll_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_goods_detail_btn_add_shopping_cart:
                showPopupwindow(view);
                break;
            case R.id.at_goods_detail_btn_buy_now:
                showPopupwindow(view);
                break;
            case R.id.ac_goodsDetail_ll_collect:
                goodsCollect();
                break;
        }
    }

    private void goodsCollect() {
        SetSubscribe.addCollection(user_id,token,goodsId,"1",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(GoodsDetailActivity.this,"收藏成功",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.item_pop_goods_num_add:
//                    int count = Integer.valueOf((String)addCartNumTv.getText());
//                    if(count==1){
//                        Toast.makeText(godd.this,"不能再减了哦",Toast.LENGTH_SHORT).show();
//                    }else{
//                        count--;
//                        addCartNumTv.setText((count)+"");
//                    }
                    break;
                case R.id.item_pop_goods_num_jian:
//                    int count2 = Integer.valueOf((String)addCartNumTv.getText());
//                    count2++;
//                    addCartNumTv.setText(count2+"");
                    break;
                case R.id.item_pop_goods_add_shopping_car:

                    break;
                case R.id.item_pop_goods_buy:
                    Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra(Const.ATTRIBUTE_ID, mPreData.getAttribute_value().get(0).getId() + "");
                    intent.putExtra(Const.GOODS_ID, goodsId);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void showPopupwindow(View view) {
        boolean b;
        if ("miaosha".equals(getIntent().getAction())) {
            b = true;
        } else {
            b = false;
        }
        GoodSizePopupwindow sizePopWin = new GoodSizePopupwindow(this, onClickListener, b, mPreData.getAttribute_value());
        View contentView = sizePopWin.getContentView();
//        addCartNumTv = ((TextView) contentView.findViewById(R.id.goodsRule_numTv));
        //设置Popupwindow显示位置（从底部弹出）
        sizePopWin.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //当弹出Popupwindow时，背景变半透明
        backgroundAlpha(0.4f);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
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

}
