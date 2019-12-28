package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
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
    @BindView(R.id.ac_bannerDetail_wv)
    WebView acBannerDetailWv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        back(titleBack);

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        changeTitle(acTitle, title);


        acBannerDetailWv.loadUrl(url);
        WebSettings settings = acBannerDetailWv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(true);
        acBannerDetailWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    // loadDialog.dismiss();
                    acBannerDetailWv.getSettings().setBlockNetworkImage(false);
                } else {
                    // 网页加载中
                    // loadDialog.show();
                }
            }
        });

    }
}
