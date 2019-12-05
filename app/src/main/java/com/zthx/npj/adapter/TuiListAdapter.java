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
import com.zthx.npj.net.been.TuiListResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TuiListAdapter extends RecyclerView.Adapter<TuiListAdapter.ViewHolder> {

    private ArrayList<TuiListResponseBean.DataBean> mList;
    private Context mContext;
    private Double allMoney=0.0;

    public TuiListAdapter(Context context,ArrayList<TuiListResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_tui_list,viewGroup,false);
        return new TuiListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.price.setText(mList.get(i).getMoney());
        viewHolder.createTime.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));
        switch ((int) mList.get(i).getStatus()){
            case 0:
                viewHolder.status.setText("审核中");
                allMoney+=Double.parseDouble(mList.get(i).getMoney());
                break;
            case 1:
                viewHolder.status.setText("申请成功");
                break;
            case 2:
                viewHolder.status.setText("申请拒绝:"+mList.get(i).getRemark());
                break;
        }
    }

    public Double getAllMoney(){
        return allMoney;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price,createTime,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            price=itemView.findViewById(R.id.item_tuiList_tv_price);
            createTime=itemView.findViewById(R.id.item_tuiList_tv_createTime);
            status=itemView.findViewById(R.id.item_tuiList_tv_status);
        }
    }
}
