package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.MessageCenterAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

public class MessageCenterActivity extends ActivityBase {

    @BindView(R.id.at_message_center_rv)
    RecyclerView atMessageCenterRv;
    @BindView(R.id.at_message_center_rl_system_message)
    RelativeLayout atMessageCenterRlSystemMessage;
    @BindView(R.id.at_message_center_iv2)
    ImageView atMessageCenterIv2;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.at_message_center_iv1)
    ImageView atMessageCenterIv1;
    @BindView(R.id.at_message_center_rl_kefu_message)
    RelativeLayout atMessageCenterRlKefuMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ButterKnife.bind(this);
        back(titleThemeBack);
        changeTitle(titleThemeTitle, "消息中心");

        setChatData();

    }

    private void setChatData() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atMessageCenterRv.setLayoutManager(manager);
        final List<Conversation> lists=JMessageClient.getConversationList();
        if (lists!=null){
            MessageCenterAdapter mAdapter = new MessageCenterAdapter(this, lists);
            atMessageCenterRv.setItemAnimator(new DefaultItemAnimator());
            atMessageCenterRv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new MessageCenterAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    String targetId=lists.get(position).getTargetId();
                    UserInfo userInfo= (UserInfo) lists.get(position).getTargetInfo();
                    openActivity(ServicesChatActivity.class,targetId,userInfo.getNickname());
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        setChatData();
    }

    @OnClick({R.id.at_message_center_rl_system_message, R.id.at_message_center_rl_kefu_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_message_center_rl_system_message:
                openActivity(NotificationListActivity.class);
                break;
            case R.id.at_message_center_rl_kefu_message:
                openActivity(ServicesChatActivity.class,"gsla1","农品街客服");
                break;
        }
    }

   /*@OnClick(R.id.at_message_center_rl_system_message)
    public void onViewClicked() {
        //startActivity(new Intent(this, SystemMessageActivity.class));
        openActivity(NotificationListActivity.class);
    }*/
}
