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
import com.zthx.npj.net.been.GoodsCateResponseBean;

import java.util.ArrayList;

public class GoodsCateItemAdapter extends RecyclerView.Adapter<GoodsCateItemAdapter.ViewHolder> {

    private ArrayList<GoodsCateResponseBean.DataBean.Child> mList;
    private Context mContext;
    private String group_id;
    private ItemClickListener mItemClickListener;


    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onResult(String goodsId,String id,String name);
    }

    public GoodsCateItemAdapter(Context context,ArrayList<GoodsCateResponseBean.DataBean.Child> list,String group_id){
        mContext=context;
        mList=list;
        this.group_id=group_id;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.activity_goodscate_item,viewGroup,false);
        return new GoodsCateItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.itemText.setText(mList.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=viewHolder.getLayoutPosition();
                String id=mList.get(position).getId()+"";
                String name=mList.get(position).getName();
                mItemClickListener.onResult(group_id,id,name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText=itemView.findViewById(R.id.ac_goodsCate_tv_item);
        }
    }
}
