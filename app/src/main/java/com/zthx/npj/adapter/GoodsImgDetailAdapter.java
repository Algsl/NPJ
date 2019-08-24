package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;

import java.util.ArrayList;

public class GoodsImgDetailAdapter extends RecyclerView.Adapter<GoodsImgDetailAdapter.ViewHolder> {

    private ArrayList<String> mList;
    private Context mContext;

    public GoodsImgDetailAdapter(Context context,ArrayList<String> list){
        mList=list;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_goods_img_detail,viewGroup,false);
        return new GoodsImgDetailAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(Uri.parse(mList.get(i))).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.img.setImageBitmap(resource);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.item_goodsImgDetail_iv_img);
        }
    }
}
