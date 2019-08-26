package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends ActivityBase {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_newsDetail_wv)
    WebView acNewsDetailWv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        back(titleBack);
        Intent intent = getIntent();
        String id = intent.getStringExtra("key0");
        changeTitle(acTitle, "图文详情");

        acNewsDetailWv.loadUrl("http://game.npj-vip.com/h5/news.html?id="+id);

        WebSettings settings = acNewsDetailWv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(true);
        acNewsDetailWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    // loadDialog.dismiss();
                    acNewsDetailWv.getSettings().setBlockNetworkImage(false);
                } else {
                    // 网页加载中
                    // loadDialog.show();
                }
            }
        });
    }
}
