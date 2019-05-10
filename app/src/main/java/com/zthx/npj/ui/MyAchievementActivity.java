package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAchievementActivity extends AppCompatActivity {

    @BindView(R.id.at_my_achievement_iv_shenqing)
    ImageView atMyAchievementIvShenqing;
    @BindView(R.id.at_my_achievement_btn_daiyanren)
    Button atMyAchievementBtnDaiyanren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_achievement);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_my_achievement_iv_shenqing, R.id.at_my_achievement_btn_daiyanren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_my_achievement_iv_shenqing:
                startActivity(new Intent(MyAchievementActivity.this, AskForPartnerActivity.class));
                break;
            case R.id.at_my_achievement_btn_daiyanren:
                startActivity(new Intent(MyAchievementActivity.this,ConfirmAchievementActivity.class));
                break;
        }
    }
}
