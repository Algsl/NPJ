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
import com.zthx.npj.net.been.AkListResponseBean;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

public class AKAdapter extends RecyclerView.Adapter<AKAdapter.ViewHolder> {

    private ArrayList<AkListResponseBean.DataBean> mList;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;
    }
    public AKAdapter(Context context, ArrayList<AkListResponseBean.DataBean> list) {
        mList = list;
        mContext = context;
    }

    public void setNewData(ArrayList<AkListResponseBean.DataBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AKAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ak_adapter, viewGroup, false);
        return new AKAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AKAdapter.ViewHolder viewHolder, int i) {
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
        Glide.with(mContext).load(mList.get(i).getImg()).into(viewHolder.mIvGoods);
        viewHolder.mTvUpdateTime.setText(mList.get(i).getUpdate_time());
        viewHolder.mTvSellNum.setText("购买次数 "+mList.get(i).getSale_num());
        viewHolder.mTvTitle.setText(mList.get(i).getTitle());
        viewHolder.mTvLookNum.setText("已观看 "+mList.get(i).getLook_num());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods;
        TextView mTvTitle;
        TextView mTvUpdateTime;
        TextView mTvSellNum;
        TextView mTvLookNum;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_ak_iv_img);
            mTvTitle = itemView.findViewById(R.id.item_ak_tv_title);
            mTvUpdateTime = itemView.findViewById(R.id.item_ak_tv_update_time);
            mTvSellNum = itemView.findViewById(R.id.item_ak_tv_sale_num);
            mTvLookNum = itemView.findViewById(R.id.item_ak_tv_look_num);
        }
    }
}
