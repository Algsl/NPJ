package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.GoodsListResponseBean;

import java.util.ArrayList;

public class ClassifyDetailAdapter extends RecyclerView.Adapter<ClassifyDetailAdapter.ViewHolder> {

    private ArrayList<GoodsListResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;


    public ClassifyDetailAdapter(Context context,ArrayList<GoodsListResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position,ArrayList<GoodsListResponseBean.DataBean> mList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_classfiy_detail_goods, viewGroup, false);
        return new ClassifyDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if (mItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position,mList);
                }
            });
        }
        if (mList!= null && mList.size() > 0) {
            Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.mIvGoods);
            viewHolder.mTvPrice.setText("￥"+mList.get(i).getUser_price());
            viewHolder.mTvSellNum.setText("已售"+mList.get(i).getSold()+"件");
            viewHolder.mTvTitle.setText(mList.get(i).getGoods_name());
            viewHolder.mTvOldPrice.setText("￥"+mList.get(i).getMarket_price());
            viewHolder.mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    public void addData(ArrayList<GoodsListResponseBean.DataBean> goodsData){
        int size=goodsData.size();
        mList.addAll(goodsData);
        notifyItemChanged(size,mList.size());
    }
    public void clearData(){
        mList.clear();
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
        TextView mTvOldPrice;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_iv_classfiy_detail_goods);
            mTvTitle = itemView.findViewById(R.id.item_tv_classfiy_detail_goods_title);
            mTvPrice = itemView.findViewById(R.id.item_tv_classfiy_detail_goods_price);
            mTvSellNum = itemView.findViewById(R.id.item_tv_classfiy_detail_goods_selled);
            mTvOldPrice = itemView.findViewById(R.id.item_tv_classfiy_detail_old_price);
        }
    }
}
