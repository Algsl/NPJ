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
import com.zthx.npj.net.been.NewsListResponseBean;
import com.zthx.npj.net.been.OtherSearchResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OtherSearchAdapter extends RecyclerView.Adapter<OtherSearchAdapter.ViewHolder> {

    private ArrayList<OtherSearchResponseBean.DataBean> mList;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }


    public OtherSearchAdapter(Context context, ArrayList<OtherSearchResponseBean.DataBean> list){
        mList=list;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_news_adapter,viewGroup,false);
        return new OtherSearchAdapter.ViewHolder(view);
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
            Glide.with(mContext).load(Uri.parse(mList.get(i).getImg())).into(viewHolder.img);
            viewHolder.title.setText(mList.get(i).getTitle());
            long time=System.currentTimeMillis()/1000-Long.valueOf(mList.get(i).getCreate_time());
            if(time/60<60){
                viewHolder.create_time.setText(time/60+"分钟前发布");
            }else if(time/3600<24){
                viewHolder.create_time.setText(time/3600+"小时前发布");
            }else if(time/3600/24<3){
                viewHolder.create_time.setText(time/3600/24+"天前发布");
            }else{
                viewHolder.create_time.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(mList.get(i).getCreate_time()*1000))+"发布");
            }

            viewHolder.lookNum.setText(mList.get(i).getHits());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,create_time,lookNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.item_news_iv_img);
            title=itemView.findViewById(R.id.item_news_tv_title);
            create_time=itemView.findViewById(R.id.item_news_tv_createTime);
            lookNum=itemView.findViewById(R.id.item_news_tv_lookNum);
        }
    }
}
