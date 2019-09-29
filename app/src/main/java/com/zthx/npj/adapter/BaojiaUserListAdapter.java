package com.zthx.npj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.BaojiaListResponseBean;
import com.zthx.npj.net.been.BaojiaUserListResponseBean;
import com.zthx.npj.utils.MyCustomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaojiaUserListAdapter extends RecyclerView.Adapter<BaojiaUserListAdapter.ViewHolder> {

    private ArrayList<BaojiaUserListResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;


    public BaojiaUserListAdapter(Context context, ArrayList<BaojiaUserListResponseBean.DataBean> list){
        mContext=context;
        mList=list;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onSeeClick(int position);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_quotation_people_list,viewGroup,false);
        return new BaojiaUserListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if(mItemClickListener!=null){
            viewHolder.see.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=viewHolder.getLayoutPosition();
                    mItemClickListener.onSeeClick(position);
                }
            });
        }

        if (mList.get(i).getCertification() == null) {
            viewHolder.itemQuotation.setVisibility(View.GONE);
        } else {
            viewHolder.itemQuotation.setVisibility(View.VISIBLE);
            String[] strs = mList.get(i).getCertification().split(",");
            for (String str : strs) {
                if (str.equals("1")) {
                    viewHolder.realName.setVisibility(View.VISIBLE);
                } else if (str.equals("2")) {
                    viewHolder.enterPrice.setVisibility(View.VISIBLE);
                } else if (str.equals("3")) {
                    viewHolder.purchase.setVisibility(View.VISIBLE);
                }else if (str.equals("4")) {
                    viewHolder.trust.setVisibility(View.VISIBLE);
                }else if (str.equals("5")) {
                    viewHolder.zizhi.setVisibility(View.VISIBLE);
                }
            }
        }

        Glide.with(mContext).load(Uri.parse(mList.get(i).getHead_img())).into(viewHolder.goodsImg);
        viewHolder.name.setText(mList.get(i).getNick_name());
        viewHolder.day.setText("已持续经营365天");
        viewHolder.time.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000)));
        MyCustomUtils.showLevelImg((int)mList.get(i).getLevel(),viewHolder.level);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImg,level;
        TextView name,time,day,see;
        LinearLayout itemQuotation;
        TextView realName,enterPrice,purchase,trust,zizhi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsImg=itemView.findViewById(R.id.item_quotation_people_head_pic);
            name=itemView.findViewById(R.id.item_quotation_people_tv_name);
            level=itemView.findViewById(R.id.item_quotaition_peopleList_tv_level);
            time=itemView.findViewById(R.id.item_quotation_people_tv_time);
            day=itemView.findViewById(R.id.item_quotation_people_tv_day);
            see=itemView.findViewById(R.id.item_quotation_people_tv_see);
            //seeInfo=itemView.findViewById(R.id.item_quotation_tv_seeInfo);

            itemQuotation=itemView.findViewById(R.id.item_quotation_people_ll);
            realName=itemView.findViewById(R.id.item_quotation_tv_realName);
            enterPrice=itemView.findViewById(R.id.item_quotation_tv_enterPrice);
            purchase=itemView.findViewById(R.id.item_quotation_tv_purchase);
            trust=itemView.findViewById(R.id.item_quotation_tv_trust);
            zizhi=itemView.findViewById(R.id.item_quotation_tv_zizhi);
        }
    }
}
