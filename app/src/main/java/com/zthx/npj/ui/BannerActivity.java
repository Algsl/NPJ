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

public class BannerActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_banner_wv)
    WebView acBannerWv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        back(titleBack);

        String title = getIntent().getStringExtra("title");
        String type = getIntent().getStringExtra("type");
        String id = getIntent().getStringExtra("id");
        changeTitle(acTitle, title);

        String url = "http://game.npj-vip.com/h5/banner.html?type="+type+"&id="+id;
        /*acBannerWv.loadUrl(url);
        acBannerWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });*/

        acBannerWv.loadUrl(url);
        WebSettings settings = acBannerWv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(true);
        acBannerWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    // loadDialog.dismiss();
                    acBannerWv.getSettings().setBlockNetworkImage(false);
                } else {
                    // 网页加载中
                    // loadDialog.show();
                }
            }
        });

    }
}
