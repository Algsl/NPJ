package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.MyTeamResponseBean;
import com.zthx.npj.net.been.UserResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.CommonDialog;
import com.zthx.npj.view.MyCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTeamActivity extends ActivityBase {
    @BindView(R.id.at_my_achievement_head_pic)
    MyCircleView atMyAchievementHeadPic;
    @BindView(R.id.at_my_achievement_tv_name)
    TextView atMyAchievementTvName;
    @BindView(R.id.at_my_achievement_tv_fudao)
    TextView atMyAchievementTvFudao;
    @BindView(R.id.at_my_achievement_btn_daiyanren)
    Button atMyAchievementBtnDaiyanren;
    @BindView(R.id.at_my_achievement_view_all)
    ProgressBar atMyAchievementViewAll;
    @BindView(R.id.at_my_achievement_view_all_2)
    ProgressBar atMyAchievementViewAll2;
    @BindView(R.id.ac_myAchievement_tv_level)
    TextView acMyAchievementTvLevel;
    @BindView(R.id.ac_myAchievement_tv_totalMyTeam)
    TextView acMyAchievementTvTotalMyTeam;
    @BindView(R.id.at_my_achievement_btn_daiyanren2)
    Button atMyAchievementBtnDaiyanren2;
    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme_img_right)
    ImageView titleThemeImgRight;
    @BindView(R.id.at_my_achievement_ll_1)
    LinearLayout atMyAchievementLl1;
    @BindView(R.id.at_my_achievement_ll_2)
    LinearLayout atMyAchievementLl2;
    @BindView(R.id.at_my_achievement_ll_3)
    LinearLayout atMyAchievementLl3;
    @BindView(R.id.at_my_achievement_ll_4)
    LinearLayout atMyAchievementLl4;


    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_achievement);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的业绩");
        changeRightImg(titleThemeImgRight, R.drawable.myachievement_more, AskForPartnerActivity.class, null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyTeam();
        getUserInfo();
    }

    private void getUserInfo() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
                UserResponseBean.DataBean data = bean.getData();
                switch (data.getLevel()) {
                    case 0:
                        acMyAchievementTvLevel.setText("普通会员");
                        break;
                    case 1:
                        acMyAchievementTvLevel.setText("VIP代言人");
                        break;
                    case 2:
                        acMyAchievementTvLevel.setText("天使代言人");
                        atMyAchievementViewAll.setMax(100);
                        break;
                    case 3:
                        acMyAchievementTvLevel.setText("金牌代言人");
                        atMyAchievementViewAll.setMax(600);
                        break;
                    case 4:
                        acMyAchievementTvLevel.setText("钻石代言人");
                        atMyAchievementViewAll.setMax(3000);
                        break;
                    case 5:
                        acMyAchievementTvLevel.setText("首席代言人");
                        atMyAchievementViewAll.setMax(10000);
                        break;
                    case 6:
                        acMyAchievementTvLevel.setText("天使股东代言人");
                        atMyAchievementViewAll2.setMax(30000);
                        break;
                    case 7:
                        acMyAchievementTvLevel.setText("金牌股东代言人");
                        atMyAchievementViewAll2.setMax(50000);
                        break;
                    case 8:
                        acMyAchievementTvLevel.setText("钻石股东代言人");
                        atMyAchievementViewAll2.setMax(100000);
                        break;
                    case 9:
                        acMyAchievementTvLevel.setText("首席股东代言人");
                        atMyAchievementViewAll2.setMax(200000);
                        break;
                    case 10:
                        acMyAchievementTvLevel.setText("城市代言人");
                        break;
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void getMyTeam() {
        SetSubscribe.myTeam(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MyTeamResponseBean bean = GsonUtils.fromJson(result, MyTeamResponseBean.class);
                MyTeamResponseBean.DataBean data = bean.getData();
                if ((int) data.getStatus() == 1) {
                   CommonDialog dialog= new CommonDialog(MyTeamActivity.this, R.style.dialog, "请先升级成为VIP代言人", new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                openActivity(MembershipPackageActivity.class);
                            }else{
                                finish();
                            }
                        }
                    });
                   dialog.setPositiveButton("升级为代言人");
                   dialog.show();
                } else if ((int) data.getStatus() == 2) {
                    CommonDialog dialog=new CommonDialog(MyTeamActivity.this, R.style.dialog, "请先绑定邀请人！", new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                openActivity(MembershipPackageActivity.class);
                            }else{
                                finish();
                            }
                        }
                    });
                    dialog.setPositiveButton("绑定邀请人");
                    dialog.show();
                } else {
                    setMyTeam(data);
                }
            }

            @Override
            public void onFault(String errorMsg) {
                Toast.makeText(MyTeamActivity.this, errorMsg, Toast.LENGTH_LONG).show();
            }
        }));
    }

    private void setMyTeam(MyTeamResponseBean.DataBean data) {
        Glide.with(MyTeamActivity.this).load(Uri.parse(data.getResult().getHead_img())).into(atMyAchievementHeadPic);
        atMyAchievementTvName.setText("推荐人：" + data.getResult().getNick_name());
        switch ((int) data.getResult().getLevel()) {
            case 0:
                acMyAchievementTvLevel.setText("普通会员");
                break;
            case 1:
                acMyAchievementTvLevel.setText("VIP代言人");
                break;
            case 2:
                acMyAchievementTvLevel.setText("天使代言人");
                break;
            case 3:
                acMyAchievementTvLevel.setText("金牌代言人");
                break;
            case 4:
                acMyAchievementTvLevel.setText("钻石代言人");
                break;
            case 5:
                acMyAchievementTvLevel.setText("首席代言人");
                break;
            case 6:
                acMyAchievementTvLevel.setText("天使股东代言人");
                break;
            case 7:
                acMyAchievementTvLevel.setText("金牌股东代言人");
                break;
            case 8:
                acMyAchievementTvLevel.setText("钻石股东代言人");
                break;
            case 9:
                acMyAchievementTvLevel.setText("首席股东代言人");
                break;
            case 10:
                acMyAchievementTvLevel.setText("城市代言人");
                break;
        }
        acMyAchievementTvTotalMyTeam.setText(data.getResult().getTotal_myteam() + "");
        atMyAchievementViewAll.setProgress((int) data.getResult().getMyteam());
        atMyAchievementViewAll2.setProgress((int) data.getResult().getMyamount());
    }

    @OnClick({R.id.at_my_achievement_btn_daiyanren, R.id.at_my_achievement_btn_daiyanren2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_achievement_btn_daiyanren:
                openActivity(ConfirmAchievementActivity.class);
                break;
            case R.id.at_my_achievement_btn_daiyanren2:
                openActivity(ConfirmAchievementActivity.class);
                break;
        }
    }
}
