package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CartOrderResponseBean;

import java.util.ArrayList;

public class CartGoodsAdapter extends RecyclerView.Adapter<CartGoodsAdapter.ViewHolder> {

    private ArrayList<CartOrderResponseBean.DataBean.List> mList;
    private Context mContext;

    public CartGoodsAdapter(Context context,ArrayList<CartOrderResponseBean.DataBean.List> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_cart_goods,viewGroup,false);
        return new CartGoodsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(mList.get(i).getGoods_img().split("/")[0].equals("http:")){
            Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
        }else{
            Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
        }
        if(mList.get(i).getItem_id().equals("0")){
            viewHolder.goodsSize.setVisibility(View.GONE);
        }else{
            viewHolder.goodsSize.setText("规格："+mList.get(i).getKey_name());
        }
        viewHolder.goodsName.setText(mList.get(i).getGoods_name());
        viewHolder.goodsPrice.setText("￥"+mList.get(i).getPrice());
        viewHolder.goodsNum.setText("x"+mList.get(i).getGoods_num());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg;
        TextView goodsName,goodsSize,goodsPrice,goodsNum,zitiHint;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_cartGoods_iv_goodsImg);
            goodsName=itemView.findViewById(R.id.item_cartGoods_tv_goodsName);
            goodsPrice=itemView.findViewById(R.id.item_cartGoods_tv_goodsPrice);
            goodsNum=itemView.findViewById(R.id.item_cartGoods_tv_goods_num);
            zitiHint=itemView.findViewById(R.id.item_cartStore_tv_zitiHint);
            goodsSize=itemView.findViewById(R.id.item_cartStore_tv_goodsSize);
        }
    }
}
