package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.XTSZResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.tencent.activity.MessageCenterActivity;
import com.zthx.npj.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultPageActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;
    @BindView(R.id.ac_defaultPage_iv)
    ImageView acDefaultPageIv;
    @BindView(R.id.ac_defaultPage_wv)
    WebView acDefaultPageWv;
    @BindView(R.id.ac_title)
    TextView acTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_page);
        ButterKnife.bind(this);

        back(titleBack);
        changeRightImg(acTitleIv, R.drawable.home_theme_message, MessageCenterActivity.class, null);

        final String type = getIntent().getStringExtra("type");
        if (type.equals("1")) {
            changeTitle(acTitle,"农业代办服务");
        } else if (type.equals("2")) {
            changeTitle(acTitle,"农产品易货");
        } else if (type.equals("3")) {
            changeTitle(acTitle,"农品宝");
        }

        DiscoverSubscribe.xtsz(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                XTSZResponseBean bean = GsonUtils.fromJson(result, XTSZResponseBean.class);
                XTSZResponseBean.DataBean data = bean.getData();
                if (data.getIs_zhifu() == 0) {
                    acDefaultPageIv.setVisibility(View.GONE);
                    acDefaultPageWv.setVisibility(View.VISIBLE);
                    String url="";
                    if(type.equals("1")){
                        url = "http://www.nongnet.com/";
                    }else if(type.equals("2")){
                        url = "http://www.jgny.net/";
                    }else if(type.equals("3")){
                        url = "http://www.ha115.com/";
                    }
                    acDefaultPageWv.loadUrl(url);
                    acDefaultPageWv.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    });
                } else {
                    acDefaultPageIv.setVisibility(View.VISIBLE);
                    acDefaultPageWv.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFault(String errorMsg) {
                Log.e("测试", "onFault: " + errorMsg);
            }
        }));
    }
}
