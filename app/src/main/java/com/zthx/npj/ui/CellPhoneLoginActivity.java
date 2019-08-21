package com.zthx.npj.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zthx.npj.R;
import com.zthx.npj.net.been.AuthLoginBean;
import com.zthx.npj.net.been.AuthLoginByMoBileBean;
import com.zthx.npj.net.been.AuthLoginByMobileResponseBean;
import com.zthx.npj.net.been.AuthLoginResponseBean;
import com.zthx.npj.net.been.MsgCodeResponseBeen;
import com.zthx.npj.net.been.PhoneLoginBean;
import com.zthx.npj.net.been.PhoneLoginResponseBean;
import com.zthx.npj.net.netsubscribe.LoginSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;
import com.zthx.npj.view.MyCircleView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CellPhoneLoginActivity extends ActivityBase {


    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_cellphone_login_iv_delete)
    ImageView atCellphoneLoginIvDelete;
    @BindView(R.id.at_cellphone_login_tv_get_code)
    TextView atCellphoneLoginTvGetCode;
    @BindView(R.id.at_cellphone_login_btn_next)
    Button atCellphoneLoginBtnNext;
    @BindView(R.id.at_cellphone_login_et_phone)
    EditText atCellphoneLoginEtPhone;
    @BindView(R.id.at_cellphone_login_et_code)
    EditText atCellphoneLoginEtCode;
    @BindView(R.id.at_cellphone_login_iv_head_pic)
    MyCircleView atCellphoneLoginIvHeadPic;
    @BindView(R.id.at_cellphone_login_tv_third_name)
    TextView atCellphoneLoginTvThirdName;
    @BindView(R.id.at_cellphone_login_tv_hint)
    TextView atCellphoneLoginTvHint;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    private String mCodeId;//短信验证码随机数
    private boolean isThirdLogin = false;
    public Handler handler = new Handler();
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phone_login);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"手机登录");
        isThirdLogin = getIntent().getBooleanExtra("flag", true);
        if (isThirdLogin) {
            Glide.with(this).load(getIntent().getStringExtra("headImg")).into(atCellphoneLoginIvHeadPic);
            atCellphoneLoginTvThirdName.setText(getIntent().getStringExtra("nickName"));
        } else {
            atCellphoneLoginTvHint.setVisibility(View.INVISIBLE);
            atCellphoneLoginTvThirdName.setText("新用户登录");
            atCellphoneLoginIvHeadPic.setImageResource(R.drawable.add_pic);
        }
    }


    @OnClick({R.id.at_cellphone_login_iv_delete, R.id.at_cellphone_login_tv_get_code, R.id.at_cellphone_login_btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_cellphone_login_iv_delete:
                atCellphoneLoginEtPhone.setText("");
                break;
            case R.id.at_cellphone_login_tv_get_code:
                if (TextUtils.isEmpty(atCellphoneLoginEtPhone.getText().toString().trim())) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    sendPhoneCode();
                }
                break;
            case R.id.at_cellphone_login_btn_next:
                if (TextUtils.isEmpty(atCellphoneLoginEtCode.getText().toString().trim())
                        || TextUtils.isEmpty(atCellphoneLoginEtPhone.getText().toString().trim())) {
                    Toast.makeText(this, "验证码或手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
                break;
        }
    }

    /**
     * 根据手机号验证码登录
     */
    private void login() {
        if (isThirdLogin) {
            //第三方绑定手机号
            authLoginByMobile();
        } else {
            //用户登录
            userLogin();
        }
    }
    //手机号登录
    private void userLogin() {
        PhoneLoginBean bean = new PhoneLoginBean();
        bean.setCode(atCellphoneLoginEtCode.getText().toString().trim());
        bean.setMobile(atCellphoneLoginEtPhone.getText().toString().trim());
        bean.setSession_id(mCodeId);
        bean.setLat(SharePerferenceUtils.getLat(this));
        bean.setLng(SharePerferenceUtils.getLng(this));
        //获取到user_id和token并保存
        LoginSubscribe.MobileLogin(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                PhoneLoginResponseBean bean = GsonUtils.fromJson(result, PhoneLoginResponseBean.class);
                SharePerferenceUtils.setUserId(CellPhoneLoginActivity.this, bean.getData().getUser_id());
                SharePerferenceUtils.setToken(CellPhoneLoginActivity.this, bean.getData().getToken());
                if(bean.getData().getInviter().equals("")){
                    startActivity(new Intent(CellPhoneLoginActivity.this, InputInvitationCodeActivity.class));
                }else{
                    startActivity(new Intent(CellPhoneLoginActivity.this, MainActivity.class));
                }

            }

            @Override
            public void onFault(String errorMsg) {
                Toast.makeText(CellPhoneLoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, this));
    }
    //第三方登录
    private void authLoginByMobile() {
        AuthLoginByMoBileBean bean = new AuthLoginByMoBileBean();
        bean.setMobile(atCellphoneLoginEtPhone.getText().toString().trim());
        bean.setCode(atCellphoneLoginEtCode.getText().toString().trim());
        bean.setSession_id(mCodeId);

        bean.setUser_id(SharePerferenceUtils.getUserId(this));
        LoginSubscribe.authLoginByMobile(bean, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AuthLoginByMobileResponseBean bean = GsonUtils.fromJson(result, AuthLoginByMobileResponseBean.class);
                SharePerferenceUtils.setUserId(CellPhoneLoginActivity.this, bean.getData().getUser_id());
                SharePerferenceUtils.setToken(CellPhoneLoginActivity.this, bean.getData().getToken());
                if(bean.getData().getInviter()==null){
                    startActivity(new Intent(CellPhoneLoginActivity.this, InputInvitationCodeActivity.class));
                }else{
                    startActivity(new Intent(CellPhoneLoginActivity.this, MainActivity.class));
                }
                //SharePerferenceUtils.setIsBindWx(CellPhoneLoginActivity.this,"bind");
            }
            @Override
            public void onFault(String errorMsg) {
                Toast.makeText(CellPhoneLoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    /**
     * 发送验证码
     */
    private void sendPhoneCode() {
        LoginSubscribe.getMobileCode(atCellphoneLoginEtPhone.getText().toString().trim(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("测试", "onSuccess: "+result);
                MsgCodeResponseBeen bean = GsonUtils.fromJson(result, MsgCodeResponseBeen.class);
                mCodeId = bean.getData().getSession_id();
                Toast.makeText(CellPhoneLoginActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                Toast.makeText(CellPhoneLoginActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, this));
    }

}
