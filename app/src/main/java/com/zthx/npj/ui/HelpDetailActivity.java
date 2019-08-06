package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;
import com.zthx.npj.net.been.PDetailResponseBean;
import com.zthx.npj.net.netsubscribe.SetSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpDetailActivity extends ActivityBase{

    @BindView(R.id.title_theme_back)
    ImageView titleThemeBack;
    @BindView(R.id.title_theme_title)
    TextView titleThemeTitle;
    @BindView(R.id.title_theme_tv_right)
    TextView titleThemeTvRight;
    @BindView(R.id.ac_helpDetail_tv_title)
    TextView acHelpDetailTvTitle;
    @BindView(R.id.ac_helpDetail_tv_content)
    TextView acHelpDetailTvContent;

    String user_id= SharePerferenceUtils.getUserId(this);
    String token=SharePerferenceUtils.getToken(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        ButterKnife.bind(this);

        back(titleThemeBack);
        changeTitle(titleThemeTitle,"帮助详情");

        String id=getIntent().getStringExtra("id");
        getDetail(id);
    }

    private void getDetail(String id) {
        SetSubscribe.pdetail(user_id,token,id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                setDetail(result);
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));
    }

    private void setDetail(String result) {
        PDetailResponseBean bean= GsonUtils.fromJson(result,PDetailResponseBean.class);
        acHelpDetailTvTitle.setText(bean.getData().getTitle());
        acHelpDetailTvContent.setText(bean.getData().getContent());
    }
}
