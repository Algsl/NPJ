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

public class CommenGoodsAdatper extends RecyclerView.Adapter<CommenGoodsAdatper.ViewHolder>{

    private List<CommentGoodsBeen> list;
    private Context mContext;

    private HomeGoodsAdapter.ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(HomeGoodsAdapter.ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public CommenGoodsAdatper(Context context, List<CommentGoodsBeen> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public CommenGoodsAdatper.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_commen_goods, viewGroup, false);
        return new CommenGoodsAdatper.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommenGoodsAdatper.ViewHolder viewHolder, int i) {
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
