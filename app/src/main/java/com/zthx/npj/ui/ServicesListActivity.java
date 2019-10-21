package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.tencent.activity.ChatActivity;
import com.zthx.npj.tencent.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicesListActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_serviceList_rl1)
    LinearLayout acServiceListRl1;
    @BindView(R.id.ac_serviceList_rl2)
    LinearLayout acServiceListRl2;
    @BindView(R.id.ac_serviceList_rl3)
    LinearLayout acServiceListRl3;
    @BindView(R.id.ac_serviceList_rl4)
    LinearLayout acServiceListRl4;
    @BindView(R.id.ac_serviceList_rl5)
    LinearLayout acServiceListRl5;

    private ConversationInfo conversationInfo=new ConversationInfo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"客服列表");
    }

    @OnClick({R.id.ac_serviceList_rl1, R.id.ac_serviceList_rl2, R.id.ac_serviceList_rl3, R.id.ac_serviceList_rl4, R.id.ac_serviceList_rl5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_serviceList_rl1:
                conversationInfo.setTitle("农品街客服1");
                conversationInfo.setId("npj1");
                break;
            case R.id.ac_serviceList_rl2:
                conversationInfo.setTitle("农品街客服2");
                conversationInfo.setId("npj2");
                break;
            case R.id.ac_serviceList_rl3:
                conversationInfo.setTitle("农品街客服3");
                conversationInfo.setId("npj3");
                break;
            case R.id.ac_serviceList_rl4:
                conversationInfo.setTitle("农品街客服4");
                conversationInfo.setId("npj4");
                break;
            case R.id.ac_serviceList_rl5:
                conversationInfo.setTitle("农品街客服5");
                conversationInfo.setId("npj5");
                break;
        }

        startChatActivity(conversationInfo);
    }

    private void startChatActivity(ConversationInfo messageInfo) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType( TIMConversationType.C2C);
        chatInfo.setId(messageInfo.getId());
        chatInfo.setChatName(messageInfo.getTitle());
        Intent intent = new Intent(BaseApp.getApp(), ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApp.getApp().startActivity(intent);
    }
}
