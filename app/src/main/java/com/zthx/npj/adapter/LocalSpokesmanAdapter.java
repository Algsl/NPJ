package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.LocalSpokesmanResponseBean;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.view.MyCircleView;

import java.util.List;

/**
 * Created by huangxin on 2019/5/21.
 */

public class LocalSpokesmanAdapter extends RecyclerView.Adapter<LocalSpokesmanAdapter.ViewHolder>{

    private List<LocalSpokesmanResponseBean.LocalSpokesmanDetail> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
        void onChooseClick(int position);
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public LocalSpokesmanAdapter(Context context, List<LocalSpokesmanResponseBean.LocalSpokesmanDetail> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public LocalSpokesmanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_local_spokesman, viewGroup, false);
        return new LocalSpokesmanAdapter.ViewHolder(view);
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
            viewHolder.choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = viewHolder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onChooseClick(position);
                }
            });
        }
        LocalSpokesmanResponseBean.LocalSpokesmanDetail localSpokesmanDetail = list.get(i);
        Glide.with(mContext).load(localSpokesmanDetail.getHead_img()).into(viewHolder.mIvHead);
        viewHolder.mTvname.setText(localSpokesmanDetail.getNick_name());
        MyCustomUtils.showLevelImg(list.get(i).getLevel(),viewHolder.mTvLevel);
        if (localSpokesmanDetail.getDistance() <1000) {
            viewHolder.mTvDistance.setText(localSpokesmanDetail.getDistance() + "米");
        } else {
            long round = Math.round((localSpokesmanDetail.getDistance() / 100d) / 10d);
            viewHolder.mTvDistance.setText(round + "km");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        MyCircleView mIvHead;
        TextView mTvname;
        ImageView mTvLevel;
        TextView mTvDistance;
        Button choose;

        ViewHolder(View itemView) {
            super(itemView);
            mIvHead = itemView.findViewById(R.id.item_local_spokesman_head_pic);
            mTvname = itemView.findViewById(R.id.item_local_spokesman_tv_name);
            mTvLevel = itemView.findViewById(R.id.item_local_spokesman_tv_daiyanren);
            mTvDistance = itemView.findViewById(R.id.item_local_spokesman_tv_distance);
            choose = itemView.findViewById(R.id.item_localSpokesman_btn_choose);
        }
    }
}
