package com.zthx.npj.banner.loader;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

public abstract class VideoLoafer implements ViewLoaderInterface<VideoView> {
    @Override
    public VideoView createView(Context context) {
        VideoView videoView = new VideoView(context);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        videoView.setLayoutParams(layoutParams);
        return videoView;
    }
}
