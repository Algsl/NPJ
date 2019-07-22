package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.HistoryResponseBean;
import com.zthx.npj.net.been.HotSearchResponseBean;

import java.util.ArrayList;

public class HotSearchAdapter extends RecyclerView.Adapter<HotSearchAdapter.ViewHolder> {

    private ArrayList<HotSearchResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }

    public HotSearchAdapter(Context context, ArrayList<HotSearchResponseBean.DataBean> list){
        mList=list;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_home_search,viewGroup,false);
        return new HotSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if (mItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        viewHolder.searchHistory.setText(mList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView searchHistory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchHistory=itemView.findViewById(R.id.item_homeSearch_tv_title);
        }
    }
}
