package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zthx.npj.R;
import com.zthx.npj.adapter.CommentAdapter;
import com.zthx.npj.adapter.GoodsImgDetailAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AddCartBean;
import com.zthx.npj.net.been.CommentResponseBean;
import com.zthx.npj.net.been.GoodsDetailResponseBean;
import com.zthx.npj.net.been.GoodsImgDetailResponseBean;
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
import com.zthx.npj.utils.SimpleUtil;
import com.zthx.npj.view.GlideImageLoader;
import com.zthx.npj.view.GoodSizePopupwindow;
import com.zthx.npj.view.SaleDetailProgressView;
import com.zthx.npj.view.TimeTextView;

import java.util.ArrayList;

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
    @BindView(R.id.ac_goodsDetail_sv)
    ScrollView acGoodsDetailSv;
    @BindView(R.id.ac_goodsDetail_iv_collect)
    ImageView acGoodsDetailIvCollect;


    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String goodsId;
    TextView tvCartNum;
    private String type = "1";

    private PreSellDetailResponseBean.DataBean mPreData;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        api.registerApp("wx76500efa65d19915");

        String id = getIntent().getStringExtra(Const.GOODS_ID);
        goodsId = id;

        acGoodsDetailIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setGoodsDetail();

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
            type = "4";
            atGoodsDetailLlGoods.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.GONE);
            getSecKillDetail(id);
        } else if (Const.PRESELL.equals(getIntent().getAction())) {
            type = "3";
            atGoodsDetailRlSecKill.setVisibility(View.GONE);
            atGoodsDetailLlGoods.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.VISIBLE);
            atGoodsDetailBtnAddShoppingCart.setVisibility(View.GONE);
            acGoodsDetailLlStore.setVisibility(View.GONE);
            getPreSellDetail(id);
        } else {
            type = "1";
            atGoodsDetailRlSecKill.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.GONE);
            getGoodsDetail(id);
        }

    }

    private void setGoodsDetail() {
        GoodsImgDetailResponseBean bean = new GoodsImgDetailResponseBean();
        ArrayList<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("2");
        lists.add("3");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acGoodsDetailRvContent.setLayoutManager(layoutManager);
        GoodsImgDetailAdapter adapter = new GoodsImgDetailAdapter(this, lists);
        acGoodsDetailRvContent.setAdapter(adapter);
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

        if (data.isCollect()) {
            acGoodsDetailIvCollect.setImageResource(R.drawable.collected);
        }else{
            acGoodsDetailIvCollect.setImageResource(R.drawable.goods_detail_collect);
        }

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
        Log.e("测试", "setPreSellData: " + mPreData.getAttribute_value().size());
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
        atGoodsDetailTvGoodsOldPrice.setText(data.getMember_price());
        atGoodsDetailTvGoodsTitle.setText(data.getGoods_name());
        atGoodsDetailSelledNum.setText("已售" + data.getSold() + "");
        atGoodsDetailHoldNum.setText("库存" + data.getInventory() + "");
        String str;
        if (data.getYunfei() == 0) {
            str = "免运费";
        } else {
            str = data.getYunfei() + "元";
        }
        atGoodsDetailTvGoodsIsBaoyou.setText("快递 " + str);
        initBanner(data.getGoods_img());
    }

    @OnClick({R.id.at_goods_detail_btn_add_shopping_cart, R.id.at_goods_detail_btn_buy_now, R.id.ac_goodsDetail_ll_collect,
            R.id.ac_goodsDetail_ll_store, R.id.at_goods_detail_btn_pre_sell_know, R.id.at_goods_detail_btn_pre_sell_comment,
            R.id.at_goods_detail_btn_pre_sell_detail, R.id.ac_goodsDetail_chooseSize, R.id.ac_goodsDetail_iv_share})
    public void onViewClicked(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        acGoodsDetailRvContent.setLayoutManager(layoutManager);
        switch (view.getId()) {
            case R.id.at_goods_detail_btn_add_shopping_cart:
                showPopupwindow(view);
                break;
            case R.id.at_goods_detail_btn_buy_now:
                showPopupwindow(view);
                break;
            case R.id.ac_goodsDetail_ll_collect:
                acGoodsDetailIvCollect.setImageResource(R.drawable.collected);
                goodsCollect();
                break;
            case R.id.at_goods_detail_btn_pre_sell_detail:
                atGoodsDetailBtnPreSellDetail.setBackgroundColor(getResources().getColor(R.color.app_theme));
                atGoodsDetailBtnPreSellDetail.setTextColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellComment.setBackgroundColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellComment.setTextColor(getResources().getColor(R.color.text3));
                setGoodsDetail();
                break;
            case R.id.at_goods_detail_btn_pre_sell_comment:
                atGoodsDetailBtnPreSellDetail.setBackgroundColor(getResources().getColor(R.color.white));
                atGoodsDetailBtnPreSellDetail.setTextColor(getResources().getColor(R.color.text3));
                atGoodsDetailBtnPreSellComment.setBackgroundColor(getResources().getColor(R.color.app_theme));
                atGoodsDetailBtnPreSellComment.setTextColor(getResources().getColor(R.color.white));
                getComments();
                break;
            case R.id.ac_goodsDetail_chooseSize:
                showPopupwindow(view);
                break;
            case R.id.ac_goodsDetail_iv_share:
                Bitmap bmp = SimpleUtil.shotScrollView(acGoodsDetailSv);
                showSingleBottomDialog(bmp);
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

            }
        }));
    }

    private void setComment(String result) {
        String test = "{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 50,\n" +
                "            \"user_id\": 25,\n" +
                "            \"goods_id\": 1,\n" +
                "            \"store_id\": 23,\n" +
                "            \"content\": \"商品质量非常好，期待下次合作\",\n" +
                "            \"img\": [\n" +
                "                \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\",\n" +
                "                \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\"\n" +
                "            ],\n" +
                "            \"status\": 0,\n" +
                "            \"create_time\": 1556095903,\n" +
                "            \"type\": 1,\n" +
                "            \"goods_star\": 5,\n" +
                "            \"logistics_star\": 5,\n" +
                "            \"service_star\": 5,\n" +
                "            \"nick_name\": \"用户15853102073\",\n" +
                "            \"head_img\": \"http://img.xingkongwl.cn/20190304/201903041832091984.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"msg\": \"加载成功\"\n" +
                "}";
        CommentResponseBean bean = GsonUtils.fromJson(test, CommentResponseBean.class);
        ArrayList<CommentResponseBean.DataBean> data = bean.getData();
        CommentAdapter adapter = new CommentAdapter(this, data);
        acGoodsDetailRvContent.setAdapter(adapter);
    }

    private void goodsCollect() {
        SetSubscribe.addCollection(user_id, token, goodsId, "1", new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(GoodsDetailActivity.this, "收藏成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    //响应弹出框点击事件
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_pop_goods_num_add:
                    int count = Integer.valueOf((String) tvCartNum.getText());
                    if (count == 1) {
                        Toast.makeText(GoodsDetailActivity.this, "不能再减了哦", Toast.LENGTH_SHORT).show();
                    } else {
                        count--;
                        tvCartNum.setText((count) + "");
                    }
                    break;
                case R.id.item_pop_goods_num_jian://数量减
                    int count2 = Integer.valueOf((String) tvCartNum.getText());
                    count2++;
                    tvCartNum.setText(count2 + "");
                    break;
                case R.id.item_pop_goods_add_shopping_car:
                    AddCartBean bean = new AddCartBean();
                    bean.setUser_id(user_id);
                    bean.setToken(token);
                    bean.setGoods_id(goodsId);
                    bean.setGoods_num("1");
                    SetSubscribe.addCart(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(GoodsDetailActivity.this, "加入购物车成功", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFault(String errorMsg) {

                        }
                    }));
                    break;
                case R.id.item_pop_goods_buy:
                    Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                    if ("miaosha".equals(getIntent().getAction())) {
                        intent.putExtra(Const.ATTRIBUTE_ID, mPreData.getAttribute_value().get(0).getId() + "");
                    } else if (Const.PRESELL.equals(getIntent().getAction())) {
                        intent.putExtra(Const.ATTRIBUTE_ID, mPreData.getAttribute_value().get(0).getId() + "");
                    } else {
                        intent.putExtra(Const.ATTRIBUTE_ID, "1");
                    }
                    intent.putExtra(Const.GOODS_ID, goodsId);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void showPopupwindow(View view) {
        String type = "1";
        GoodSizePopupwindow sizePopWin;
        if ("miaosha".equals(getIntent().getAction())) {
            type = "1";
            sizePopWin = new GoodSizePopupwindow(this, onClickListener, type, mPreData.getAttribute_value());
        } else if ("presell".equals(getIntent().getAction())) {
            type = "2";
            sizePopWin = new GoodSizePopupwindow(this, onClickListener, type, mPreData.getAttribute_value());
        } else {
            type = "3";
            sizePopWin = new GoodSizePopupwindow(this, onClickListener, type, null);
        }
        View contentView = sizePopWin.getContentView();
//        addCartNumTv = ((TextView) contentView.findViewById(R.id.goodsRule_numTv));
        //设置Popupwindow显示位置（从底部弹出）
        sizePopWin.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //当弹出Popupwindow时，背景变半透明
        backgroundAlpha(0.4f);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        tvCartNum = contentView.findViewById(R.id.item_pop_goods_tv_num);

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
                dialog.dismiss();
            }
        });
    }
}
