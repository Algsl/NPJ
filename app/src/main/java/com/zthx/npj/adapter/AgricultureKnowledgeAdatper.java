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
import com.zthx.npj.net.been.AgricultureKnowledgerBeen;
import com.zthx.npj.net.been.DiscoverSolutionListResponseBean;

import java.util.ArrayList;
import java.util.List;

public class AgricultureKnowledgeAdatper extends RecyclerView.Adapter<AgricultureKnowledgeAdatper.ViewHolder> {

    private ArrayList<DiscoverSolutionListResponseBean.DataBean> mList;
    private Context mContext;

    private AgricultureKnowledgeAdatper.ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(AgricultureKnowledgeAdatper.ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }
    public AgricultureKnowledgeAdatper(ArrayList<DiscoverSolutionListResponseBean.DataBean> list, Context context) {
        mContext = context;
        mList = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dicover_service_knowledge, viewGroup, false);
        return new AgricultureKnowledgeAdatper.ViewHolder(view);
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
        //Glide.with(mContext).load(mList.get(i).getImg()).into(viewHolder.mIvPic);
        switch ((int) mList.get(i).getId()){
            case 1:
                viewHolder.mIvPic.setImageResource(R.drawable.apple);
                break;
            case 2:
                viewHolder.mIvPic.setImageResource(R.drawable.xigua);
                break;
            case 4:
                viewHolder.mIvPic.setImageResource(R.drawable.lanmei);
                break;
        }
        viewHolder.mTvName.setText(mList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvName;

        ViewHolder(View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.item_iv_agriculture_knowledge);
            mTvName = itemView.findViewById(R.id.item_tv_agriculture_knowledge_name);
        }
    }
}
