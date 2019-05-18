package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.view.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.at_help_ll_question1)
    LinearLayout atHelpLlQuestion1;
    @BindView(R.id.at_help_tv_kefu_tel)
    TextView atHelpTvKefuTel;
    @BindView(R.id.at_help_tv_kefu_online)
    TextView atHelpTvKefuOnline;
    @BindView(R.id.at_help_tv_pingjia)
    TextView atHelpTvPingjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_theme_back, R.id.at_help_ll_question1,R.id.at_help_tv_kefu_tel, R.id.at_help_tv_kefu_online, R.id.at_help_tv_pingjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_theme_back:
                break;
            case R.id.at_help_ll_question1:
                startActivity(new Intent(this, HelpDetailActivity.class));
                break;
            case R.id.at_help_tv_kefu_tel:
                new CommonDialog(this, R.style.dialog, "400-800-1234", new CommonDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {

                    }
                }).show();
                break;
            case R.id.at_help_tv_kefu_online:
                break;
            case R.id.at_help_tv_pingjia:
                break;
        }
    }

}
