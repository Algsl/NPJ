package com.zthx.npj.tencent.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.zthx.npj.R;
import com.zthx.npj.tencent.util.Constants;

public class ChatActivity extends AppCompatActivity {

    private ChatInfo mChatInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        Bundle bundle=getIntent().getExtras();

        mChatInfo=new ChatInfo();
        mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);


        // 从布局文件中获取聊天面板
        final ChatLayout mChatLayout = findViewById(R.id.chat_layout);
        // 单聊面板的默认 UI 和交互初始化
        mChatLayout.initDefault();
        mChatLayout.setChatInfo(mChatInfo);

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
    }
}
