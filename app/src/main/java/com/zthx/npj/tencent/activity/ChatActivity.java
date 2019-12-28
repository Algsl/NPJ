package com.zthx.npj.tencent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.tencent.util.Constants;
import com.zthx.npj.ui.UserMsgActivity;

public class ChatActivity extends AppCompatActivity {

    private ChatInfo mChatInfo;
    private TitleBarLayout mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        BaseApp.getApp().addActivity(this);

        TIMUserConfig config=new TIMUserConfig();
        config.enableReadReceipt(true);

        Bundle bundle=getIntent().getExtras();

        mChatInfo=new ChatInfo();
        mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);


        // 从布局文件中获取聊天面板
        final ChatLayout mChatLayout = findViewById(R.id.chat_layout);

        // 单聊面板的默认 UI 和交互初始化
        mChatLayout.initDefault();
        mChatLayout.setChatInfo(mChatInfo);

        mTitleBar=mChatLayout.getTitleBar();
        mTitleBar.getRightIcon().setVisibility(View.GONE);

        mChatLayout.getMessageLayout().setOnItemClickListener(new MessageLayout.OnItemClickListener() {
            @Override
            public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
                //因为adapter中第一条为加载条目，位置需减1
                mChatLayout.getMessageLayout().showItemPopMenu(position - 1, messageInfo, view);
            }

            @Override
            public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
                if (null == messageInfo || null == messageInfo.getTIMMessage()) {
                    return;
                }
                ChatInfo info = new ChatInfo();
                info.setId(messageInfo.getTIMMessage().getSender());
            }
        });
        ChatsetRead();
    }

    private void ChatsetRead() {
        //对单聊会话已读上报
        String peer = mChatInfo.getId();  //获取与用户 "sample_user_1" 的会话
        TIMConversation conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, peer);                      //会话对方用户帐号
        //将此会话的所有消息标记为已读
        conversation.setReadMessage(null, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                Log.e("测试", "setReadMessage failed, code: " + code + "|desc: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.d("测试", "setReadMessage succ");
            }
        });
    }
}
