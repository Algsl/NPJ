package com.zthx.npj.entity;

import android.graphics.Bitmap;

public class PaoMaBean {
    private String mFront;
    private String mBack ; //后面的文字
    private String mUrl ;//包含的链接
    private Bitmap bitmap;

    public PaoMaBean(String mFront, String mBack, String mUrl) {
        this.mFront = mFront;
        this.mBack = mBack;
        this.mUrl = mUrl;
    }

    public PaoMaBean(String mBack, String mUrl) {
        this.mBack = mBack;
        this.mUrl = mUrl;
    }
    public PaoMaBean(String mBack) {
        this.mBack = mBack;
    }
    public PaoMaBean(Bitmap bitmap,String mBack) {
        this.bitmap=bitmap;
        this.mBack = mBack;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getmBack() {
        return mBack;
    }

    public void setmBack(String mBack) {
        this.mBack = mBack;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmFront() {
        return mFront;
    }

    public void setmFront(String mFront) {
        this.mFront = mFront;
    }
}
