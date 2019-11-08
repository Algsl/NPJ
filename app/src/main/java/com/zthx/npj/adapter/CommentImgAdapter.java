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

import java.util.ArrayList;

public class CommentImgAdapter extends RecyclerView.Adapter<CommentImgAdapter.ViewHolder> {

    private ArrayList<String> imgList;
    private Context mContext;

    public CommentImgAdapter(Context context,ArrayList<String> imgs){
        mContext=context;
        imgList=imgs;
    }


    @NonNull
    @Override
    public CommentImgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_comment_img,viewGroup,false);
        return new CommentImgAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentImgAdapter.ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(Uri.parse(imgList.get(i))).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.commentImg_riv);
        }
    }
}
