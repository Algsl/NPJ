package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.BaojiaListResponseBean;
import com.zthx.npj.net.been.SupplyOrderResponseBean;

import java.util.ArrayList;

public class BaojiaListAdapter extends RecyclerView.Adapter<BaojiaListAdapter.ViewHolder> {

    private ArrayList<BaojiaListResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;


    public BaojiaListAdapter(Context context, ArrayList<BaojiaListResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onSeeClick(int position);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_store_quotation,viewGroup,false);
        return new BaojiaListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if(mItemClickListener!=null){
            viewHolder.see.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSeeClick(position);
                }
            });
        }
        Glide.with(mContext).load(Uri.parse(mList.get(i).getImg())).into(viewHolder.goodsImg);
        viewHolder.title.setText(mList.get(i).getTitle());
        viewHolder.number.setText(mList.get(i).getBaojia_num()+"人报价");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg;
        TextView title,number;
        Button see;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_store_quotation_pic);
            title=itemView.findViewById(R.id.item_store_quotation_title);
            number=itemView.findViewById(R.id.item_store_quotation_tv_number);
            see=itemView.findViewById(R.id.item_store_quotation_btn_see);
        }
    }
}
