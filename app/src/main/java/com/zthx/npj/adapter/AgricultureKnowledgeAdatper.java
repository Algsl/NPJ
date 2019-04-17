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
import com.zthx.npj.net.been.AgricultureKnowledgerBeen;
import com.zthx.npj.net.been.HomeGoodsBeen;

import java.util.List;

public class AgricultureKnowledgeAdatper extends RecyclerView.Adapter<AgricultureKnowledgeAdatper.ViewHolder> {

    private List<AgricultureKnowledgerBeen> mList;
    private Context mContext;

    public AgricultureKnowledgeAdatper(List list, Context context) {
        mContext = context;
        mList = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dicover_service_knowledge, viewGroup, false);
        return new AgricultureKnowledgeAdatper.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
