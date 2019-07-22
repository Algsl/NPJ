package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.MyCouponResponseBean;

import java.util.ArrayList;

public class MyCouponAdapter extends RecyclerView.Adapter<MyCouponAdapter.ViewHolder> {

    private ArrayList<MyCouponResponseBean.DataBean> mList;
    private Context mContext;
    private String mType;

    public MyCouponAdapter(Context context,ArrayList<MyCouponResponseBean.DataBean> list,String type){
        mContext=context;
        mList=list;
        mType=type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_coupon_list,viewGroup,false);
        return new MyCouponAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        long end_time=mList.get(i).getEnd_time();
        long now_time=System.currentTimeMillis();
        if(mType.equals("1")){
            viewHolder.rlBg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.coupon_bg_used));
            viewHolder.tvUserStatus.setText("已使用");
        }else if(mType.equals("2")){
            if(end_time-now_time<24*60*60*1000){
                viewHolder.rlBg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.coupon_bg_wguoqi));
            }else{
                viewHolder.rlBg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.coupon_bg));
            }
        }else{
            viewHolder.rlBg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.coupon_bg_guoqi));
            viewHolder.tvUserStatus.setText("已失效");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlBg;
        TextView tvUserStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlBg=itemView.findViewById(R.id.item_coupon_list_bg);
            tvUserStatus=itemView.findViewById(R.id.item_coupon_list_tv_status);
        }
    }
}
