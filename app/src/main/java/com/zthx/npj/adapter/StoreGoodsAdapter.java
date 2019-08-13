package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyGoodsBean;
import com.zthx.npj.net.been.MyGoodsResponseBean;
import com.zthx.npj.utils.ImageCircleConner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreGoodsAdapter extends RecyclerView.Adapter<StoreGoodsAdapter.ViewHolder> {

    private ArrayList<MyGoodsResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClick(int position);
        void onShoppingCartClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }


    public StoreGoodsAdapter(Context context,ArrayList<MyGoodsResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_classfiy_detail_goods,viewGroup,false);
        return new StoreGoodsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.goodsOldPrice.setVisibility(View.GONE);
        viewHolder.goodsSale.setVisibility(View.GONE);
        viewHolder.shoppingCart.setVisibility(View.VISIBLE);

        if(mItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onItemClick(position);
                }
            });
            viewHolder.shoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onShoppingCartClick(position);
                }
            });
        }

        Glide.with(mContext).load(mList.get(i).getGoods_img()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.goodsImg.setImageBitmap(ImageCircleConner.toRoundCorner(resource,16));
            }
        });
        viewHolder.goodsName.setText(mList.get(i).getGoods_name());
        viewHolder.goodsPrice.setText("￥"+mList.get(i).getMarket_price());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg,shoppingCart;
        TextView goodsName,goodsPrice,goodsOldPrice;
        LinearLayout goodsSale;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_iv_classfiy_detail_goods);
            shoppingCart=itemView.findViewById(R.id.item_detailGoods_iv_shoppingCart);
            goodsName=itemView.findViewById(R.id.item_tv_classfiy_detail_goods_title);
            goodsPrice=itemView.findViewById(R.id.item_tv_classfiy_detail_goods_price);
            goodsOldPrice=itemView.findViewById(R.id.item_tv_classfiy_detail_old_price);
            goodsSale=itemView.findViewById(R.id.item_classfiy_ll);
        }
    }
}