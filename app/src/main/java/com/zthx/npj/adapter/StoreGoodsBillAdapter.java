package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.MyOrderListResponseBean;
import com.zthx.npj.view.TimeTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by huangxin on 2019/5/15.
 */

public class StoreGoodsBillAdapter extends RecyclerView.Adapter<StoreGoodsBillAdapter.ViewHolder>{

    private ArrayList<MyOrderListResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
        void onSendClick(int position);
        void onDrawBackClick(int position);
        //void onRefund(String id);
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public StoreGoodsBillAdapter(Context context, ArrayList<MyOrderListResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_store_goods_bill, viewGroup, false);
        return new StoreGoodsBillAdapter.ViewHolder(view);
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
            viewHolder.goodsSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSendClick(position);
                }
            });
            viewHolder.goodsdrawback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onDrawBackClick(position);
                }
            });
        }
        if (list!= null && list.size() > 0) {
            if(list.get(i).getGoods_img().split("/")[0].equals("http:")){
                Glide.with(mContext).load(Uri.parse(list.get(i).getGoods_img())).into(viewHolder.goodsImg);
            }else{
                Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+list.get(i).getGoods_img())).into(viewHolder.goodsImg);
            }
            viewHolder.goodsName.setText(list.get(i).getGoods_name());
            viewHolder.goodsPrice.setText("￥"+list.get(i).getGoods_price());
            viewHolder.goodsNum.setText("x "+list.get(i).getGoods_num()+"");
            viewHolder.orderSn.setText("订单号："+list.get(i).getOrder_sn());
            viewHolder.orderTime.setText("下单时间："+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date(list.get(i).getOrder_time()*1000)));
            switch (list.get(i).getOrder_state()){
                case "0":
                    viewHolder.orderState.setText("已取消");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    break;
                case "1":
                    viewHolder.orderState.setText("未付款");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    break;
                case "2":
                    viewHolder.orderState.setText("待发货");

                    viewHolder.goodsSend.setVisibility(View.VISIBLE);
                    viewHolder.goodsdrawback.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
                case "3":
                    viewHolder.orderState.setText("已发货");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    viewHolder.goodsdrawback.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.VISIBLE);

                    viewHolder.goodsBillHint.setText("确认收货剩余时间：");
                    long time=list.get(i).getOrder_time()+14*24*60*60-System.currentTimeMillis()/1000;
                    if(time<=0){
                        //mItemClickListener.onRefund(list.get(i).getId()+"");
                        viewHolder.orderState.setText("已收货");
                        viewHolder.residueLlTime.setVisibility(View.GONE);
                    }else{
                        setTime(viewHolder.refundTvTime,time);
                    }
                    break;
                    /*viewHolder.orderState.setText("已发货");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    viewHolder.goodsdrawback.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;*/
                case "4":
                    /*viewHolder.orderState.setText("已发货");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    viewHolder.goodsdrawback.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;*/
                case "5":
                    viewHolder.orderState.setText("已完成");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    viewHolder.goodsdrawback.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
                case "6":viewHolder.orderState.setText("申请退款");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    viewHolder.goodsdrawback.setVisibility(View.VISIBLE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.refund);
                    viewHolder.residueLlTime.setVisibility(View.VISIBLE);

                    viewHolder.goodsBillHint.setText("自动退款剩余时间：");
                    long time1=list.get(i).getRefund_time()+3*24*60*60-System.currentTimeMillis()/1000;
                    if(time1<=0){
                        //mItemClickListener.onRefund(list.get(i).getId()+"");
                        viewHolder.residueLlTime.setVisibility(View.GONE);
                    }else{
                        setTime(viewHolder.refundTvTime,time1);
                    }
                    break;
                case "7":viewHolder.orderState.setText("已退款");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    viewHolder.goodsdrawback.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
                case "8":viewHolder.orderState.setText("已取消退款");
                    viewHolder.goodsSend.setVisibility(View.GONE);
                    viewHolder.goodsdrawback.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.refuse_refund);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
            }
        }
    }

    public void setTime(TimeTextView ttv, long time) {
        long second = time % 60;//计算秒
        long min = time / 60 % 60;
        long hour = time / 3600 % 24;
        long day = time / 3600 / 24;
        ttv.setTimes(new long[]{hour, min, second, day});
        if (!ttv.isRun()) {
            ttv.run();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg,goodsIvState;
        TextView goodsName;
        TextView goodsPrice;
        TextView goodsNum;
        TextView orderSn;
        TextView orderTime;
        TextView orderState;
        TextView goodsSend;
        TextView goodsdrawback;
        TextView goodsBillHint;
        LinearLayout residueLlTime;
        TimeTextView refundTvTime;
        LinearLayout itemLl;

        ViewHolder(View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_store_goods_bill_iv_pic);
            goodsName=itemView.findViewById(R.id.item_store_goods_bill_tv_title);
            goodsPrice=itemView.findViewById(R.id.item_store_goods_bill_tv_price);
            goodsNum=itemView.findViewById(R.id.item_store_goods_bill_tv_goodsNum);
            orderSn=itemView.findViewById(R.id.item_store_goods_bill_tv_orderSn);
            orderTime=itemView.findViewById(R.id.item_store_goods_bill_tv_orderTime);
            orderState=itemView.findViewById(R.id.item_store_goods_bill_tv_state);
            goodsSend=itemView.findViewById(R.id.item_store_goods_bill_tv_send);
            goodsdrawback=itemView.findViewById(R.id.item_store_goods_bill_tv_drawaback);
            residueLlTime=itemView.findViewById(R.id.item_storeGoodsBill_ll_time);

            goodsIvState=itemView.findViewById(R.id.item_storeGoodsBill_iv_state);
            refundTvTime=itemView.findViewById(R.id.item_storeGoodsBill_tv_time);
            goodsBillHint=itemView.findViewById(R.id.item_storeGoodsBill_tv_hint);
            itemLl=itemView.findViewById(R.id.item_store_goods_bill_ll);
        }
    }
}
