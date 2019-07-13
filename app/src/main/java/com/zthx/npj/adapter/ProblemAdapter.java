package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.ProblemResponseBean;

import java.util.ArrayList;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder> {

    private ArrayList<ProblemResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }


    public ProblemAdapter(Context context,ArrayList<ProblemResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_help,viewGroup,false);
        return new ProblemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if(mItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onItemClickListener(i);
                }
            });
        }
        if(mList.size()>0){
            Glide.with(mContext).load(Uri.parse(mList.get(i).getImg())).into(viewHolder.problemIv);
            viewHolder.problemTitle.setText(mList.get(i).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView problemIv;
        TextView problemTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            problemIv=itemView.findViewById(R.id.item_help_iv_problemImg);
            problemTitle=itemView.findViewById(R.id.item_help_tv_problemTitle);
        }
    }
}
