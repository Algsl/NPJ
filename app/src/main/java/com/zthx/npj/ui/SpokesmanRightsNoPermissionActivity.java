package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.adapter.SpokesmanQuanLiAdapter;
import com.zthx.npj.net.been.SpokesmanQuanLiResponsebean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpokesmanRightsNoPermissionActivity extends ActivityBase {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.ac_spokesman_mcv_headImg)
    MyCircleView acSpokesmanMcvHeadImg;
    @BindView(R.id.ac_spokesman_tv_userName)
    TextView acSpokesmanTvUserName;
    @BindView(R.id.ac_membership_rv)
    RecyclerView acMembershipRv;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spokesman_rights);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "代言人权益");
        getData();
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
                //showToast(errorMsg);
                SharePerferenceUtils.setUserId(SpokesmanRightsNoPermissionActivity.this, "");
            }
        }));
    }

    private void getData() {
        GiftSubscribe.getSpokesmanQuan(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SpokesmanQuanLiResponsebean spokesmanQuanLiResponsebean = GsonUtils.fromJson(result, SpokesmanQuanLiResponsebean.class);
                ArrayList<SpokesmanQuanLiResponsebean.DataBean> data = spokesmanQuanLiResponsebean.getData();
                LinearLayoutManager manager = new LinearLayoutManager(SpokesmanRightsNoPermissionActivity.this, LinearLayoutManager.VERTICAL, false);
                acMembershipRv.setLayoutManager(manager);
                SpokesmanQuanLiAdapter mAdapter = new SpokesmanQuanLiAdapter(SpokesmanRightsNoPermissionActivity.this, data);
                acMembershipRv.setItemAnimator(new DefaultItemAnimator());
                acMembershipRv.setAdapter(mAdapter);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }
}
