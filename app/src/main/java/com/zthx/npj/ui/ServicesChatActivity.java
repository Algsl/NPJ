package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.ChatConversationAdapter;
import com.zthx.npj.adapter.ChatListAdapter;
import com.zthx.npj.net.been.StoreDetailResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

public class ServicesChatActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.ac_serviceChat_et_content)
    EditText acServiceChatEtContent;
    @BindView(R.id.ac_serviceChat_iv_expression)
    ImageView acServiceChatIvExpression;
    @BindView(R.id.ac_serviceChat_iv_add)
    ImageView acServiceChatIvAdd;
    @BindView(R.id.ac_serviceChat_tv_send)
    TextView acServiceChatTvSend;
    @BindView(R.id.ac_serviceChat_lv)
    ListView acServiceChatLv;

    private String chat_name="";
    private String receiveTitle="";
    private Conversation mConversation=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_chat);
        ButterKnife.bind(this);
        chat_name=getIntent().getStringExtra("key0");
        receiveTitle=getIntent().getStringExtra("key1");
        back(titleBack);
        changeTitle(acTitle,receiveTitle);
        getChatMsg();


        acServiceChatEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (acServiceChatEtContent.getText().toString().trim().equals("")) {
                    acServiceChatIvAdd.setVisibility(View.VISIBLE);
                    acServiceChatTvSend.setVisibility(View.GONE);
                } else {
                    acServiceChatIvAdd.setVisibility(View.GONE);
                    acServiceChatTvSend.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void getChatMsg() {
        if(mConversation==null){
            mConversation = JMessageClient.getSingleConversation(chat_name);
        }
        List<Message> lists = mConversation.getMessagesFromNewest(0,18);
        ChatListAdapter adapter=new ChatListAdapter(this,lists);
        acServiceChatLv.setAdapter(adapter);
        acServiceChatLv.setSelection(lists.size()-1);
        adapter.notifyDataSetInvalidated();
    }
    //用户端发送消息设置
    @OnClick(R.id.ac_serviceChat_tv_send)
    public void onViewClicked() {
        String text = acServiceChatEtContent.getText().toString().trim();
        if (!TextUtils.isEmpty(chat_name) && !TextUtils.isEmpty(text)) {
            //通过username和appkey拿到会话对象，通过指定appkey可以创建一个和跨应用用户的会话对象，从而实现跨应用的消息发送
            if (mConversation == null) {
                mConversation = Conversation.createSingleConversation(chat_name, "");
            }
            //构造message content对象
            TextContent textContent = new TextContent(text);
            //设置自定义的extra参数
            textContent.setStringExtra("", "");
            //创建message实体，设置消息发送回调。创建消息内容，包含内容信息，发送方信息。
            final Message message = mConversation.createSendMessage(textContent, "用户18435224024");
            message.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i == 0) {
                        acServiceChatEtContent.setText("");
                        getChatMsg();
                    } else {

                    }
                }
            });

            //设置消息发送时的一些控制参数
            MessageSendingOptions options = new MessageSendingOptions();
            options.setNeedReadReceipt(true);//是否需要对方用户发送消息已读回执
            options.setRetainOffline(true);//是否当对方用户不在线时让后台服务区保存这条消息的离线消息
            options.setShowNotification(true);//是否让对方展示sdk默认的通知栏通知
            options.setCustomNotificationEnabled(true);//是否需要自定义对方收到这条消息时sdk默认展示的通知栏中的文字
            if (true) {
                options.setNotificationTitle(message.getFromName());//自定义对方收到消息时通知栏展示的title
                //options.setNotificationAtPrefix("时光之刃");//自定义对方收到消息时通知栏展示的@信息的前缀
                options.setNotificationText(text);//自定义对方收到消息时通知栏展示的text
            }
            /*if (!TextUtils.isEmpty(mEt_customMsgCount.getText())) {
                try {
                    options.setMsgCount(Integer.valueOf(mEt_customMsgCount.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mProgressDialog = MsgProgressDialog.show(CreateSigTextMessageActivity.this, message);*/
            //发送消息
            JMessageClient.sendMessage(message, options);
        } else {
            Toast.makeText(getApplicationContext(), "必填字段不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}