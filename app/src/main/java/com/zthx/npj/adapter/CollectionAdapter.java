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
import com.zthx.npj.net.been.CollectionResponseBean;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private ArrayList<CollectionResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;
    private String mType;

    public CollectionAdapter(Context context,ArrayList<CollectionResponseBean.DataBean> list,String type){
        mContext=context;
        mList=list;
        mType=type;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
        void onItemAddCart(int position);
        void onItemDelete(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_my_collect_goods,viewGroup,false);
        return new CollectionAdapter.ViewHolder(view);
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
            viewHolder.addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onItemAddCart(position);
                }
            });
            viewHolder.delCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onItemDelete(position);
                }
            });
        }
        if(mList.size()>0){
            if(mType.equals("3")){
                Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
                viewHolder.goodsPrice.setText("￥ "+mList.get(i).getGoods_price());
            }else{
                Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
                viewHolder.goodsPrice.setText("￥ "+mList.get(i).getUser_price());
            }
            viewHolder.goodsName.setText(mList.get(i).getGoods_name());

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg,addCart,delCollection;
        TextView goodsName,goodsPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_my_collect_goods_pic);
            addCart=itemView.findViewById(R.id.item_my_collect_iv_addCart);
            delCollection=itemView.findViewById(R.id.item_my_collect_iv_delCollection);
            goodsName=itemView.findViewById(R.id.item_my_collect_goods_tv_title);
            goodsPrice=itemView.findViewById(R.id.item_my_collect_goods_tv_price);
        }
    }
}
