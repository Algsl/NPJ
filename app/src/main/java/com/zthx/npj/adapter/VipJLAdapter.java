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
import com.zthx.npj.net.been.VipJLResponseBean;

import java.util.ArrayList;

public class VipJLAdapter extends RecyclerView.Adapter<VipJLAdapter.ViewHolder> {

    private ArrayList<VipJLResponseBean.DataBean> mList;
    private Context mContext;

    public VipJLAdapter(Context context,ArrayList<VipJLResponseBean.DataBean> list){
        mList=list;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_my_wallet_detail,viewGroup,false);
        return new VipJLAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvTime.setText("");
        viewHolder.tvPrice.setText("+");
        viewHolder.tvTime.setText("");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHead;
        TextView tvTitle,tvPrice,tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHead=itemView.findViewById(R.id.item_myWallet_iv_type);
            tvTitle=itemView.findViewById(R.id.item_myWallet_tv_type);
            tvPrice=itemView.findViewById(R.id.item_myWallet_tv_money);
            tvTime=itemView.findViewById(R.id.item_myWallet_tv_time);
        }
    }
}
