package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.entity.NotificationBean;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class NotificationDetailActivity extends ActivityBase {
    @BindView(R.id.ac_notification_tv_title)
    TextView acNotificationTvTitle;
    @BindView(R.id.ac_notification_tv_content)
    TextView acNotificationTvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            if(bundle!=null){
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
            }
            String notification_id=getIntent().getStringExtra("key0");
            if(notification_id!=null){
                ArrayList<NotificationBean> lists=SharePerferenceUtils.getList();
                title=lists.get(Integer.valueOf(notification_id)).getTitle();
                content=lists.get(Integer.valueOf(notification_id)).getContent();
            }
            acNotificationTvTitle.setText(title);
            acNotificationTvContent.setText(content);
        }
    }
}
