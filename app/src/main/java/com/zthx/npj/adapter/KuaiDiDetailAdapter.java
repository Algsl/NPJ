package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.LookKDResponseBean;

import java.util.ArrayList;

public class KuaiDiDetailAdapter extends RecyclerView.Adapter<KuaiDiDetailAdapter.ViewHolder> {

    private ArrayList<LookKDResponseBean.DataBean.DataBean1> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }
    public KuaiDiDetailAdapter(Context context,ArrayList<LookKDResponseBean.DataBean.DataBean1> list){
        mList=list;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_kuaidi,viewGroup,false);
        return new KuaiDiDetailAdapter.ViewHolder(view);
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
        if(mList.size()>0){
            viewHolder.kuaidi_content.setText(mList.get(i).getContext());
            viewHolder.time.setText(mList.get(i).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView kuaidi_content;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kuaidi_content=itemView.findViewById(R.id.item_kuaidi_tv_content);
            time=itemView.findViewById(R.id.item_kuaidi_tv_time);
        }
    }
}
