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
import com.zthx.npj.net.been.SupplyOrderResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SupplyOrderAdapter extends RecyclerView.Adapter<SupplyOrderAdapter.ViewHolder> {

    private ArrayList<SupplyOrderResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;


    public SupplyOrderAdapter(Context context,ArrayList<SupplyOrderResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
        void onOptionClcik(int position);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_store_goods_bill,viewGroup,false);
        return new SupplyOrderAdapter.ViewHolder(view);
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
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onOptionClcik(position);
                }
            });
        }
        if(mList.size()>0){
            Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
            viewHolder.goodsName.setText(mList.get(i).getGoods_name());
            viewHolder.goodsPrice.setText("已付款"+mList.get(i).getOrder_price());
            viewHolder.goodsNum.setText(mList.get(i).getOrder_num()+"斤");
            viewHolder.goodsOrder.setText("订单号："+mList.get(i).getOrder_sn());
            //viewHolder.goodsTime.setText("下单时间："+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date(mList.get(i).getOrder_time())));
            switch (mList.get(i).getOrder_state()+""){
                case "1":break;
                case "2":break;
                case "3":break;
                case "4":break;
                case "5":break;
                case "6":break;
                case "7":break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg;
        TextView goodsName,goodsPrice,goodsNum,goodsOrder,goodsTime,goodsState;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            goodsImg=itemView.findViewById(R.id.item_store_goods_bill_iv_pic);
            goodsName=itemView.findViewById(R.id.item_store_goods_bill_tv_title);
            goodsPrice=itemView.findViewById(R.id.item_store_goods_bill_tv_price);
            goodsNum=itemView.findViewById(R.id.item_store_goods_bill_tv_goodsNum);
            goodsOrder=itemView.findViewById(R.id.item_store_goods_bill_tv_orderSn);
            goodsState=itemView.findViewById(R.id.item_store_goods_bill_tv_state);
        }
    }
}