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

import java.util.List;

public class AKAdapter extends RecyclerView.Adapter<AKAdapter.ViewHolder> {

    private List<CommentGoodsBeen> mList;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;
    }
    public AKAdapter(Context context, List<CommentGoodsBeen> list) {
        mList = list;
        mContext = context;
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
        if (mList!= null && mList.size() > 0) {
            viewHolder.mIvGoods.setBackgroundResource(R.mipmap.ic_launcher);
            viewHolder.mTvPrice.setText(mList.get(i).getGoodsPrice());
            viewHolder.mTvSellNum.setText(mList.get(i).getGoodsSellNum());
            viewHolder.mTvTitle.setText(mList.get(i).getGoodsTitle());
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods;
        TextView mTvTitle;
        TextView mTvPrice;
        TextView mTvSellNum;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_iv_comment_goods);
            mTvTitle = itemView.findViewById(R.id.item_tv_comment_goods_title);
            mTvPrice = itemView.findViewById(R.id.item_tv_comment_goods_price);
            mTvSellNum = itemView.findViewById(R.id.item_tv_comment_goods_sell_num);
        }
    }
}
