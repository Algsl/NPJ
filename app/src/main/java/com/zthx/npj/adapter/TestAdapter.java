package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.LocalStoreResponseBean;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private ArrayList<LocalStoreResponseBean.DataBean> mList;
    private Context mContext;

    public TestAdapter(Context context,ArrayList<LocalStoreResponseBean.DataBean> list) {
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item,viewGroup,false);
        return new TestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Glide.with(mContext).load(mList.get(i).getStore_img()).into(viewHolder.mIvStore);
        viewHolder.mTvStoreName.setText(mList.get(i).getStore_name());
        /*viewHolder.mTvAddress.setText(mList.get(i).getAddress2());
        double distance=(double)mList.get(i).getDistance();
        viewHolder.mTvDistance.setText(distance>1000?String.format("%.1f",distance/1000)+"km":distance+"m");
        viewHolder.mTvPopularity.setText("当前人气"+mList.get(i).getPopularity());
        viewHolder.mTvOffer.setText("葫芦币折扣"+ mList.get(i).getOffer() + "%现金");
        viewHolder.mTvRelief.setText("新会员减免"+ (int)Double.parseDouble(mList.get(i).getRelief()) +"元现金");*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateList(ArrayList<LocalStoreResponseBean.DataBean> newData){
        if(newData!=null){
            mList.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public void resetList(){
        mList=new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvStore;
        TextView mTvStoreName;
        TextView mTvAddress;
        TextView mTvDistance;
        TextView mTvPopularity;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;
        TextView mTvOffer;
        TextView mTvRelief;

        ViewHolder(View itemView) {
            super(itemView);
            mIvStore = itemView.findViewById(R.id.item_location_store_pic);
            mTvStoreName = itemView.findViewById(R.id.item_location_store_tv_title);
            mTvAddress = itemView.findViewById(R.id.item_location_store_tv_address);
            mTvDistance = itemView.findViewById(R.id.item_location_store_tv_distance);
            mTvPopularity = itemView.findViewById(R.id.item_location_store_tv_popularity);
            star1 = itemView.findViewById(R.id.item_location_store_iv_star1);
            star2 = itemView.findViewById(R.id.item_location_store_iv_star2);
            star3 = itemView.findViewById(R.id.item_location_store_iv_star3);
            star4 = itemView.findViewById(R.id.item_location_store_iv_star4);
            star5 = itemView.findViewById(R.id.item_location_store_iv_star5);
            mTvOffer = itemView.findViewById(R.id.item_location_store_tv_offer);
            mTvRelief = itemView.findViewById(R.id.item_location_store_tv_relief);
        }
    }
}
