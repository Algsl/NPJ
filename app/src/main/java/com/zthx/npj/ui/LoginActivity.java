package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zthx.npj.R;
import com.zthx.npj.net.netutils.HttpUtils;

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

    public static IWXAPI mWxApi;

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
                registerWx();
                break;
            case R.id.at_login_tv_new:
                break;
            case R.id.at_login_tv_more_login:
                startActivity(new Intent(this, CellPhoneLoginActivity.class));
                break;
        }
    }

    private void registerWx() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, "wx76500efa65d19915", false);
        // 将该app注册到微信
        mWxApi.registerApp("wx76500efa65d19915");
        wxLogin();
    }

    private void wxLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        //像微信发送请求
        LoginActivity.mWxApi.sendReq(req);
    }
}
