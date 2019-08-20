package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.ReferrerResponseBean;
import com.zthx.npj.net.netsubscribe.GiftSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReferrerActivity extends ActivityBase {
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.ac_referrer_mcv_headImg)
    MyCircleView acReferrerMcvHeadImg;
    @BindView(R.id.ac_referrer_tv_nickName)
    TextView acReferrerTvNickName;
    @BindView(R.id.rl_title)
    LinearLayout rlTitle;
    @BindView(R.id.ac_referrer_tv_mobile)
    TextView acReferrerTvMobile;
    @BindView(R.id.ac_referrer_tv_chat)
    TextView acReferrerTvChat;
    @BindView(R.id.ac_referrer_tv_change)
    TextView acReferrerTvChange;
    @BindView(R.id.ac_referrer_tv_level)
    TextView acReferrerTvLevel;


    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private String mobile="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrer);
        ButterKnife.bind(this);
        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的上级");

        getMyReferrer();
    }

    private void getMyReferrer() {
        GiftSubscribe.referrer(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: "+result );
                setMyReferrer(result);
            }

            @Override
            public void onFault(String errorMsg) {
                Toast.makeText(ReferrerActivity.this,"您还没有推荐人",Toast.LENGTH_LONG).show();
            }
        }));
    }

    private void setMyReferrer(String result) {
        ReferrerResponseBean bean = GsonUtils.fromJson(result, ReferrerResponseBean.class);
        Glide.with(this).load(Uri.parse(bean.getData().getHead_img())).into(acReferrerMcvHeadImg);
        acReferrerTvNickName.setText(bean.getData().getNick_name());
        acReferrerTvMobile.setText("手机号" + bean.getData().getMobile());
        switch ((int) bean.getData().getLevel()){
            case 0:acReferrerTvLevel.setText("普通会员");break;
            case 1:acReferrerTvLevel.setText("VIP代言人");break;
            case 2:acReferrerTvLevel.setText("天使代言人");break;
            case 3:acReferrerTvLevel.setText("金牌代言人");break;
            case 4:acReferrerTvLevel.setText("钻石代言人");break;
            case 5:acReferrerTvLevel.setText("首席代言人");break;
            case 6:acReferrerTvLevel.setText("天使股东代言人");break;
            case 7:acReferrerTvLevel.setText("金牌股东代言人");break;
            case 8:acReferrerTvLevel.setText("钻石股东代言人");break;
            case 9:acReferrerTvLevel.setText("首席股东代言人");break;
            case 10:acReferrerTvLevel.setText("城市代言人");break;
        }
        mobile=bean.getData().getMobile();
    }

    @OnClick({R.id.ac_referrer_tv_chat, R.id.ac_referrer_tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_referrer_tv_chat:
                Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+mobile));
                startActivity(intent);
                break;
            case R.id.ac_referrer_tv_change:

                break;
        }
    }
}
