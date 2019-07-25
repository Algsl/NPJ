package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.media.IRenderView;
import com.zthx.npj.media.IjkVideoView;
import com.zthx.npj.net.been.AkVideoResponseBean;
import com.zthx.npj.net.been.UploadVideoCommentResponseBean;
import com.zthx.npj.net.been.VideoInfoResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.fragment.SelectVideoFragment;
import com.zthx.npj.ui.fragment.VideoCommentFragment;
import com.zthx.npj.ui.fragment.WebFragment;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgricultureVideoMainActivity extends ActivityBase implements SelectVideoFragment.OnFragmentInteractionListener {

    @BindView(R.id.at_avm_tb)
    TabLayout atAvmTb;
    @BindView(R.id.at_avm_vp)
    ViewPager atAvmVp;
    @BindView(R.id.at_ak_video_btn_buy)
    Button atAkVideoBtnBuy;
    @BindView(R.id.at_ak_video_player)
    IjkVideoView atAkVideoPlayer;
    @BindView(R.id.at_ak_video_et_comment)
    EditText atAkVideoEtComment;

    private String videoUrl = "";
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_video_main);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra(Const.VIDEO_ID);
        List<String> list = new ArrayList<>();
        list.add("选集");
        list.add("简介");
        list.add("评论");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(SelectVideoFragment.newInstance(id));
        list2.add(SelectVideoFragment.newInstance(id));
        list2.add(new VideoCommentFragment());
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(),this, list, list2);
        atAvmVp.setAdapter(mAdapter);
        atAvmTb.setTabMode(TabLayout.MODE_FIXED);
        atAvmTb.setTabGravity(TabLayout.GRAVITY_CENTER);
        atAvmTb.setupWithViewPager(atAvmVp);

        atAkVideoEtComment.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent != null && KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    uploadComment(videoId);
                    return true;
                }
                return false;
            }
        });
    }

    private void uploadComment(String id) {
        DiscoverSubscribe.uploadComment(id,atAkVideoEtComment.getText().toString().trim(),SharePerferenceUtils.getUserId(this),
                BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {

                        UploadVideoCommentResponseBean uploadVideoCommentResponseBean = GsonUtils.fromJson(result, UploadVideoCommentResponseBean.class);
                        int status = uploadVideoCommentResponseBean.getData().getStatus();
                        if (status == 2) {
                            Toast.makeText(AgricultureVideoMainActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AgricultureVideoMainActivity.this,"请先购买课程",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
    }


    @Override
    public void onFragmentInteraction(AkVideoResponseBean.DataBean dataBean) {
        getVideoInfo(dataBean);
        videoId = dataBean.getList_id()+"";
    }

    @Override
    public void onDataGet(AkVideoResponseBean.DataBean dataBean) {
        getVideoInfo(dataBean);
        videoId = dataBean.getList_id()+"";
    }

    private void getVideoInfo(AkVideoResponseBean.DataBean dataBean) {
        DiscoverSubscribe.getVideoInfo(dataBean.getId() + "", dataBean.getStatus() + "", SharePerferenceUtils.getUserId(this),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        VideoInfoResponseBean videoInfoResponseBean = GsonUtils.fromJson(result, VideoInfoResponseBean.class);
                        videoUrl = videoInfoResponseBean.getData().getVideo();
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        videoUrl = "";
                    }
                }));
    }

    @OnClick({R.id.at_ak_video_player, R.id.at_ak_video_btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_ak_video_player:
                if ("".equals(videoUrl)) {
                    Toast.makeText(this, "请先购买课程", Toast.LENGTH_SHORT).show();
                } else {
                    if (atAkVideoPlayer.isPlaying()) {
                        atAkVideoPlayer.pause();
                        atAkVideoPlayer.release(true);
                    }
                    atAkVideoPlayer.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
                    atAkVideoPlayer.setVideoURI(Uri.parse(videoUrl));
                    atAkVideoPlayer.start();
                }
                break;
            case R.id.at_ak_video_btn_buy:
                buyLisense(videoId);
                break;
        }
    }

    private void buyLisense(String videoId) {
        DiscoverSubscribe.buyVideo(videoId, SharePerferenceUtils.getUserId(this),BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Intent intent = new Intent(AgricultureVideoMainActivity.this, VideoBuyConfirmActivity.class);
                intent.putExtra(Const.VIDEO_BUY_INFO,result);
                startActivity(intent);

            }

            @Override
            public void onFault(String errorMsg) {

            }
        },this));
    }
}
