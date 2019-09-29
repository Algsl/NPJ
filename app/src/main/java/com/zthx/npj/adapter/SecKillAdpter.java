package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SecKillTodayResponseBean;
import com.zthx.npj.view.SaleProgressView;
import com.zthx.npj.view.TimeTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecKillAdpter extends RecyclerView.Adapter<SecKillAdpter.ViewHolder> {

    private Context mContext;
    private String type;
    private ArrayList<SecKillTodayResponseBean.DataBean> mList;
    private SecKillAdpter.ItemClickListener mItemClickListener ;

    public interface ItemClickListener{
        void onItemClick(int position) ;
        void onBuyClick(int position);
    }
    public void setOnItemClickListener(SecKillAdpter.ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public SecKillAdpter(Context context, ArrayList<SecKillTodayResponseBean.DataBean> list,String type) {
        mContext = context;
        mList = list;
        this.type=type;
    }
    @NonNull
    @Override
    public SecKillAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sec_kill_adpter, viewGroup, false);
        return new SecKillAdpter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if (mItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
            viewHolder.buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onBuyClick(position);
                }
            });
        }
        Glide.with(mContext).load(mList.get(i).getGoods_img()).into(viewHolder.mIvGoods);
        viewHolder.mTvNewPrice.setText("￥"+mList.get(i).getGoods_price());
        viewHolder.mTvLeb.setText(mList.get(i).getGoods_name());
        viewHolder.mTvOldPrice.setText("￥"+mList.get(i).getMarket_price());
        viewHolder.mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.mTvTitle.setText(mList.get(i).getGoods_desc());
        viewHolder.mSpv.setTotalAndCurrentCount((int)mList.get(i).getGoods_num(),(int)mList.get(i).getSold());
        switch (type){
            case "1"://抢购结束
                viewHolder.time.setVisibility(View.VISIBLE);
                viewHolder.time.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis())));
                viewHolder.buy.setText("已结束");
                break;
            case "2"://抢购进行中
                viewHolder.time.setVisibility(View.GONE);
                viewHolder.num.setVisibility(View.GONE);
                break;
            case "3"://抢购即将开始
                viewHolder.mSpv.setVisibility(View.GONE);
                viewHolder.num.setVisibility(View.VISIBLE);
                viewHolder.num.setText("抢购份："+mList.get(i).getGoods_num()+"份");
                viewHolder.ll1.setVisibility(View.GONE);
                viewHolder.ll2.setVisibility(View.VISIBLE);
                viewHolder.buy.setVisibility(View.GONE);
                viewHolder.timewill.setVisibility(View.VISIBLE);
                long time = (mList.get(i).getBegin_time()*1000 - System.currentTimeMillis())/1000;
                long second =time%60;//计算秒
                long min=time/60%60;
                long hour=time/3600%24;
                long day=time/3600/24;
                viewHolder.timewill.setTimes(new long[]{hour, min, second,day});
                if (!viewHolder.timewill.isRun()) {
                    viewHolder.timewill.run();
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGoods;
        TextView mTvTitle,mTvLeb,mTvNewPrice,mTvOldPrice,time,num;
        TimeTextView timewill;
        SaleProgressView mSpv;
        LinearLayout ll1,ll2;
        Button buy;

        ViewHolder(View itemView) {
            super(itemView);
            mIvGoods = itemView.findViewById(R.id.item_sec_kill_iv_goods_pic);
            mTvTitle = itemView.findViewById(R.id.item_sec_kill_tv_title);
            mTvNewPrice = itemView.findViewById(R.id.item_sec_kill_tv_price);
            mTvOldPrice = itemView.findViewById(R.id.item_sec_kill_tv_old_price);
            mTvLeb = itemView.findViewById(R.id.item_sec_kill_tv_goods_leb);
            mSpv = itemView.findViewById(R.id.item_sec_kill_spv);

            num=itemView.findViewById(R.id.item_sec_kill_tv_num);
            time=itemView.findViewById(R.id.item_sec_kill_tv_time);
            timewill=itemView.findViewById(R.id.item_sec_kill_tv_timeWill);
            ll1=itemView.findViewById(R.id.item_sec_kill_ll_l1);
            ll2=itemView.findViewById(R.id.item_sec_kill_ll_l2);
            buy=itemView.findViewById(R.id.item_sec_kill_btn_buy);
        }
    }
}
