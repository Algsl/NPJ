package com.zthx.npj.banner.loader;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class LocalVideoLoader extends VideoLoafer {
    @Override
    public void displayView(Context context, Object path, VideoView view) {
        try {
            view.setBackgroundColor(Color.TRANSPARENT);
            Uri uri=Uri.parse((String) path);
            view.setVideoPath(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
