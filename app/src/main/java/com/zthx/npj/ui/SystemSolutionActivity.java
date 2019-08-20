package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.base.Const;
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
        /*MyMediaController controller=new MyMediaController(this);
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
        atSystemSolutionPlayer.setMediaController(controller);
        atSystemSolutionPlayer.release(true);
        atSystemSolutionPlayer.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        atSystemSolutionPlayer.setVideoURI(Uri.parse(dataBean.getVideo()));
        atSystemSolutionPlayer.start();*/
        ijkVideoView.setUrl(dataBean.getVideo()); //设置视频地址
        StandardVideoController controller = new StandardVideoController(this);
        ijkVideoView.setVideoController(controller); //设置控制器，如需定制可继承 BaseVideoController
        ijkVideoView.start();
    }

    //自动播放设置
    @Override
    public void onDataGet(SolutionVideoResponseBean.DataBean dataBean) {
        /*MyMediaController controller=new MyMediaController(this);
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
        atSystemSolutionPlayer.start();*/
        ijkVideoView.setUrl(dataBean.getVideo()); //设置视频地址
        StandardVideoController controller = new StandardVideoController(this);
        ijkVideoView.setVideoController(controller); //设置控制器，如需定制可继承 BaseVideoController
        //ijkVideoView.start(); //开始播放，不调用则不自动播放
        //高级设置（可选，须在 start()之前调用方可生效）
        /*PlayerConfig playerConfig = new PlayerConfig.Builder()
                .enableCache() //启用边播边缓存功能
                .autoRotate() //启用重力感应自动进入/退出全屏功能
                .enableMediaCodec()//启动硬解码，启用后可能导致视频黑屏，音画不同步
                .usingSurfaceView() //启用 SurfaceView 显示视频，不调用默认使用 TextureView
                .savingProgress() //保存播放进度
                .disableAudioFocus() //关闭 AudioFocusChange 监听
                .setLooping() //循环播放当前正在播放的视频
                .build();
        ijkVideoView.setPlayerConfig(playerConfig);*/
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
}
