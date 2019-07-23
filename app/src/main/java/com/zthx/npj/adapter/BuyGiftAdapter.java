package com.zthx.npj.adapter;

import android.content.Context;
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
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.GiftListResponseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxin on 2019/5/22.
 */

public class BuyGiftAdapter extends RecyclerView.Adapter<BuyGiftAdapter.ViewHolder>{

    private ArrayList<GiftListResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;
    }

    public BuyGiftAdapter(Context context, ArrayList<GiftListResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_buy_gift, viewGroup, false);
        return new BuyGiftAdapter.ViewHolder(view);
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
        Glide.with(mContext).load(Uri.parse(list.get(i).getImg())).into(viewHolder.mIvGoods);
        viewHolder.mTvPrice.setText(list.get(i).getPrice());
        viewHolder.mTvDes.setText(list.get(i).getDescription());
        viewHolder.mTvTitle.setText(list.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods;
        TextView mTvTitle;
        TextView mTvPrice;
        TextView mTvDes;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_buy_gift_pic);
            mTvTitle = itemView.findViewById(R.id.item_buy_gift_tv_title);
            mTvDes = itemView.findViewById(R.id.item_buy_gift_tv_des);
            mTvPrice = itemView.findViewById(R.id.item_buy_gift_tv_price);
        }
    }
}
