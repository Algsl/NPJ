package com.zthx.npj.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.utils.TimeFormat;
import com.zthx.npj.view.MyCircleView;

import java.util.List;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

public class MessageCenterAdapter extends RecyclerView.Adapter<MessageCenterAdapter.ViewHolder>{

    private List<Conversation> mList;
    private Context mContext;

    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    public MessageCenterAdapter(Context context, List<Conversation> list) {
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chat, viewGroup, false);
        return new MessageCenterAdapter.ViewHolder(view);
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
        if (mList!= null && mList.size() > 0) {
            Conversation conversation=mList.get(i);
            UserInfo userInfo= (UserInfo) mList.get(i).getTargetInfo();
            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                @Override
                public void gotResult(int i, String s, Bitmap bitmap) {
                    if(i==0){
                        viewHolder.img.setImageBitmap(bitmap);
                    }else{
                        viewHolder.img.setImageResource(R.drawable.logo);
                    }
                }
            });
            viewHolder.name.setText(userInfo.getNickname());
            viewHolder.msg.setText(conversation.getLatestText());
            TimeFormat timeFormat=new TimeFormat(mContext,conversation.getLastMsgDate());
            viewHolder.lastTime.setText(timeFormat.getDetailTime());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MyCircleView img;
        TextView name,msg,lastTime;

        ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.item_chat_mcv_img);
            name=itemView.findViewById(R.id.item_chat_tv_name);
            msg=itemView.findViewById(R.id.item_chat_tv_msg);
            lastTime=itemView.findViewById(R.id.item_chat_tv_time);
        }
    }
}
