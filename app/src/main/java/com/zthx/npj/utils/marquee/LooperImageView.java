package com.zthx.npj.utils.marquee;


import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;

import java.util.List;

public class LooperImageView extends FrameLayout {
    private List<LooperBean> tipList;
    private int curTipIndex = 0;
    private long lastTimeMillis;
    private static final int ANIM_DELAYED_MILLIONS = 3 * 1000;
    /**
     * 动画持续时长
     */
    private static final int ANIM_DURATION = 1 * 1000;
    private View tv_tip_out, tv_tip_in;
    private Animation anim_out, anim_in;
    private int nowPosition;

    public LooperImageView(Context context) {
        super(context);
        initTipFrame();
        initAnimation();
    }

    public LooperImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTipFrame();
        initAnimation();
    }

    public LooperImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTipFrame();
        initAnimation();
    }

    private void initTipFrame() {
        tv_tip_out = newView();
        tv_tip_in = newView();
        addView(tv_tip_in);
        addView(tv_tip_out);
    }

    private View newView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_looper_image, null);
        return inflate;
    }

    private void initAnimation() {
        anim_out = newAnimation(0, -1);
        anim_in = newAnimation(1, 0);
        anim_in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTipAndPlayAnimationWithCheck();
            }
        });
    }

    private Animation newAnimation(float fromYValue, float toYValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromYValue, Animation.RELATIVE_TO_SELF, toYValue);
        anim.setDuration(ANIM_DURATION);
        anim.setStartOffset(ANIM_DELAYED_MILLIONS);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    private void updateTipAndPlayAnimationWithCheck() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000) {
            return;
        }
        lastTimeMillis = System.currentTimeMillis();
        updateTipAndPlayAnimation();
    }

    private void updateTipAndPlayAnimation() {
        if (curTipIndex % 2 == 0) {
            updateTip(tv_tip_out);
            tv_tip_in.startAnimation(anim_out);
            tv_tip_out.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_in);
        } else {
            updateTip(tv_tip_in);
            tv_tip_out.startAnimation(anim_out);
            tv_tip_in.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_out);
        }
    }


    private void updateTip(View tipView) {
        final ImageView imageView = (ImageView) tipView.findViewById(R.id.image_view);
        Glide.with(tipView.getContext())
                .load(Uri.parse(tipList.get(curTipIndex % tipList.size()).getHeadImg()))
                .into(imageView);
        getNextTip();
    }

    /**
     * 获取下一条消息
     *
     * @return
     */
    private LooperBean getNextTip() {
        if (isListEmpty(tipList)) return null;
        int nextPostion = curTipIndex++ % tipList.size();
        if (tipList.size() == 1) {
            setNowPosition(0);
        } else if (nextPostion == 0) {
            setNowPosition(tipList.size() - 1);
        } else {
            setNowPosition(nextPostion - 1);
        }
        return tipList.get(nextPostion);
    }

    public  boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public void setTipList(List<LooperBean> tipList, int index) {
        this.tipList = tipList;
        curTipIndex = index;
        updateTip(tv_tip_out);
        updateTipAndPlayAnimation();
    }

    private void setNowPosition(int position) {
        this.nowPosition = position;
    }

    private int getNowPosition() {
        return nowPosition;
    }

    public int getNowTip() {
        if (isListEmpty(tipList)) return -1;
        return getNowPosition();
    }
}