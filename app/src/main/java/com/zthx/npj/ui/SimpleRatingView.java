package com.zthx.npj.ui;

import android.content.Context;
import android.widget.ImageView;

import com.zthx.npj.R;
import com.zthx.npj.base.IRatingView;

public class SimpleRatingView implements IRatingView {

    @Override
    public int getStateRes(int posi, int state) {
        int id = R.drawable.xing2;
        switch (state) {
            case STATE_NONE:
                id = R.drawable.xing2;
                break;
            /*case STATE_HALF:
                id = R.drawable.ic_star_half_red_400_36dp;
                break;*/
            case STATE_FULL:
                id = R.drawable.xing1;
                break;
            default:
                break;
        }
        return id;
    }

    @Override
    public int getCurrentState(float rating, int numStars, int position) {
        position++;
        float dis = position - rating;
        if (dis <= 0) {
            return STATE_FULL;
        }
        if (dis == 0.5) {
            return STATE_HALF;
        }
        if (dis > 0.5) {
            return STATE_NONE;
        }
        return 0;
    }


    @Override
    public ImageView getRatingView(Context context, int numStars, int posi) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}