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
import com.zthx.npj.net.been.HomeGoodsBeen;

import java.util.List;

public class StoreBillMessageAdapter  extends RecyclerView.Adapter<StoreBillMessageAdapter.ViewHolder> {
    private List<CommentGoodsBeen> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public StoreBillMessageAdapter(Context context, List<CommentGoodsBeen> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public StoreBillMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_stotr_bill, viewGroup, false);
        return new StoreBillMessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StoreBillMessageAdapter.ViewHolder viewHolder, int i) {
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
        if (list!= null && list.size() > 0) {
            //Glide.with(mContext).load(list.get(i).getMallPic()).into(viewHolder.mIvGoods);
        } else {
            viewHolder.mIvGoods.setBackgroundResource(R.mipmap.ic_launcher);
            viewHolder.mIvMall.setBackgroundResource(R.mipmap.ic_launcher);
            viewHolder.mTvOldPrice.setText(list.get(i).getGoodsOldPrice());
            viewHolder.mTvTitle.setText(list.get(i).getGoodsTitle());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods;
        TextView mTvTitle;
        TextView mTvNewPrice;
        TextView mTvOldPrice;
        ImageView mIvMall;
        TextView mTvMallName;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_iv_home_goods);
            mTvTitle = itemView.findViewById(R.id.item_tv_home_goods_title);
            mTvNewPrice = itemView.findViewById(R.id.item_tv_home_goods_new_price);
            mTvOldPrice = itemView.findViewById(R.id.item_tv_home_goods_old_price);
            mIvMall = itemView.findViewById(R.id.item_iv_home_goods_mall);
            mTvMallName = itemView.findViewById(R.id.item_tv_home_goods_mall_name);
        }
    }
}
