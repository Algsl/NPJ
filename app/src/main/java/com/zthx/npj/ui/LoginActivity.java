package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.at_login_logo)
    ImageView atLoginLogo;
    @BindView(R.id.at_login_iv_head_text)
    ImageView atLoginIvHeadText;
    @BindView(R.id.at_login_ll_weixin)
    LinearLayout atLoginLlWeixin;
    @BindView(R.id.at_login_tv_new)
    TextView atLoginTvNew;
    @BindView(R.id.at_login_tv_more_login)
    TextView atLoginTvMoreLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_login_ll_weixin, R.id.at_login_tv_new, R.id.at_login_tv_more_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_login_ll_weixin:
                break;
            case R.id.at_login_tv_new:
                break;
            case R.id.at_login_tv_more_login:
                startActivity(new Intent(this, CellPhoneLoginActivity.class));
                break;
        }
    }
}
