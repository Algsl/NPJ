package com.zthx.npj.banner.loader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LocalImageLoader extends ImageLoader {
    @Override
    public void displayView(Context context, Object path, ImageView view) {
        try {
            Glide.with(context).load(Uri.parse((String) path)).into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
