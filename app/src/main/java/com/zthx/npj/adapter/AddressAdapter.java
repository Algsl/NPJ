package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.AddressListResponseBean;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {


    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    private ItemClickListener mItemClickListener;
    public interface ItemClickListener{
        void onItemClick(int position);
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
    private ArrayList<AddressListResponseBean.DataBean> mList;
    private Context mContext;
    public AddressAdapter(Context context,ArrayList<AddressListResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_address_msg,viewGroup,false);
        return new AddressAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if (mItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder.mEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onEditClick(position);
                }
            });
            viewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onDeleteClick(position);
                }
            });
        }
        viewHolder.mConsignee.setText(mList.get(i).getConsignee());
        viewHolder.mMobile.setText(mList.get(i).getMobile());
        viewHolder.mAddress.setText(mList.get(i).getAlladdress());
        if(mList.get(i).getIs_default()==0){
            viewHolder.mIsDefault.setText("");
        }else{
            viewHolder.mIsDefault.setText("默认地址");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mConsignee;
        TextView mAddress;
        TextView mMobile;
        TextView mIsDefault;
        TextView mEdit;
        TextView mDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mConsignee=itemView.findViewById(R.id.item_address_tv_consignee);
            mAddress= itemView.findViewById(R.id.item_address_tv_address);
            mMobile=itemView.findViewById(R.id.item_address_tv_mobile);
            mIsDefault=itemView.findViewById(R.id.item_address_tv_isDefault);
            mEdit=itemView.findViewById(R.id.item_address_tv_edit);
            mDelete=itemView.findViewById(R.id.item_address_tv_delete);
        }
    }
}
