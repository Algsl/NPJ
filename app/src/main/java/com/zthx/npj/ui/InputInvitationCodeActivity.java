package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputInvitationCodeActivity extends AppCompatActivity {

    @BindView(R.id.at_input_invitation_code_btn_done)
    Button atInputInvitationCodeBtnDone;
    @BindView(R.id.at_input_invitation_code_btn_local_people)
    Button atInputInvitationCodeBtnLocalPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_invitation_code);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_input_invitation_code_btn_done, R.id.at_input_invitation_code_btn_local_people})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_input_invitation_code_btn_done:
                break;
            case R.id.at_input_invitation_code_btn_local_people:
                startActivity(new Intent(this, LocalSpokesmanActivity.class));
                break;
        }
    }
}
