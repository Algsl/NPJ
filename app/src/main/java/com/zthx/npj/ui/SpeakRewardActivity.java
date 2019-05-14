package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.CommenRewardAdapter;
import com.zthx.npj.net.been.CommentGoodsBeen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeakRewardActivity extends AppCompatActivity {

    @BindView(R.id.at_speak_reward_rv)
    RecyclerView atSpeakRewardRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_reward);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atSpeakRewardRv.setLayoutManager(manager);
        List<CommentGoodsBeen> list = new ArrayList<>();
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        list.add(new CommentGoodsBeen());
        CommenRewardAdapter mAdapter = new CommenRewardAdapter(this,list);
        atSpeakRewardRv.setAdapter(mAdapter);
    }
}
