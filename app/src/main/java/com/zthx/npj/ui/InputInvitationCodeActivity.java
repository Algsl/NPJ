package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.net.netsubscribe.LoginSubscribe;
import com.zthx.npj.net.netutils.OnSuccessAndFaultListener;
import com.zthx.npj.net.netutils.OnSuccessAndFaultSub;
import com.zthx.npj.utils.SharePerferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputInvitationCodeActivity extends AppCompatActivity {

    @BindView(R.id.at_input_invitation_code_btn_done)
    Button atInputInvitationCodeBtnDone;
    @BindView(R.id.at_input_invitation_code_btn_local_people)
    Button atInputInvitationCodeBtnLocalPeople;
    @BindView(R.id.at_input_invitation_code_et_phone)
    EditText atInputInvitationCodeEtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_invitation_code);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 100) {
            atInputInvitationCodeEtPhone.setText(data.getStringExtra("code"));
        }
    }

    @OnClick({R.id.at_input_invitation_code_btn_done, R.id.at_input_invitation_code_btn_local_people})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_input_invitation_code_btn_done:
                if (TextUtils.isEmpty(atInputInvitationCodeEtPhone.getText().toString().trim())){
                    Toast.makeText(this,"推荐人手机号不能为空",Toast.LENGTH_SHORT).show();
                } else {
                    invitation();
                }
                break;
            case R.id.at_input_invitation_code_btn_local_people:
                startActivityForResult(new Intent(this, LocalSpokesmanActivity.class), 1);
                break;
        }
    }

    private void invitation() {
        LoginSubscribe.invitation(atInputInvitationCodeEtPhone.getText().toString().trim(), SharePerferenceUtils.getUserId(this),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        startActivity(new Intent(InputInvitationCodeActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        Toast.makeText(InputInvitationCodeActivity.this, "请求失败：" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                },this));
    }
}
