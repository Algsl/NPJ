package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImgArticalActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_artical);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"图文详情");
    }
}
