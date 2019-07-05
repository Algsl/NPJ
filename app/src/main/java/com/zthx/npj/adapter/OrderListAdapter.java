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
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.OrderResponseBean;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    private ArrayList<OrderResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public OrderListAdapter(Context context, ArrayList<OrderResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public OrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_list, viewGroup, false);
        return new OrderListAdapter.ViewHolder(view);
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
        if (list!= null && list.size() > 0) {
            Glide.with(mContext).load(Uri.parse(list.get(i).getGoods_img())).into(viewHolder.goodsImg);
            viewHolder.storeName.setText(list.get(i).getStore_name());
            viewHolder.goodsName.setText(list.get(i).getGoods_name());
            viewHolder.goodsPrice.setText(list.get(i).getGoods_price());
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg;
        TextView storeName,goodsName,goodsPrice,goodsNum,orderPrice,orderState;
        Button cancel,delete,cuidan,pay,query,confirm,again,comment,goodsReturn;
        ViewHolder(View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_orderList_iv_goodsImg);
            storeName=itemView.findViewById(R.id.item_orderList_tv_storeName);
            goodsName=itemView.findViewById(R.id.item_orderList_tv_goodsName);
            goodsPrice=itemView.findViewById(R.id.item_orderList_tv_goodsPrice);
            goodsNum=itemView.findViewById(R.id.item_orderList_tv_goodsNum);
            orderPrice=itemView.findViewById(R.id.item_orderList_tv_orderPrice);
            orderState=itemView.findViewById(R.id.item_orderList_tv_orderState);
            cancel=itemView.findViewById(R.id.item_orderList_btn_cancel);
            delete=itemView.findViewById(R.id.item_orderList_btn_delete);
            cuidan=itemView.findViewById(R.id.item_orderList_btn_cuidan);
            pay=itemView.findViewById(R.id.item_orderList_btn_pay);
            query=itemView.findViewById(R.id.item_orderList_btn_query);
            confirm=itemView.findViewById(R.id.item_orderList_btn_confirm);
            again=itemView.findViewById(R.id.item_orderList_btn_again);
            comment=itemView.findViewById(R.id.item_orderList_btn_comment);
            goodsReturn=itemView.findViewById(R.id.item_orderList_btn_goodsReturn);
        }
    }
}
