package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CartOrderResponseBean;

import java.util.ArrayList;

public class CartStoreAdapter extends RecyclerView.Adapter<CartStoreAdapter.ViewHolder> {

    private ArrayList<ArrayList<CartOrderResponseBean.DataBean.List>> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onZitiClick(int position);
        void onPeisongClick(int position);
    }

    public CartStoreAdapter(Context context,ArrayList<ArrayList<CartOrderResponseBean.DataBean.List>> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_cart_store,viewGroup,false);
        return new CartStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if(mItemClickListener!=null){
            viewHolder.btnZiti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onZitiClick(position);
                    viewHolder.btnZiti.setBackgroundColor(mContext.getResources().getColor(R.color.app_theme));
                    viewHolder.btnZiti.setTextColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnPeisong.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnPeisong.setTextColor(mContext.getResources().getColor(R.color.text3));
                    viewHolder.rlZiti.setVisibility(View.VISIBLE);
                    viewHolder.rlPeisong.setVisibility(View.GONE);
                }
            });
            viewHolder.btnPeisong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onPeisongClick(position);
                    viewHolder.btnPeisong.setBackgroundColor(mContext.getResources().getColor(R.color.app_theme));
                    viewHolder.btnPeisong.setTextColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnZiti.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                    viewHolder.btnZiti.setTextColor(mContext.getResources().getColor(R.color.text3));
                    viewHolder.rlPeisong.setVisibility(View.VISIBLE);
                    viewHolder.rlZiti.setVisibility(View.GONE);
                }
            });
        }

        viewHolder.storeName.setText(mList.get(i).get(0).getStore_name());
        long goodsNum=0;
        double orderPrice=0;
        for(int j=0;j<mList.get(i).size();j++){
            goodsNum+=mList.get(i).get(j).getGoods_num();
            orderPrice+=Double.parseDouble(mList.get(i).get(j).getPrice())*mList.get(i).get(j).getGoods_num();
        }
        viewHolder.goodsNum.setText("共"+goodsNum+"件商品  小计：");
        viewHolder.orderPrice.setText("￥ "+orderPrice);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mContext);
        viewHolder.itemCartStoreRv.setLayoutManager(layoutManager);
        CartGoodsAdapter adapter=new CartGoodsAdapter(mContext,mList.get(i));
        viewHolder.itemCartStoreRv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName,goodsNum,orderPrice;
        RecyclerView itemCartStoreRv;
        RelativeLayout rlZiti,rlPeisong;
        Button btnZiti,btnPeisong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCartStoreRv=itemView.findViewById(R.id.item_cartStore_rv);
            storeName=itemView.findViewById(R.id.item_cartStore_tv_store_name);
            goodsNum=itemView.findViewById(R.id.item_cartStore_tv_allGoodsNum);
            orderPrice=itemView.findViewById(R.id.item_cartStore_tv_orderPrice);
            rlZiti=itemView.findViewById(R.id.item_cartStore_rl_ziti);
            rlPeisong=itemView.findViewById(R.id.item_cartStore_rl_peisong);
            btnZiti=itemView.findViewById(R.id.item_cartStore_btn_ziti);
            btnPeisong=itemView.findViewById(R.id.item_cartStore_btn_peisong);
        }
    }
}
