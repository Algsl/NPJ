package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.BankCardResponseBean;

import java.util.ArrayList;

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.ViewHolder> {

    private ArrayList<BankCardResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;


    public BankCardAdapter(Context context,ArrayList<BankCardResponseBean.DataBean> list){
        mList=list;
        mContext=context;
    }
    public interface ItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_bank_card,viewGroup,false);
        return new BankCardAdapter.ViewHolder(view);
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
        }
        if(mList.size()>0){
            if(mList.get(i).getBank_bg().split("/")[0].equals("http:")){
                Glide.with(mContext).load(Uri.parse(mList.get(i).getBank_bg())).into(viewHolder.cardBg);
            }else{
                Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getBank_bg())).into(viewHolder.cardBg);
            }
            if(mList.get(i).getBank_logo().split("/")[0].equals("http:")){
                Glide.with(mContext).load(Uri.parse(mList.get(i).getBank_logo())).into(viewHolder.cardIcon);
            }else{
                Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com"+mList.get(i).getBank_logo())).into(viewHolder.cardIcon);
            }
            viewHolder.bankName.setText(mList.get(i).getBank_name());
            viewHolder.cardNum.setText(mList.get(i).getCard_number());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardIcon;
        private ImageView cardBg;
        private TextView bankName,cardNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardIcon=itemView.findViewById(R.id.item_bankCard_iv_cardIcon);
            cardBg=itemView.findViewById(R.id.item_bankCard_ll_cardBg);
            bankName=itemView.findViewById(R.id.item_bankCard_tv_bankName);
            cardNum= itemView.findViewById(R.id.item_bankCard_tv_cardNumber);
        }
    }
}
