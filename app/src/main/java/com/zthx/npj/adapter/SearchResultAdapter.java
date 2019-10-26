package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.SearchResponseBean;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private ArrayList<SearchResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface  ItemClickListener{
        void onItemClick(int position);
    }

    public SearchResultAdapter(Context context,ArrayList<SearchResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_search_result,viewGroup,false);
        return new SearchResultAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
       if(mItemClickListener!=null){
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int position=viewHolder.getLayoutPosition();
                   mItemClickListener.onItemClick(position);
               }
           });
       }
        if(mList.get(i).getGoods_img().split("/")[0].equals("http:")){
            Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
        }else{
            Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
        }
        viewHolder.goodsName.setText(mList.get(i).getGoods_name());
        viewHolder.memberPrice.setText("￥"+mList.get(i).getUser_price());
        viewHolder.marketPrice.setText("￥"+mList.get(i).getMarket_price());
        viewHolder.marketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.sold.setText("已售"+mList.get(i).getSold());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg;
        TextView goodsName,memberPrice,marketPrice,sold;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_searchResult_iv_goodsImg);
            goodsName=itemView.findViewById(R.id.item_searchResult_tv_goodsName);
            memberPrice=itemView.findViewById(R.id.item_searchResult_tv_MemberPrice);
            marketPrice=itemView.findViewById(R.id.item_searchResult_tv_MarketPrice);
            sold=itemView.findViewById(R.id.item_searchResult_tv_sold);
        }
    }
}
