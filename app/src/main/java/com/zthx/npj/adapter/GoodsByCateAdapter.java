package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
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
import com.zthx.npj.net.been.GoodsByCateResponseBean;
import com.zthx.npj.net.been.StoreGoodsListResponseBean;

import java.util.ArrayList;

public class GoodsByCateAdapter extends RecyclerView.Adapter<GoodsByCateAdapter.ViewHolder> {

    private ArrayList<GoodsByCateResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;
    private long mLevel;

    public interface ItemClickListener{
        void onItemClick(int position);
        void onShoppingCartClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }


    public GoodsByCateAdapter(Context context, ArrayList<GoodsByCateResponseBean.DataBean> list, long level){
        mContext=context;
        mList=list;
        mLevel=level;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_classfiy_detail_goods,viewGroup,false);
        return new GoodsByCateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.goodsSale.setVisibility(View.GONE);
        viewHolder.shoppingCart.setVisibility(View.GONE);

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

        Glide.with(mContext).load(mList.get(i).getGoods_img()).into(viewHolder.goodsImg);
        viewHolder.goodsName.setText(mList.get(i).getGoods_name());
        viewHolder.goodsPrice.setText("￥"+mList.get(i).getUser_price());
        viewHolder.goodsOldPrice.setText("￥"+mList.get(i).getMarket_price());
        viewHolder.goodsOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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
