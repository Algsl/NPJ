package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.StoreManagerBillResponseBean;

import java.util.ArrayList;
import java.util.List;

public class StoreBillMessageAdapter  extends RecyclerView.Adapter<StoreBillMessageAdapter.ViewHolder> {
    private ArrayList<StoreManagerBillResponseBean.DataBean> list;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public StoreBillMessageAdapter(Context context, ArrayList<StoreManagerBillResponseBean.DataBean> list) {
        this.list = list;
        mContext = context;
    }
    @NonNull
    @Override
    public StoreBillMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_stotr_bill, viewGroup, false);
        return new StoreBillMessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StoreBillMessageAdapter.ViewHolder viewHolder, int i) {
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
            viewHolder.title.setText("商品普通利润");
            viewHolder.money.setText("+125元");
            viewHolder.createTime.setText("2019-04-19   12:05:26");
            viewHolder.orderSn.setText("YBFA684654645165146");
        }
    }

    @Override
    public int getItemCount() {
        //return list.size();
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,money,createTime,orderSn;
        ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_storeBill_tv_title);
            money=itemView.findViewById(R.id.item_storeBill_tv_money);
            createTime=itemView.findViewById(R.id.item_storeBill_tv_createTime);
            orderSn=itemView.findViewById(R.id.item_storeBill_tv_orderSn);
        }
    }
}
