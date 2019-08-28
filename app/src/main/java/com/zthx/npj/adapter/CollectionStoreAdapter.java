package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CollectionStoreResponseBean;
import com.zthx.npj.utils.ImageCircleConner;

import java.util.ArrayList;

public class CollectionStoreAdapter extends RecyclerView.Adapter<CollectionStoreAdapter.ViewHolder> {

    private ArrayList<CollectionStoreResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public CollectionStoreAdapter(Context context,ArrayList<CollectionStoreResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_my_collect_store,viewGroup,false);
        return new CollectionStoreAdapter.ViewHolder(view);
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
            viewHolder.delCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onDeleteClick(position);
                }
            });
        }
        Glide.with(mContext).load(Uri.parse(mList.get(i).getStore_img())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.storeImg.setImageBitmap(ImageCircleConner.toRoundCorner(resource,16));
            }
        });
        viewHolder.storeName.setText(mList.get(i).getStore_name());
        viewHolder.goodsCount.setText(mList.get(i).getCount()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView storeImg;
        TextView storeName,goodsCount,delCollection;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeImg=itemView.findViewById(R.id.item_my_collect_store_pic);
            storeName=itemView.findViewById(R.id.item_my_collect_store_name);
            goodsCount=itemView.findViewById(R.id.item_myCollect_tv_goodsCount);
            delCollection=itemView.findViewById(R.id.item_myCollect_tv_delCollection);
        }
    }
}
