package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.BaojiaUserDetailResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaojiaUserDetailAdapter extends RecyclerView.Adapter<BaojiaUserDetailAdapter.ViewHolder>{

    private ArrayList<BaojiaUserDetailResponseBean.DataBean> mList;
    private Context mContext;

    public BaojiaUserDetailAdapter(Context context,ArrayList<BaojiaUserDetailResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_baojia_detail,viewGroup,false);
        return new BaojiaUserDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(mList.size()>0){
            viewHolder.issueTime.setText("报价时间："+new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));
            viewHolder.content.setText(mList.get(i).getContent());
            viewHolder.remark.setText("备注："+mList.get(i).getRemark());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView issueTime,content,remark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            issueTime=itemView.findViewById(R.id.item_baojia_detail_tv_time);
            content=itemView.findViewById(R.id.item_baojia_detail_tv_content);
            remark=itemView.findViewById(R.id.item_baojia_detail_tv_mark);
        }
    }
}
