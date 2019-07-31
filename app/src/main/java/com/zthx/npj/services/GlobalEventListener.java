package com.zthx.npj.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import com.zthx.npj.ui.ServicesChatActivity;

/**
 * 在demo中对于通知栏点击事件和在线消息接收事件，我们都直接在全局监听
 */
public class GlobalEventListener {
    private Context appContext;

    public GlobalEventListener(Context context) {
        appContext = context;
        JMessageClient.registerEventReceiver(this);
    }

    public void onEvent(NotificationClickEvent event) {
        jumpToActivity(event.getMessage());
    }

    private void jumpToActivity(Message msg) {
        UserInfo fromUser = msg.getFromUser();

        final Intent notificationIntent = new Intent(appContext, ServicesChatActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.putExtra("key0",fromUser.getUserName());
        notificationIntent.putExtra("key1","我的小店铺");
        /*notificationIntent.putExtra(ServicesChatActivity.EXTRA_FROM_USERNAME, fromUser.getUserName());
        notificationIntent.putExtra(ServicesChatActivity.EXTRA_FROM_APPKEY, fromUser.getAppKey());
        notificationIntent.putExtra(ServicesChatActivity.EXTRA_MSG_TYPE, msg.getContentType().toString());
        notificationIntent.putExtra(ServicesChatActivity.EXTRA_MSGID, msg.getId());*/
        appContext.startActivity(notificationIntent);
    }
}
