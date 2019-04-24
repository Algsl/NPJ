package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.List;

public class PreSellAdapter extends RecyclerView.Adapter<PreSellAdapter.ViewHolder> {
    private Context mContext;
    private List<CommentGoodsBeen> mList;
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public PreSellAdapter(Context context,List<CommentGoodsBeen> list) {
        mContext = context;
        mList = list;
    }
    @NonNull
    @Override
    public PreSellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pre_sell,viewGroup,false);
        return new PreSellAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
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
            //Glide.with(mContext).load(list.get(i).getMallPic()).into(viewHolder.mIvGoods);
            viewHolder.mIvGoods.setBackgroundResource(R.mipmap.ic_launcher);
            viewHolder.mTvTitle.setText(mList.get(i).getGoodsTitle());
            viewHolder.mTvPrice.setText("￥" +mList.get(i).getGoodsPrice());
            viewHolder.mTvYuDing.setText("预定 " +mList.get(i).getSelledNum());
            viewHolder.mTvYuShou.setText("预售" +mList.get(i).getTotalNum());
            viewHolder.mTvDaCheng.setText("98%");
            viewHolder.mPb.setProgress(98);
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
        TextView mTvPrice;
        TextView mTvYuDing;
        TextView mTvYuShou;
        TextView mTvDaCheng;
        ProgressBar mPb;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_pre_sell_pic);
            mTvTitle = itemView.findViewById(R.id.item_pre_sell_tv_title);
            mTvPrice = itemView.findViewById(R.id.item_pre_sell_tv_price);
            mTvYuDing = itemView.findViewById(R.id.item_pre_sell_tv_yuding);
            mTvYuShou = itemView.findViewById(R.id.item_pre_sell_tv_yushou);
            mTvDaCheng = itemView.findViewById(R.id.item_pre_sell_tv_dacheng);
            mPb = itemView.findViewById(R.id.at_pre_sell_pb);
        }
    }
}
