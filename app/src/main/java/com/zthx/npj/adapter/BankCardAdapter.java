package com.zthx.npj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            switch (mList.get(i).getId()+""){
                case "1":
                    viewHolder.cardBg.setBackgroundResource(R.drawable.nongyebg);
                    viewHolder.cardIcon.setImageResource(R.drawable.nongye);
                    break;
                case "2":
                    viewHolder.cardBg.setBackgroundResource(R.drawable.gongshangbg);
                    viewHolder.cardIcon.setImageResource(R.drawable.gongshang);
                    break;
                case "3":
                    viewHolder.cardBg.setBackgroundResource(R.drawable.zhongguobg);
                    viewHolder.cardIcon.setImageResource(R.drawable.zhongguo);
                    break;
                case "4":
                    viewHolder.cardBg.setBackgroundResource(R.drawable.jianshebg);
                    viewHolder.cardIcon.setImageResource(R.drawable.jianshe);
                    break;
                case "5":
                    viewHolder.cardBg.setBackgroundResource(R.drawable.jiaotongbg);
                    viewHolder.cardIcon.setImageResource(R.drawable.jiaotong);
                    break;
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
        private LinearLayout cardBg;
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
