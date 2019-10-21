package com.zthx.npj.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmAchievementActivity extends ActivityBase {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.ac_confirmAchievement_btn_apply)
    Button acConfirmAchievementBtnApply;

    private String user_id=SharePerferenceUtils.getUserId(this);
    private String token=SharePerferenceUtils.getToken(this);
    private String level=SharePerferenceUtils.getUserLevel(this);

    private long type;
    private long appLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_achievement);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的申请");

        type=Long.parseLong(getIntent().getStringExtra("key0"));
        appLevel=Long.parseLong(getIntent().getStringExtra("key1"));
    }

    private void applyUpgrade() {
        SetSubscribe.userApp(user_id,token,type,appLevel,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                showToast("申请升级成功");
                finish();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    @OnClick(R.id.ac_confirmAchievement_btn_apply)
    public void onViewClicked() {
        applyUpgrade();
    }
}
