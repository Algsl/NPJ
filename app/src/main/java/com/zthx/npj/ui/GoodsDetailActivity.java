package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.zthx.npj.R;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.GoodsDetailResponseBean;
import com.zthx.npj.net.been.PreSellDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.PreSellSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.view.GoodSizePopupwindow;
import com.zthx.npj.view.SaleDetailProgressView;
import com.zthx.npj.view.TimeTextView;

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
            atGoodsDetailRlSecKill.setVisibility(View.VISIBLE);
            long startTime = System.nanoTime();
            atGoodsDetailTtv.setTimes(new long[]{1, 23, 22});
            if (!atGoodsDetailTtv.isRun()) {
                atGoodsDetailTtv.run();
            }
            atGoodsDetailSpv.setTotalAndCurrentCount(100, 20);
        } else if (Const.PRESELL.equals(getIntent().getAction())) {
            atGoodsDetailRlSecKill.setVisibility(View.GONE);
            atGoodsDetailLlGoods.setVisibility(View.GONE);
            atGoodsDetailLlPresell.setVisibility(View.VISIBLE);
            getPreSellDetail(id);
        } else {
            getGoodsDetail(id);
        }

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
        }));
    }

    private void setPreSellData(String result) {
        PreSellDetailResponseBean preSellDetailResponseBean = GsonUtils.fromJson(result, PreSellDetailResponseBean.class);
        PreSellDetailResponseBean.DataBean data = preSellDetailResponseBean.getData();
        mPreData = data;
        atGoodsDetailTvPreSellTitle.setText(data.getGoods_name());
        atGoodsDetailTvPreSellPrice.setText("¥"+data.getGoods_price());
        atGoodsDetailTvPreSellYuding.setText(data.getUser_num());
        atGoodsDetailTvPreSellYushou.setText(data.getSale_price());
        atGoodsDetailTvPreSellDacheng.setText(data.getProportion()+ "%");
        atPreSellPb.setProgress(Integer.parseInt(data.getProportion()+""));
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

    }

    @OnClick({R.id.at_goods_detail_btn_add_shopping_cart, R.id.at_goods_detail_btn_buy_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_goods_detail_btn_add_shopping_cart:
                showPopupwindow(view);
                break;
            case R.id.at_goods_detail_btn_buy_now:
                showPopupwindow(view);
                break;
        }
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
                    intent.putExtra(Const.ATTRIBUTE_ID, mPreData.getAttribute_value().get(0).getId());
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

}
