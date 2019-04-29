package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.ui.fragment.IntroduceFragment;
import com.zthx.npj.ui.fragment.SelectVideoFragment;
import com.zthx.npj.ui.fragment.VideoCommentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgricultureVideoMainActivity extends AppCompatActivity {

    @BindView(R.id.at_avm_tb)
    TabLayout atAvmTb;
    @BindView(R.id.at_avm_vp)
    ViewPager atAvmVp;
    @BindView(R.id.at_ak_video_btn_buy)
    Button atAkVideoBtnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_video_main);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        list.add("选集");
        list.add("简介");
        list.add("评论");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new SelectVideoFragment());
        list2.add(new IntroduceFragment());
        list2.add(new VideoCommentFragment());
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atAvmVp.setAdapter(mAdapter);
        atAvmTb.setTabMode(TabLayout.MODE_FIXED);
        atAvmTb.setTabGravity(TabLayout.GRAVITY_CENTER);
        atAvmTb.setupWithViewPager(atAvmVp);
    }

    @OnClick(R.id.at_ak_video_btn_buy)
    public void onViewClicked() {
        
    }
}
