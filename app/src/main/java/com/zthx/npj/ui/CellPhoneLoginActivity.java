package com.zthx.npj.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zthx.npj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CellPhoneLoginActivity extends AppCompatActivity {

    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.at_cellphone_login_btn_next)
    Button atCellphoneLoginBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phone_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.at_cellphone_login_btn_next)
    public void onViewClicked() {
    }
}
