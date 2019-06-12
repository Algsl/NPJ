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
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SpokesmanQuanLiResponsebean;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxin on 2019/6/12.
 */

public class SpokesmanQuanLiAdapter extends RecyclerView.Adapter<SpokesmanQuanLiAdapter.ViewHolder>{

    private ArrayList<SpokesmanQuanLiResponsebean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public SpokesmanQuanLiAdapter(Context context, ArrayList<SpokesmanQuanLiResponsebean.DataBean> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_spokesman_quanli, viewGroup, false);
        return new SpokesmanQuanLiAdapter.ViewHolder(view);
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
        if (list!= null && list.size() > 0) {
            Glide.with(mContext).load(list.get(i).getImg()).into(viewHolder.mIvPic);
            viewHolder.mTvContent.setText(list.get(i).getDescription());
            viewHolder.mTvTitle.setText(list.get(i).getTitle());
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MyCircleView mIvPic;
        TextView mTvTitle;
        TextView mTvContent;

        ViewHolder(View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.item_spokesman_quanli_pic);
            mTvTitle = itemView.findViewById(R.id.item_spokesman_quanli_tv_title);
            mTvContent = itemView.findViewById(R.id.item_spokesman_quanli_tv_content);
        }
    }
}
