package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.adapter.BaojiaUserDetailAdapter;
import com.zthx.npj.net.been.BaojiaUserDetailResponseBean;
import com.zthx.npj.net.been.BaojiaUserListResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaojiaUserDetailActivity extends ActivityBase {


    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_baojia_detail_mv_headImg)
    MyCircleView acBaojiaDetailMvHeadImg;
    @BindView(R.id.ac_baojia_detail_tv_nickName)
    TextView acBaojiaDetailTvNickName;
    @BindView(R.id.ac_baojia_detail_tv_level)
    ImageView acBaojiaDetailTvLevel;
    @BindView(R.id.ac_baojia_detail_tv_day)
    TextView acBaojiaDetailTvDay;
    @BindView(R.id.item_quotation_people_ll)
    LinearLayout itemQuotationPeopleLl;
    @BindView(R.id.ac_baojia_detail_rv)
    RecyclerView acBaojiaDetailRv;
    @BindView(R.id.ac_baojia_tv_seeInfo)
    TextView acBaojiaTvSeeInfo;
    @BindView(R.id.ac_baojia_btn_chat)
    Button acBaojiaBtnChat;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String id = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baojia_detail);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "商家报价");

        String result = getIntent().getStringExtra("result");
        int position = getIntent().getIntExtra("position", 0);

        BaojiaUserListResponseBean bean = GsonUtils.fromJson(result, BaojiaUserListResponseBean.class);
        id = bean.getData().get(position).getId() + "";
        Glide.with(this).load(Uri.parse(bean.getData().get(position).getHead_img())).into(acBaojiaDetailMvHeadImg);
        acBaojiaDetailTvNickName.setText(bean.getData().get(position).getNick_name());
        switch ((int) bean.getData().get(position).getLevel()) {
            case 0:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level0);
                break;
            case 1:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level1);
                break;
            case 2:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level2);
                break;
            case 3:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level3);
                break;
            case 4:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level4);
                break;
            case 5:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level5);
                break;
            case 6:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level6);
                break;
            case 7:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level7);
                break;
            case 8:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level8);
                break;
            case 9:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level9);
                break;
            case 10:
                acBaojiaDetailTvLevel.setImageResource(R.drawable.level10);
                break;
        }

        getBaojiaDetail();
    }

    private void getBaojiaDetail() {
        SetSubscribe.baojiaUserDetail(user_id, token, id, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaojiaUserDetailResponseBean bean = GsonUtils.fromJson(result, BaojiaUserDetailResponseBean.class);
                ArrayList<BaojiaUserDetailResponseBean.DataBean> data = bean.getData();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BaojiaUserDetailActivity.this);
                acBaojiaDetailRv.setLayoutManager(layoutManager);
                BaojiaUserDetailAdapter adapter = new BaojiaUserDetailAdapter(BaojiaUserDetailActivity.this, data);
                acBaojiaDetailRv.setAdapter(adapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.ac_baojia_tv_seeInfo, R.id.ac_baojia_btn_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_baojia_tv_seeInfo:
                openActivity(UserMsgActivity.class);
                break;
            case R.id.ac_baojia_btn_chat:
                break;
        }
    }
}
