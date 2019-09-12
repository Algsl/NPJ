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
import com.zthx.npj.net.been.UserMoneyResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserMoneyAdapter extends RecyclerView.Adapter<UserMoneyAdapter.ViewHolder> {

    private ArrayList<UserMoneyResponseBean.DataBean.MingXi> mList;
    private Context mContext;

    public UserMoneyAdapter(Context context,ArrayList<UserMoneyResponseBean.DataBean.MingXi> list){
        mContext=context;
        mList=list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_my_wallet_detail,viewGroup,false);
        return new UserMoneyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (mList.get(i).getType()+""){
            case "1"://充值
                viewHolder.ivType.setImageResource(R.drawable.qbmx_cz);
                viewHolder.title.setText(mList.get(i).getTitle());
                viewHolder.tvMoney.setText("+ "+mList.get(i).getPrice());
                break;
            case "2"://提现
                viewHolder.ivType.setImageResource(R.drawable.qbmx_tx);
                viewHolder.title.setText(mList.get(i).getTitle());
                viewHolder.tvMoney.setText("- "+mList.get(i).getPrice());
                break;
        }
        viewHolder.tvTime.setText(new SimpleDateFormat("MM月dd日 hh:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivType;
        TextView title,tvTime,tvMoney;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivType=itemView.findViewById(R.id.item_myWallet_iv_type);
            title=itemView.findViewById(R.id.item_myWallet_tv_type);
            tvTime=itemView.findViewById(R.id.item_myWallet_tv_time);
            tvMoney=itemView.findViewById(R.id.item_myWallet_tv_money);
        }
    }
}
