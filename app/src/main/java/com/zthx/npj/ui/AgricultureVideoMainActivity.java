package com.zthx.npj.ui;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.zthx.npj.R;
import com.zthx.npj.adapter.DiscoverViewPagerAdapter;
import com.zthx.npj.base.BaseConstant;
import com.zthx.npj.base.Const;
import com.zthx.npj.net.been.AkVideoResponseBean;
import com.zthx.npj.net.been.UploadVideoCommentResponseBean;
import com.zthx.npj.net.been.VideoInfoResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.ui.fragment.JianjieFragment;
import com.zthx.npj.ui.fragment.SelectVideoFragment;
import com.zthx.npj.ui.fragment.VideoCommentFragment;
import com.zthx.npj.ui.fragment.WebFragment;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgricultureVideoMainActivity extends ActivityBase implements WebFragment.OnFragmentInteractionListener, SelectVideoFragment.OnFragmentInteractionListener {

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
    @BindView(R.id.back)
    ImageView back;

    private String videoUrl = "";
    private String videoId;
    private String id;
    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_video_main);
        ButterKnife.bind(this);

        back(back);

        id = getIntent().getStringExtra(Const.VIDEO_ID);//视频id

        List<String> list = new ArrayList<>();
        list.add("选集");
        list.add("简介");
        list.add("评论");
        List<Fragment> list2 = new ArrayList<>();
        list2.add(SelectVideoFragment.newInstance(id));
        list2.add(JianjieFragment.newInstance(id));
        list2.add(new VideoCommentFragment().newInstance(id));
        DiscoverViewPagerAdapter mAdapter = new DiscoverViewPagerAdapter(getSupportFragmentManager(), this, list, list2);
        atAvmVp.setAdapter(mAdapter);
        atAvmTb.setTabMode(TabLayout.MODE_FIXED);
        atAvmTb.setTabGravity(TabLayout.GRAVITY_CENTER);
        atAvmTb.setupWithViewPager(atAvmVp);

        atAkVideoEtComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    uploadComment(id);
                }
                return false;
            }
        });
    }

    private void uploadComment(String id) {
        if (user_id.equals("")) {
            CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        startActivity(new Intent(AgricultureVideoMainActivity.this, LoginActivity.class));
                    }
                }
            });
            dialog.setTitle("提示");
            dialog.setPositiveButton("去登录");
            dialog.show();
        } else {
            DiscoverSubscribe.uploadComment(id, atAkVideoEtComment.getText().toString().trim(), SharePerferenceUtils.getUserId(this), BaseConstant.TOKEN, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                @Override
                public void onSuccess(String result) {
                    UploadVideoCommentResponseBean uploadVideoCommentResponseBean = GsonUtils.fromJson(result, UploadVideoCommentResponseBean.class);
                    int status = uploadVideoCommentResponseBean.getData().getStatus();
                    if (status == 2) {
                        Toast.makeText(AgricultureVideoMainActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AgricultureVideoMainActivity.this, "请先购买课程", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFault(String errorMsg) {

                }
            }));
        }

    }


    private void getVideoInfo(final AkVideoResponseBean.DataBean dataBean, final String type) {
        DiscoverSubscribe.getVideoInfo(dataBean.getId() + "", dataBean.getStatus() + "", SharePerferenceUtils.getUserId(this),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) throws IOException {
                        VideoInfoResponseBean videoInfoResponseBean = GsonUtils.fromJson(result, VideoInfoResponseBean.class);
                        videoUrl = videoInfoResponseBean.getData().getVideo();
                        switch (type) {
                            case "2":
                                atAkVideoPlayer.setUrl(videoUrl); //设置视频地址
                                atAkVideoPlayer.setTitle(dataBean.getTitle());
                                final StandardVideoController controller = new StandardVideoController(AgricultureVideoMainActivity.this);
                                controller.getThumb().setImageBitmap(MyCustomUtils.getVideoThumbnail(videoUrl));
                                /*Glide.with(AgricultureVideoMainActivity.this).load(Uri.parse("http://app.npj-vip.com/public/upload/20191120/b22c7dffe5897cc012245e8fb9f6e9f6.jpg")).asBitmap().into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        controller.getThumb().setImageBitmap(resource);
                                    }
                                });*/
                                /*try {
                                    Bitmap bitmap=Glide.with(AgricultureVideoMainActivity.this).asBitmap().load(Uri.parse("http://app.npj-vip.com/public/upload/20191120/b22c7dffe5897cc012245e8fb9f6e9f6.jpg")).submit().get();
                                    controller.getThumb().setImageBitmap(bitmap);
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }*/
                                atAkVideoPlayer.setScreenScale(IjkVideoView.SCREEN_SCALE_MATCH_PARENT);
                                atAkVideoPlayer.setVideoController(controller); //设置控制器，如需定制可继承 BaseVideoController
                                break;
                            case "1":
                                atAkVideoPlayer.release();
                                atAkVideoPlayer.setUrl(videoUrl); //设置视频地址
                                atAkVideoPlayer.setTitle(dataBean.getTitle());
                                StandardVideoController controller1 = new StandardVideoController(AgricultureVideoMainActivity.this);
                                //controller1.getThumb().setImageBitmap(MyCustomUtils.getVideoThumbnail(videoUrl));
                                atAkVideoPlayer.setScreenScale(IjkVideoView.SCREEN_SCALE_MATCH_PARENT);
                                atAkVideoPlayer.setVideoController(controller1); //设置控制器，如需定制可继承 BaseVideoController
                                atAkVideoPlayer.start();
                                break;
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        videoUrl = "";
                        videoId = dataBean.getList_id() + "";
                        CommonDialog dialog = new CommonDialog(AgricultureVideoMainActivity.this, R.style.dialog, "请先购买该课程", new CommonDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if (confirm) {
                                    buyLisense(videoId);
                                } else {
                                    finish();
                                }
                            }
                        });
                        dialog.setPositiveButton("去购买");
                        dialog.show();
                    }
                }));
    }

    @OnClick({R.id.at_ak_video_player, R.id.at_ak_video_btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_ak_video_player:
                break;
            case R.id.at_ak_video_btn_buy:
                uploadComment(id);
                break;
        }
    }

    private void buyLisense(String videoId) {
        DiscoverSubscribe.buyVideo(videoId, user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Intent intent = new Intent(AgricultureVideoMainActivity.this, VideoBuyConfirmActivity.class);
                intent.putExtra(Const.VIDEO_BUY_INFO, result);
                startActivity(intent);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }, this));
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        atAkVideoPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atAkVideoPlayer.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        atAkVideoPlayer.release();
    }


    @Override
    public void onBackPressed() {
        if (!atAkVideoPlayer.onBackPressed()) {
            super.onBackPressed();
        }
    }

    //手动播放
    @Override
    public void onFragmentInteraction(AkVideoResponseBean.DataBean dataBean) {
        if (user_id.equals("")) {
            CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        startActivity(new Intent(AgricultureVideoMainActivity.this, LoginActivity.class));
                    }
                }
            });
            dialog.setTitle("提示");
            dialog.setPositiveButton("去登录");
            dialog.show();
        } else {
            getVideoInfo(dataBean, "1");
        }

    }

    //自动播放
    @Override
    public void onDataGet(AkVideoResponseBean.DataBean dataBean) {
        if (user_id.equals("")) {
            CommonDialog dialog = new CommonDialog(this, R.style.dialog, "用户未登录", false, new CommonDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        startActivity(new Intent(AgricultureVideoMainActivity.this, LoginActivity.class));
                    }
                }
            });
            dialog.setTitle("提示");
            dialog.setPositiveButton("去登录");
            dialog.show();
        } else {
            getVideoInfo(dataBean, "2");
        }
    }


}
