package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.view.SaleProgressView;

import java.util.List;

public class SecKillAdpter extends RecyclerView.Adapter<SecKillAdpter.ViewHolder> {

    private Context mContext;
    private List<CommentGoodsBeen> mList;
    private SecKillAdpter.ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(SecKillAdpter.ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public SecKillAdpter(Context context, List<CommentGoodsBeen> list) {
        mContext = context;
        mList = list;
    }
    @NonNull
    @Override
    public SecKillAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sec_kill_adpter, viewGroup, false);
        return new SecKillAdpter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
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
        if (mList!= null && mList.size() > 0) {
            //Glide.with(mContext).load(list.get(i).getMallPic()).into(viewHolder.mIvGoods);
            viewHolder.mIvGoods.setBackgroundResource(R.mipmap.ic_launcher);
            viewHolder.mTvNewPrice.setText(mList.get(i).getGoodsPrice());
            viewHolder.mTvLeb.setText(mList.get(i).getGoodsLeb());
            viewHolder.mTvOldPrice.setText(mList.get(i).getGoodsOldPrice());
            viewHolder.mTvTitle.setText(mList.get(i).getGoodsTitle());
            viewHolder.mSpv.setTotalAndCurrentCount(100,20);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods;
        TextView mTvTitle;
        TextView mTvLeb;
        TextView mTvNewPrice;
        TextView mTvOldPrice;
        SaleProgressView mSpv;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_sec_kill_iv_goods_pic);
            mTvTitle = itemView.findViewById(R.id.item_sec_kill_tv_title);
            mTvNewPrice = itemView.findViewById(R.id.item_sec_kill_tv_price);
            mTvOldPrice = itemView.findViewById(R.id.item_sec_kill_tv_old_price);
            mTvLeb = itemView.findViewById(R.id.item_sec_kill_tv_goods_leb);
            mSpv = itemView.findViewById(R.id.item_sec_kill_spv);
        }
    }
}
