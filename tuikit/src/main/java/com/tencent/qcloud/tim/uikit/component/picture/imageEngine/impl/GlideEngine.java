package com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestListener;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.ImageEngine;

import java.util.concurrent.ExecutionException;


public class GlideEngine implements ImageEngine {


    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        /*Glide.with(context)
                .asBitmap() // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .override(resize, resize)
                        .placeholder(placeholder)
                        .centerCrop())
                .into(imageView);*/
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .placeholder(placeholder)
                .override(resize,resize)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView,
                                 Uri uri) {
        /*Glide.with(context)
                .asBitmap() // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .override(resize, resize)
                        .placeholder(placeholder)
                        .centerCrop())
                .into(imageView);*/
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .placeholder(placeholder)
                .override(resize,resize)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        /*Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                        .fitCenter())
                .into(imageView);*/
        Glide.with(context)
                .load(uri)
                .override(resizeX,resizeY)
                .centerCrop()
                .into(imageView);
    }


    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        /*Glide.with(context)
                .asGif()
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                        .fitCenter())
                .into(imageView);*/
        Glide.with(context)
                .load(uri)
                .asGif()
                .override(resizeX,resizeY)
                .priority(Priority.HIGH)
                .fitCenter()
                .into(imageView);
    }


    @Override
    public boolean supportAnimatedGif() {
        return true;
    }

    public static void loadCornerImage(final ImageView imageView, String filePath, RequestListener listener, final float radius) {
        CornerTransform transform = new CornerTransform(TUIKit.getAppContext(), radius);
        ColorDrawable drawable = new ColorDrawable(Color.GRAY);
        /*RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(drawable)
                .transform(transform);
        Glide.with(TUIKit.getAppContext())
                .load(filePath)
                .apply(options)
                .listener(listener)
                .into(imageView);*/
        Glide.with(TUIKit.getAppContext())
                .load(filePath)
                .centerCrop()
                .placeholder(drawable)
                .listener(listener)
                .into(imageView);
      /*Glide.with(TUIKit.getAppContext()).load(filePath).asBitmap().centerCrop().placeholder(drawable).listener(listener).into(new SimpleTarget<Bitmap>() {
          @Override
          public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
              imageView.setImageBitmap(toRoundCorner(resource, (int) radius));
          }
      });*/
    }

    public static void loadImage(ImageView imageView, String filePath, RequestListener listener) {
        Glide.with(TUIKit.getAppContext())
                .load(filePath)
                .listener(listener)
                .into(imageView);
    }

    public static void loadProfileImage(ImageView imageView, String filePath, RequestListener listener) {
        /*Glide.with(TUIKit.getAppContext())
                .load(filePath)
                .listener(listener)
                .apply(new RequestOptions().error(R.drawable.default_user_icon))
                .into(imageView);*/
        Glide.with(TUIKit.getAppContext())
                .load(filePath)
                .listener(listener)
                .error(R.drawable.default_user_icon)
                .into(imageView);
    }

    public static void clear(ImageView imageView) {
        //Glide.with(TUIKit.getAppContext()).clear(imageView);
    }

    public static void loadImage(ImageView imageView, Uri uri) {
        if (uri == null) {
            return;
        }
        /*Glide.with(TUIKit.getAppContext())
                .load(uri)
                .apply(new RequestOptions().error(R.drawable.default_user_icon))
                .into(imageView);*/
        Glide.with(TUIKit.getAppContext())
                .load(uri)
                .error(R.drawable.default_user_icon)
                .into(imageView);
    }

    public static void loadImage(String filePath, String url) {
        /*try {
            File file = Glide.with(TUIKit.getAppContext()).asFile().load(url).submit().get();
            File destFile = new File(filePath);
            file.renameTo(destFile);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        Log.e("测试", "loadImage: "+filePath+" "+url );
    }

    public static Bitmap loadBitmap(String imageUrl, int targetImageSize) throws InterruptedException, ExecutionException {
        if (TextUtils.isEmpty(imageUrl)) {
            return null;
        }
       /* return Glide.with(TUIKit.getAppContext()).asBitmap()
                .load(imageUrl)
                .apply(new RequestOptions().error(R.drawable.default_user_icon))
                .into(targetImageSize, targetImageSize)
                .get();*/
        return Glide.with(TUIKit.getAppContext())
                .load(imageUrl)
                .asBitmap()
                .error(R.drawable.default_user_icon)
                .into(targetImageSize,targetImageSize)
                .get();
    }


    public static   Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
