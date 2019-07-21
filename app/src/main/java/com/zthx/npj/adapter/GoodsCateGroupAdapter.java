package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.GoodsCateResponseBean;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

public class GoodsCateGroupAdapter extends RecyclerView.Adapter<GoodsCateGroupAdapter.ViewHolder> {

    private ArrayList<GoodsCateResponseBean.DataBean> mList;
    private Context mContext;


    public GoodsCateGroupAdapter(Context context,ArrayList<GoodsCateResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_store_cartid_group,viewGroup,false);
        return new GoodsCateGroupAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        GridLayoutManager layoutManager=new GridLayoutManager(mContext,3, LinearLayoutManager.VERTICAL,false);
        viewHolder.goodsCateItem.setLayoutManager(layoutManager);
        GoodsCateItemAdapter adapter=new GoodsCateItemAdapter(mContext,mList.get(i).getChild());
        adapter.setOnItemClickListener(new GoodsCateItemAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String cate_id=mList.get(i).getChild().get(position).getId()+"";
                String cateName=mList.get(i).getChild().get(position).getName();
                SharePerferenceUtils.setCateId(cate_id);
                SharePerferenceUtils.setCateName(cateName);
            }
        });
        viewHolder.goodsCateItem.setAdapter(adapter);
        viewHolder.title.setText(mList.get(i).getName());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        RecyclerView goodsCateItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_storeCartid_group_tv_title);
            goodsCateItem=itemView.findViewById(R.id.item_storeCartid_group_gv);
        }

    }
}
