package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MySupplyListResponseBean;
import com.zthx.npj.net.been.PurchaseListResponseBean;
import com.zthx.npj.utils.ImageCircleConner;

import java.util.ArrayList;

public class PurchaseListAdapter extends RecyclerView.Adapter<PurchaseListAdapter.ViewHolder> {

    private ArrayList<PurchaseListResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;
    private String type;

    public PurchaseListAdapter(Context context, ArrayList<PurchaseListResponseBean.DataBean> list, String type){
        mContext=context;
        mList=list;
        this.type=type;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
        void onSaleClick(int position);
        void onSupplyEditClick(int position);
        void onSupplyDeleteClick(int position);
        void onSupplyShareClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_want_buy_list,viewGroup,false);
        return new PurchaseListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if(mItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onItemClick(position);
                }
            });
            viewHolder.onSale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSaleClick(position);
                }
            });
            viewHolder.editGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSupplyEditClick(position);
                }
            });
            viewHolder.deleteGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSupplyDeleteClick(position);
                }
            });
            viewHolder.shareGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSupplyShareClick(position);
                }
            });
        }
        Glide.with(mContext).load(Uri.parse(mList.get(i).getImg())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.goodsImg.setImageBitmap(ImageCircleConner.toRoundCorner(resource,16));
            }
        });
        viewHolder.goodsName.setText(mList.get(i).getTitle());
        viewHolder.amount.setText(mList.get(i).getAmount()+mList.get(i).getUnit());
        if(type.equals("1")){
            viewHolder.onSale.setText("下架");
        }else{
            viewHolder.onSale.setText("上架");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg;
        TextView goodsName,amount,onSale,shareGoods,deleteGoods,editGoods;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_want_buy_manager_list_pic);
            goodsName=itemView.findViewById(R.id.item_want_buy_manager_list_title);
            amount=itemView.findViewById(R.id.item_wantBuy_tv_amount);

            onSale=itemView.findViewById(R.id.item_wantBuy_btn_sale);
            shareGoods=itemView.findViewById(R.id.item_wantBuy_btn_share);
            deleteGoods=itemView.findViewById(R.id.item_wantBuy_btn_delete);
            editGoods=itemView.findViewById(R.id.item_wantBuy_btn_edit);
        }
    }
}
