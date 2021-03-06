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
import com.zthx.npj.net.been.CategoryResponseBean;

import java.util.ArrayList;

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder> {

    private ArrayList<CategoryResponseBean.DataBean.Child> mList;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public ClassifyAdapter(Context context, ArrayList<CategoryResponseBean.DataBean.Child> list){
        mList=list;
        mContext=context;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_classfiy_home_category,viewGroup,false);
        return new ClassifyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if(itemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    itemClickListener.onItemClick(position);
                }
            });
        }
        if(mList.size()>0){
            if(mList.get(i).getImage().split("/")[0].equals("http:")){
                Glide.with(mContext).load(Uri.parse(mList.get(i).getImage())).into(viewHolder.iv_icon);
            }else{
                Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getImage())).into(viewHolder.iv_icon);
            }
            viewHolder.tv_name.setText(mList.get(i).getName());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView iv_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.item_home_name);
            iv_icon = itemView.findViewById(R.id.item_album);
        }
    }
}
