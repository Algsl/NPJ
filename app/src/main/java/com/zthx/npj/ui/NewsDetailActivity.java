package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.NeedDetailResponseBean;
import com.zthx.npj.net.been.NewsResponseBean;
import com.zthx.npj.net.netsubscribe.DiscoverSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends ActivityBase {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    @BindView(R.id.ac_newsDetail_tv_title)
    TextView acNewsDetailTvTitle;
    @BindView(R.id.ac_newsDetail_tv_creatTime)
    TextView acNewsDetailTvCreatTime;
    @BindView(R.id.ac_newsDetail_tv_content)
    TextView acNewsDetailTvContent;
    @BindView(R.id.ac_newsDetail_iv_img)
    ImageView acNewsDetailIvImg;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        back(titleBack);
        acTitleIv.setImageResource(R.drawable.goods_detail_share);

        String id=getIntent().getStringExtra("key0");
        getNewsDetail(id);
    }

    private void getNewsDetail(String id) {
        DiscoverSubscribe.newsDetail(id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                NewsResponseBean bean=GsonUtils.fromJson(result,NewsResponseBean.class);
                NewsResponseBean.DataBean data=bean.getData();
                acNewsDetailTvTitle.setText(data.getTitle());
                acNewsDetailTvCreatTime.setText("疑难杂症  "+new SimpleDateFormat("MM月dd日  hh:ss").format(new Date(data.getCreate_time())));
                acNewsDetailTvContent.setText(data.getContent());
                Glide.with(NewsDetailActivity.this).load(Uri.parse(data.getImg())).into(acNewsDetailIvImg);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
