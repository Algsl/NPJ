package com.zthx.npj.tencent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.component.action.PopActionClickListener;
import com.tencent.qcloud.tim.uikit.component.action.PopDialogAdapter;
import com.tencent.qcloud.tim.uikit.component.action.PopMenuAction;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.utils.PopWindowUtil;
import com.zthx.npj.R;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.util.Constants;
import com.zthx.npj.tencent.util.TencentUtil;
import com.zthx.npj.ui.ActivityBase;
import com.zthx.npj.ui.ServicesListActivity;
import com.zthx.npj.ui.SystemMessageActivity;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageCenterActivity extends ActivityBase {


    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.at_message_center_iv1)
    ImageView atMessageCenterIv1;
    @BindView(R.id.at_message_center_iv2)
    ImageView atMessageCenterIv2;
    @BindView(R.id.conversation_layout)
    ConversationLayout conversationLayout;

    private static final String TAG = "测试";
    @BindView(R.id.tv_unReadMsg)
    TextView tvUnReadMsg;


    private List<PopMenuAction> mConversationPopActions = new ArrayList<>();
    private ListView mConversationPopList;
    private PopupWindow mConversationPopWindow;
    private PopDialogAdapter mConversationPopAdapter;
    private View mBaseView;


    private int type = 0;
    private int i = 0;

    private String phone = "";
    private String headImg;
    private String nickName;
    private String signature;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private int msgNum=SharePerferenceUtils.getMessageNum(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center1);
        ButterKnife.bind(this);
        back(titleThemeBack);
        changeTitle(titleThemeTitle, "消息中心");

        initView();
        initPopMenuAction();

        getUserMsg();


        if(msgNum!=0){
            tvUnReadMsg.setVisibility(View.VISIBLE);
            if(msgNum>99){
                tvUnReadMsg.setText("⋯");
            }else{
                tvUnReadMsg.setText(msgNum+"");
            }
        }else{
            tvUnReadMsg.setVisibility(View.GONE);
        }
    }

    private void getUserMsg() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
                SharePerferenceUtils.setUserLevel(MessageCenterActivity.this, bean.getData().getLevel() + "");
                BaseConstant.TOKEN = SharePerferenceUtils.getToken(MessageCenterActivity.this);
                TencentUtil.updateProfile(bean.getData().getHead_img(), bean.getData().getNick_name(), "");
            }

            @Override
            public void onFault(String errorMsg) {
                //showToast(errorMsg);
                SharePerferenceUtils.setUserId(MessageCenterActivity.this, "");
            }
        }));
    }

    private void initView() {
        mBaseView = LayoutInflater.from(this).inflate(R.layout.activity_message_center1, null);
        conversationLayout.initDefault();
        TitleBarLayout titleBarLayout = conversationLayout.getTitleBar();
        titleBarLayout.setVisibility(View.GONE);
        getChatList();
    }

    private void getChatList() {
        conversationLayout.getConversationList().setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo messageInfo) {
                startChatActivity(messageInfo);
            }
        });

        conversationLayout.getConversationList().setOnItemLongClickListener(new ConversationListLayout.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, int position, ConversationInfo messageInfo) {
                startPopShow(view, position, messageInfo);
            }
        });
    }

    private void startChatActivity(ConversationInfo messageInfo) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(messageInfo.isGroup() ? TIMConversationType.Group : TIMConversationType.C2C);
        chatInfo.setId(messageInfo.getId());
        chatInfo.setChatName(messageInfo.getTitle());
        Intent intent = new Intent(BaseApp.getApp(), ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApp.getApp().startActivity(intent);
    }

    private void showItemPopMenu(final int index, final ConversationInfo conversationInfo, float locationX, float locationY) {
        if (mConversationPopActions == null || mConversationPopActions.size() == 0)
            return;
        View itemPop = LayoutInflater.from(this).inflate(R.layout.pop_menu_layout, null);
        mConversationPopList = itemPop.findViewById(R.id.pop_menu_list);
        mConversationPopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopMenuAction action = mConversationPopActions.get(position);
                if (action.getActionClickListener() != null) {
                    action.getActionClickListener().onActionClick(index, conversationInfo);
                }
                mConversationPopWindow.dismiss();
            }
        });

        for (int i = 0; i < mConversationPopActions.size(); i++) {
            PopMenuAction action = mConversationPopActions.get(i);
            if (conversationInfo.isTop()) {
                if (action.getActionName().equals(getResources().getString(R.string.chat_top))) {
                    action.setActionName(getResources().getString(R.string.quit_chat_top));
                }
            } else {
                if (action.getActionName().equals(getResources().getString(R.string.quit_chat_top))) {
                    action.setActionName(getResources().getString(R.string.chat_top));
                }

            }
        }
        mConversationPopAdapter = new PopDialogAdapter();
        mConversationPopList.setAdapter(mConversationPopAdapter);
        mConversationPopAdapter.setDataSource(mConversationPopActions);
        mConversationPopWindow = PopWindowUtil.popupWindow(itemPop, mBaseView, (int) locationX, (int) locationY);
        mBaseView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mConversationPopWindow.dismiss();
            }
        }, 10000); // 10s后无操作自动消失
    }


    private void initPopMenuAction() {
        // 设置长按conversation显示PopAction
        List<PopMenuAction> conversationPopActions = new ArrayList<PopMenuAction>();
        PopMenuAction action = new PopMenuAction();
        action.setActionName(getResources().getString(R.string.chat_top));
        action.setActionClickListener(new PopActionClickListener() {
            @Override
            public void onActionClick(int position, Object data) {
                conversationLayout.setConversationTop(position, (ConversationInfo) data);
            }
        });
        conversationPopActions.add(action);
        action = new PopMenuAction();
        action.setActionClickListener(new PopActionClickListener() {
            @Override
            public void onActionClick(int position, Object data) {
                conversationLayout.deleteConversation(position, (ConversationInfo) data);
            }
        });
        action.setActionName(getResources().getString(R.string.chat_delete));
        conversationPopActions.add(action);
        mConversationPopActions.clear();
        mConversationPopActions.addAll(conversationPopActions);
    }


    private void startPopShow(View view, int position, ConversationInfo info) {
        showItemPopMenu(position, info, view.getX(), view.getY() + view.getHeight() / 2);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.at_message_center_rl_system_message, R.id.at_message_center_rl_kefu_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_message_center_rl_system_message:
                openActivity(SystemMessageActivity.class);
                break;
            case R.id.at_message_center_rl_kefu_message:
                openActivity(ServicesListActivity.class);
                break;
        }
    }


}
