package com.zthx.npj.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.zthx.npj.banner.transformer.AccordionTransformer;
import com.zthx.npj.banner.transformer.BackgroundToForegroundTransformer;
import com.zthx.npj.banner.transformer.CubeInTransformer;
import com.zthx.npj.banner.transformer.CubeOutTransformer;
import com.zthx.npj.banner.transformer.DefaultTransformer;
import com.zthx.npj.banner.transformer.DepthPageTransformer;
import com.zthx.npj.banner.transformer.FlipHorizontalTransformer;
import com.zthx.npj.banner.transformer.FlipVerticalTransformer;
import com.zthx.npj.banner.transformer.ForegroundToBackgroundTransformer;
import com.zthx.npj.banner.transformer.RotateDownTransformer;
import com.zthx.npj.banner.transformer.RotateUpTransformer;
import com.zthx.npj.banner.transformer.ScaleInOutTransformer;
import com.zthx.npj.banner.transformer.StackTransformer;
import com.zthx.npj.banner.transformer.TabletTransformer;
import com.zthx.npj.banner.transformer.ZoomInTransformer;
import com.zthx.npj.banner.transformer.ZoomOutSlideTransformer;
import com.zthx.npj.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
