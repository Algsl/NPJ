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
import com.zthx.npj.net.been.SecKillGoodsDetailResponseBean;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GoodSizePopupwindow extends PopupWindow {

    private final LabelsView labelsView;
    private final RelativeLayout minusView;
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
    private TextView toVIP;

    private boolean hasAttribute;
    private String attribute_id="";
    private double lisheng;

    //预售
    public GoodSizePopupwindow(Context mContext, View.OnClickListener itemsOnClick, final PreSellDetailResponseBean.DataBean data1) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_goods_size, null);

        addView = view.findViewById(R.id.item_pop_goods_num_add);
        minusView = view.findViewById(R.id.item_pop_goods_num_jian);
        memberPrice = view.findViewById(R.id.pop_goods_size_tv_price);
        mAddShoppingCar = view.findViewById(R.id.item_pop_goods_add_shopping_car);
        prePrice=view.findViewById(R.id.pop_goods_size_tv_old_price);
        spec=view.findViewById(R.id.pw_goodsSize_tv_spec);
        mBuyNow = view.findViewById(R.id.item_pop_goods_buy);
        labelsView = (LabelsView) view.findViewById(R.id.labels);
        cancel=view.findViewById(R.id.pp_goodsSize_iv_cancel);
        goodsRlNum=view.findViewById(R.id.pw_goodsSize_rl_buy);
        final TextView choose=view.findViewById(R.id.pop_goods_size_tv_choice);
        storeNum=view.findViewById(R.id.pop_goods_size_tv_total_num);

        memberPrice.setVisibility(View.GONE);
        mAddShoppingCar.setVisibility(View.GONE);
        prePrice.setText("￥"+data1.getGoods_price());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
//        // 设置按钮监听
        minusView.setOnClickListener(itemsOnClick);
        addView.setOnClickListener(itemsOnClick);
        mAddShoppingCar.setOnClickListener(itemsOnClick);
        mBuyNow.setOnClickListener(itemsOnClick);

        storeNum.setText("库存："+data1.getGoods_num());
        spec.setText("规格：");
        ArrayList<String> label = new ArrayList<>();
        for(int i=0;i<data1.getAttribute_value().size();i++){
            label.add(data1.getAttribute_value().get(i).getPre_number()+"份");
        }

        if(data1.getAttribute_value().size()==0 || data1.getAttribute_value()==null){
            hasAttribute=false;
        }else{
            hasAttribute=true;
        }

        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
        /*labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                if(label.isSelected()){
                    attribute_id=data1.getAttribute_value().get(position).getId()+"";
                    choose.setText("已选："+label);
                    prePrice.setText("￥"+data1.getAttribute_value().get(position).getPre_price());
                }
            }
        });*/
        if(data1.getAttribute_value().size()>0){
            attribute_id=data1.getAttribute_value().get(0).getId()+"";
            labelsView.setSelects(0);
            prePrice.setText("￥"+data1.getAttribute_value().get(0).getPre_price());
            storeNum.setText("库存："+data1.getAttribute_value().get(0).getPre_number());
        }
        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                if(isSelect){
                    attribute_id=data1.getAttribute_value().get(position).getId()+"";
                    Log.e("测试", "onLabelSelectChange: "+data.toString() );
                    choose.setText("已选："+data.toString());
                    prePrice.setText("￥"+data1.getAttribute_value().get(position).getPre_price());
                }
            }
        });
        this.setOutsideTouchable(false);
        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
    }

    //普通商品
    public GoodSizePopupwindow(Context mContext, View.OnClickListener itemsOnClick, final GoodsDetailResponseBean.DataBean data1) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_goods_size, null);

        addView = view.findViewById(R.id.item_pop_goods_num_add);
        minusView = view.findViewById(R.id.item_pop_goods_num_jian);
        memberPrice = view.findViewById(R.id.pop_goods_size_tv_price);
        mAddShoppingCar = view.findViewById(R.id.item_pop_goods_add_shopping_car);
        mBuyNow = view.findViewById(R.id.item_pop_goods_buy);
        labelsView = (LabelsView) view.findViewById(R.id.labels);
        cancel=view.findViewById(R.id.pp_goodsSize_iv_cancel);
        goodsRlNum=view.findViewById(R.id.pw_goodsSize_rl_buy);
        spec=view.findViewById(R.id.pw_goodsSize_tv_spec);
        storeNum=view.findViewById(R.id.pop_goods_size_tv_total_num);
        final TextView choose=view.findViewById(R.id.pop_goods_size_tv_choice);
        prePrice=view.findViewById(R.id.pop_goods_size_tv_old_price);
        toVIP=view.findViewById(R.id.pw_goodsSize_tv_toVIP);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        prePrice.setText("￥"+data1.getUser_price());
        memberPrice.setText("代言人价"+data1.getMember_price());
        storeNum.setText("库存："+data1.getInventory());

        goodsRlNum.setVisibility(View.VISIBLE);
//        // 设置按钮监听
        minusView.setOnClickListener(itemsOnClick);
        addView.setOnClickListener(itemsOnClick);
        mAddShoppingCar.setOnClickListener(itemsOnClick);
        mBuyNow.setOnClickListener(itemsOnClick);

        if(data1.getAttributes().size()==0 || data1.getAttributes()==null){
            hasAttribute=false;
        }else{
            hasAttribute=true;
        }
        lisheng=Double.parseDouble(data1.getUser_price())-Double.parseDouble(data1.getMember_price());
        toVIP.setText("成为农品街代言人此单立省"+new DecimalFormat("#0.00").format(lisheng)+"元");

        spec.setText(data1.getSpec());
        ArrayList<String> label = new ArrayList<>();
        for(int i=0;i<data1.getAttributes().size();i++){
            label.add(data1.getAttributes().get(i).getKey_name());
        }

        spec.setText(data1.getSpec()==null?"规格":data1.getSpec());
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。

        if(data1.getAttributes().size()>0){
            attribute_id=data1.getAttributes().get(0).getItem_id()+"";
            labelsView.setSelects(0);
            memberPrice.setText("代言人价"+data1.getAttributes().get(0).getSpec_member_price());
            prePrice.setText("￥"+data1.getAttributes().get(0).getSpec_user_price());
            storeNum.setText("库存："+data1.getAttributes().get(0).getStore_count());
            lisheng=Double.parseDouble(data1.getAttributes().get(0).getSpec_user_price())-Double.parseDouble(data1.getAttributes().get(0).getSpec_member_price());
            toVIP.setText("成为农品街代言人此单立省"+new DecimalFormat("#0.00").format(lisheng)+"元");
        }

        /*labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                if(label.isSelected()){
                    attribute_id=data.getAttributes().get(position).getItem_id()+"";
                    choose.setText("已选："+labelText);
                    memberPrice.setText("代言人价"+data.getAttributes().get(position).getSpec_member_price());
                    prePrice.setText("￥"+data.getAttributes().get(position).getSpec_user_price());
                    storeNum.setText("库存："+data.getAttributes().get(position).getStore_count());
                    lisheng=Double.parseDouble(data.getAttributes().get(position).getSpec_user_price())-Double.parseDouble(data.getAttributes().get(position).getSpec_member_price());
                    toVIP.setText("成为农品街代言人此单立省"+new DecimalFormat("#0.00").format(lisheng)+"元");
                }
            }
        });*/
        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                if(isSelect){
                    attribute_id=data1.getAttributes().get(position).getItem_id()+"";
                    choose.setText("已选："+data.toString());
                    memberPrice.setText("代言人价"+data1.getAttributes().get(position).getSpec_member_price());
                    prePrice.setText("￥"+data1.getAttributes().get(position).getSpec_user_price());
                    storeNum.setText("库存："+data1.getAttributes().get(position).getStore_count());
                    lisheng=Double.parseDouble(data1.getAttributes().get(position).getSpec_user_price())-Double.parseDouble(data1.getAttributes().get(position).getSpec_member_price());
                    toVIP.setText("成为农品街代言人此单立省"+new DecimalFormat("#0.00").format(lisheng)+"元");
                }
            }
        });

        this.setOutsideTouchable(false);
        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
    }

    //秒杀商品
    public GoodSizePopupwindow(Context mContext, View.OnClickListener itemsOnClick, SecKillGoodsDetailResponseBean.DataBean data) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_goods_size, null);

        addView = view.findViewById(R.id.item_pop_goods_num_add);
        minusView = view.findViewById(R.id.item_pop_goods_num_jian);
        memberPrice = view.findViewById(R.id.pop_goods_size_tv_price);
        mAddShoppingCar = view.findViewById(R.id.item_pop_goods_add_shopping_car);
        mBuyNow = view.findViewById(R.id.item_pop_goods_buy);
        labelsView = (LabelsView) view.findViewById(R.id.labels);
        cancel=view.findViewById(R.id.pp_goodsSize_iv_cancel);
        goodsRlNum=view.findViewById(R.id.pw_goodsSize_rl_buy);
        spec=view.findViewById(R.id.pw_goodsSize_tv_spec);
        storeNum=view.findViewById(R.id.pop_goods_size_tv_total_num);
        final TextView choose=view.findViewById(R.id.pop_goods_size_tv_choice);


        memberPrice.setVisibility(View.GONE);
        goodsRlNum.setVisibility(View.VISIBLE);
        mAddShoppingCar.setVisibility(View.GONE);

        storeNum.setText("库存："+data.getGoods_num());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

//        // 设置按钮监听
        minusView.setOnClickListener(itemsOnClick);
        addView.setOnClickListener(itemsOnClick);
        mAddShoppingCar.setOnClickListener(itemsOnClick);
        mBuyNow.setOnClickListener(itemsOnClick);


        /*spec.setText(data.getSpec());
        ArrayList<String> label = new ArrayList<>();
        for(int i=0;i<data.getAttributes().size();i++){
            label.add(data.getAttributes().get(i).getKey_name());
        }
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(View label, String labelText, int position) {
                choose.setText("已选："+labelText);
                memberPrice.setText("代言人价"+data.getAttributes().get(position).getSpec_member_price());
                secKillOldPrice.setText("￥"+data.getAttributes().get(position).getSpec_user_price());
                storeNum.setText("库存："+data.getAttributes().get(position).getStore_count());
                spec.setText(data.getSpec()==null?"规格":data.getSpec());
            }
        });*/

        this.setOutsideTouchable(false);
        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
    }

    public String getAttId(){
        return attribute_id;
    }
    public boolean getHasAttribute(){
        return hasAttribute;
    }
    public double getLiSheng(){
        return lisheng;
    }
}
