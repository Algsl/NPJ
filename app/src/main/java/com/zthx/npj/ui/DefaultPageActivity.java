package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultPageActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_page);
        ButterKnife.bind(this);

        back(titleBack);
        changeRightImg(acTitleIv,R.drawable.home_theme_message,MessageCenterActivity.class,null);
    }
}
