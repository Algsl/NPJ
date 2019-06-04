package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.HomeGoodsBeen;
import com.zthx.npj.net.been.RecommendResponseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页商品列表适配器
 */
public class HomeGoodsAdapter extends RecyclerView.Adapter<HomeGoodsAdapter.ViewHolder> {
    private ArrayList<RecommendResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public HomeGoodsAdapter(Context context, ArrayList<RecommendResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public HomeGoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_goods, viewGroup, false);
        return new HomeGoodsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeGoodsAdapter.ViewHolder viewHolder, int i) {
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
        Glide.with(mContext).load(list.get(i).getGoods_img()).into(viewHolder.mIvGoods);
        Glide.with(mContext).load(list.get(i).getStore_img()).into(viewHolder.mIvMall);
        viewHolder.mTvMallName.setText(list.get(i).getStore_name());
        viewHolder.mTvNewPrice.setText(list.get(i).getMember_price());
        viewHolder.mTvOldPrice.setText(list.get(i).getMarket_price());
        viewHolder.mTvOldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        viewHolder.mTvTitle.setText(list.get(i).getGoods_name());
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
