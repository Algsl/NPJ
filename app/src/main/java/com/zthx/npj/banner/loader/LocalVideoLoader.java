package com.zthx.npj.banner.loader;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.zthx.npj.utils.MyCustomUtils;

public class LocalVideoLoader extends VideoLoafer {
    /*@Override
    public void displayView(Context context, Object path, VideoView view) {
        try {
            view.setBackgroundColor(Color.TRANSPARENT);
            Uri uri=Uri.parse((String) path);
            view.setVideoPath(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void displayView(Context context, Object path, IjkVideoView view) {
        try {
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setUrl((String) path);
            StandardVideoController controller = new StandardVideoController(context);
            controller.getThumb().setImageBitmap(MyCustomUtils.getVideoThumbnail((String) path));
            view.setScreenScale(IjkVideoView.SCREEN_SCALE_MATCH_PARENT);
            view.setVideoController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
