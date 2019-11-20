package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.SystemMessageAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;
import com.zthx.npj.net.been.SystemMessageResponseBean;
import com.zthx.npj.net.been.SystemMsgResponseBean;
import com.zthx.npj.net.netsubscribe.MainSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemMessageActivity extends ActivityBase {

    @BindView(R.id.at_system_message_rv)
    RecyclerView atSystemMessageRv;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"系统消息");

        getSystemMsg();
    }

    private void getSystemMsg() {
        MainSubscribe.systemMsg(user_id,token,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LinearLayoutManager manager = new LinearLayoutManager(SystemMessageActivity.this, LinearLayoutManager.VERTICAL, false);
                atSystemMessageRv.setLayoutManager(manager);
                SystemMsgResponseBean bean=GsonUtils.fromJson(result,SystemMsgResponseBean.class);
                ArrayList<SystemMsgResponseBean.DataBean> data=bean.getData();
                SystemMessageAdapter mAdapter = new SystemMessageAdapter(SystemMessageActivity.this, data);
                atSystemMessageRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
