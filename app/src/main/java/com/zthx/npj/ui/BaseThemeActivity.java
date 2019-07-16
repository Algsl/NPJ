package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseThemeActivity extends AppCompatActivity {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_theme);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.title_theme_back)
    public void onViewClicked() {
        finish();
    }
}
