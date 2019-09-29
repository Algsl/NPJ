package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MySupplyListResponseBean;
import com.zthx.npj.utils.MyCustomUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MySupplyListAdapter extends RecyclerView.Adapter<MySupplyListAdapter.ViewHolder> {

    private ArrayList<MySupplyListResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;
    private String type;

    public MySupplyListAdapter(Context context,ArrayList<MySupplyListResponseBean.DataBean> list,String type){
        mContext=context;
        mList=list;
        this.type=type;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
        void onSaleClick(int position);
        void onSupplyEditClick(int position);
        void onSupplyDeleteClick(int position);
        void onSupplyShareClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_store_goods_list,viewGroup,false);
        return new MySupplyListAdapter.ViewHolder(view);
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
            viewHolder.onSale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSaleClick(position);
                }
            });
            viewHolder.editGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSupplyEditClick(position);
                }
            });
            viewHolder.deleteGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSupplyDeleteClick(position);
                }
            });
            viewHolder.shareGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSupplyShareClick(position);
                }
            });
        }
        String url = mList.get(i).getGoods_img();
        if (url.substring(url.length() - 4).equals(".mp4")) {
            viewHolder.goodsImg.setImageBitmap(MyCustomUtils.getVideoThumbnail(url));
            viewHolder.ivVideo.setVisibility(View.VISIBLE);
        } else {
            Glide.with(mContext).load(Uri.parse(mList.get(i).getGoods_img())).into(viewHolder.goodsImg);
        }
        viewHolder.goodsName.setText(mList.get(i).getGoods_name());
        //viewHolder.marketPrice.setText("市场价："+mList.get(i).getMarket_price());
        //viewHolder.memberPrice.setText("会员价："+mList.get(i).getMember_price());
        viewHolder.marketPrice.setText("￥"+mList.get(i).getPrice()+"/"+mList.get(i).getGoods_unit());
        viewHolder.memberPrice.setVisibility(View.INVISIBLE);
        viewHolder.sold.setText("销量："+(mList.get(i).getSold()==null?"0":mList.get(i).getSold()+""));
        viewHolder.goodsNumber.setText("库存："+mList.get(i).getGoods_num()+mList.get(i).getGoods_unit());
        if(type.equals("1")){
            viewHolder.onSale.setText("下架");
        }else{
            viewHolder.onSale.setText("上架");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg,ivVideo;
        TextView goodsName,marketPrice,memberPrice,sold,goodsNumber,onSale,shareGoods,deleteGoods,editGoods;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_storeGoodsList_iv_goodsImg);
            goodsName=itemView.findViewById(R.id.item_storeGoodsList_tv_goodsName);
            marketPrice=itemView.findViewById(R.id.item_storeGoodsList_tv_marketPrice);
            memberPrice=itemView.findViewById(R.id.item_storeGoodsList_tv_memberPrice);
            sold=itemView.findViewById(R.id.item_storeGoodsList_tv_sold);
            goodsNumber=itemView.findViewById(R.id.item_storeGoodsList_tv_inventory);
            onSale=itemView.findViewById(R.id.item_storeGoodsList_tv_onSale);
            shareGoods=itemView.findViewById(R.id.item_storeGoodsList_tv_share);
            deleteGoods=itemView.findViewById(R.id.item_storeGoodsList_tv_delete);
            editGoods=itemView.findViewById(R.id.item_storeGoodsList_tv_edit);
            ivVideo=itemView.findViewById(R.id.item_storeQuotation_iv_video);
        }
    }
}
