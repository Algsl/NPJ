package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SystemMessageBean;
import com.zthx.npj.net.been.SystemMessageResponseBean;
import com.zthx.npj.utils.ImageCircleConner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemMessageAdapter extends RecyclerView.Adapter<SystemMessageAdapter.ViewHolder> {

    private ArrayList<SystemMessageResponseBean.DataBean> mList;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public SystemMessageAdapter(Context context, ArrayList<SystemMessageResponseBean.DataBean> list) {
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_system_message, viewGroup, false);
        return new SystemMessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
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
        Glide.with(mContext).load(Uri.parse(mList.get(i).getMsgImg())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.msgIv.setImageBitmap(ImageCircleConner.toRoundCorner(resource,16));
            }
        });
        viewHolder.title.setText(mList.get(i).getMsgTitle());
        viewHolder.content.setText(mList.get(i).getMsgContent());
        viewHolder.msgFrom.setText(mList.get(i).getMsgFrom());
        viewHolder.nowTime.setText(new SimpleDateFormat("yy/MM/dd").format(new Date(mList.get(i).getMsgTime())));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView msgIv;
        TextView title,content,nowTime,msgFrom;

        ViewHolder(View itemView) {
            super(itemView);
            msgIv=itemView.findViewById(R.id.item_systemMsg_iv_msg);
            title=itemView.findViewById(R.id.item_systemMsg_tv_title);
            content=itemView.findViewById(R.id.item_systemMsg_tv_content);
            nowTime=itemView.findViewById(R.id.item_systemMsg_tv_time);
            msgFrom=itemView.findViewById(R.id.item_systemMsg_tv_from);
        }
    }
}
