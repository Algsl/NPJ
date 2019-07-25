package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.adapter.AskForPartnerAdapter;
import com.zthx.npj.net.been.UserAppLogResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AskForPartnerActivity extends ActivityBase {

    @BindView(R.id.at_ask_for_partner_rv)
    RecyclerView atAskForPartnerRv;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;

    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_partner);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"合伙人申请记录");

        getUserAppLog();
    }

    private void getUserAppLog() {
        SetSubscribe.userAppLog(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setUserAppLog(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setUserAppLog(String result) {
        UserAppLogResponseBean bean = GsonUtils.fromJson(result, UserAppLogResponseBean.class);
        ArrayList<UserAppLogResponseBean.DataBean> data = bean.getData();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        atAskForPartnerRv.setLayoutManager(manager);
        AskForPartnerAdapter mAdapter = new AskForPartnerAdapter(this, data);
        atAskForPartnerRv.setAdapter(mAdapter);
    }

}
