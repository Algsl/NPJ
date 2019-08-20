package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpokesmanRightsNoPermissionActivity extends ActivityBase {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_spokesman_mcv_headImg)
    MyCircleView acSpokesmanMcvHeadImg;
    @BindView(R.id.ac_spokesman_tv_userName)
    TextView acSpokesmanTvUserName;
    @BindView(R.id.ac_spokesman_tv_addGift)
    TextView acSpokesmanTvAddGift;
    @BindView(R.id.ac_spokesman_tv_ruzhu)
    TextView acSpokesmanTvRuzhu;
    @BindView(R.id.ac_spokesman_tv_shopping)
    TextView acSpokesmanTvShopping;
    @BindView(R.id.ac_spokesman_tv_generateHB)
    TextView acSpokesmanTvGenerateHB;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spokesman_rights);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "代言人权益");

        getUserInfo();
    }

    private void getUserInfo() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
                UserResponseBean.DataBean data = bean.getData();
                Glide.with(SpokesmanRightsNoPermissionActivity.this).load(Uri.parse(data.getHead_img())).into(acSpokesmanMcvHeadImg);
                acSpokesmanTvUserName.setText(data.getNick_name());
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick({R.id.ac_spokesman_tv_addGift, R.id.ac_spokesman_tv_ruzhu, R.id.ac_spokesman_tv_shopping, R.id.ac_spokesman_tv_generateHB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_spokesman_tv_addGift:
                break;
            case R.id.ac_spokesman_tv_ruzhu:
                break;
            case R.id.ac_spokesman_tv_shopping:
                break;
            case R.id.ac_spokesman_tv_generateHB:
                openActivity(HaiBaoActivity.class);
                break;
        }
    }
}
