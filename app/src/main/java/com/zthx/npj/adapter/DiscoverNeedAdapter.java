package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.NeedListResponseBean;
import com.zthx.npj.net.been.SupplyListResponseBean;

import java.util.ArrayList;

public class DiscoverNeedAdapter extends RecyclerView.Adapter<DiscoverNeedAdapter.ViewHolder>{

    private ArrayList<NeedListResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public DiscoverNeedAdapter(Context context, ArrayList<NeedListResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }

    public void updateData(ArrayList<NeedListResponseBean.DataBean> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_discover_need, viewGroup, false);
        return new DiscoverNeedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        Glide.with(mContext).load(list.get(i).getImg()).into(viewHolder.mIvPic);
        viewHolder.mTvNeedNum.setText(list.get(i).getAmount());
        if(list.get(i).getDistance()>1000){
            viewHolder.mTvDistance.setText((list.get(i).getDistance()/1000)+"km");
        }else{
            viewHolder.mTvDistance.setText(list.get(i).getDistance()+"m");
        }
        viewHolder.mTvTitle.setText(list.get(i).getTitle());
        if(list.get(i).getIs_top()==0){
            viewHolder.mTvIsTop.setVisibility(View.GONE);
        }else{
            viewHolder.mTvIsTop.setVisibility(View.VISIBLE);
        }
        if(list.get(i).getCert()==null){
            viewHolder.company.setVisibility(View.GONE);
            viewHolder.realName.setVisibility(View.GONE);
        }else{
            String[] strs=list.get(i).getCert().split(",");
            for(String str:strs){
                if(str.equals("1")){
                    viewHolder.realName.setVisibility(View.VISIBLE);
                }else if(str.equals("2")){
                    viewHolder.company.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvTitle;
        TextView mTvNeedNum;
        TextView mTvDistance;
        TextView mTvIsTop;
        TextView company,realName;
        ViewHolder(View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.item_discover_need_pic);
            mTvTitle = itemView.findViewById(R.id.item_discover_need_tv_title);
            mTvNeedNum = itemView.findViewById(R.id.item_discover_need_tv_num);
            mTvDistance= itemView.findViewById(R.id.item_discover_need_tv_distance);
            mTvIsTop=itemView.findViewById(R.id.item_discover_need_tv_isTop);
            company=itemView.findViewById(R.id.item_discoverNeed_tv_company);
            realName=itemView.findViewById(R.id.item_discoverNeed_tv_realName);
        }
    }
}
