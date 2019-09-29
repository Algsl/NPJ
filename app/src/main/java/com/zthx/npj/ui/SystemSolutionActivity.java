package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.SolutionVideoResponseBean;
import com.zthx.npj.ui.fragment.VideoListFragment;
import com.zthx.npj.ui.fragment.WebFragment;
import com.zthx.npj.utils.MyCustomUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SystemSolutionActivity extends ActivityBase implements WebFragment.OnFragmentInteractionListener,VideoListFragment.OnFragmentInteractionListener{

    @BindView(R.id.at_system_solution_tb)
    TabLayout atSystemSolutionTb;
    @BindView(R.id.at_system_solution_vp)
    ViewPager atSystemSolutionVp;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.player)
    IjkVideoView ijkVideoView;

    private WindowManager wm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemolution);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, getIntent().getStringExtra("title"));

        wm = SystemSolutionActivity.this.getWindowManager();

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

    //点击播放设置
    @Override
    public void onFragmentInteraction(SolutionVideoResponseBean.DataBean dataBean) {
        ijkVideoView.release();
        ijkVideoView.setUrl(dataBean.getVideo()); //设置视频地址
        ijkVideoView.setTitle(dataBean.getTitle());
        StandardVideoController controller = new StandardVideoController(this);
        ijkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_MATCH_PARENT);
        ijkVideoView.setVideoController(controller); //设置控制器，如需定制可继承 BaseVideoController
        ijkVideoView.start();
    }

    //自动播放设置
    @Override
    public void onDataGet(SolutionVideoResponseBean.DataBean dataBean) {
        ijkVideoView.setUrl(dataBean.getVideo()); //设置视频地址
        ijkVideoView.setTitle(dataBean.getTitle());
        StandardVideoController controller = new StandardVideoController(this);
        controller.getThumb().setImageBitmap(MyCustomUtils.getVideoThumbnail(dataBean.getVideo()));
        ijkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_MATCH_PARENT);
        ijkVideoView.setVideoController(controller); //设置控制器，如需定制可继承 BaseVideoController
    }
    @Override
    protected void onPause() {
        super.onPause();
        ijkVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ijkVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkVideoView.release();
    }


    @Override
    public void onBackPressed() {
        if (!ijkVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
