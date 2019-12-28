package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WXPayFinishActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_orderFinish_tv_goodsName)
    TextView acOrderFinishTvGoodsName;
    @BindView(R.id.ac_orderFinish_tv_seeOrder)
    TextView acOrderFinishTvSeeOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxfinish);
        ButterKnife.bind(this);

        titleThemeBack.setVisibility(View.GONE);
        String title=getIntent().getStringExtra("title");
        String content=getIntent().getStringExtra("content");
        String type=getIntent().getStringExtra("type");
        titleThemeTitle.setText(title);
        acOrderFinishTvGoodsName.setText(content);

        acOrderFinishTvSeeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(MainActivity.class);
            }
        });
    }
}
