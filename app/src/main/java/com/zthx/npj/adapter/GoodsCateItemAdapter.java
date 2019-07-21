package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public GoodsCateItemAdapter(Context context,ArrayList<GoodsCateResponseBean.DataBean.Child> list){
        mContext=context;
        mList=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_store_cartid_item,viewGroup,false);
        return new GoodsCateItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=viewHolder.getLayoutPosition();
                mItemClickListener.onItemClick(position);
            }
        });
        viewHolder.content.setText(mList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.item_storeCartid_item_tv_title);
        }
    }
}
