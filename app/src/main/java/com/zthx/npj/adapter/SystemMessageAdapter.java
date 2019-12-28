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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.SystemMessageResponseBean;
import com.zthx.npj.net.been.SystemMsgResponseBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SystemMessageAdapter extends RecyclerView.Adapter<SystemMessageAdapter.ViewHolder> {

    private ArrayList<SystemMsgResponseBean.DataBean> mList;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public SystemMessageAdapter(Context context, ArrayList<SystemMsgResponseBean.DataBean> list) {
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_system_message, viewGroup, false);
        return new SystemMessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
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
        if(mList.get(i).getImg()!=null){
            Glide.with(mContext).load(Uri.parse("http://app.npj-vip.com/"+mList.get(i).getImg())).into(viewHolder.msgIv);
        }
        switch ((int) mList.get(i).getType()){
            case 1:
                break;
        }

        viewHolder.title.setText(mList.get(i).getTitle());
        viewHolder.content.setText(mList.get(i).getDescription());
        viewHolder.msgFrom.setText("来自  "+mList.get(i).getSource());
        viewHolder.nowTime.setText(new SimpleDateFormat("yy/MM/dd").format(new Date(mList.get(i).getCreate_time()*1000)));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView msgIv;
        TextView title,content,nowTime,msgFrom;

        ViewHolder(View itemView) {
            super(itemView);
            msgIv=itemView.findViewById(R.id.item_systemMsg_iv_msg);
            title=itemView.findViewById(R.id.item_systemMsg_tv_title);
            content=itemView.findViewById(R.id.item_systemMsg_tv_content);
            nowTime=itemView.findViewById(R.id.item_systemMsg_tv_time);
            msgFrom=itemView.findViewById(R.id.item_systemMsg_tv_from);
        }
    }
}
