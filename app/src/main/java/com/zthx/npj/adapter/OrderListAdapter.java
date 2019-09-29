package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
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
        void onCancelClick(int position);
        void onDeleteClick(int position);
        void onPayClick(int position);
        void onCuiDanClick(int position);
        void onQueryClick(int position);
        void onConfirmClick(int position);
        void onAgainClick(int position);
        void onCommentClick(int position);
        void onGoodsReturn(int position);
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
            viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onCancelClick(position);
                }
            });
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onDeleteClick(position);
                }
            });
            viewHolder.cuidan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onCuiDanClick(position);
                }
            });
            viewHolder.pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onPayClick(position);
                }
            });
            viewHolder.query.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onQueryClick(position);
                }
            });
            viewHolder.confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onConfirmClick(position);
                }
            });
            viewHolder.again.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onAgainClick(position);
                }
            });
            viewHolder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onCommentClick(position);
                }
            });
            viewHolder.goodsReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onGoodsReturn(position);
                }
            });
        }
        if (list!= null && list.size() > 0) {
            Glide.with(mContext).load(Uri.parse(list.get(i).getGoods_img())).into(viewHolder.goodsImg);
            viewHolder.storeName.setText(list.get(i).getStore_name());
            viewHolder.goodsName.setText(list.get(i).getGoods_name());
            viewHolder.goodsPrice.setText("￥ "+list.get(i).getGoods_price());
            viewHolder.goodsNum.setText("x "+list.get(i).getGoods_num());
            viewHolder.orderPrice.setText("￥ "+list.get(i).getOrder_price());
            switch (list.get(i).getOrder_state()+""){
                case "0"://已取消
                    viewHolder.orderState.setText("已取消");

                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.VISIBLE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    viewHolder.again.setVisibility(View.GONE);
                    viewHolder.comment.setVisibility(View.GONE);
                    viewHolder.goodsReturn.setVisibility(View.GONE);
                    break;
                case "1"://未取消，未付款
                    viewHolder.orderState.setText("待支付");

                    viewHolder.cancel.setVisibility(View.VISIBLE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.VISIBLE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    viewHolder.again.setVisibility(View.GONE);
                    viewHolder.comment.setVisibility(View.GONE);
                    viewHolder.goodsReturn.setVisibility(View.GONE);
                    break;
                case "2"://已付款，待发货
                    viewHolder.orderState.setText("待发货");

                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.VISIBLE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    viewHolder.again.setVisibility(View.GONE);
                    viewHolder.comment.setVisibility(View.GONE);
                    if(list.get(i).getOrder_type().equals("1")){//判断是否为礼包店商品
                        viewHolder.goodsReturn.setVisibility(View.GONE);
                    }else{
                        viewHolder.goodsReturn.setVisibility(View.VISIBLE);
                    }
                    break;
                case "3"://已发货，待收货
                    viewHolder.orderState.setText("待收货");

                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.VISIBLE);
                    viewHolder.confirm.setVisibility(View.VISIBLE);
                    viewHolder.again.setVisibility(View.GONE);
                    viewHolder.comment.setVisibility(View.GONE);
                    if(list.get(i).getOrder_type().equals("1")){
                        viewHolder.goodsReturn.setVisibility(View.GONE);
                    }else{
                        viewHolder.goodsReturn.setVisibility(View.VISIBLE);
                    }
                    break;
                case "4"://已收货，待评价
                    viewHolder.orderState.setText("待评价");
                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    if(list.get(i).getOrder_type().equals("0")){
                        viewHolder.again.setVisibility(View.VISIBLE);
                    }else{
                        viewHolder.again.setVisibility(View.GONE);
                    }

                    viewHolder.comment.setVisibility(View.VISIBLE);
                    viewHolder.goodsReturn.setVisibility(View.GONE);
                    break;
                case "5":
                    viewHolder.orderState.setText("已完成");

                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    viewHolder.again.setVisibility(View.VISIBLE);
                    viewHolder.comment.setVisibility(View.GONE);
                    viewHolder.goodsReturn.setVisibility(View.GONE);
                    break;//已完成
                case "6"://申请退款
                    viewHolder.orderState.setText("退款中");

                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    viewHolder.again.setVisibility(View.GONE);
                    viewHolder.comment.setVisibility(View.GONE);
                    viewHolder.goodsReturn.setVisibility(View.GONE);
                    break;
                case "7"://已退款
                    viewHolder.orderState.setText("已退款");

                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    viewHolder.again.setVisibility(View.GONE);
                    viewHolder.comment.setVisibility(View.GONE);
                    viewHolder.goodsReturn.setVisibility(View.GONE);
                    break;
                case "8":
                    viewHolder.orderState.setText("取消退款");

                    viewHolder.cancel.setVisibility(View.GONE);
                    viewHolder.delete.setVisibility(View.GONE);
                    viewHolder.cuidan.setVisibility(View.GONE);
                    viewHolder.pay.setVisibility(View.GONE);
                    viewHolder.query.setVisibility(View.GONE);
                    viewHolder.confirm.setVisibility(View.GONE);
                    viewHolder.again.setVisibility(View.GONE);
                    viewHolder.comment.setVisibility(View.GONE);
                    viewHolder.goodsReturn.setVisibility(View.GONE);
                    break;
            }
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
