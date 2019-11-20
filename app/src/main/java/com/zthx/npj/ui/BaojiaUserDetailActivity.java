package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.zthx.npj.R;
import com.zthx.npj.adapter.BaojiaUserDetailAdapter;
import com.zthx.npj.base.BaseApp;
import com.zthx.npj.net.been.BaojiaUserDetailResponseBean;
import com.zthx.npj.net.been.BaojiaUserListResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.activity.ChatActivity;
import com.zthx.npj.tencent.util.Constants;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaojiaUserDetailActivity extends ActivityBase {


    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_baojia_detail_mv_headImg)
    MyCircleView acBaojiaDetailMvHeadImg;
    @BindView(R.id.ac_baojia_detail_tv_nickName)
    TextView acBaojiaDetailTvNickName;
    @BindView(R.id.ac_baojia_detail_tv_level)
    ImageView acBaojiaDetailTvLevel;
    @BindView(R.id.ac_baojia_detail_tv_day)
    TextView acBaojiaDetailTvDay;
    @BindView(R.id.item_quotation_people_ll)
    LinearLayout itemQuotationPeopleLl;
    @BindView(R.id.ac_baojia_detail_rv)
    RecyclerView acBaojiaDetailRv;
    @BindView(R.id.ac_baojia_tv_seeInfo)
    TextView acBaojiaTvSeeInfo;
    @BindView(R.id.ac_baojia_btn_chat)
    Button acBaojiaBtnChat;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String id = "";
    private String bjuser_id="";
    private ConversationInfo conversationInfo=new ConversationInfo();

    private String nick_name;
    private String mobile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baojia_detail);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "商家报价");

        String result = getIntent().getStringExtra("result");
        int position = getIntent().getIntExtra("position", 0);

        BaojiaUserListResponseBean bean = GsonUtils.fromJson(result, BaojiaUserListResponseBean.class);

        id = bean.getData().get(position).getId() + "";
        if(bean.getData().get(position).getHead_img().split("/")[0].equals("http:")){
            Glide.with(this).load(Uri.parse(bean.getData().get(position).getHead_img())).into(acBaojiaDetailMvHeadImg);
        }else{
            Glide.with(this).load(Uri.parse("http://app.npj-vip.com"+bean.getData().get(position).getHead_img())).into(acBaojiaDetailMvHeadImg);
        }
        nick_name=bean.getData().get(position).getNick_name();
        mobile=bean.getData().get(position).getMobile();
        acBaojiaDetailTvNickName.setText(bean.getData().get(position).getNick_name());
        MyCustomUtils.showLevelImg((int)bean.getData().get(position).getLevel(),acBaojiaDetailTvLevel);
        getBaojiaDetail();
    }

    private void getBaojiaDetail() {
        SetSubscribe.baojiaUserDetail(user_id, token, id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaojiaUserDetailResponseBean bean = GsonUtils.fromJson(result, BaojiaUserDetailResponseBean.class);
                ArrayList<BaojiaUserDetailResponseBean.DataBean> data = bean.getData();
                bjuser_id=data.get(0).getUser_id();

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BaojiaUserDetailActivity.this);
                acBaojiaDetailRv.setLayoutManager(layoutManager);
                BaojiaUserDetailAdapter adapter = new BaojiaUserDetailAdapter(BaojiaUserDetailActivity.this, data);
                acBaojiaDetailRv.setItemAnimator(new DefaultItemAnimator());
                acBaojiaDetailRv.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.ac_baojia_tv_seeInfo, R.id.ac_baojia_btn_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_baojia_tv_seeInfo:
                openActivity(UserMsgActivity.class,bjuser_id);
                break;
            case R.id.ac_baojia_btn_chat:
                conversationInfo.setTitle(nick_name);
                conversationInfo.setId(mobile);
                startChatActivity(conversationInfo);
                break;
        }
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
}
