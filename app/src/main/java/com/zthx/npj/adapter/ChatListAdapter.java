package com.zthx.npj.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.utils.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;

public class ChatListAdapter extends BaseAdapter {


    private final int TYPE_SEND_TXT=0;
    private final int TYPE_RECEIVE_TXT=1;

    private List<Message> mList;
    private Context mContext;

    public ChatListAdapter(Context context, List<Message> list){
        mContext=context;
        mList=reverse(list);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = mList.get(position);
        return msg.getDirect() == MessageDirect.send ? TYPE_SEND_TXT : TYPE_RECEIVE_TXT;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Message msg = mList.get(i);
        TextContent content= (TextContent) msg.getContent();
        Log.e("测试", "getView: "+msg);
        Log.e("测试", "getView: "+content.getText());
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=createViewByType(msg,i);
            holder.ll_businessCard=view.findViewById(R.id.ll_businessCard);
            holder.msgTime = (TextView) view.findViewById(R.id.jmui_send_time_txt);
            holder.headIcon = (ImageView) view.findViewById(R.id.jmui_avatar_iv);
            holder.displayName = (TextView) view.findViewById(R.id.jmui_display_name_tv);
            holder.txtContent = (TextView) view.findViewById(R.id.jmui_msg_content);
            holder.sendingIv = (ImageView) view.findViewById(R.id.jmui_sending_iv);
            holder.resend = (ImageButton) view.findViewById(R.id.jmui_fail_resend_ib);
            holder.text_receipt = (TextView) view.findViewById(R.id.text_receipt);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        long nowDate = msg.getCreateTime();
        if (i == 18) {
            if (i == 0 || i % 18 == 0) {
                TimeFormat timeFormat = new TimeFormat(mContext, nowDate);
                holder.msgTime.setText(timeFormat.getDetailTime());
                holder.msgTime.setVisibility(View.VISIBLE);
            } else {
                long lastDate = mList.get(i - 1).getCreateTime();
                // 如果两条消息之间的间隔超过五分钟则显示时间
                if (nowDate - lastDate > 300000) {
                    TimeFormat timeFormat = new TimeFormat(mContext, nowDate);
                    holder.msgTime.setText(timeFormat.getDetailTime());
                    holder.msgTime.setVisibility(View.VISIBLE);
                } else {
                    holder.msgTime.setVisibility(View.GONE);
                }
            }
        } else {
            if (i == 0 || i == 18
                    || (i - 18) % 18 == 0) {
                TimeFormat timeFormat = new TimeFormat(mContext, nowDate);

                holder.msgTime.setText(timeFormat.getDetailTime());
                holder.msgTime.setVisibility(View.VISIBLE);
            } else {
                long lastDate = mList.get(i - 1).getCreateTime();
                // 如果两条消息之间的间隔超过五分钟则显示时间
                if (nowDate - lastDate > 300000) {
                    TimeFormat timeFormat = new TimeFormat(mContext, nowDate);
                    holder.msgTime.setText(timeFormat.getDetailTime());
                    holder.msgTime.setVisibility(View.VISIBLE);
                } else {
                    holder.msgTime.setVisibility(View.GONE);
                }
            }
        }
        String extraBusiness = content.getStringExtra("businessCard");
        if (extraBusiness != null) {
            holder.txtContent.setVisibility(View.GONE);
            holder.ll_businessCard.setVisibility(View.VISIBLE);
        } else {
            holder.ll_businessCard.setVisibility(View.GONE);
            holder.txtContent.setVisibility(View.VISIBLE);
        }
        holder.txtContent.setText(content.getText());
        return view;
    }


    private List<Message> reverse(List<Message> list) {
        if (list.size() > 0) {
            Collections.reverse(list);
        }
        return list;
    }

    private View createViewByType(Message msg, int position) {
        return getItemViewType(position) == TYPE_SEND_TXT ?
                LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_send_text, null) :
                LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_receive_text, null);
    }

    public class ViewHolder{
        public TextView msgTime;
        public ImageView headIcon;
        public TextView displayName;
        public TextView txtContent;
        public ImageButton resend;
        public ImageView voice;
        public ImageView sendingIv;
        public LinearLayout ll_businessCard;
        public ImageView business_head;
        public TextView tv_nickUser;
        public TextView tv_userName;
        public TextView text_receipt;
    }
}
