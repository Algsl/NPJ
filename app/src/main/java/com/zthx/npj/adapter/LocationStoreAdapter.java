package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.LocalStoreResponseBean;
import com.zthx.npj.utils.ImageCircleConner;

import java.util.ArrayList;
import java.util.List;

public class LocationStoreAdapter extends RecyclerView.Adapter<LocationStoreAdapter.ViewHolder> {
    private ArrayList<LocalStoreResponseBean.DataBean> list;
    private Context mContext;

    private LocationStoreAdapter.ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public LocationStoreAdapter(Context context, ArrayList<LocalStoreResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public LocationStoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_location_store, viewGroup, false);
        return new LocationStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LocationStoreAdapter.ViewHolder viewHolder, int i) {
        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        //Glide.with(mContext).load(list.get(i).getStore_img()).into(viewHolder.mIvStore);
        Glide.with(mContext).load(list.get(i).getStore_img()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.mIvStore.setImageBitmap(ImageCircleConner.toRoundCorner(resource,16));
            }
        });
        viewHolder.mTvStoreName.setText(list.get(i).getStore_name());
        viewHolder.mTvAddress.setText(list.get(i).getAddress2());
        double distance=(double)list.get(i).getDistance();
        viewHolder.mTvDistance.setText(distance>1000?(distance/1000)+"km":distance+"m");
        viewHolder.mTvPopularity.setText("当前人气"+list.get(i).getPopularity());
        viewHolder.mTvOffer.setText("葫芦币折扣"+ list.get(i).getOffer() + "%现金");
        viewHolder.mTvRelief.setText("新会员减免"+ list.get(i).getRelief() +"元现");
        switch (list.get(i).getStar()) {
            case 1:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 2:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 3:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star3.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 4:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star3.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star4.setBackgroundResource(R.drawable.item_location_store_star);
                break;
            case 5:
                viewHolder.star1.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star2.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star3.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star4.setBackgroundResource(R.drawable.item_location_store_star);
                viewHolder.star5.setBackgroundResource(R.drawable.item_location_store_star);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvStore;
        TextView mTvStoreName;
        TextView mTvAddress;
        TextView mTvDistance;
        TextView mTvPopularity;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;
        TextView mTvOffer;
        TextView mTvRelief;

        ViewHolder(View itemView) {
            super(itemView);
            mIvStore = itemView.findViewById(R.id.item_location_store_pic);
            mTvStoreName = itemView.findViewById(R.id.item_location_store_tv_title);
            mTvAddress = itemView.findViewById(R.id.item_location_store_tv_address);
            mTvDistance = itemView.findViewById(R.id.item_location_store_tv_distance);
            mTvPopularity = itemView.findViewById(R.id.item_location_store_tv_popularity);
            star1 = itemView.findViewById(R.id.item_location_store_iv_star1);
            star2 = itemView.findViewById(R.id.item_location_store_iv_star2);
            star3 = itemView.findViewById(R.id.item_location_store_iv_star3);
            star4 = itemView.findViewById(R.id.item_location_store_iv_star4);
            star5 = itemView.findViewById(R.id.item_location_store_iv_star5);
            mTvOffer = itemView.findViewById(R.id.item_location_store_tv_offer);
            mTvRelief = itemView.findViewById(R.id.item_location_store_tv_relief);
        }
    }
}
