package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.img)
    ImageView img1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        back(titleBack);
        String title=getIntent().getStringExtra("title");
        String img=getIntent().getStringExtra("img");

         changeTitle(acTitle,title);

        Glide.with(this).load(Uri.parse(img)).into(img1);
    }
}
