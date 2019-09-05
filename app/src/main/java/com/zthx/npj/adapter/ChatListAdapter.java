package com.zthx.npj.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zthx.npj.R;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.TimeFormat;

import java.io.File;
import java.util.Collections;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

import static com.alibaba.mobileim.channel.constant.WXType.WXTribeMsgType.image;

public class ChatListAdapter extends BaseAdapter {

    //发送和接受文本消息
    private final int TYPE_SEND_TXT=0;
    private final int TYPE_RECEIVE_TXT=1;

    //发送和接受图片消息
    private final int TYPE_SEND_IMAGE = 2;
    private final int TYPE_RECEIVER_IMAGE = 3;


    private List<Message> mList;
    private Context mContext;
    private TextContent content;

    private int mStart;

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
        switch (msg.getContentType()) {
            case text:
                return msg.getDirect() == MessageDirect.send ? TYPE_SEND_TXT
                        : TYPE_RECEIVE_TXT;
            case image:
                return msg.getDirect() == MessageDirect.send ? TYPE_SEND_IMAGE
                        : TYPE_RECEIVER_IMAGE;
            default:
                return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addMsgToList(Message msg) {
        mList.add(msg);
        incrementStartPosition();
        notifyDataSetChanged();
    }

    //当有新消息加到MsgList，自增mStart
    private void incrementStartPosition() {
        ++mStart;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {//绑定视图
        final Message msg = mList.get(i);

        final ViewHolder holder;
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
            holder.picture = (ImageView) view.findViewById(R.id.jmui_picture_iv);
            holder.progressTv = (TextView) view.findViewById(R.id.jmui_progress_tv);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        //时间显示
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

        //头像显示
        if(String.valueOf(mList.get(i).getDirect()).equals("send")){//发送方设置头像
            UserInfo userInfo=JMessageClient.getMyInfo();
            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                @Override
                public void gotResult(int i, String s, Bitmap bitmap) {
                    holder.headIcon.setImageBitmap(bitmap);
                }
            });
        }else{//接收方设置头像
            UserInfo userInfo= (UserInfo) mList.get(i).getTargetInfo();
            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                @Override
                public void gotResult(int i, String s, Bitmap bitmap) {
                    if(i==0){
                        holder.headIcon.setImageBitmap(bitmap);
                    }else{
                        holder.headIcon.setImageResource(R.drawable.logo);
                    }
                }
            });
        }

        //根据不同的类型显示不同的消息
        switch (msg.getContentType()){
            case text:
               content= (TextContent) msg.getContent();
                holder.txtContent.setText(content.getText());
                String extraBusiness = content.getStringExtra("businessCard");
                if (extraBusiness != null) {
                    holder.txtContent.setVisibility(View.GONE);
                    holder.ll_businessCard.setVisibility(View.VISIBLE);
                    holder.headIcon.setImageResource(R.drawable.confirm_wechat);
                } else {
                    holder.ll_businessCard.setVisibility(View.GONE);
                    holder.txtContent.setVisibility(View.VISIBLE);
                }
                break;
            case image:
                ImageContent imgContent = (ImageContent) msg.getContent();
                final String jiguang = imgContent.getStringExtra("jiguang");
                // 先拿本地缩略图
                final String path = imgContent.getLocalThumbnailPath();
                Log.e("测试", "getView: "+msg.getContent()+" "+jiguang+" "+path );
                if (path == null) {
                    //从服务器上拿缩略图
                    imgContent.downloadThumbnailImage(msg, new DownloadCompletionCallback() {
                        @Override
                        public void onComplete(int status, String desc, File file) {
                            Log.e("测试", "onComplete: "+status+" "+desc+" "+file );
                            if (status == 0) {
                                Glide.with(mContext).load(file).into(holder.picture);
                            }
                        }
                    });
                } else {
                    Log.e("测试", "getView: "+path );
                    Picasso.with(mContext).load(new File(path)).into(holder.picture);
                }
                break;
        }



        return view;
    }


    private List<Message> reverse(List<Message> list) {
        if (list.size() > 0) {
            Collections.reverse(list);
        }
        return list;
    }

    private View createViewByType(Message msg, int position) {//会话类型
        switch (msg.getContentType()) {
            case text://文字
                return getItemViewType(position) == TYPE_SEND_TXT ?
                        LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_send_text, null) :
                        LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_receive_text, null);
            case image://图片
                return getItemViewType(position) == TYPE_SEND_IMAGE ?
                        LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_send_image, null) :
                        LayoutInflater.from(mContext).inflate(R.layout.jmui_chat_item_receive_image, null);
            default:
                return null;
        }
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
        public ImageView picture;
        public TextView progressTv;
    }
}
