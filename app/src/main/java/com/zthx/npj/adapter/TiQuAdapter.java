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
import com.zthx.npj.net.been.TiQuResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TiQuAdapter extends RecyclerView.Adapter<TiQuAdapter.ViewHolder> {

    private ArrayList<TiQuResponseBean.DataBean> mList;
    private Context mContext;

    public TiQuAdapter(Context context,ArrayList<TiQuResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_my_wallet_detail,viewGroup,false);
        return new TiQuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvTitle.setText(mList.get(i).getTitle());
        viewHolder.tvPrice.setText("-"+mList.get(i).getPrice());
        viewHolder.ivHead.setImageResource(R.drawable.ytqje);
        viewHolder.tvTime.setText(new SimpleDateFormat("MM月dd日 hh:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));
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
