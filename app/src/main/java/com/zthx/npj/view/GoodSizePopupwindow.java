package com.zthx.npj.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.zthx.npj.R;
import com.zthx.npj.net.been.GoodsDetailResponseBean;
import com.zthx.npj.net.been.PreSellDetailResponseBean;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GoodSizePopupwindow extends PopupWindow {

    private final LabelsView labelsView;
    private final RelativeLayout minusView;
    private final TextView secKillOldPrice;
    private final TextView memberPrice;
    private final RelativeLayout addView;
    private final Button mAddShoppingCar;
    private final Button mBuyNow;
    private final RelativeLayout goodsRlNum;
    private ImageView cancel;
    private Context mContext;
    private View view;
    private TextView spec;
    private TextView storeNum;
    private TextView prePrice;

    public GoodSizePopupwindow(Context mContext, View.OnClickListener itemsOnClick, String type,PreSellDetailResponseBean.DataBean data) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_goods_size, null);

        addView = view.findViewById(R.id.item_pop_goods_num_add);
        minusView = view.findViewById(R.id.item_pop_goods_num_jian);
        secKillOldPrice = view.findViewById(R.id.pop_goods_size_tv_sec_old_price);
        memberPrice = view.findViewById(R.id.pop_goods_size_tv_price);
        mAddShoppingCar = view.findViewById(R.id.item_pop_goods_add_shopping_car);
        prePrice=view.findViewById(R.id.pop_goods_size_tv_old_price);


        mBuyNow = view.findViewById(R.id.item_pop_goods_buy);
        labelsView = (LabelsView) view.findViewById(R.id.labels);
        cancel=view.findViewById(R.id.pp_goodsSize_iv_cancel);
        goodsRlNum=view.findViewById(R.id.pw_goodsSize_rl_buy);
        final TextView choose=view.findViewById(R.id.pop_goods_size_tv_choice);
        storeNum=view.findViewById(R.id.pop_goods_size_tv_total_num);


        prePrice.setText("￥"+data.getGoods_price());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        switch (type){
            case "1":
                secKillOldPrice.setVisibility(View.VISIBLE);
                memberPrice.setVisibility(View.GONE);
                break;
            case "2":
                secKillOldPrice.setVisibility(View.GONE);
                memberPrice.setVisibility(View.GONE);
                mAddShoppingCar.setVisibility(View.GONE);
                break;
            case "3":
                goodsRlNum.setVisibility(View.VISIBLE);
                break;
        }
//        // 设置按钮监听
        minusView.setOnClickListener(itemsOnClick);
        addView.setOnClickListener(itemsOnClick);
        mAddShoppingCar.setOnClickListener(itemsOnClick);
        mBuyNow.setOnClickListener(itemsOnClick);



        ArrayList<String> label = new ArrayList<>();
        for(int i=0;i<data.getAttribute_value().size();i++){
            label.add("x"+data.getAttribute_value().get(i).getPre_number()+" ￥"+data.getAttribute_value().get(i).getPre_price());
        }
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                choose.setText("已选："+labelText);
            }
        });
        this.setOutsideTouchable(false);
        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
    }

    public GoodSizePopupwindow(Context mContext, View.OnClickListener itemsOnClick, final GoodsDetailResponseBean.DataBean data) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_goods_size, null);

        addView = view.findViewById(R.id.item_pop_goods_num_add);
        minusView = view.findViewById(R.id.item_pop_goods_num_jian);
        secKillOldPrice = view.findViewById(R.id.pop_goods_size_tv_sec_old_price);
        memberPrice = view.findViewById(R.id.pop_goods_size_tv_price);
        mAddShoppingCar = view.findViewById(R.id.item_pop_goods_add_shopping_car);
        mBuyNow = view.findViewById(R.id.item_pop_goods_buy);
        labelsView = (LabelsView) view.findViewById(R.id.labels);
        cancel=view.findViewById(R.id.pp_goodsSize_iv_cancel);
        goodsRlNum=view.findViewById(R.id.pw_goodsSize_rl_buy);
        spec=view.findViewById(R.id.pw_goodsSize_tv_spec);
        storeNum=view.findViewById(R.id.pop_goods_size_tv_total_num);
        final TextView choose=view.findViewById(R.id.pop_goods_size_tv_choice);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        goodsRlNum.setVisibility(View.VISIBLE);
//        // 设置按钮监听
        minusView.setOnClickListener(itemsOnClick);
        addView.setOnClickListener(itemsOnClick);
        mAddShoppingCar.setOnClickListener(itemsOnClick);
        mBuyNow.setOnClickListener(itemsOnClick);


        spec.setText(data.getSpec());
        ArrayList<String> label = new ArrayList<>();
        for(int i=0;i<data.getAttributes().size();i++){
            label.add(data.getAttributes().get(i).getKey_name());
        }
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                choose.setText("已选："+labelText);
                memberPrice.setText("会员价"+data.getAttributes().get(position).getSpec_member_price());
                secKillOldPrice.setText("￥"+data.getAttributes().get(position).getSpec_user_price());
                storeNum.setText("库存："+data.getAttributes().get(position).getStore_count());
                spec.setText(data.getSpec());
            }
        });

        this.setOutsideTouchable(false);
        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
    }
}
