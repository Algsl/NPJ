package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.LocalStoreResponseBean;

import java.util.ArrayList;

public class LocalStoreAdapter extends RecyclerView.Adapter<LocalStoreAdapter.ViewHolder> {

    private ArrayList<LocalStoreResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }

    public LocalStoreAdapter(Context context,ArrayList<LocalStoreResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_local_store,viewGroup,false);
        return new LocalStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if(mItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        if (mList.size()>0){
                Glide.with(mContext).load(Uri.parse(mList.get(i).getStore_img())).into(viewHolder.headImg);
                viewHolder.storeName.setText(mList.get(i).getStore_name());
                viewHolder.storeAddress.setText(mList.get(i).getAddress2());
                viewHolder.storeDistance.setText(mList.get(i).getDistance()+"m");
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView headImg;
        TextView storeName,storeAddress,storeDistance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headImg=itemView.findViewById(R.id.item_local_store_head_pic);
            storeName=itemView.findViewById(R.id.item_local_store_tv_name);
            storeAddress=itemView.findViewById(R.id.item_local_store_tv_daiyanren);
            storeDistance=itemView.findViewById(R.id.item_local_store_tv_distance);
        }
    }
}
