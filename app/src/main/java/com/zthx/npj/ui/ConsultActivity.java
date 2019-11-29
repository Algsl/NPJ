package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_consult_wv)
    WebView acConsultWv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        ButterKnife.bind(this);

        String title=getIntent().getStringExtra("key0");
        String url=getIntent().getStringExtra("key1");

        back(titleBack);
        changeTitle(acTitle, title);


        acConsultWv.loadUrl(url);
        WebSettings settings = acConsultWv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(true);
        acConsultWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    // loadDialog.dismiss();
                    acConsultWv.getSettings().setBlockNetworkImage(false);
                } else {
                    // 网页加载中
                    // loadDialog.show();
                }
            }
        });
    }
}
