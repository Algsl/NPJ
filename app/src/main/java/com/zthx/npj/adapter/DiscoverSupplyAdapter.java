package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SupplyListResponseBean;
import com.zthx.npj.utils.ImageCircleConner;

import java.util.ArrayList;
import java.util.List;

public class DiscoverSupplyAdapter extends RecyclerView.Adapter<DiscoverSupplyAdapter.ViewHolder>{

    private ArrayList<SupplyListResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public DiscoverSupplyAdapter(Context context, ArrayList<SupplyListResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }

    public void updateData(ArrayList<SupplyListResponseBean.DataBean> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_discover_supply, viewGroup, false);
        return new DiscoverSupplyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
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
        //Glide.with(mContext).load(list.get(i).getGoods_img()).into(viewHolder.mIvPic);
        Glide.with(mContext).load(list.get(i).getGoods_img()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.mIvPic.setImageBitmap(ImageCircleConner.toRoundCorner(resource,16));
            }
        });
        viewHolder.mTvPrice.setText(list.get(i).getPrice());
        if(Integer.valueOf(list.get(i).getDistance())>1000){
            viewHolder.mTvDistance.setText((Integer.valueOf(list.get(i).getDistance())/1000)+"km");
        }else{
            viewHolder.mTvDistance.setText(list.get(i).getDistance()+"m");
        }

        viewHolder.mTvTitle.setText(list.get(i).getTitle());
        viewHolder.mTvSupplyUnit.setText("元/"+list.get(i).getGoods_unit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvTitle;
        TextView mTvPrice;
        RelativeLayout mRlSupply;
        RelativeLayout mRlNeed;
        TextView mTvSupplyUnit;
        TextView mTvSellNum;
        TextView mTvDistance;

        ViewHolder(View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.item_discover_supply_pic);
            mTvTitle = itemView.findViewById(R.id.item_discover_supply_tv_title);
            mTvPrice = itemView.findViewById(R.id.item_discover_supply_tv_price);
            mTvDistance= itemView.findViewById(R.id.item_discover_supply_tv_distance);
            mTvSupplyUnit = itemView.findViewById(R.id.item_discover_supply_tv_price_danwei);
            mRlSupply = itemView.findViewById(R.id.item_discover_supply_rl);
            mRlNeed = itemView.findViewById(R.id.item_discover_need_rl);
        }
    }
}
