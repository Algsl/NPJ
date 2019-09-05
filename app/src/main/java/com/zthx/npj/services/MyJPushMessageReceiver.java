package com.zthx.npj.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zthx.npj.jpush.Logger;
import com.zthx.npj.jpush.TagAliasOperatorHelper;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class MyJPushMessageReceiver extends JPushMessageReceiver {
    @Override
    public void onTagOperatorResult(Context context,JPushMessage jPushMessage) {
        Logger.e("测试","tag 增删查改的操作");
        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
        Logger.e("测试","查询某个 tag 与当前用户的绑定状态");
        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        Logger.e("测试","alias 相关的操作");
        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }
}
