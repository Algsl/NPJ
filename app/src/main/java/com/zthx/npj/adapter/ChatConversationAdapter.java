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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Message;

public class ChatConversationAdapter extends RecyclerView.Adapter<ChatConversationAdapter.ViewHolder> {

    private List<Message> mList;
    private Context mContext;

    public ChatConversationAdapter(Context context,List<Message> list){
        mContext=context;
        mList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(mList.get(i).getDirect().equals("receive")){
            view=LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_receive_text,viewGroup,false);
        }else{
            view=LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_send_text,viewGroup,false);
        }

        return new ChatConversationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextContent content= (TextContent) mList.get(i).getContent();
        viewHolder.tv_time.setText(new SimpleDateFormat("HH:mm").format(new Date(mList.get(i).getCreateTime())));
        viewHolder.tv_userName.setText(content.getText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_business_Card;
        ImageView business_head;
        TextView tv_nickUser,tv_userName,tv_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_business_Card =itemView.findViewById(R.id.ll_businessCard);
            business_head = itemView.findViewById(R.id.business_head);
            tv_nickUser =  itemView.findViewById(R.id.tv_nickUser);
            tv_userName =  itemView.findViewById(R.id.tv_userName);
            tv_time=itemView.findViewById(R.id.jmui_send_time_txt);
        }
    }
}
