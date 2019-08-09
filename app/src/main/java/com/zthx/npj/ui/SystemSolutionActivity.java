package com.zthx.npj.ui;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.media.AndroidMediaController;
import com.zthx.npj.media.IRenderView;
import com.zthx.npj.media.IjkVideoView;
import com.zthx.npj.media.MyMediaController;
import com.zthx.npj.net.been.SolutionVideoResponseBean;
import com.zthx.npj.ui.fragment.VideoListFragment;
import com.zthx.npj.ui.fragment.WebFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SystemSolutionActivity extends ActivityBase implements VideoListFragment.OnFragmentInteractionListener, WebFragment.OnFragmentInteractionListener {

    @BindView(R.id.at_system_solution_tb)
    TabLayout atSystemSolutionTb;
    @BindView(R.id.at_system_solution_vp)
    ViewPager atSystemSolutionVp;
    @BindView(R.id.at_system_solution_player)
    IjkVideoView atSystemSolutionPlayer;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    private WindowManager wm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemolution);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle,getIntent().getStringExtra("title"));

        wm=SystemSolutionActivity.this.getWindowManager();

        List<String> list = new ArrayList<>();
        list.add("视频选集");
        list.add("图文教程");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(VideoListFragment.newInstance(getIntent().getStringExtra(Const.VIDEO_ID)));
        list2.add(WebFragment.newInstance(getIntent().getStringExtra(Const.VIDEO_ID)));
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atSystemSolutionVp.setAdapter(mAdapter);
        atSystemSolutionTb.setTabMode(TabLayout.MODE_FIXED);
        atSystemSolutionTb.setTabGravity(TabLayout.GRAVITY_CENTER);
        atSystemSolutionTb.setupWithViewPager(atSystemSolutionVp);

    }

    @Override
    public void onFragmentInteraction(SolutionVideoResponseBean.DataBean dataBean) {
        atSystemSolutionPlayer.stopPlayback();
        atSystemSolutionPlayer.release(true);
        MyMediaController controller=new MyMediaController(this);
        controller.setListener(new MyMediaController.OnVideoListener() {
            @Override
            public void onPause(boolean pause) {

            }
            @Override
            public void onFullScreen(boolean full) {
                if (full) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    //做自己想做的操作
                }
            }
        });
        controller.attach(atSystemSolutionPlayer);
        atSystemSolutionPlayer.setMediaController(controller);
        atSystemSolutionPlayer.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        atSystemSolutionPlayer.setVideoURI(Uri.parse(dataBean.getVideo()));
        atSystemSolutionPlayer.start();
    }

    @Override
    public void onDataGet(SolutionVideoResponseBean.DataBean dataBean) {

        MyMediaController controller=new MyMediaController(this);
        controller.setListener(new MyMediaController.OnVideoListener() {
            @Override
            public void onPause(boolean pause) {

            }

            @Override
            public void onFullScreen(boolean full) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) atSystemSolutionPlayer.getLayoutParams();
                if (full) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        });
        controller.attach(atSystemSolutionPlayer);
        atSystemSolutionPlayer.setMediaController(controller);
        atSystemSolutionPlayer.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        atSystemSolutionPlayer.setVideoURI(Uri.parse(dataBean.getVideo()));
        atSystemSolutionPlayer.start();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
