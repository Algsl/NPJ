package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zthx.npj.R;
import com.zthx.npj.adapter.MessageCenterAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageCenterActivity extends AppCompatActivity {

    @BindView(R.id.at_message_center_rv)
    RecyclerView atMessageCenterRv;
    @BindView(R.id.at_message_center_rl_system_message)
    RelativeLayout atMessageCenterRlSystemMessage;
    @BindView(R.id.at_message_center_iv2)
    ImageView atMessageCenterIv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atMessageCenterRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        MessageCenterAdapter mAdapter = new MessageCenterAdapter(this, list);
        atMessageCenterRv.setAdapter(mAdapter);
    }

    @OnClick(R.id.at_message_center_rl_system_message)
    public void onViewClicked() {
        startActivity(new Intent(this, SystemMessageActivity.class));
    }
}