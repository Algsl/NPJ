package com.zthx.npj.banner.loader;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.dueeeke.videoplayer.player.IjkVideoView;

/*public abstract class VideoLoafer implements ViewLoaderInterface<VideoView> {
    @Override
    public VideoView createView(Context context) {
        VideoView videoView = new VideoView(context);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        videoView.setLayoutParams(layoutParams);
        return videoView;
    }
}*/
public abstract class VideoLoafer implements ViewLoaderInterface<IjkVideoView> {
    @Override
    public IjkVideoView createView(Context context) {
        IjkVideoView videoView=new IjkVideoView(context);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        videoView.setLayoutParams(layoutParams);
        return videoView;
    }
}