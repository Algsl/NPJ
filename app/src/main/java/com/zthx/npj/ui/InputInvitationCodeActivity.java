package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.net.been.InvitationResponseBean;
import com.zthx.npj.net.netsubscribe.LoginSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.GsonUtils;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputInvitationCodeActivity extends ActivityBase {

    @BindView(R.id.at_input_invitation_code_btn_done)
    Button atInputInvitationCodeBtnDone;
    @BindView(R.id.at_input_invitation_code_btn_local_people)
    Button atInputInvitationCodeBtnLocalPeople;
    @BindView(R.id.at_input_invitation_code_et_phone)
    EditText atInputInvitationCodeEtPhone;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title_iv)
    ImageView acTitleIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_invitation_code);
        ButterKnife.bind(this);
        back(titleBack);
        changeTitle(acTitle,"输入邀请码");
        atLocationStoreTvRuzhu.setText("跳过");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 100) {
            atInputInvitationCodeEtPhone.setText(data.getStringExtra("code"));
        }
    }

    @OnClick({R.id.at_input_invitation_code_btn_done,
            R.id.at_input_invitation_code_btn_local_people,
            R.id.at_location_store_tv_ruzhu
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_input_invitation_code_btn_done:
                if (TextUtils.isEmpty(atInputInvitationCodeEtPhone.getText().toString().trim())) {
                    Toast.makeText(this, "推荐人手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    invitation();
                }
                break;
            case R.id.at_input_invitation_code_btn_local_people:
                startActivityForResult(new Intent(this, LocalSpokesmanActivity.class), 1);
                break;
            case R.id.at_location_store_tv_ruzhu:
                //SharePerferenceUtils.setIsBindSpokes(InputInvitationCodeActivity.this,"unbind");
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void invitation() {
        if(atInputInvitationCodeEtPhone.getText().toString().trim().equals(SharePerferenceUtils.getUserMobile(this))){
            showToast("请不要添加自己为推荐人");
        }else{
            LoginSubscribe.invitation(atInputInvitationCodeEtPhone.getText().toString().trim(), SharePerferenceUtils.getUserId(this),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        showToast("代言人绑定成功");
                        //SharePerferenceUtils.setIsBindSpokes(InputInvitationCodeActivity.this,"bind");
                        startActivity(new Intent(InputInvitationCodeActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        InvitationResponseBean bean=GsonUtils.fromJson(errorMsg,InvitationResponseBean.class);
                        switch (bean.getCode()){
                            case -1:
                                break;
                            case -3:
                                showToast("请填写有效的手机号");
                                break;
                            case -5:
                                showToast("请不要添加直属成员为推荐人");
                                break;
                        }
                        //Toast.makeText(InputInvitationCodeActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                }, this));
        }
    }
}
