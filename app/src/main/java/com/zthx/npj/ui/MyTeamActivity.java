package com.zthx.npj.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.zthx.npj.utils.MyCustomUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import java.text.DecimalFormat;

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
    @BindView(R.id.at_my_achievement_view_all)
    ProgressBar atMyAchievementViewAll;
    @BindView(R.id.at_my_achievement_view_all_2)
    ProgressBar atMyAchievementViewAll2;

    @BindView(R.id.ac_myAchievement_tv_totalMyTeam)
    TextView acMyAchievementTvTotalMyTeam;
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
    @BindView(R.id.ac_tvBg)
    TextView acTvBg;
    @BindView(R.id.ac_myAchievement_tv_totalTeam)
    TextView acMyAchievementTvTotalTeam;
    @BindView(R.id.ac_myAchievement_tv_myTeam)
    TextView acMyAchievementTvMyTeam;
    @BindView(R.id.at_my_achievement_btn_teamDYR)
    Button atMyAchievementBtnTeamDYR;
    @BindView(R.id.at_my_achievement_btn_bossDYR)
    Button atMyAchievementBtnBossDYR;
    @BindView(R.id.at_my_achievement_btn_cityDYR)
    Button atMyAchievementBtnCityDYR;
    @BindView(R.id.ac_myAchievement_tv_teamNow)
    TextView acMyAchievementTvTeamNow;
    @BindView(R.id.ac_myAchievement_tv_teamNext)
    TextView acMyAchievementTvTeamNext;
    @BindView(R.id.ac_myAchievement_tv_bossNow)
    TextView acMyAchievementTvBossNow;
    @BindView(R.id.ac_myAchievement_tv_bossNext)
    TextView acMyAchievementTvBossNext;
    @BindView(R.id.ac_myAchievement_tv_totalBoss)
    TextView acMyAchievementTvTotalBoss;
    @BindView(R.id.ac_myAchievement_tv_myBoss)
    TextView acMyAchievementTvMyBoss;
    @BindView(R.id.ac_tvBg1)
    TextView acTvBg1;
    @BindView(R.id.ac_myAchievement_Iv_level)
    ImageView acMyAchievementIvLevel;


    private String user_id = SharePerferenceUtils.getUserId(this);
    private String token = SharePerferenceUtils.getToken(this);
    private int total_team;
    private int total_boss;
    private int my_team;
    private int my_boss;

    private int type;
    private int appLevel;

    private static final String TAG = "测试";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_achievement);
        ButterKnife.bind(this);

        getUserInfo();


        back(titleThemeBack);
        changeTitle(titleThemeTitle, "我的业绩");
        changeRightImg(titleThemeImgRight, R.drawable.myachievement_more, AskForPartnerActivity.class, null);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getUserInfo() {
        SetSubscribe.getUserInfo(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserResponseBean bean = GsonUtils.fromJson(result, UserResponseBean.class);
                UserResponseBean.DataBean data = bean.getData();
                switch (data.getTeam_level()) {
                    case 0:
                        appLevel = 1;
                        acMyAchievementTvTeamNow.setText("无");
                        acMyAchievementTvTeamNext.setText("天使代言人");
                        total_team = 100;
                        break;
                    case 1:
                        appLevel = 2;
                        acMyAchievementTvTeamNow.setText("天使代言人");
                        acMyAchievementTvTeamNext.setText("金牌代言人");
                        total_team = 600;
                        break;
                    case 2:
                        appLevel = 3;
                        acMyAchievementTvTeamNow.setText("金牌代言人");
                        acMyAchievementTvTeamNext.setText("钻石代言人");
                        total_team = 3000;
                        break;
                    case 3:
                        appLevel = 4;
                        acMyAchievementTvTeamNow.setText("钻石代言人");
                        acMyAchievementTvTeamNext.setText("首席代言人");
                        total_team = 10000;
                        break;
                }

                switch (data.getBoss_level()) {
                    case 0:
                        appLevel = 1;
                        acMyAchievementTvBossNow.setText("无");
                        acMyAchievementTvBossNext.setText("天使股东代言人");
                        total_boss = 30000;
                        break;
                    case 1:
                        appLevel = 2;
                        acMyAchievementTvBossNow.setText("天使股东代言人");
                        acMyAchievementTvBossNext.setText("金牌股东代言人");
                        total_boss = 50000;
                        break;
                    case 2:
                        appLevel = 3;
                        acMyAchievementTvBossNow.setText("金牌股东代言人");
                        acMyAchievementTvBossNext.setText("钻石股东代言人");
                        total_boss = 100000;
                        break;
                    case 3:
                        appLevel = 4;
                        acMyAchievementTvBossNow.setText("钻石股东代言人");
                        acMyAchievementTvBossNext.setText("首席股东代言人");
                        total_boss = 200000;
                        break;
                }
                atMyAchievementViewAll.setMax(total_team);
                atMyAchievementViewAll2.setMax(total_boss);

                getMyTeam();
            }

            @Override
            public void onFault(String errorMsg) {
                SharePerferenceUtils.setUserId(MyTeamActivity.this, "");
            }
        }));
    }

    private void getMyTeam() {
        SetSubscribe.myTeam(user_id, token, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MyTeamResponseBean bean = GsonUtils.fromJson(result, MyTeamResponseBean.class);
                MyTeamResponseBean.DataBean data = bean.getData();
                setMyTeam(data);
                /*if ((int) data.getStatus() == 1) {
                    CommonDialog dialog = new CommonDialog(MyTeamActivity.this, R.style.dialog, "请先升级成为VIP代言人", new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                openActivity(MembershipPackageActivity.class);
                            } else {
                                finish();
                            }
                        }
                    });
                    dialog.setPositiveButton("升级为代言人");
                    dialog.show();
                } else if ((int) data.getStatus() == 2) {
                    CommonDialog dialog = new CommonDialog(MyTeamActivity.this, R.style.dialog, "请先绑定邀请人！", new CommonDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                openActivity(MembershipPackageActivity.class);
                            } else {
                                finish();
                            }
                        }
                    });
                    dialog.setPositiveButton("绑定邀请人");
                    dialog.show();
                } else {

                }*/

            }

            @Override
            public void onFault(String errorMsg) {
                Toast.makeText(MyTeamActivity.this, errorMsg, Toast.LENGTH_LONG).show();
            }
        }));
    }

    private void setMyTeam(MyTeamResponseBean.DataBean data) {
        //设置推荐人信息
        Glide.with(MyTeamActivity.this).load(Uri.parse(data.getResult().getHead_img())).into(atMyAchievementHeadPic);
        atMyAchievementTvName.setText("推荐人：" + data.getResult().getNick_name());//推荐人
        acMyAchievementTvTotalMyTeam.setText(data.getResult().getTotal_myteam() + "");//辅导总人数

        my_team = (int) data.getResult().getMyteam();
        my_boss = (int) Double.parseDouble(data.getResult().getMyamount());

        //设置团队代言人

        //设置人数进度
        atMyAchievementViewAll.setProgress((int) data.getResult().getMyteam());

        acMyAchievementTvMyTeam.setText(data.getResult().getMyteam() + "人");//设置当前人数
        acMyAchievementTvTotalTeam.setText(total_team + "");//设置总人数

        //设置团队代言人标识
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) acTvBg.getLayoutParams();
        layoutParams.width = (int) (data.getResult().getMyteam() * 10.0 / 10 / total_team * acTvBg.getMeasuredWidth());
        acTvBg.setLayoutParams(layoutParams);


        //股东代言人
        //设置消费进度
        atMyAchievementViewAll2.setProgress((int) Double.parseDouble(data.getResult().getMyamount()));

        acMyAchievementTvMyBoss.setText(new DecimalFormat("0.0").format(Double.parseDouble(data.getResult().getMyamount())) + "元");//设置当前消费
        acMyAchievementTvTotalBoss.setText((total_boss) + "元");//设置总消费

        //设置股东代言人标识
        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) acTvBg1.getLayoutParams();
        layoutParams1.width = (int) (Double.parseDouble(data.getResult().getMyamount()) / total_boss * acTvBg1.getMeasuredWidth());
        Log.e("测试", "setMyTeam: " + layoutParams1.width + " " + acTvBg1.getMeasuredWidth() + " " + acTvBg1.getMeasuredHeight() + " " + (int) (Double.parseDouble(data.getResult().getMyamount()) / total_boss));
        acTvBg1.setLayoutParams(layoutParams1);

        MyCustomUtils.showLevelImg(data.getResult().getCity_level(),data.getResult().getBoss_level(),data.getResult().getTeam_level(),data.getResult().getLevel(),acMyAchievementIvLevel);
    }

    @OnClick({R.id.at_my_achievement_btn_teamDYR, R.id.at_my_achievement_btn_bossDYR, R.id.at_my_achievement_btn_cityDYR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_achievement_btn_teamDYR:
                type = 1;
                if (my_team >= total_team) {
                    openActivity(ConfirmAchievementActivity.class, type + "", appLevel + "");
                } else {
                    showToast("团队人数不足，还差" + (total_team - my_team) + "人才可升级");
                }
                break;
            case R.id.at_my_achievement_btn_bossDYR:
                type = 2;
                if (my_boss >= total_boss) {
                    openActivity(ConfirmAchievementActivity.class, type + "", appLevel + "");
                } else {
                    showToast("消费不足，还差" + (total_boss - my_boss) + "元才可升级");
                }
                break;
            case R.id.at_my_achievement_btn_cityDYR:
                openActivity(ConfirmAchievementActivity.class, "3", "1");
                break;
        }
    }
}
