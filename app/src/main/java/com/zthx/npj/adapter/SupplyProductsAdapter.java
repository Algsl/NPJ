package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.NeedDetailResponseBean;
import com.zthx.npj.net.been.SupplyDetailResponseBean;

import java.util.ArrayList;

public class SupplyProductsAdapter extends RecyclerView.Adapter<SupplyProductsAdapter.ViewHolder> {

    private ArrayList<String> mList;
    private Context mContext;

    public SupplyProductsAdapter(Context context,ArrayList<String> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_supply_products,viewGroup,false);
        return new SupplyProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(mList.size()>0){
            Glide.with(mContext).load(Uri.parse(mList.get(i))).into(viewHolder.imgContent);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContent=itemView.findViewById(R.id.item_supplyProducts_iv_content);
        }
    }
}
