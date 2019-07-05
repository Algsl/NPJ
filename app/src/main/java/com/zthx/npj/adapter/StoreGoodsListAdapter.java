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
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.MyGoodsResponseBean;
import com.zthx.npj.net.been.MyStoreResponseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxin on 2019/5/14.
 */

public class StoreGoodsListAdapter extends RecyclerView.Adapter<StoreGoodsListAdapter.ViewHolder>{

    private ArrayList<MyGoodsResponseBean.DataBean> list;
    private Context mContext;
    private String type;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
        void onSaleClick(int position);
        void onShareClick(int position);
        void onEditClick(int position);
        void onDelete(int position);
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public StoreGoodsListAdapter(Context context, ArrayList<MyGoodsResponseBean.DataBean> list,String type) {
        this.type=type;
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_store_goods_list, viewGroup, false);
        return new StoreGoodsListAdapter.ViewHolder(view);
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
            viewHolder.onSale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSaleClick(position);
                }
            });
            viewHolder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onShareClick(position);
                }
            });
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onEditClick(position);
                }
            });
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onDelete(position);
                }
            });
        }
        if (list!= null && list.size() > 0) {
            Glide.with(mContext).load(Uri.parse(list.get(i).getGoods_img())).into(viewHolder.goodsImg);
            viewHolder.goodsName.setText(list.get(i).getGoods_name());
            viewHolder.marketPrice.setText("市场价："+list.get(i).getMarket_price());
            viewHolder.memberPrice.setText("代言人价："+list.get(i).getMember_price());
            viewHolder.sold.setText("销量："+list.get(i).getSold());
            viewHolder.inventory.setText("库存："+list.get(i).getInventory());
            if(type.equals("1")){
                viewHolder.onSale.setText("下架");
            }else{
                viewHolder.onSale.setText("上架");
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg;
        TextView goodsName;
        TextView marketPrice;
        TextView memberPrice;
        TextView sold;
        TextView inventory;
        TextView onSale;
        TextView share;
        TextView edit;
        TextView delete;

        ViewHolder(View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_storeGoodsList_iv_goodsImg);
            goodsName=itemView.findViewById(R.id.item_storeGoodsList_tv_goodsName);
            marketPrice=itemView.findViewById(R.id.item_storeGoodsList_tv_marketPrice);
            memberPrice=itemView.findViewById(R.id.item_storeGoodsList_tv_memberPrice);
            sold=itemView.findViewById(R.id.item_storeGoodsList_tv_sold);
            inventory=itemView.findViewById(R.id.item_storeGoodsList_tv_inventory);
            onSale=itemView.findViewById(R.id.item_storeGoodsList_tv_onSale);
            share=itemView.findViewById(R.id.item_storeGoodsList_tv_share);
            edit=itemView.findViewById(R.id.item_storeGoodsList_tv_edit);
            delete=itemView.findViewById(R.id.item_storeGoodsList_tv_delete);
        }
    }
}
