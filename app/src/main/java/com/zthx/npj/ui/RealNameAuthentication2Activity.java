package com.zthx.npj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameAuthentication2Activity extends AppCompatActivity {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.at_real_name_authentication2_btn_confirm)
    Button atRealNameAuthentication2BtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.at_location_store_tv_ruzhu, R.id.at_real_name_authentication2_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at_location_store_tv_ruzhu:

                break;
            case R.id.at_real_name_authentication2_btn_confirm:
                startActivity(new Intent(this,ConfirmAttestationSuccessActivity.class));
                break;
        }
    }
}
