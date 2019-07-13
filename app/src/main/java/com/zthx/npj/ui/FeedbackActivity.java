package com.zthx.npj.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.FeedBackBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.title_theme)
    RelativeLayout titleTheme;
    @BindView(R.id.ac_feedBack_tv_title1)
    TextView acFeedBackTvTitle1;
    @BindView(R.id.ac_feedBack_tv_title2)
    TextView acFeedBackTvTitle2;
    @BindView(R.id.ac_feedBack_tv_title3)
    TextView acFeedBackTvTitle3;
    @BindView(R.id.ac_feedBack_tv_title4)
    TextView acFeedBackTvTitle4;
    @BindView(R.id.ac_feedBack_tv_title5)
    TextView acFeedBackTvTitle5;
    @BindView(R.id.ac_feedBack_tv_title6)
    TextView acFeedBackTvTitle6;
    @BindView(R.id.ac_feedBack_et_description)
    EditText acFeedBackEtDescription;
    @BindView(R.id.ac_feedBack_et_realname)
    EditText acFeedBackEtRealname;
    @BindView(R.id.ac_feedBack_et_mobile)
    EditText acFeedBackEtMobile;
    @BindView(R.id.ac_feedBack_btn_commit)
    Button acFeedBackBtnCommit;

    String user_id= SharePerferenceUtils.getUserId(this);
    String token=SharePerferenceUtils.getToken(this);
    String title="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
    }
    TextView[] tvs={acFeedBackTvTitle1,acFeedBackTvTitle2,acFeedBackTvTitle3,acFeedBackTvTitle4,acFeedBackTvTitle5,acFeedBackTvTitle6};
    private void itemSelect(TextView tv){
        for(int i=0;i<tvs.length;i++){
            if(tv.equals(tvs[i])){
                title=tv.getText().toString();
                tv.setTextColor(Color.RED);
            }else{
                tvs[i].setTextColor(Color.CYAN);
            }
        }
    }

    @OnClick({R.id.ac_feedBack_tv_title1, R.id.ac_feedBack_tv_title2, R.id.ac_feedBack_tv_title3, R.id.ac_feedBack_tv_title4, R.id.ac_feedBack_tv_title5, R.id.ac_feedBack_tv_title6, R.id.ac_feedBack_btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_feedBack_tv_title1:
                itemSelect(acFeedBackTvTitle1);
                break;
            case R.id.ac_feedBack_tv_title2:
                itemSelect(acFeedBackTvTitle2);
                break;
            case R.id.ac_feedBack_tv_title3:
                itemSelect(acFeedBackTvTitle3);
                break;
            case R.id.ac_feedBack_tv_title4:
                itemSelect(acFeedBackTvTitle4);
                break;
            case R.id.ac_feedBack_tv_title5:
                itemSelect(acFeedBackTvTitle5);
                break;
            case R.id.ac_feedBack_tv_title6:
                itemSelect(acFeedBackTvTitle6);
                break;
            case R.id.ac_feedBack_btn_commit:
                FeedBackBean bean=new FeedBackBean();
                bean.setUser_id(user_id);
                bean.setTitle(token);
                bean.setTitle(title);
                bean.setDescription(acFeedBackEtDescription.getText().toString().trim());
                bean.setImg("");
                bean.setRealname(acFeedBackEtRealname.getText().toString().trim());
                bean.setMobile(acFeedBackEtMobile.getText().toString().trim());
                SetSubscribe.feedBack(bean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
                    }

                    @Override
                    public void onFault(String errorMsg) {

                    }
                }));
                break;
        }
    }
}
