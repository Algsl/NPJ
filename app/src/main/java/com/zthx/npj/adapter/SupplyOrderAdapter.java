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
import com.zthx.npj.net.been.SupplyOrderResponseBean;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.view.TimeTextView;

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
        void onSendClick(int position);
        void onRefundClick(int position);
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
            viewHolder.sendGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSendClick(position);
                }
            });
            viewHolder.refund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onRefundClick(position);
                }
            });
        }
        if(mList.size()>0){
            String url = mList.get(i).getGoods_img();
            if (url.substring(url.length() - 4).equals(".mp4")) {
                viewHolder.goodsImg.setImageBitmap(MyCustomUtils.getVideoThumbnail(url));
                viewHolder.ivVideo.setVisibility(View.VISIBLE);
            } else {
                Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
            }
            Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
            viewHolder.goodsName.setText(mList.get(i).getTitle());
            viewHolder.goodsPrice.setText("已付款 ￥"+mList.get(i).getOrder_price());
            viewHolder.goodsNum.setText(mList.get(i).getOrder_num()+"斤");
            viewHolder.goodsOrder.setText("订  单  号："+mList.get(i).getOrder_sn());
            viewHolder.goodsTime.setText("下单时间："+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date(mList.get(i).getOrder_time()*1000)));
            switch (mList.get(i).getOrder_state()+""){
                case "1"://未付款
                    break;
                case "2"://待发货
                    viewHolder.goodsState.setText("待发货");

                    viewHolder.sendGoods.setVisibility(View.VISIBLE);
                    viewHolder.refund.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
                case "3"://待收货
                    viewHolder.goodsState.setText("已发货");

                    viewHolder.sendGoods.setVisibility(View.GONE);
                    viewHolder.refund.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
                case "4"://待评价
                    viewHolder.goodsState.setText("已发货");
                    viewHolder.sendGoods.setVisibility(View.GONE);
                    viewHolder.refund.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
                case "5"://已完成
                    viewHolder.goodsState.setText("已发货");
                    viewHolder.sendGoods.setVisibility(View.GONE);
                    viewHolder.refund.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
                case "6"://申请退款
                    viewHolder.goodsState.setText("申请退款");

                    viewHolder.sendGoods.setVisibility(View.GONE);
                    viewHolder.refund.setVisibility(View.VISIBLE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.refund);
                    viewHolder.residueLlTime.setVisibility(View.VISIBLE);
                    long time=mList.get(i).getUpdate_time()+3*24*60*60-System.currentTimeMillis()/1000;
                    if(time<=0){
                        //mItemClickListener.onRefund(list.get(i).getId()+"");
                        viewHolder.residueLlTime.setVisibility(View.GONE);
                    }else{
                        setTime(viewHolder.refund_time,time);
                    }
                    break;
                case "7"://已退款
                    viewHolder.goodsState.setText("已退款");
                    viewHolder.sendGoods.setVisibility(View.GONE);
                    viewHolder.refund.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.fahuo);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;

                case "8":
                    viewHolder.goodsState.setText("已取消退款");
                    viewHolder.sendGoods.setVisibility(View.GONE);
                    viewHolder.refund.setVisibility(View.GONE);
                    viewHolder.goodsIvState.setImageResource(R.drawable.refuse_refund);
                    viewHolder.residueLlTime.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg,goodsIvState,ivVideo;
        TextView goodsName,goodsPrice,goodsNum,goodsOrder,goodsTime,goodsState,sendGoods,refund,residueTvTime;
        LinearLayout residueLlTime;
        TimeTextView refund_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            goodsImg=itemView.findViewById(R.id.item_store_goods_bill_iv_pic);
            goodsName=itemView.findViewById(R.id.item_store_goods_bill_tv_title);
            goodsPrice=itemView.findViewById(R.id.item_store_goods_bill_tv_price);
            goodsNum=itemView.findViewById(R.id.item_store_goods_bill_tv_goodsNum);
            goodsOrder=itemView.findViewById(R.id.item_store_goods_bill_tv_orderSn);
            goodsState=itemView.findViewById(R.id.item_store_goods_bill_tv_state);
            goodsIvState=itemView.findViewById(R.id.item_storeGoodsBill_iv_state);
            sendGoods=itemView.findViewById(R.id.item_store_goods_bill_tv_send);
            refund=itemView.findViewById(R.id.item_store_goods_bill_tv_drawaback);
            goodsTime=itemView.findViewById(R.id.item_store_goods_bill_tv_orderTime);
            residueLlTime=itemView.findViewById(R.id.item_storeGoodsBill_ll_time);
            residueTvTime=itemView.findViewById(R.id.item_storeGoodsBill_tv_time);
            ivVideo=itemView.findViewById(R.id.item_storeQuotation_iv_video);

            refund_time=itemView.findViewById(R.id.item_storeGoodsBill_tv_time);
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
}
