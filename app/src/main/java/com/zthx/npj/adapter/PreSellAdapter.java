package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.PreSellResponseBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PreSellAdapter extends RecyclerView.Adapter<PreSellAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<PreSellResponseBean.DataBean> mList;
    private String mType;
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
        void onPreClick(int position);
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;
    }

    public PreSellAdapter(Context context,ArrayList<PreSellResponseBean.DataBean> list,String type) {
        mContext = context;
        mList = list;
        mType=type;
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
            viewHolder.itemPre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onPreClick(position);
                }
            });
        }
        if(!mType.equals("0")){
            viewHolder.itemPre.setVisibility(View.GONE);
            viewHolder.itemOver.setVisibility(View.VISIBLE);
        }else{
            viewHolder.itemPre.setVisibility(View.VISIBLE);
            viewHolder.itemOver.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(mList.get(i).getGoods_img()).into(viewHolder.mIvGoods);
        viewHolder.mTvTitle.setText(mList.get(i).getGoods_name());
        viewHolder.mTvPrice.setText("￥" +mList.get(i).getGoods_price());
        viewHolder.mTvYuDing.setText("预定 " +mList.get(i).getUser_num());
        viewHolder.mTvYuShou.setText("众筹" +new DecimalFormat("0.0").format(Double.parseDouble(mList.get(i).getGoods_price())*Double.parseDouble(mList.get(i).getSale_num())));
        viewHolder.mTvDaCheng.setText(new DecimalFormat("0.0").format((Double.parseDouble(mList.get(i).getSale_num())* 100)/Double.parseDouble(mList.get(i).getGoods_num())) +"%");
        viewHolder.mPb.setProgress((Integer.parseInt(mList.get(i).getSale_num())* 100)/Integer.parseInt(mList.get(i).getGoods_num()));
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
        Button itemPre,itemOver;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_pre_sell_pic);
            mTvTitle = itemView.findViewById(R.id.item_pre_sell_tv_title);
            mTvPrice = itemView.findViewById(R.id.item_pre_sell_tv_price);
            mTvYuDing = itemView.findViewById(R.id.item_pre_sell_tv_yuding);
            mTvYuShou = itemView.findViewById(R.id.item_pre_sell_tv_yushou);
            mTvDaCheng = itemView.findViewById(R.id.item_pre_sell_tv_dacheng);
            mPb = itemView.findViewById(R.id.at_pre_sell_pb);
            itemPre=itemView.findViewById(R.id.item_presell_btn_pre);
            itemOver=itemView.findViewById(R.id.item_presell_btn_over);
        }
    }
}
