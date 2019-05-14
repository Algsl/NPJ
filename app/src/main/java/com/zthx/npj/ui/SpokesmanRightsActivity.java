package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpokesmanRightsActivity extends AppCompatActivity {

    @BindView(R.id.at_spokesman_right_btn_tiqu)
    Button atSpokesmanRightBtnTiqu;
    @BindView(R.id.at_spokesman_rl_daiyanjiangli)
    RelativeLayout atSpokesmanRlDaiyanjiangli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spokesman_rights2);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.at_spokesman_right_btn_tiqu,R.id.at_spokesman_rl_daiyanjiangli})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.at_spokesman_right_btn_tiqu:

                break;
            case R.id.at_spokesman_rl_daiyanjiangli:
                startActivity(new Intent(this, SpeakRewardActivity.class));
                break;
        }
    }
}
