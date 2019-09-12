package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Splash2Activity extends ActivityBase {
    @BindView(R.id.skip)
    Button skip;
    @BindView(R.id.ac_splash2_iv_img)
    ImageView acSplash2IvImg;
    private CountDownTimer cDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        ButterKnife.bind(this);
        startClock();
    }

    private void startClock() {
        cDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                skip.setText("跳过 " + l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                skip.setText("跳过 " + 0 + "s");
                openActivity(MainActivity.class);
                finish();
            }
        }.start();
    }


    @OnClick(R.id.skip)
    public void onViewClicked() {
        openActivity(MainActivity.class);
        finish();
        cDownTimer.cancel();
    }
}
