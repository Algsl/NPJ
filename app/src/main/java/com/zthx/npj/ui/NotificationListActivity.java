package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.NotificationAdapter;
import com.zthx.npj.entity.NotificationBean;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationListActivity extends ActivityBase {


    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.ac_notification_recycle)
    RecyclerView acNotificationRecycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationlist);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"系统通知列表");

        setData();
    }

    private void setData() {
        ArrayList<NotificationBean> lists=SharePerferenceUtils.getList();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        acNotificationRecycle.setLayoutManager(layoutManager);
        NotificationAdapter adapter=new NotificationAdapter(this,lists);
        acNotificationRecycle.setAdapter(adapter);
        adapter.setOnItemClickListener(new NotificationAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openActivity(NotificationDetailActivity.class,position+"");
            }
        });
    }
}
