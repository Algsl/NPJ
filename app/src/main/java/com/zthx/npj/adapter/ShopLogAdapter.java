package com.zthx.npj.adapter;

import android.content.Context;
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
import com.zthx.npj.net.been.ShopLogResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShopLogAdapter extends RecyclerView.Adapter<ShopLogAdapter.ViewHolder> {

    private ArrayList<ShopLogResponseBean.DataBean.MingXi> mList;
    private Context mContext;

    public ShopLogAdapter(Context context,ArrayList<ShopLogResponseBean.DataBean.MingXi> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_my_wallet_detail,viewGroup,false);
        return new ShopLogAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        /*viewHolder.tvTime.setText("");
        viewHolder.tvPrice.setText("+");
        viewHolder.tvTime.setText("");*/
        switch (mList.get(i).getType()+"") {
            case "1"://充值
                viewHolder.ivHead.setImageResource(R.drawable.qbmx_cz);
                viewHolder.tvPrice.setText("+"+mList.get(i).getPrice());
                break;
            case "2":
                viewHolder.ivHead.setImageResource(R.drawable.qbmx_tx);
                viewHolder.tvPrice.setText("-"+mList.get(i).getPrice());
                break;
        }
        viewHolder.tvTime.setText(new SimpleDateFormat("MM月dd日 hh:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));
        viewHolder.tvTitle.setText(mList.get(i).getTitle());
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
