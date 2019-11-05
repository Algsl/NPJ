package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zthx.npj.R;
import com.zthx.npj.net.been.AlsoLikeResponseBean;

import java.util.ArrayList;

public class AlsoLikeAdatper extends RecyclerView.Adapter<AlsoLikeAdatper.ViewHolder>{

    private ArrayList<AlsoLikeResponseBean.DataBean> mList;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position,ArrayList<AlsoLikeResponseBean.DataBean> mList) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener = itemClickListener ;

    }

    public AlsoLikeAdatper(Context context, ArrayList<AlsoLikeResponseBean.DataBean> list) {
        mList = list;
        mContext = context;
    }

    public void addData(ArrayList<AlsoLikeResponseBean.DataBean> goodsData){
        int size=goodsData.size();
        mList.addAll(goodsData);
        notifyItemChanged(size,mList.size());
    }
    public void clearData(){
        mList.clear();
    }


    @NonNull
    @Override
    public AlsoLikeAdatper.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_commen_goods, viewGroup, false);
        return new AlsoLikeAdatper.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlsoLikeAdatper.ViewHolder viewHolder, int i) {
        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position,mList);
                }
            });
        }
        if (mList!= null && mList.size() > 0) {
            Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.mIvGoods);
            viewHolder.mTvPrice.setText("￥"+mList.get(i).getMember_price());
            viewHolder.mTvSellNum.setText("已售"+mList.get(i).getSold()+"件");
            viewHolder.mTvTitle.setText(mList.get(i).getGoods_name());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView mIvGoods;
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
